package com.cusbromen.semanticControl;

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

public class SymbolTableHashMap implements SymbolTable{
    private JSONObject metadata;
    private String dbsJsonPath;

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
