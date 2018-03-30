package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class KeyNode extends Node {


    private Key key;
    private ArrayList<Long> childs;


    /**
     * Empty key node
     * @param key key to append
     */
    public KeyNode(Key key) {
        this.key = key;
    }

    /**
     * Constructor to restore Node
     * @param types Types of the primary key
     * @param file File to read from
     * @throws IOException if something goes wrong
     */
    public KeyNode(ArrayList<Type> types, RandomAccessFile file) throws IOException {
        key = new Key();
        readFromFile(types, file);
    }


    /**
     * Method to write the key node to a file
     * @param file file to write to
     * @throws IOException if something goes wrong
     */
    public void writeToFile(RandomAccessFile file) throws IOException {
        file.writeLong(availableSpace);
        key.writeToFile(file);
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
        key.readFromFile(types, file);
        int size = file.readInt();
        for (int i = 0; i < size; i++) {
            childs.add(file.readLong());
        }
    }

    public Key getKey() {
        return key;
    }

    public ArrayList<Long> getChilds() {
        return childs;
    }
}
