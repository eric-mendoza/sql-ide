package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class LeafNode extends Node{


    // Properties of a B+ tree
    private long nextNode;
    private long prevNode;

    // Leaf nodes can store a lot of tuples
    private ArrayList<Pair> pairs;


    /**
     * Default constructor
     */
    public LeafNode(long freeBytes) {
        availableSpace = freeBytes;
        pairs = new ArrayList<>();
        nextNode = -1;
        prevNode = -1;
        this.parent = -1;
    }

    /**
     * Cosntructor
     * @param keyTypes Types of the keys
     * @param types Types of the stored records
     * @param file File to read from
     * @throws IOException if there is problems :D
     */
    public LeafNode(ArrayList<Type> keyTypes, ArrayList<Type> types,
                    RandomAccessFile file) throws IOException{
        readFromFile(keyTypes, types, file);
    }


    /**
     * Method to write LeafNode to fil
     * @param file File to write to
     * @throws IOException if there is some problem writing on file
     */
    @Override
    public void writeToFile(RandomAccessFile file) throws IOException{
        head = file.getFilePointer();
        file.seek(head + 8);
        file.writeLong(parent);
        file.writeLong(prevNode);
        file.writeLong(nextNode);
        file.writeLong(head);
        file.writeInt(pairs.size());
        for (Pair p :
                pairs) {
            p.writeToFile(file);
        }
        long next = file.getFilePointer();
        // Bytes written
        long diff = next - head;
        file.seek(head);
        availableSpace -= diff;
        file.writeLong(availableSpace);
        file.seek(next);

    }

    /**
     * Method to read LeafNode from disk
     * @param types types of the records to restore
     * @param file file to read from
     * @throws IOException if there is some problem reading
     */
    @Override
    public void readFromFile(ArrayList<Type> keyTypes, ArrayList<Type> types,
                             RandomAccessFile file) throws IOException{
        pairs = new ArrayList<>();
        availableSpace = file.readLong();
        parent = file.readLong();
        prevNode = file.readLong();
        nextNode = file.readLong();
        head = file.readLong();
        int size = file.readInt();
        for (int i = 0; i < size; i++) {
            pairs.add(new Pair(new Key(keyTypes, file),
                    new Tuple(types, file)));
        }

    }

    /**
     * Adds a tuple to the leaf node
     * @param t tuple to add
     * @param file file to write to
     * @throws IOException if something goes wrong
     */
    void add(Key k, Tuple t, RandomAccessFile file) throws IOException{
        Pair p = new Pair(k, t);
        Utility.sortedInsert(pairs, p);
        availableSpace -= p.size();
        file.seek(head);
        file.writeLong(availableSpace);
        file.seek(file.getFilePointer() + 32);
        file.writeInt(pairs.size());
        for (Pair temp :
                pairs) {
            temp.writeToFile(file);
        }
    }




    public void dump(String ident) {
        System.out.println(ident + "----- LEAF NODE " + this.loc() + " -----");
        System.out.println(ident + "Location: " + this.loc());
        System.out.println(ident + "Number of Records: " + this.pairs.size());
        System.out.println(ident + "Available Space: " + this.availableSpace);
        System.out.println(ident + "Parent: " + this.parent);
        System.out.println(ident + "Prev Node: " + this.prevNode);
        System.out.println(ident + "Next Node: " + this.nextNode);
        System.out.println(ident + "---------------------------");
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

    public ArrayList<Pair> getPairs() {
        return pairs;
    }

    public void setPairs(ArrayList<Pair> pairs) {
        this.pairs = pairs;
    }

    public void setNextNode(long nextNode) {
        this.nextNode = nextNode;
    }

    public void setPrevNode(long prevNode) {
        this.prevNode = prevNode;
    }

}
