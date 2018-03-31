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
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class BpTree {

    private RandomAccessFile file;

    // Disk block size
    private int blockSize;

    // Pointer to tree root
    private long root;


    // Pointer to next insertion
    private long nextInsert;


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
            nextInsert = file.readLong();
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
     *                  must be greater than 44 bytes
     */
    public BpTree(RandomAccessFile file, ArrayList<Type> keyTypes,
                  ArrayList<Type> recordTypes, int blockSize,
                  int keySize, int recordSize) throws IOException {

        if (blockSize < 44){
            throw new IOException("Block size must be at least 44 bytes");
        }

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

        root = blockSize;
        file.writeLong(root);
        file.writeLong(root + blockSize);


        file.seek(root);
        // Flag if node is a leaf
        // in this case root is.
        file.writeBoolean(true);

        // Initialize the leaf node with blocksize - 1 free space
        // the -1 is because of the boolean write above
        (new LeafNode(blockSize - 1)).writeToFile(file);
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

    /**
     * Recursive method to search the key
     * @param key key to search
     * @return the leaf node where is the key
     * @throws IOException if there is some problem with file
     */
    private LeafNode treeSearch(Key key) throws IOException{

        // If it is a leaf
        if (file.readBoolean()) {
            return new LeafNode(keyTypes, recordTypes, file);
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


    private LeafNode uniqueSearch(Key key) throws IOException, InvalidParameterException{
        file.seek(root);
        LeafNode leafNode = treeSearch(key);
        for (Key k : leafNode.getKeys()){
            if (k.compareTo(key) == 0)
                throw new InvalidParameterException("Key must be unique");
        }
        return leafNode;
    }

    /**
     * Insertion of char leaf into the B+ tree
     * @param row Table row to insert
     */
    public void insert(Key key, Tuple row) throws IOException {

        LeafNode leafNode = uniqueSearch(key);
        boolean isFull = leafNode.getAvailableSpace()
                - (key.size() + row.size()) < 0;
        if (isFull) {
            // Split the bucket
            long parent = leafNode.getParent();
            ArrayList<Key> keys = leafNode.getKeys();
            ArrayList<Tuple> tuples = leafNode.getTuples();
            int firstNodeSize = keys.size() / 2;
            ArrayList<Key> firstNodeKeys = new ArrayList<>();
            ArrayList<Tuple> firstNodeRecords = new ArrayList<>();

            ArrayList<Key> secondNodeKeys = new ArrayList<>();
            ArrayList<Tuple> secondNodeRecords = new ArrayList<>();
            for (int i = 0; i < keys.size(); i++) {
                if (i < firstNodeSize) {
                    firstNodeKeys.add(keys.get(i));
                    firstNodeRecords.add(tuples.get(i));
                }else {
                    secondNodeKeys.add(keys.get(i));
                    secondNodeRecords.add(tuples.get(i));
                }
            }
            secondNodeKeys.add(key);
            secondNodeRecords.add(row);

            // Write the first node
            leafNode.setKeys(firstNodeKeys);
            leafNode.setTuples(firstNodeRecords);
            leafNode.setAvailableSpace(blockSize - 1);
            leafNode.setNextNode(nextInsert);

            // Write the second node
            file.seek(nextInsert);
            // Next node is a leaf
            file.writeBoolean(true);
            LeafNode nextNode = new LeafNode(blockSize - 1);
            nextNode.setHead(nextInsert + 1);
            nextNode.setPrevNode(leafNode.loc());
            nextNode.setKeys(secondNodeKeys);
            nextNode.setTuples(secondNodeRecords);
            nextInsert += blockSize;



            splitParents(parent, keys.get(firstNodeSize),
                    leafNode, nextNode, keys.size());


        } else {
            leafNode.add(key, row, file);
        }

    }


    /**
     * Method that inserts a key into parent nodes,
     * it splits parents if needed
     * @param parent parent pointer
     * @param key key to insert
     * @param maxDegree max degree of the parents
     * @throws IOException if something goes wrong
     */
    private void splitParents(long parent, Key key, Node child1,
                              Node child2, int maxDegree) throws IOException{

        // Parent does not exist
        if (parent < 0) {

            KeyNode keyParent = new KeyNode(blockSize -1);
            keyParent.getKeys().add(key);
            keyParent.getChilds().add(child1.loc());
            keyParent.getChilds().add(child2.loc());
            file.seek(nextInsert);
            root = nextInsert;

            // Is a key node
            file.writeBoolean(false);
            keyParent.writeToFile(file);

            parent = nextInsert;

            nextInsert += blockSize;

        }else {
            // +1 because we already know it is a key
            file.seek(parent + 1);

            // Load parent to memory
            KeyNode keyNode = new KeyNode(keyTypes, file);

            // Check if parent needs to be splitted
            if (keyNode.getKeys().size() == maxDegree) {
                int newSize = maxDegree / 2;
                int otherHalf = maxDegree - newSize;
                ArrayList<Key> keys  = keyNode.getKeys();
                ArrayList<Long> childs = keyNode.getChilds();

                ArrayList<Key> leftNodeKeys = new ArrayList<>();
                ArrayList<Key> rightNodeKeys = new ArrayList<>();
                ArrayList<Long> leftNodeChilds = new ArrayList<>();
                ArrayList<Long> rightNodeChilds = new ArrayList<>();
                for (int i = 0; i < maxDegree; i++) {
                    if (i < newSize) {
                        leftNodeKeys.add(keys.get(i));
                        leftNodeChilds.add(childs.get(i));
                    }else if (i > otherHalf){
                        rightNodeKeys.add(keys.get(i));
                        rightNodeChilds.add(childs.get(i));
                    }
                }
                leftNodeChilds.add(childs.get(childs.size() - 2));

                rightNodeKeys.add(key);
                rightNodeChilds.add(child1.loc());
                rightNodeChilds.add(child2.loc());

                keyNode.setKeys(leftNodeKeys);
                keyNode.setChilds(leftNodeChilds);
                keyNode.setAvailableSpace(blockSize - 1);


                parent = nextInsert;
                file.seek(nextInsert);
                file.writeBoolean(false);
                KeyNode newKeyNode = new KeyNode(blockSize - 1);
                newKeyNode.setHead(nextInsert + 1);
                newKeyNode.setKeys(rightNodeKeys);
                newKeyNode.setChilds(rightNodeChilds);
                nextInsert += blockSize;


                splitParents(keyNode.getParent(), keys.get(newSize), keyNode,
                        newKeyNode, maxDegree);


            }else {
                // Simply insert if not
                keyNode.getChilds().add(child2.loc());
                keyNode.add(key, child2.loc(), file);
            }
        }

        // Write first node
        file.seek(child1.loc() + 1);
        child1.setParent(parent);
        child1.writeToFile(file);

        file.seek(child2.loc() + 1);
        child2.setParent(parent);
        child2.writeToFile(file);
    }

    







}
