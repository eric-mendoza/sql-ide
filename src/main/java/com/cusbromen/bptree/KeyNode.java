package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class KeyNode extends Node {


    private ArrayList<Key> keys;
    private ArrayList<Long> childs;


    /**
     * Empty key node
     */
    public KeyNode() {
        keys = new ArrayList<>();
        childs = new ArrayList<>();
    }

    /**
     * Constructor to restore Node
     * @param types Types of the primary key
     * @param file File to read from
     * @throws IOException if something goes wrong
     */
    public KeyNode(ArrayList<Type> types, RandomAccessFile file) throws IOException {
        keys = new ArrayList<>();
        childs = new ArrayList<>();
        readFromFile(types, file);
    }


    /**
     * Method to write the key node to a file
     * @param file file to write to
     * @throws IOException if something goes wrong
     */
    public void writeToFile(RandomAccessFile file) throws IOException {
        file.writeLong(availableSpace);
        file.writeInt(keys.size());
        for (Key key : keys) {
            key.writeToFile(file);
        }
        file.writeInt(childs.size());
        for (Long child: childs) {
            file.writeLong(child);
        }
    }

    /**
     * Method to restore node from file
     * @param types types of the primary key
     * @param file file to restore from
     * @throws IOException if something goes wrong
     */
    public void readFromFile(ArrayList<Type> types, RandomAccessFile file) throws IOException {
        availableSpace = file.readLong();
        // Load the keys
        int size = file.readInt();
        for (int i = 0; i < size; i++) {
            keys.add(new Key(types, file));
        }
        // Load child pointers
        size = file.readInt();
        for (int i = 0; i < size; i++) {
            childs.add(file.readLong());
        }
    }

    public ArrayList<Key> getKeys() {
        return keys;
    }

    public ArrayList<Long> getChilds() {
        return childs;
    }
}
