package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class LeafNode extends Node{


    // Properties of a B+ tree
    private long nextNode;
    private long prevNode;

    // Leaf nodes can store a lot of tuples
    private ArrayList<Tuple> tuples;

    /**
     * Default constructor
     */
    public LeafNode(long freeBytes) {
        availableSpace = freeBytes;
        tuples = new ArrayList<>();
        nextNode = -1;
        prevNode = -1;
    }

    /**
     * Cosntructor
     * @param types Types of the stored records
     * @param file File to read from
     * @throws IOException if there is problems :D
     */
    public LeafNode(ArrayList<Type> types, RandomAccessFile file) throws IOException{
        readFromFile(types, file);
    }


    /**
     * Method to write LeafNode to fil
     * @param file File to write to
     * @throws IOException if there is some problem writing on file
     */
    public void writeToFile(RandomAccessFile file) throws IOException{
        file.writeLong(availableSpace);
        file.writeLong(prevNode);
        file.writeLong(nextNode);
        file.writeInt(tuples.size());
        for (Tuple tuple : tuples) {
            tuple.writeToFile(file);
        }
    }

    /**
     * Method to read LeafNode from disk
     * @param types types of the records to restore
     * @param file file to read from
     * @throws IOException if there is some problem reading
     */
    public void readFromFile(ArrayList<Type> types, RandomAccessFile file) throws IOException{
        tuples = new ArrayList<>();
        availableSpace = file.readLong();
        prevNode = file.readLong();
        nextNode = file.readLong();
        int size = file.readInt();
        for (int i = 0; i < size; i++) {
            tuples.add(new Tuple(types, file));
        }
    }

    public long getAvailableSpace() {
        return availableSpace;
    }

    public long getNextNode() {
        return nextNode;
    }

    public long getPrevNode() {
        return prevNode;
    }
}
