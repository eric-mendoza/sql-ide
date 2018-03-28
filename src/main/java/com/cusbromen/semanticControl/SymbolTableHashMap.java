package com.cusbromen.semanticControl;

import com.cusbromen.antlr.SqlParser;
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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@SuppressWarnings("unchecked") // JSON's fault
public class SymbolTableHashMap implements SymbolTable{
    private JSONObject metadata, dbInUseMetadata;
    private String dbsJsonPath;

    public String showDatabases() {
        if (!metadata.isEmpty()) {
            Set<?> keys = metadata.keySet();
            String dbList = "";

            for (Object key : keys) {
                dbList = dbList + "*\t" + key.toString() + "<br>";
            }

            return dbList;
        } else {
            return "0";
        }
    }

    public String showTables(String currentDb) {
        if (currentDb != null){

            try {
                Set<?> keys = dbInUseMetadata.keySet();
                String tableList = "";

                for (Object key : keys) {
                    tableList = tableList + "*\t" + key.toString() + "<br>";
                }

                return tableList;

            } catch (Exception e) {
                e.printStackTrace(); return "0";
            }
        } else {
            return "0";
        }
    }

    public int renameTable(String currentDb, String oldName, String newName){
        if (currentDb != null){
            String dbPath = "metadata/" + currentDb + "/" + currentDb + ".json";
            File db = new File(dbPath);

            try {
                if (!dbInUseMetadata.containsKey(oldName)) {
                    return 3;
                } else if (dbInUseMetadata.containsKey(newName)) {
                    return 2;
                } else {
                    Object info = dbInUseMetadata.get(oldName);
                    dbInUseMetadata.remove(oldName);
                    dbInUseMetadata.put(newName, info);
                    PrintWriter writer = new PrintWriter(db, "UTF-8");
                    writer.write(dbInUseMetadata.toJSONString());
                    writer.close();
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
        // return 0: You are not using a db.
        // return 1: success
        // return 2: table already exists
        // return 3: old table does not exist
        if (currentDb != null){
            String dbPath = "metadata/" + currentDb + "/" + currentDb + ".json";
            File db = new File(dbPath);

            // ACTION
            // TODO ACTION

            return 4;
        } else {
            // Not using a db
            return 0;
        }
    }

    /**
     * Creates metadata for new table
     * @param currentDb database name
     * @param tableName
     * @param elementList list of elements of the new table
     * @param jsonParser
     * @return 0, db does not exist; 1, success; 2 table already exists; 3: you are not using any db
     */
    public int createTable(String currentDb, String tableName, List<SqlParser.Table_elementContext> elementList, JSONParser jsonParser) {
        if (currentDb != null){
            String dbPath = "metadata/" + currentDb + "/" + currentDb + ".json";
            File db = new File(dbPath);

            try {
                if (dbInUseMetadata.containsKey(tableName)) {
                    // Table already exists
                    return 2;

                } else {
                    // traverse elements list
                    JSONObject fields = new JSONObject();
                    for (SqlParser.Table_elementContext element : elementList) {
                        String id = element.ID().getText();
                        String typeDef = element.data_type_def().data_type().getText();

                        JSONObject insideInfo = new JSONObject();
                        insideInfo.put("type", typeDef);
                        if (element.column_constraint() != null) {
                            insideInfo.put("column_constraint", "NOT NULL");
                        }
                        if (element.data_type_def().c_constraint() != null) {
                            if (element.data_type_def().c_constraint().length_constraint() != null) {
                                insideInfo.put("length_constraint", element.data_type_def().c_constraint().length_constraint().NUMBER().getText());
                            }
                            if (element.data_type_def().c_constraint().keys_constraint() != null) {
                                if (element.data_type_def().c_constraint().keys_constraint().getText().contains("PRIMARYKEY")) {
                                    insideInfo.put("constraint", "PRIMARY KEY");
                                }
                                if (element.data_type_def().c_constraint().keys_constraint().getText().contains("FOREIGNKEY")) {
                                    insideInfo.put("constraint", "FOREIGN KEY");
                                }
                                if (element.data_type_def().c_constraint().keys_constraint().getText().contains("CHECK")) {
                                    insideInfo.put("constraint", "CHECK");
                                }
                            }
                        }

                        fields.put(id, insideInfo); // TODO las mete en desorden, no se porque
                    }

                    dbInUseMetadata.put(tableName, fields);
                    PrintWriter writer = new PrintWriter(db, "UTF-8");
                    writer.write(dbInUseMetadata.toJSONString());
                    writer.close();

                    // Increase number of tables in metadata
                    PrintWriter writer2 = new PrintWriter(dbsJsonPath, "UTF-8");
                    JSONObject table = (JSONObject) metadata.get(currentDb);
                    Long noTables = (Long) table.get("noTables");
                    noTables++;
                    table.put("noTables", noTables);

                    writer2.write(metadata.toJSONString());
                    writer2.close();
                    return 1;
                }
            } catch (Exception e) { e.printStackTrace(); return 0;}
        } else {
            // You haven't selected a db to use
            return 3;
        }
    }

    /**
     * This method creates a new db and returns a String with the message of the operation
     * @param id is the name of the new db
     * @return 0: Database Created, 1: Database name is already in use
     */
    @Override
    public int createDb(String id) {
        // Try to open the master file (dbs.json) on metadata directory
        try {
            // Verify if db already exists
            if (metadata.get(id) == null){
                JSONObject newDb = new JSONObject();
                newDb.put("noTables", 0);
                metadata.put(id, newDb);

                // Overwrite existing json
                PrintWriter writer = new PrintWriter(new File(dbsJsonPath), "UTF-8");
                writer.write(metadata.toJSONString());
                writer.close();
            }

            // DB already exists
            else {
                return 1;
            }

            // Create a directory for the new db
            File newDb = new File("metadata/" + id + "/" + id + ".json");
            newDb.getParentFile().mkdir();
            newDb.createNewFile();

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
                File dbsJsonFile = new File(dbsJsonPath);
                PrintWriter writer = new PrintWriter(dbsJsonFile, "UTF-8");
                writer.write(metadata.toJSONString());
                writer.close();

                // Create new destiny path
                new File("metadata/" + newName).mkdir();
                Path fileToMovePath = Paths.get("metadata/" + oldName + "/" + oldName + ".json");
                Path targetPath = Paths.get("metadata/" + newName + "/" + newName + ".json");

                Files.move(fileToMovePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                Files.delete(Paths.get("metadata/" + oldName));  // Delete old path

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
        try {
            this.dbsJsonPath = dbsJsonPath;

            // See if metadata dir exists
            File dbs = new File(dbsJsonPath);

            // If it doesn't exists, create it
            if (!dbs.exists()){
                dbs.getParentFile().mkdir();
                dbs.createNewFile();
                PrintWriter writer = new PrintWriter(dbs, "UTF-8");

                // Create bject with all dbs
                metadata = new JSONObject();

                // Write to file
                writer.write(metadata.toJSONString());

                writer.close();
            }

            // Load it to memory
            else {
                metadata = (JSONObject) jsonParser.parse(new FileReader(dbsJsonPath));
            }

            // Load last used db to memory
            if (lastUsedDb != null) loadDbMetadata(lastUsedDb, jsonParser);

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
        String dbPath = "metadata/" + dbName + "/" + dbName + ".json";
        File db = new File(dbPath);
        if (db.exists()){
            try {
                dbInUseMetadata = (JSONObject) jsonParser.parse(new FileReader(dbPath));
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
}
