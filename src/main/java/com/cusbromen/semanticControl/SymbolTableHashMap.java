package com.cusbromen.semanticControl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;


@SuppressWarnings("unchecked") // JSON's fault
public class SymbolTableHashMap implements SymbolTable{
    private JSONObject metadata, dbInUseMetadata;
    private String dbsJsonPath, dbInUseId;
    private List<String> verboseParser;

    public SymbolTableHashMap(List<String> verboseParser) {
        this.verboseParser = verboseParser;
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
    public String showTables(String currentDb) {
        verboseParser.add("Will try to show tables!");

        if (currentDb != null){

            try {
                Set<?> keys = dbInUseMetadata.keySet();
                String tableList = "";

                verboseParser.add(">> Finding keys for tables in database " + currentDb + ".");

                for (Object key : keys) {
                    tableList = tableList + "*\t" + key.toString() + "<br>";
                }

                verboseParser.add(">> Found keys for tables in " + currentDb + "! " + tableList + ".");

                return tableList;

            } catch (Exception e) {
                verboseParser.add(">> Failed in finding keys for tables in database " + currentDb);
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

    public int alterTable(String currentDb) {
        verboseParser.add(">> Will try to alter metadata for table");
        // return 0: You are not using a db.
        // return 1: success
        // return 2: table already exists
        // return 3: old table does not exist
        if (currentDb != null){
            String dbPath = "metadata/" + currentDb + "/" + currentDb + ".json";
            File db = new File(dbPath);

            verboseParser.add(">> Using file: " + dbPath);

            // ACTION
            // TODO ACTION

            return 4;
        } else {
            // Not using a db
            return 0;
        }
    }

    public int createTable(String tableName, JSONObject tableProps) {
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
                dbInUseMetadata.put(tableName, tableProps);
                PrintWriter writer = new PrintWriter(db, "UTF-8");
                writer.write(dbInUseMetadata.toJSONString());
                writer.close();

                // Increase number of tables in metadata
                verboseParser.add(">> Writing changes to file " + dbsJsonPath);
                PrintWriter writer2 = new PrintWriter(dbsJsonPath, "UTF-8");
                JSONObject table = (JSONObject) metadata.get(dbInUseId);
                Integer noTables = (Integer) table.get("noTables");
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
    @Override
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
    @Override
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
    @Override
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

        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
