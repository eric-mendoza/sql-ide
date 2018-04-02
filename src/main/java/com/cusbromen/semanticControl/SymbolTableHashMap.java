package com.cusbromen.semanticControl;

import com.cusbromen.antlr.SqlParser;
import com.cusbromen.bptree.*;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@SuppressWarnings("unchecked") // JSON's fault
public class SymbolTableHashMap {
    private JSONObject metadata, dbInUseMetadata;
    private String dbsJsonPath, dbInUseId;
    public  String temporalErrorMessage;
    private List<String> verboseParser;
    private int BLOCKSIZE = 4096;

    public SymbolTableHashMap(List<String> verboseParser) {
        this.verboseParser = verboseParser;
        dbInUseId = null;
        dbInUseMetadata = null;
    }

    public String showDatabases() {
        verboseParser.add(">> Will try to show databases!");

        if (!metadata.isEmpty()) {
            Set<?> keys = metadata.keySet();
            String dbList = "";

            verboseParser.add(">> Finding keys for databases.");

            for (Object key : keys) {
                dbList = dbList + "*\t" + key.toString() + "<br>";
            }

            verboseParser.add(">> Found all keys for databases! " + dbList);

            return dbList;
        } else {
            verboseParser.add(">> The metadata.json file is empty!");
            return "0";
        }
    }

    public String showTables() {
        verboseParser.add("Will try to show tables!");

        if (dbInUseId != null){

            try {
                Set<?> keys = dbInUseMetadata.keySet();
                String tableList = "";

                verboseParser.add(">> Finding keys for tables in database " + dbInUseId + ".");

                for (Object key : keys) {
                    tableList = tableList + "*\t" + key.toString() + "<br>";
                }

                verboseParser.add(">> Found keys for tables in " + dbInUseId + "! " + tableList + ".");

                return tableList;

            } catch (Exception e) {
                verboseParser.add(">> Failed in finding keys for tables in database " + dbInUseId);
                e.printStackTrace(); return "0";
            }
        } else {
            verboseParser.add(">> ");
            return "0";
        }
    }

    public int renameTable(String oldName, String newName){
        verboseParser.add(">> Will try to rename metadata for table " + oldName);

        if (dbInUseId != null){
            String dbPath = "metadata/" + dbInUseId + "/" + dbInUseId + ".json";
            File db = new File(dbPath);
            verboseParser.add(">> Locating file " + dbPath);

            try {
                if (!dbInUseMetadata.containsKey(oldName)) {
                    verboseParser.add(">> Table is not in database " + oldName + "!");
                    return 3;
                } else if (dbInUseMetadata.containsKey(newName)) {
                    verboseParser.add(">> Database already has " + newName + "!");
                    return 2;
                } else {
                    // Change own info
                    JSONObject info = (JSONObject) dbInUseMetadata.get(oldName);
                    verboseParser.add(">> Using info " + info.toString());
                    dbInUseMetadata.remove(oldName);
                    dbInUseMetadata.put(newName, info);


                    // Change info of tables referenced by this table
                    JSONObject allConstraints = (JSONObject) info.get("constraints");
                    Set<String> constraintsNames = allConstraints.keySet();
                    ArrayList<String[]> tablesToChangeIngoingReferenced = new ArrayList<>(); // (TableReferenced, constraintId)

                    for (String constraintId : constraintsNames) {
                        if (constraintId.startsWith("FK_")){
                            JSONObject constraint = (JSONObject) allConstraints.get(constraintId);
                            String referencedTable = (String) constraint.get("referencedTable");
                            tablesToChangeIngoingReferenced.add(new String[]{referencedTable, constraintId});
                        }
                    }

                    // Make changes on referenced tables
                    for (String[] tuple : tablesToChangeIngoingReferenced) {
                        // Get table
                        JSONObject referencedTable = (JSONObject) dbInUseMetadata.get(tuple[0]);
                        JSONObject ingoingReferences = (JSONObject) referencedTable.get("ingoingReferences");

                        // Update constraint
                        ingoingReferences.put(tuple[1], newName);
                    }

                    // Change info of tables Change info of tables that reference this table
                    JSONObject ingoingReferences = (JSONObject) info.get("ingoingReferences");
                    if (ingoingReferences != null){
                        Set<String> constraintsIds = ingoingReferences.keySet();
                        tablesToChangeIngoingReferenced = new ArrayList<>(); // (TableReferenced, constraintId)

                        for (String constraintId : constraintsIds) {
                            String tableId = (String) ingoingReferences.get(constraintId);
                            tablesToChangeIngoingReferenced.add(new String[]{tableId, constraintId});
                        }

                        // Make changes on referenced tables
                        for (String[] tuple : tablesToChangeIngoingReferenced) {
                            // Get table
                            JSONObject referencedTable = (JSONObject) dbInUseMetadata.get(tuple[0]);
                            JSONObject tableConstraints = (JSONObject) referencedTable.get("constraints");
                            JSONObject constraint = (JSONObject) tableConstraints.get(tuple[1]);

                            // Update constraint
                            constraint.put("referencedTable", newName);
                        }
                    }


                    verboseParser.add(">> Changed info to: " + dbInUseMetadata.toString());
                    PrintWriter writer = new PrintWriter(db, "UTF-8");
                    writer.write(dbInUseMetadata.toJSONString());
                    writer.close();
                    verboseParser.add(">> Wrote to file!");

                    // RENAME TREE
                    Path fileToMovePath = Paths.get("metadata/" + dbInUseId + "/" + oldName);
                    Path targetPath = Paths.get("metadata/" + dbInUseId + "/" + newName);
                    verboseParser.add(">> Changes made into path " + targetPath + "!");
                    Files.move(fileToMovePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

                    return 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            // Not using a db
            return 0;
        }
    }


    public int addConstraintsAndRefreshJson(String tableName, ArrayList<String[]> newReferencedTables) {
        // Verify if there are foreign keys references to create
        if (newReferencedTables.size() >= 1){
            for (String[] newReferencedTableTuple : newReferencedTables) {
                // Add reference to referenced table
                JSONObject referencedTable = getTable(newReferencedTableTuple[0]);  // get table [referencedTable]
                JSONObject ingoingReferences = (JSONObject) referencedTable.get("ingoingReferences");  // get ingoing references
                JSONObject inRef;
                if (ingoingReferences == null){
                    // Create ingoing references object
                    inRef = new JSONObject();
                } else {
                    // Cast object
                    inRef = ingoingReferences;

                    // Verify that constraint has unique name
                    if (inRef.get(newReferencedTableTuple[1]) != null) return 3;
                }
                inRef.put(newReferencedTableTuple[1], tableName);  // add the new reference <referencedTable, constraintName>
            }
        }
        try{
            // Update JSON
            PrintWriter writer = new PrintWriter("metadata/" + dbInUseId + "/" + dbInUseId + ".json", "UTF-8");
            writer.write(dbInUseMetadata.toJSONString());
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * This method creates a new Table in the DB in use
     * @param tableName the name of the new table
     * @param tableProps is the JSON with the properties of the table
     * @param newReferencedTables This tuple contains (referencedTableId, constraintId)
     * @return 2, if table already existed; 1, success; 0: error; 3: constraint duplicated; 4: pk not nullable
     */
    public int createTable(String tableName, JSONObject tableProps, ArrayList<String[]> newReferencedTables) {
        verboseParser.add(">> Will try to create metadata for table " + tableName);
        String dbPath = "metadata/" + dbInUseId + "/" + dbInUseId + ".json";
        File db = new File(dbPath);
        verboseParser.add(">> Using file: " + dbPath);

        try {
            if (dbInUseMetadata.containsKey(tableName)) {
                // Table already exists
                verboseParser.add(">> Table already exists!");
                return 2;

            } else {
                verboseParser.add(">> Writing changes to file " + dbPath);

                // Verify if there are foreign keys references to create
                if (newReferencedTables.size() > 0){
                    for (String[] newReferencedTableTuple : newReferencedTables) {
                        // Add reference to referenced table
                        JSONObject referencedTable = getTable(newReferencedTableTuple[0]);  // get table
                        Object ingoingReferences = referencedTable.get("ingoingReferences");  // get ingoing references
                        JSONObject inRef;
                        if (ingoingReferences == null){
                            // Create ingoing references array
                            inRef = new JSONObject();
                        } else {
                            // Cast object
                            inRef = (JSONObject) ingoingReferences;

                            // Verify that constraint has unique name
                            if (inRef.get(newReferencedTableTuple[1]) != null) return 3;
                        }

                        inRef.put(newReferencedTableTuple[1], tableName);  // add the new reference <referencedTable, constraintName>
                        referencedTable.put("ingoingReferences", inRef);  // Update references
                        //dbInUseMetadata.put(newReferencedTableTuple[0], referencedTable);  // Update table in dbMetadata
                    }
                }

                // Get the names of primary key
                ArrayList<String> pks = getPrimaryKey(tableProps, tableName);

                // If there are no Primary keys, create surrogate pk
                boolean surrogatePk = pks == null;

                // Verify that primary keys are not Nullable
                if (!surrogatePk){
                    if (!verifyPrimaryKeysAreNotNullable(pks, tableProps)){
                        return 4;
                    }
                }

                // Save new table
                dbInUseMetadata.put(tableName, tableProps);

                // Overwrite db JSON
                PrintWriter writer = new PrintWriter(db, "UTF-8");
                writer.write(dbInUseMetadata.toJSONString());
                writer.close();

                // Increase number of tables in metadata
                verboseParser.add(">> Writing changes to file " + dbsJsonPath);
                PrintWriter writer2 = new PrintWriter(dbsJsonPath, "UTF-8");
                JSONObject table = (JSONObject) metadata.get(dbInUseId);
                Integer noTables = ((Number) table.get("noTables")).intValue();
                noTables++;
                table.put("noTables", noTables);

                writer2.write(metadata.toJSONString());
                writer2.close();

                /*
                    BTREE Management
                 */

                // Get columns (id, type)
                ArrayList<String[]> columns = getTableColumnTypes(tableName);

                // Create table BTree structure
                ArrayList<Type> primaryTypes = new ArrayList<>();
                ArrayList<Type> rowType = new ArrayList<>();

                // Create surrogate PK if necessary
                if (surrogatePk){
                    primaryTypes.add(typeGetter("DATE"));
                }

                // Add all the types of column
                for (String[] column : columns) {
                    String columnId = column[0];
                    String columnType = column[1];

                    if (!surrogatePk){
                        if (pks.contains(columnId)){
                            primaryTypes.add(typeGetter(columnType));
                        } else {
                            rowType.add(typeGetter(columnType));
                        }
                    } else {
                        rowType.add(typeGetter(columnType));
                    }
                }


                // If table is not created CREATE TABLE
                BpTree bpTree = new BpTree(getTableTreePath(tableName), primaryTypes, rowType, BLOCKSIZE);

                bpTree.close();
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace(); return 0;
        }
    }


    /**
     * This method creates a new db and returns a String with the message of the operation
     * @param id is the name of the new db
     * @return 0: Database Created, 1: Database name is already in use
     */
    public int createDb(String id) {
        verboseParser.add(">> Will try to create metadata for database " + id);
        // Try to open the master file (dbs.json) on metadata directory
        try {
            // Verify if db already exists
            if (metadata.get(id) == null){
                verboseParser.add(">> metadata.json didn't exist! Creating file...");
                JSONObject newDb = new JSONObject();
                newDb.put("noTables", 0);
                metadata.put(id, newDb);

                // Overwrite existing json
                PrintWriter writer = new PrintWriter(new File(dbsJsonPath), "UTF-8");
                writer.write(metadata.toJSONString());
                writer.close();

                verboseParser.add(">> metadata.json file created!");
            }

            // DB already exists
            else {
                verboseParser.add(">> metadata.json file already exists!");
                return 1;
            }

            // Create a directory for the new db
            verboseParser.add(">> Creating new directory for database");
            File newDb = new File("metadata/" + id + "/" + id + ".json");
            newDb.getParentFile().mkdir();
            newDb.createNewFile();
            verboseParser.add(">> Directory for " + newDb.getPath() + " created!");

            PrintWriter writer = new PrintWriter(newDb, "UTF-8");
            writer.write("{}");
            writer.close();



        } catch (IOException e) {
            System.err.println(e.toString());
        }
        return 0;
    }

    /**
     * Renames a DB with the name oldName to newName
     * @param oldName the original name of the db
     * @param newName the newName for the db
     * @return 0: Database rename successful, 1: oldName db doesn't exists, 2: newName db already exists
     */
    public int renameDb(String oldName, String newName) {
        verboseParser.add(">> Will try to rename database " + oldName + " in metadata!");
        // Verify if the target DB exists
        if (metadata.get(oldName) != null){
            // Verify if the newName is in use
            if (metadata.get(newName) != null){
                return 2;
            }

            // Change all info in JSON
            Object oldInfo = metadata.remove(oldName);
            metadata.put(newName, oldInfo);

            // Overwrite existing json
            try {
                verboseParser.add(">> Using file " + dbsJsonPath);
                File dbsJsonFile = new File(dbsJsonPath);
                PrintWriter writer = new PrintWriter(dbsJsonFile, "UTF-8");
                writer.write(metadata.toJSONString());
                writer.close();
                verboseParser.add(">> Changed metadata and wrote to file " + dbsJsonPath + "!");

                // Move the whole directory
                verboseParser.add(">> Creating new destiny path!");
                new File("metadata/" + newName).mkdir();
                Path fileToMovePath = Paths.get("metadata/" + oldName);
                Path targetPath = Paths.get("metadata/" + newName);
                verboseParser.add(">> Changes made into path " + targetPath + "!");
                Files.move(fileToMovePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

                // Rename db json
                fileToMovePath = Paths.get("metadata/" + newName + "/" + oldName + ".json");
                targetPath = Paths.get("metadata/" + newName + "/" + newName + ".json");
                verboseParser.add(">> Changes made into path " + targetPath + "!");
                Files.move(fileToMovePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

                verboseParser.add(">> Deleted previous directory!");

            } catch (IOException e){
                e.printStackTrace();
            }


        } else {
            // Target Db doesn't exists
            return 1;
        }
        return 0;
    }

    /**
     * Reads the metadata file and parses it
     */
    public void readMetadata(String dbsJsonPath, JSONParser jsonParser, String lastUsedDb) {
        verboseParser.add(">> Will try to read metadata!");
        try {
            this.dbsJsonPath = dbsJsonPath;

            // See if metadata dir exists
            verboseParser.add("Using file: " + dbsJsonPath);
            File dbs = new File(dbsJsonPath);

            // If it doesn't exists, create it
            if (!dbs.exists()){
                dbs.getParentFile().mkdir();
                dbs.createNewFile();
                PrintWriter writer = new PrintWriter(dbs, "UTF-8");
                verboseParser.add(">> File didn't exist! Now created!");

                // Create bject with all dbs
                metadata = new JSONObject();

                // Write to file
                writer.write(metadata.toJSONString());

                writer.close();
                verboseParser.add(">> Changes written to " + dbsJsonPath);
            }

            // Load it to memory
            else {
                verboseParser.add(">> Changes written to " + dbsJsonPath);
                metadata = (JSONObject) jsonParser.parse(new FileReader(dbsJsonPath));
            }

            // Load last used db to memory
            if (lastUsedDb != null) {
                loadDbMetadata(lastUsedDb, jsonParser);
                verboseParser.add(">> Loading las used database to memory: " + dbInUseId);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the metadata file from a db
     * @param dbName the desired db
     * @return True, if correctly loaded, False, otherwise
     */
    public boolean loadDbMetadata(String dbName, JSONParser jsonParser){
        verboseParser.add(">> Will try to load metadata from databases");
        String dbPath = "metadata/" + dbName + "/" + dbName + ".json";
        File db = new File(dbPath);
        verboseParser.add("Using file: " + dbPath);

        if (db.exists()){
            try {
                dbInUseMetadata = (JSONObject) jsonParser.parse(new FileReader(dbPath));
                dbInUseId = dbName;  // Update DB in use
                verboseParser.add(">> Data loaded!");
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    /**
     * Verifies if a db exists
     * @param id the db
     */
    public boolean dbExists(String id) {
        Object db = metadata.get(id);
        if (db == null) return false;
        return true;
    }

    /**
     * Gets a db metadata JSONObject
     * @param id name of the db
     * @return db all tables
     */
    public JSONObject getDb(String id, JSONParser jsonParser){
        verboseParser.add(">> Will try to load metadata from database");
        String dbPath = "metadata/" + id + "/" + id + ".json";
        File db = new File(dbPath);
        verboseParser.add("Using file: " + dbPath);
        JSONObject dbObject = null;

        if (db.exists()){
            try {
                dbObject = (JSONObject) jsonParser.parse(new FileReader(dbPath));
                verboseParser.add(">> Data loaded!");
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            return dbObject;
        }
        return null;  // This should never happen
    }

    public List<String> getVerboseParser() { return verboseParser; }

    public void deleteDb(String dbId) {
        try {
            // Delete from general metadata
            metadata.remove(dbId);

            verboseParser.add(">> Loading file " + dbsJsonPath);
            verboseParser.add(">> Removing " + dbId + " from file " + dbsJsonPath);
            File dbsJsonFile = new File(dbsJsonPath);
            PrintWriter writer = new PrintWriter(dbsJsonFile, "UTF-8");
            writer.write(metadata.toJSONString());
            writer.close();
            verboseParser.add(">> Changed metadata and wrote to file " + dbsJsonPath + "!");

            Files.walkFileTree(Paths.get("metadata/" + dbId), new DeleteFileVisitor());  // Delete old path and all his files
            verboseParser.add(">> Deleted directory!");

            // Verify if it was the dbInUse and delete it
            if (dbId.equals(dbInUseId)){
                dbInUseId = null;
                dbInUseMetadata = null;
                File dbInUseFile = new File("metadata/lastDbUsed");
                dbInUseFile.delete();
            }

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Show the columns of a specified table
     * @param idTable the target table
     * @return Columns and props in String, 0: if table doesn't exists
     */
    public String showColumns(String idTable) {
        // See if table exists
        Object tableObject = dbInUseMetadata.get(idTable);
        if (tableObject != null){
            String result = "";
            JSONObject table = (JSONObject) tableObject;
            JSONObject columns = (JSONObject) table.get("columns");
            Set<String> columnNames = columns.keySet();
            String type;
            Object constraintObject;
            JSONObject column;
            for (String columnId : columnNames) {
                // Print name
                result += "<br>*    " + columnId + ": ";

                // Type
                column = (JSONObject) columns.get(columnId);  // Get column props
                type = (String) column.get("type");
                result += "Type: " + type;

                // Nullable
                constraintObject = column.get("nullable");
                if (constraintObject != null){
                    result += ", Nullable: " + constraintObject;
                } else {
                    result += ", Nullable: " + "true";
                }

                // Length
                // Nullable
                constraintObject = column.get("length");
                if (constraintObject != null){
                    result += ", Length: " + constraintObject;
                }
            }

            return result;
        } else {
            return "0";
        }
    }

    /**
     * Deletes a table. Assumes that there are no ingoing references, and that table exists.
     * @param tableId table to delete
     */
    public void deleteTable(String tableId) {
        try {
            // Drop FK_ constraints before deleting table, to eliminate ingoing references
            JSONObject table = (JSONObject) dbInUseMetadata.get(tableId);
            JSONObject constraints = (JSONObject) table.get("constraints");
            Set<String> constraintsIds = constraints.keySet();
            for (String constraintId : constraintsIds) {
                if (constraintId.startsWith("FK_")){
                    dropConstraint(tableId, constraintId);
                }
            }

            dbInUseMetadata.remove(tableId);

            // Update JSON
            PrintWriter writer = new PrintWriter("metadata/" + dbInUseId + "/" + dbInUseId + ".json", "UTF-8");
            writer.write(dbInUseMetadata.toJSONString());
            writer.close();

            // DELETE TREE
            Files.delete(Paths.get(getTableTreePath(tableId)));  // Delete old path

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a constraint of a table
     * @param tableOriginId table modified
     * @param constraintId the constraint to be deleted
     * @return 0: Constraint no existe; 1: Table invalid; 2: No DB selected; 3: Correct
     */
    public int dropConstraint(String tableOriginId, String constraintId){
        try {
            if (dbInUseMetadata != null){
                Object tableObject = dbInUseMetadata.get(tableOriginId);
                if (tableObject != null){
                    JSONObject table = (JSONObject) tableObject;
                    JSONObject constraints = (JSONObject) table.get("constraints");

                    // verificar si existe el constraint
                    Object constraint = constraints.get(constraintId);
                    if (constraint == null) return 0;

                    // Analizar tipo de constraint
                    if (constraintId.startsWith("PK_")){
                        constraints.remove(constraintId);  // Delete
                        // TODO: Que deberia ocurrir con el arbol
                    }

                    else if (constraintId.startsWith("FK_")){
                        // Eliminar de tabla referenciada
                        JSONObject constraintFk = (JSONObject) constraint;
                        String referencedTableId = (String) constraintFk.get("referencedTable");
                        JSONObject referencedTable = (JSONObject) dbInUseMetadata.get(referencedTableId);
                        JSONObject ingoingReferences = (JSONObject) referencedTable.get("ingoingReferences");
                        ingoingReferences.remove(constraintId);

                        // Eliminar de tabla origen
                        constraints.remove(constraintId);  // Delete
                    }

                    else {
                        // Eliminar Check
                        constraints.remove(constraintId);
                    }

                    // Rewrite metadata for both tables
                    PrintWriter writer = new PrintWriter("metadata/" + dbInUseId + "/" + dbInUseId + ".json", "UTF-8");
                    writer.write(dbInUseMetadata.toJSONString());
                    writer.close();


                } else {
                    // Table doesn't exists
                    return 1;
                }
            } else {
                // Haven't selected a DB
                return 2;
            }

            return 3;  // correct
        } catch (IOException e){
            return 0;
        }
    }

    public ArrayList<String> getColumnsUsedInConstraints(JSONObject table){
        ArrayList<String> columns = new ArrayList<>();
        // Get table
        JSONObject constraints = (JSONObject) table.get("constraints");
        Set<String> constraintsIds = constraints.keySet();
        for (String constrain : constraintsIds) {
            if (constrain.startsWith("PK_")){
                JSONArray columnsA = (JSONArray) constraints.get(constrain);
                for (Object c : columnsA) {
                    columns.add((String) c);
                }
            }

            else if (constrain.startsWith("FK_")){
                JSONObject columnsB = (JSONObject) constraints.get(constrain);
                JSONArray columnsA = (JSONArray) columnsB.get("columns");
                for (Object c : columnsA) {
                    columns.add((String) c);
                }
            }

            else {
                // TODO: Get tables used in the check condition
                System.out.println("Here we should be getting the tables used in CHECK");
            }
        }
        return columns;
    }

    /**
     * Deletes a constraint of a table
     * @param tableId table modified
     * @param columnId the column to be deleted
     * @return 0: Constraint no existe; 1: Table invalid; 2: No DB selected; 3: Correct
     */
    public int dropColumn(String tableId, String columnId) {
        try {
            if (dbInUseMetadata != null){
                JSONObject tableObject = (JSONObject) dbInUseMetadata.get(tableId);
                if (tableObject != null){
                    ArrayList<String> notDeletableColumns = getColumnsUsedInConstraints(tableObject);
                    if (notDeletableColumns.contains(columnId)){
                        return 2;
                    }

                    // DROP COLUMN
                    ((JSONObject) tableObject.get("columns")).remove(columnId);

                    PrintWriter writer = new PrintWriter("metadata/" + dbInUseId + "/" + dbInUseId + ".json", "UTF-8");
                    writer.write(dbInUseMetadata.toJSONString());
                    writer.close();


                } else {
                    return 0;
                }
            } else {
                return 1;
            }
            return 3;
        } catch (IOException e){
            return 0;
        }
    }

    /**
     * This method is going to be used while inserting rows. It returns the names and types of the columns in a table.
     * This method requires the table to exist and to have a dataBase in use. (ColumnId, ColumnType
     * @param tableId analyzed table
     * @return A list of arrays of length 2, being their values the next ones
     */
    public ArrayList<String[]> getTableColumnTypes(String tableId){
        JSONObject table = (JSONObject) dbInUseMetadata.get(tableId);
        JSONObject columns = (JSONObject) table.get("columns");
        Set<String> columnNames = columns.keySet();
        ArrayList<String[]> columnsTuples = new ArrayList<>();
        for (String columnId : columnNames) {
            columnsTuples.add(new String[]{columnId, (String)((JSONObject) columns.get(columnId)).get("type")});  // Get type
        }
        return columnsTuples;
    }

    public LinkedHashMap<String, String> getTableColumnTypes(JSONObject table){
        JSONObject columns = (JSONObject) table.get("columns");
        Set<String> columnNames = columns.keySet();
        LinkedHashMap<String, String> columnsTuples = new LinkedHashMap<>();
        for (String columnId : columnNames) {
            columnsTuples.put(columnId, (String)((JSONObject) columns.get(columnId)).get("type"));  // Get type
        }
        return columnsTuples;
    }


    /**
     * Verifies if a table exists in the db in use
     * @param idTable searched table
     * @return True, if it exists; False, otherwise
     */
    public boolean tableExists(String idTable){
        return dbInUseMetadata.get(idTable) != null;
    }

    public JSONObject getTable(String idTable){
        return (JSONObject) dbInUseMetadata.get(idTable);
    }

    public JSONArray getPrimaryKey(String referencedTableId) {
        JSONObject table = (JSONObject) dbInUseMetadata.get(referencedTableId);
        JSONObject tableConstraints = (JSONObject) table.get("constraints");
        return (JSONArray) tableConstraints.get("PK_" + referencedTableId);
    }

    public JSONArray getPrimaryKey(JSONObject table, String referencedTableId) {
        JSONObject tableConstraints = (JSONObject) table.get("constraints");
        return (JSONArray) tableConstraints.get("PK_" + referencedTableId);
    }

    public boolean verifyPrimaryKeysAreNotNullable(ArrayList<String> keysId, JSONObject table){
        JSONObject columns = (JSONObject) table.get("columns");
        for (String keyId : keysId) {
            JSONObject column = (JSONObject) columns.get(keyId);
            if (column.get("nullable") == null){
                return false;
            }
        }

        return true;
    }

    public Type typeGetter(String type){
        switch (type){
            case "INT":
                return Type.INT;

            case "FLOAT":
                return Type.FLOAT;

            case "DATE":
                return Type.DATE;

            case "CHAR":
                return Type.CHARS;
        }
        return Type.INT;
    }

    String getTableTreePath(String tableName){
        return "metadata/" + dbInUseId + "/" + tableName;
    }

    /**
     * 'UPDATE' ID 'SET' ID '=' ID (',' ID '=' ID)* ('WHERE' check_exp)* ';'
     * @param idList
     * @param conditionList
     * @return
     */
    public String update(List<TerminalNode> idList, List<SqlParser.Check_expContext> conditionList) {
        // Finish gathering info
        String tableName = idList.get(0).getText();                 // table to update

        // updates come in 2-tuples. Let's fill two lists:
        // 1. Column names for index i
        // 2. Value to update for index i

        List<String> columnNames = new LinkedList<>();              // names for columns to change
        List<String> newValues = new LinkedList<>();                // values for columns

        // traverse idList to get column names and values
        for (int i = 1; i < idList.size(); i++) {
            // odd indexes for IDs are column values
            if (i % 2 != 0) {
                columnNames.add(idList.get(i).getText());
            } else {
                // even indexes for IDs are values
                newValues.add(idList.get(i).getText());
            }
        }

        System.out.println("table Name: " + tableName + "\ncolumn names: " + columnNames + "\nnew values: " + newValues);

        return null;
    }

    public String insert(String tableId, List<TerminalNode> columnsToInsert, ArrayList<ArrayList<String>> rowsToInsert) {
        try {
            if (dbInUseId != null){
                JSONObject table = getTable(tableId);
                if (table != null){
                    LinkedHashMap<String, String> columnsAndTypes = getTableColumnTypes(table); // (id, type)
                    ArrayList<String> columnsId = new ArrayList<>(columnsAndTypes.keySet());
                    ArrayList<String> insertingRow, columnsToInsertId = new ArrayList<>();
                    ArrayList<ArrayList<String>> insertingRows = new ArrayList<>();
                    JSONObject columns = (JSONObject) table.get("columns");

                    for (TerminalNode token : columnsToInsert) {
                        String columnIdToInsert = token.getSymbol().getText();
                        JSONObject column = (JSONObject) columns.get(columnIdToInsert);

                        if (column == null){
                            temporalErrorMessage = "Error: Couldn't insert row. Column <strong>" + columnIdToInsert + "</strong> doesn't exists in table <strong>" + tableId + "</strong>.";
                            return temporalErrorMessage;
                        }

                        columnsToInsertId.add(columnIdToInsert);
                    }

                    // Verify if they specified the columns to insert the values
                    // They did specified the columns
                    if (columnsToInsert.size() > 0){
                        for (ArrayList<String> row : rowsToInsert) {
                            insertingRow = generateNullList(columnsId.size());  // Generate new null row

                            // Verify if row has the right number of columns
                            if (row.size() != columnsToInsert.size()){
                                temporalErrorMessage = "Error: Couldn't insert row </strong>" + row.toString() + "</strong> because it exceeds the number of columns specified to insert.";
                                return temporalErrorMessage;
                            }

                            // Analize each value to be inserted
                            for (int i = 0; i < row.size(); i++) {
                                String value = row.get(i);
                                String columnIdToInsert = columnsToInsertId.get(i);

                                // Insert value to its position on null array
                                int columnPosition = columnsId.indexOf(columnIdToInsert);
                                insertingRow.remove(columnPosition);
                                insertingRow.add(columnPosition, value);
                            }

                            // save the modified insert
                            insertingRows.add(insertingRow);
                        }
                    }

                    // they didn't specified the columns
                    else {
                        for (ArrayList<String> row : rowsToInsert) {
                            insertingRow = generateNullList(columnsId.size());  // Generate new null row

                            // Verify if row has the right number of columns
                            if (row.size() != columnsId.size()){
                                temporalErrorMessage = "Error: Couldn't insert row </strong>" + row.toString() + "</strong> because it exceeds the number of columns in table.";
                                return temporalErrorMessage;
                            }

                            // Analize each value to be inserted
                            for (int i = 0; i < row.size(); i++) {
                                String value = row.get(i);

                                // Insert value to its position on null array
                                insertingRow.remove(i);
                                insertingRow.add(i, value);
                            }

                            // save the modified insert
                            insertingRows.add(insertingRow);

                        }
                    }

                    ArrayList<String> primaryKeysColumnNames = getPrimaryKey(tableId);
                    boolean surrogatePk = false;
                    if (primaryKeysColumnNames == null) surrogatePk = true;
                    BpTree bpTree = new BpTree(getTableTreePath(tableId));
                    for (ArrayList<String> inRow : insertingRows) {
                        columnsId = new ArrayList<>(columnsAndTypes.keySet());
                        // Separate PK from the other columns
                        ArrayList<String> primaryValues = new ArrayList<>();
                        if (!surrogatePk){
                            for (String pk : primaryKeysColumnNames) {
                                int pkIndex = columnsId.indexOf(pk);  // Get primary key index on columns
                                columnsId.remove(pk);
                                primaryValues.add(inRow.remove(pkIndex));  // Remove and insert pk value
                            }
                        } else {
                            primaryValues.add(null);
                        }

                        // Verify constraints
                        // Verify Foreign Key

                        // Verify Checks


                        // Open Btree
                        Key key = new Key();
                        Tuple row = new Tuple();
                        temporalErrorMessage = null;

                        // Pk
                        for (int i = 0; i < primaryValues.size(); i++) {
                            String value, columnId, type;
                            if (!surrogatePk){
                                value = primaryValues.get(i);
                                columnId = primaryKeysColumnNames.get(i);
                                type = columnsAndTypes.get(columnId);
                            } else {
                                // Surrogate key values
                                value = null;
                                columnId = "PK_" + tableId;
                                type = "DATE";
                            }

                            // Verify length
                            Integer charLength = null;
                            if (!surrogatePk) charLength = getColumnRequiredLength(table, columnId);

                            Record recordToInsert = recordGenerator(value, type, charLength, surrogatePk);

                            if (recordToInsert == null){
                                return temporalErrorMessage;
                            }

                            key.add(recordToInsert);
                        }

                        // Rows
                        for (int i = 0; i < inRow.size(); i++) {
                            String value = inRow.get(i);
                            String columnId = columnsId.get(i);
                            String type = columnsAndTypes.get(columnId);

                            // Velify Nullability
                            if (value == null){
                                boolean columnNullable = isColumnNullable(columns, columnId);
                                if (!columnNullable){
                                    temporalErrorMessage = "Error: Column <strong>" + columnId + "</strong> can't have nullable values.";
                                    return temporalErrorMessage;
                                }
                            }

                            // Verify length
                            Integer charLength = getColumnRequiredLength(table, columnId);

                            Record recordToInsert = recordGenerator(value, type, charLength, false);

                            if (recordToInsert == null){
                                return temporalErrorMessage;
                            }

                            row.add(recordToInsert);
                        }

                        // Insert in tree
                        try {
                            bpTree.insert(key, row);
                        } catch (InvalidParameterException e){
                            temporalErrorMessage = "Error: Couldn't insert row <strong>" + inRow.toString() + "</strong> because primary keys already exists.";
                            return temporalErrorMessage;
                        }
                    }

                    // Close tree
                    bpTree.close();

                    // Augment number of records on table
                    augmentRecordsOnTable(table, insertingRows.size());
                    // TODO Cambiar el mensaje pa insertingRows.size()
                    return "Successfully inserted <strong>" + insertingRows.toString() + "</strong> rows in table <strong>" + tableId + "</strong>.";
                } else {
                    temporalErrorMessage = "Error: Table doesn't exists: <strong>" + tableId + "</strong> in insert.";
                    return temporalErrorMessage;
                }
            } else {
                temporalErrorMessage = "Error: You haven't selected a DB.";
                return temporalErrorMessage;
            }
        } catch (IOException e) {
            e.printStackTrace();
            temporalErrorMessage = "Error: Error while writing rows in the tree.";
            return temporalErrorMessage;
        }
    }

    private boolean isColumnNullable(JSONObject columns, String columnId) {
        return null == ((JSONObject) columns.get(columnId)).get("nullable");
    }

    private Integer getColumnRequiredLength(JSONObject table, String columnId) {
        JSONObject columns = (JSONObject) table.get("columns");
        JSONObject column = (JSONObject) columns.get(columnId);
        Number number = ((Number) column.get("length"));
        if (number != null){
            return number.intValue();
        } else {
            return null;
        }
    }

    private void augmentRecordsOnTable(JSONObject table, int size) {
        try{
            Integer noRecords = ((Number) table.get("noRecords")).intValue();
            table.put("noRecords", noRecords + size);

            PrintWriter writer = new PrintWriter("metadata/" + dbInUseId + "/" + dbInUseId + ".json", "UTF-8");
            writer.write(dbInUseMetadata.toJSONString());
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    private Record recordGenerator(String value, String type, Integer charLength, boolean surrogatePk){
        if (value != null){
            switch (type){
                case "INT":
                    try {
                        Integer myInt = Integer.valueOf(value);
                        return new IntRecord(myInt);
                    } catch (NumberFormatException e){
                        temporalErrorMessage = "Error: Invalid INT <strong>" + value + "</strong> at insert.";
                        return null;
                    }

                case "FLOAT":
                    try{
                        Double myFloat = Double.valueOf(value);
                        return new FloatRecord(myFloat);
                    } catch (NumberFormatException e){
                        temporalErrorMessage = "Error: Invalid FLOAT <strong>" + value + "</strong> at insert.";
                        return null;
                    }


                case "DATE":
                    try{
                        if (!surrogatePk){
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            df.setLenient(false);
                            Date fecha2 = df.parse(value);
                            return new DateRecord(fecha2);
                        } else {
                            return new DateRecord(new Date());
                        }

                    } catch (java.text.ParseException e) {
                        temporalErrorMessage = "Error: Invalid date <strong>" + value + "</strong> at insert.";
                        return null;
                    }

                case "CHAR":
                    char[] result = value.substring(1, value.length() - 1).toCharArray();
                    if (result.length > charLength){
                        temporalErrorMessage = "Error: Invalid char, <strong>" + value + "</strong> length must be at most <strong>" + charLength + "</strong> chars long.";
                        return null;
                    }
                    return new CharRecord(result);
            }
        } else {
            switch (type){
                case "INT":
                    return new IntRecord();

                case "FLOAT":
                    return new FloatRecord();

                case "DATE":
                    return new DateRecord();

                case "CHAR":
                    return new CharRecord();
            }
        }

        return null;
    }

    private ArrayList<String> generateNullList(int size){
        ArrayList<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(null);
        }
        return list;
    }


    public ArrayList<Tuple> searchRaw(ArrayList<String> SelectColumns, ArrayList<String> fromTables, ArrayList<String> postFixWhereCondition, ArrayList<String[]> orderByTuples){
        return null;
    }

    public JSONObject getDbInUse() {
        return dbInUseMetadata;
    }
}
