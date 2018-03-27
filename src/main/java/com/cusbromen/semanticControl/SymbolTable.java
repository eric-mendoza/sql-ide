package com.cusbromen.semanticControl;


public interface SymbolTable {
    /**
     * This method creates a new db and returns a String with the message of the operation
     * @param ID is the name of the new db
     * @return 0: Database Created, 1: Database name is already in use
     */
    int createDb(String ID);

    /**
     * Renames a DB with the name oldName to newName
     * @param oldName the original name of the db
     * @param newName the newName for the db
     * @return 0: Database rename successful, 1: oldName db doesn't exists, 2: newName db already exists
     */
    int renameDb(String oldName, String newName);

    /**
     * Verifies if a db exists
     */
    boolean dbExists(String id);
}
