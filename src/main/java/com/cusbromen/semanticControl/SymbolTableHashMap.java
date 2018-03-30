package com.cusbromen.semanticControl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@SuppressWarnings("unchecked") // JSON's fault
public class SymbolTableHashMap {
    private JSONObject metadata, dbInUseMetadata;
    private String dbsJsonPath, dbInUseId;
    private List<String> verboseParser;

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

    // TODO Verify that currentDB isnt null before coming here (Also use the variable with the name of the db in use)
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

    public int renameTable(String currentDb, String oldName, String newName){
        verboseParser.add(">> Will try to rename metadata for table " + oldName);

        if (currentDb != null){
            String dbPath = "metadata/" + currentDb + "/" + currentDb + ".json";
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
                    Object info = dbInUseMetadata.get(oldName);
                    verboseParser.add(">> Using info " + info.toString());
                    dbInUseMetadata.remove(oldName);
                    dbInUseMetadata.put(newName, info);
                    verboseParser.add(">> Changed info to: " + dbInUseMetadata.toString());
                    PrintWriter writer = new PrintWriter(db, "UTF-8");
                    writer.write(dbInUseMetadata.toJSONString());
                    writer.close();
                    verboseParser.add(">> Wrote to file!");
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
     * @return 2, if table already existed; 1, success; 0: error; 3: constraint duplicated
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

                // Create new destiny path
                verboseParser.add(">> Creating new destiny path!");
                new File("metadata/" + newName).mkdir();
                Path fileToMovePath = Paths.get("metadata/" + oldName + "/" + oldName + ".json");
                Path targetPath = Paths.get("metadata/" + newName + "/" + newName + ".json");
                verboseParser.add(">> Changes made into path " + targetPath + "!");

                Files.move(fileToMovePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                Files.delete(Paths.get("metadata/" + oldName));  // Delete old path
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
            if (dbInUseId.equals(dbId)){
                dbInUseId = null;
                dbInUseMetadata = null;
                File dbInUseFile = new File("metadata/lastDbUsed");
                dbInUseFile.delete();
            }

            // TODO ELIMINAR DE LAS TABLAS A LAS QUE ESTA HACIA REFERENCIA, LA REFERENCIA

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
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
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
}
