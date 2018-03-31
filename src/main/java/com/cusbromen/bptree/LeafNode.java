package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class LeafNode extends Node{


    // Properties of a B+ tree
    private long nextNode;
    private long prevNode;

    // Leaf nodes can store a lot of tuples
    private ArrayList<Key> keys;
    private ArrayList<Tuple> tuples;

    /**
     * Default constructor
     */
    public LeafNode(long freeBytes) {
        availableSpace = freeBytes;
        keys = new ArrayList<>();
        tuples = new ArrayList<>();
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
    public void writeToFile(RandomAccessFile file) throws IOException{
        head = file.getFilePointer();
        file.seek(head + 8);
        file.writeLong(parent);
        file.writeLong(prevNode);
        file.writeLong(nextNode);
        file.writeLong(head);
        file.writeInt(tuples.size());
        for (int i = 0; i < tuples.size(); i++) {
            keys.get(i).writeToFile(file);
            tuples.get(i).writeToFile(file);
        }

        back = file.getFilePointer();
        // Bytes written
        long diff = back - head;
        file.seek(head);
        availableSpace -= diff;
        file.writeLong(availableSpace);
        file.seek(back);

    }

    /**
     * Method to read LeafNode from disk
     * @param types types of the records to restore
     * @param file file to read from
     * @throws IOException if there is some problem reading
     */
    public void readFromFile(ArrayList<Type> keyTypes, ArrayList<Type> types,
                             RandomAccessFile file) throws IOException{
        keys = new ArrayList<>();
        tuples = new ArrayList<>();
        availableSpace = file.readLong();
        parent = file.readLong();
        prevNode = file.readLong();
        nextNode = file.readLong();
        head = file.readLong();
        int size = file.readInt();
        for (int i = 0; i < size; i++) {
            keys.add(new Key(keyTypes, file));
            tuples.add(new Tuple(types, file));
        }
        back = file.getFilePointer();
    }

    /**
     * Adds a tuple to the leaf node
     * @param t tuple to add
     * @param file file to write to
     * @throws IOException if something goes wrong
     */
    void add(Key k, Tuple t, RandomAccessFile file) throws IOException{
        file.seek(back);
        k.writeToFile(file);
        t.writeToFile(file);
        availableSpace -= file.getFilePointer() - back;
        back = file.getFilePointer();
        keys.add(k);
        tuples.add(t);
        updateHeader(file);
    }

    /**
     * Updates the node characteristics on disk
     */
    private void updateHeader(RandomAccessFile file) throws IOException {
        file.seek(head);
        file.writeLong(availableSpace);
        file.writeLong(parent);
        file.writeLong(prevNode);
        file.writeLong(nextNode);
        file.seek(file.getFilePointer() + 8);
        file.writeInt(keys.size());
    }

    /**
     * Method to search a key on the current node
     * @param k key to search
     * @return the record of the key
     */
    Tuple get(Key k) {
        for (int i = 0; i < keys.size(); i++) {
            if (k.compareTo(keys.get(i)) == 0){
                return tuples.get(i);
            }
        }
        return null;
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


    public ArrayList<Key> getKeys() {
        return keys;
    }

    public ArrayList<Tuple> getTuples() {
        return tuples;
    }

    public void setNextNode(long nextNode) {
        this.nextNode = nextNode;
    }

    public void setPrevNode(long prevNode) {
        this.prevNode = prevNode;
    }

    public void setKeys(ArrayList<Key> keys) {
        this.keys = keys;
    }

    public void setTuples(ArrayList<Tuple> tuples) {
        this.tuples = tuples;
    }
}
