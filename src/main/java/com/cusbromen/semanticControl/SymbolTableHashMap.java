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
import java.util.List;

public class SymbolTableHashMap implements SymbolTable{
    private JSONObject metadata;
    private String dbsJsonPath;

    public int alterTable(String currentDb, SqlParser.Alter_tableContext ctx, JSONParser jsonParser) {
        // return 0: db does not exist.
        // return 1: success
        // return 2: table already exists
        // return 3: old table does not exist
        JSONObject selectedDb;
        String dbPath = "metadata/" + currentDb + "/" + currentDb + ".json";
        File db = new File(dbPath);
        if (ctx.alter_rename() != null) {
            //RENAME
            String oldName = ctx.alter_rename().ID().get(0).getText();
            String newName = ctx.alter_rename().ID().get(1).getText();

            try {
                if (!db.exists()) { return 0; } else {
                    selectedDb = (JSONObject) jsonParser.parse(new FileReader(dbPath));
                    if (!selectedDb.containsKey(oldName)) {
                        return 3;
                    } else if (selectedDb.containsKey(newName)) {
                        return 2;
                    } else {
                        Object info = selectedDb.get(oldName);
                        selectedDb.remove(oldName);
                        selectedDb.put(newName, info);
                        PrintWriter writer = new PrintWriter(db, "UTF-8");
                        writer.write(selectedDb.toJSONString());
                        writer.close();
                        return 1;
                    }
                }
            } catch (Exception e) { e.printStackTrace(); return 0;}

        } else if (ctx.alter_action() != null) {
            // ACTION
            String tableName = ctx.alter_action().ID().getText();
            // TODO ACTION
        }
        return 4;
    }

    /**
     * Creates metadata for new table
     * @param currentDb database name
     * @param tableName
     * @param elementList list of elements of the new table
     * @param jsonParser
     * @return 0, db does not exist; 1, success; 2 table already exists
     */
    public int createTable(String currentDb, String tableName, List<SqlParser.Table_elementContext> elementList, JSONParser jsonParser) {
        JSONObject selectedDb;
        String dbPath = "metadata/" + currentDb + "/" + currentDb + ".json";
        File db = new File(dbPath);

        try {
            if (!db.exists()) { return 0; } else {
                selectedDb = (JSONObject) jsonParser.parse(new FileReader(dbPath));
                if (selectedDb.containsKey(tableName)) {
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

                    selectedDb.put(tableName, fields);
                    PrintWriter writer = new PrintWriter(db, "UTF-8");
                    writer.write(selectedDb.toJSONString());
                    writer.close();
                    return 1;
                }
            }
        } catch (Exception e) { e.printStackTrace(); return 0;}
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
    public void readMetadata(String dbsJsonPath, JSONParser jsonParser) {
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
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
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
