/**
 * @author Rodrigo Custodio
 * Description: B+ tree implementation,
 * tree is stored and managed on disk memory.
 */
package com.cusbromen.bptree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class BpTree {

    private RandomAccessFile file;

    // Disk block size
    private int blockSize;

    // Pointer to tree root
    private long root;

    // Types of the table
    private ArrayList<Type> recordTypes;

    // Types of the key
    private ArrayList<Type> keyTypes;


    /**
     * Open a file that already exists, and extract tree
     * characteristics from header
     * @param fileName name of the file
     */
    public BpTree(String fileName) throws IOException{
        File f = new File(fileName);
        if (f.exists() && f.isFile()) {
            file = new RandomAccessFile(fileName, "rw");
            keyTypes = new ArrayList<>();
            recordTypes = new ArrayList<>();
            // Retrieve the header
            blockSize = file.readInt();
            int size = file.readInt();
            for (int i = 0; i < size; i++) {
                keyTypes.add(Type.fromInt(file.readInt()));
            }
            size = file.readInt();
            for (int i = 0; i < size; i++) {
                recordTypes.add(Type.fromInt(file.readInt()));
            }
            root = file.readLong();
            file.seek(root);
        }else {
            throw new FileNotFoundException(fileName + ": No such file");
        }
    }


    /**
     * Constructor with Random AccessFile
     * creates the header in the file
     * @param file File where B+ tree is stored
     * @param blockSize blockSize of the BpTree, it must match
     *                  the of the disk for efficient storage
     */
    public BpTree(RandomAccessFile file, ArrayList<Type> keyTypes,
                  ArrayList<Type> recordTypes, int blockSize) throws IOException {
        this.file = file;
        this.blockSize = blockSize;
        initHeader(file, keyTypes, recordTypes, blockSize);
    }

    /**
     * Constructor to make a Random Access File
     * from file name, creates the file and
     * inserts the tree header
     * @param fileName File name
     */
    public BpTree(String fileName, ArrayList<Type> keyTypes,
                  ArrayList<Type> recordTypes, int blockSize) throws IOException {
        this.blockSize = blockSize;
        file = new RandomAccessFile(fileName, "rw");
        initHeader(file, keyTypes, recordTypes, blockSize);
    }

    /**
     * Method to write the header into the B+ file
     * @param file file to write in
     * @param recordTypes recordTypes to store
     * @param blockSize disk block size
     * @throws IOException if file can't be written
     */
    private void initHeader(RandomAccessFile file, ArrayList<Type> keyTypes,
                            ArrayList<Type> recordTypes, int blockSize) throws IOException {
        // Write the header of the tree
        file.writeInt(blockSize);

        // Write the key types
        this.keyTypes = keyTypes;
        file.writeInt(keyTypes.size());
        for (Type t : keyTypes) {
            file.writeInt(t.val());
        }

        // Write the record types
        this.recordTypes = recordTypes;
        file.writeInt(recordTypes.size());
        for (Type t : recordTypes) {
            file.writeInt(t.val());
        }

        root = file.getFilePointer() + 8;
        file.writeLong(root);

        // Flag if node is a leaf
        // in this case root is.
        file.writeBoolean(true);

        // Initialize the leaf node
        (new LeafNode(blockSize)).writeToFile(file);
    }

    /**
     * Method that closes the tree
     * @throws IOException if can't be closed
     */
    public void close() throws IOException{
        file.close();
    }

    /**
     * Search method
     * @param key key to find
     * @return Tuple if exists, null if not
     */
    public LeafNode search(Key key) throws IOException{
        file.seek(root);
        return treeSearch(key);
    }

    public LeafNode treeSearch(Key key) throws IOException{

        // If it is a leaf
        if (file.readBoolean()) {
            return new LeafNode(recordTypes, file);
        }

        // Then it is a key
        KeyNode keyNode = new KeyNode(keyTypes, file);
        ArrayList<Key> keys = keyNode.getKeys();
        ArrayList<Long> childs = keyNode.getChilds();

        // Check first node
        if (key.compareTo(keys.get(0)) < 0) {
            file.seek(childs.get(0));
            return treeSearch(key);
        }

        // Check middle nodes
        for (int i = 0; i < keys.size() - 1; i++) {
            if (key.compareTo(keys.get(i)) >= 0
                    && key.compareTo(keys.get(i + 1)) < 0) {
                file.seek(childs.get(i + 1));
                return treeSearch(key);
            }
        }

        // Check final node
        if (key.compareTo(keys.get(keys.size() - 1)) >= 0){
            file.seek(childs.get(childs.size() - 1));
            return treeSearch(key);
        }

        return null;
    }

    /**
     * Insertion of char leaf into the B+ tree
     * @param val Table row to insert
     */
    public void insert(Key key, Tuple val) throws IOException {
//        file.seek(root);
//        boolean isLeaf = file.readBoolean();
//        if (isLeaf) {
//            LeafNode leafNode = new LeafNode(recordTypes, file);
//        } else {
////            KeyNode keyNode = new KeyNode(keyTypes, file);
//        }

    }



}
