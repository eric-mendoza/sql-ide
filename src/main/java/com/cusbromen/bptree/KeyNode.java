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
    public KeyNode(long freeBytes) {
        keys = new ArrayList<>();
        childs = new ArrayList<>();
        parent = -1;
        availableSpace = freeBytes;
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
        head = file.getFilePointer();
        file.writeLong(availableSpace);
        file.writeLong(parent);
        file.writeLong(head);
        file.writeInt(keys.size());
        for (int i = 0; i < keys.size(); i++) {
            file.writeLong(childs.get(i));
            keys.get(i).writeToFile(file);
        }
        file.writeLong(childs.get(childs.size() -1));

        back = file.getFilePointer();
        // Bytes written
        long diff = back - head;
        file.seek(head);
        availableSpace -= diff;
        file.writeLong(availableSpace);
        file.seek(back);
    }

    /**
     * Method to restore node from file
     * @param types types of the primary key
     * @param file file to restore from
     * @throws IOException if something goes wrong
     */
    public void readFromFile(ArrayList<Type> types, RandomAccessFile file) throws IOException {
        availableSpace = file.readLong();
        parent = file.readLong();
        head = file.readLong();
        // Load the keys
        int size = file.readInt();
        for (int i = 0; i < size; i++) {
            childs.add(file.readLong());
            keys.add(new Key(types, file));
        }
        childs.add(file.readLong());

        back = file.getFilePointer();
    }


    /**
     * Adds key to the node
     * @param k Key to add
     * @throws IOException if something goes wrong
     */
    public void add(Key k, long child, RandomAccessFile file) throws IOException{
        file.seek(back);
        k.writeToFile(file);
        file.writeLong(child);
        availableSpace -= file.getFilePointer() - back;
        back = file.getFilePointer();
        keys.add(k);
        file.seek(head);
        file.writeLong(availableSpace);
        file.writeLong(parent);
        file.seek(file.getFilePointer() + 8);
        file.writeInt(keys.size());

    }

    public ArrayList<Key> getKeys() {
        return keys;
    }

    public ArrayList<Long> getChilds() {
        return childs;
    }
}
