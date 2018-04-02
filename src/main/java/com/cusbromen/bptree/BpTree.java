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
import java.util.Arrays;
import java.util.Collections;

public class BpTree {

    private RandomAccessFile file;

    // Disk block size
    private int blockSize;

    // Pointer to tree root
    private long root;
    private long nextInsertPointer;

    // Array that contains available disk pages
    private ArrayList<Long> freePages;


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
            freePages = new ArrayList<>();
            // Retrieve the header
            blockSize = file.readInt();


            int size = file.readInt();
            for (int i = 0; i < size; i++) {
                freePages.add(file.readLong());
            }

            size = file.readInt();
            for (int i = 0; i < size; i++) {
                keyTypes.add(Type.fromInt(file.readInt()));
            }
            size = file.readInt();
            for (int i = 0; i < size; i++) {
                recordTypes.add(Type.fromInt(file.readInt()));
            }
            root = file.readLong();
            nextInsertPointer = file.getFilePointer();
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

        if (blockSize < 44){
            throw new IOException("Block size must be at least 44 bytes");
        }

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

        freePages = new ArrayList<>();
        // Write the freePages that can be used
        file.writeInt(freePages.size());
        for (long freePage:
             freePages) {
            file.writeLong(freePage);
        }

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
        nextInsertPointer = file.getFilePointer();
        file.writeLong(root + blockSize);
        nextInsert = root + blockSize;


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
        file.seek(nextInsertPointer);
        file.writeLong(nextInsert);
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


    /**
     * Method that gets all the records of the table
     * @return All the record of the table
     * @throws IOException If something goes bad
     */
    public  ArrayList<Pair> all() throws IOException{
        file.seek(root);
        // While is not a Leaf Node
        // we will reach the first node
        // through the first child of every key node
        while (!file.readBoolean()) {
            KeyNode keyNode = new KeyNode(keyTypes, file);
            file.seek(keyNode.getChilds().get(0));
        }


        ArrayList<Pair> rows = new ArrayList<>();
        LeafNode leafNode;

        // We reached the first leaf node
        // so we traverse it to get all the records
        do {
            leafNode = new LeafNode(keyTypes, recordTypes, file);
            ArrayList<Pair> pairs = leafNode.getPairs();
            rows.addAll(pairs);
            if (leafNode.getNextNode() > 0) {
                file.seek(leafNode.getNextNode() + 1);
            }
        }while (leafNode.getNextNode() > 0);

        return rows;


    }



    /**
     * Method that gets a unique record per search
     * @param key key to find
     * @return Tuple with the values
     * @throws IOException if something goes wrong
     */
    public Pair equalSearch(Key key) throws IOException{
        LeafNode leafNode = search(key);
        ArrayList<Pair> pairs = leafNode.getPairs();
        for (Pair p :
                pairs) {
            if (key.compareTo(p.getKey()) == 0){
                return p;
            }
        }

        return null;
    }

    /**
     * min <= keys <= max
     * @param min Key min to compare
     * @param max key max to compare
     * @return All rows that satisfy criteria
     * @throws IOException If something goes wrong
     */
    public ArrayList<Pair> rangeSearch(Key min, Key max) throws IOException{
        LeafNode leafNode = search(min);

        ArrayList<Pair> rows = new ArrayList<>();
        do {
            ArrayList<Pair> pairs = leafNode.getPairs();
            for (Pair p :
                    pairs) {
                if (min.compareTo(p.getKey()) <= 0 && max.compareTo(p.getKey()) >= 0) {
                    rows.add(p);
                }
            }
            if (leafNode.getNextNode() > 0) {
                file.seek(leafNode.getNextNode() + 1);
                leafNode = new LeafNode(keyTypes, recordTypes, file);
            }else {
                break;
            }
        } while(true);

        return rows;
    }

    /**
     * min < keys <= max
     * @param min Key min to compare
     * @param max key max to compare
     * @return All rows that satisfy criteria
     * @throws IOException If something goes wrong
     */
    public ArrayList<Pair> upperRangeSearch(Key min, Key max) throws IOException{
        LeafNode leafNode = search(min);

        ArrayList<Pair> rows = new ArrayList<>();
        do {
            ArrayList<Pair> pairs = leafNode.getPairs();
            for (Pair p :
                    pairs) {
                if (min.compareTo(p.getKey()) < 0 && max.compareTo(p.getKey()) >= 0) {
                    rows.add(p);
                }
            }
            if (leafNode.getNextNode() > 0) {
                file.seek(leafNode.getNextNode() + 1);
                leafNode = new LeafNode(keyTypes, recordTypes, file);
            }else {
                break;
            }
        } while(true);

        return rows;
    }


    /**
     * keys <= max
     * @param max key max to compare
     * @return All rows that satisfy criteria
     * @throws IOException If something goes wrong
     */
    public ArrayList<Pair> upperRangeSearch(Key max) throws IOException{
        LeafNode leafNode = search(max);

        ArrayList<Pair> rows = new ArrayList<>();
        do {
            ArrayList<Pair> pairs = leafNode.getPairs();
            for (Pair p :
                    pairs) {
                if (max.compareTo(p.getKey()) >= 0) {
                    rows.add(p);
                }
            }
            if (leafNode.getNextNode() > 0) {
                file.seek(leafNode.getNextNode() + 1);
                leafNode = new LeafNode(keyTypes, recordTypes, file);
            }else {
                break;
            }
        } while(true);

        return rows;
    }


    /**
     * keys < max
     * @param max key max to compare
     * @return All rows that satisfy criteria
     * @throws IOException If something goes wrong
     */
    public ArrayList<Pair> upperStrictRangeSearch(Key max) throws IOException{
        LeafNode leafNode = search(max);

        ArrayList<Pair> rows = new ArrayList<>();
        do {
            ArrayList<Pair> pairs = leafNode.getPairs();
            for (Pair p :
                    pairs) {
                if (max.compareTo(p.getKey()) > 0) {
                    rows.add(p);
                }
            }
            if (leafNode.getNextNode() > 0) {
                file.seek(leafNode.getNextNode() + 1);
                leafNode = new LeafNode(keyTypes, recordTypes, file);
            }else {
                break;
            }
        } while(true);

        return rows;
    }

    /**
     * min <= keys < max
     * @param min Key min to compare
     * @param max key max to compare
     * @return All rows that satisfy criteria
     * @throws IOException If something goes wrong
     */
    public ArrayList<Pair> lowerRangeSearch(Key min, Key max) throws IOException{
        LeafNode leafNode = search(min);

        ArrayList<Pair> rows = new ArrayList<>();
        do {
            ArrayList<Pair> pairs = leafNode.getPairs();
            for (Pair p :
                    pairs) {
                if (min.compareTo(p.getKey()) <= 0 && max.compareTo(p.getKey()) > 0) {
                    rows.add(p);
                }
            }
            if (leafNode.getNextNode() > 0) {
                file.seek(leafNode.getNextNode() + 1);
                leafNode = new LeafNode(keyTypes, recordTypes, file);
            }else {
                break;
            }
        } while(true);

        return rows;
    }

    /**
     * min <= keys
     * @param min Key min to compare
     * @return All rows that satisfy criteria
     * @throws IOException If something goes wrong
     */
    public ArrayList<Pair> lowerRangeSearch(Key min) throws IOException{
        LeafNode leafNode = search(min);

        ArrayList<Pair> rows = new ArrayList<>();
        do {
            ArrayList<Pair> pairs = leafNode.getPairs();
            for (Pair p :
                    pairs) {
                if (min.compareTo(p.getKey()) <= 0) {
                    rows.add(p);
                }
            }
            if (leafNode.getNextNode() > 0) {
                file.seek(leafNode.getNextNode() + 1);
                leafNode = new LeafNode(keyTypes, recordTypes, file);
            }else {
                break;
            }
        } while(true);

        return rows;
    }


    /**
     * min < keys
     * @param min Key min to compare
     * @return All rows that satisfy criteria
     * @throws IOException If something goes wrong
     */
    public ArrayList<Pair> lowerStrictRangeSearch(Key min) throws IOException{
        LeafNode leafNode = search(min);

        ArrayList<Pair> rows = new ArrayList<>();
        do {
            ArrayList<Pair> pairs = leafNode.getPairs();
            for (Pair p :
                    pairs) {
                if (min.compareTo(p.getKey()) < 0) {
                    rows.add(p);
                }
            }
            if (leafNode.getNextNode() > 0) {
                file.seek(leafNode.getNextNode() + 1);
                leafNode = new LeafNode(keyTypes, recordTypes, file);
            }else {
                break;
            }
        } while(true);

        return rows;
    }



    /**
     *  min < keys < max
     * @param min Key min to compare
     * @param max key max to compare
     * @return All rows that satisfy criteria
     * @throws IOException If something goes wrong
     */
    public ArrayList<Pair> strictRangeSearch(Key min, Key max) throws IOException{
        LeafNode leafNode = search(min);

        ArrayList<Pair> rows = new ArrayList<>();
        do {
            ArrayList<Pair> pairs = leafNode.getPairs();
            for (Pair p :
                    pairs) {
                if (min.compareTo(p.getKey()) < 0 && max.compareTo(p.getKey()) > 0) {
                    rows.add(p);
                }
            }
            if (leafNode.getNextNode() > 0) {
                file.seek(leafNode.getNextNode() + 1);
                leafNode = new LeafNode(keyTypes, recordTypes, file);
            }else {
                break;
            }
        } while(true);

        return rows;
    }


    /**
     * Search for unique key
     * @param key key that does not exist
     * @return Leaf node to insert the key
     * @throws IOException If there is some file error
     * @throws InvalidParameterException If key already exists
     */
    private LeafNode uniqueSearch(Key key) throws IOException, InvalidParameterException{
        file.seek(root);
        LeafNode leafNode = treeSearch(key);
        for (Pair p : leafNode.getPairs()){
            if (p.getKey().compareTo(key) == 0)
                throw new InvalidParameterException("Key must be unique");
        }
        return leafNode;
    }

    /**
     * Insertion of char leaf into the B+ tree
     * @param row Table row to insert
     */
    public void insert(Key key, Tuple row) throws IOException, InvalidParameterException {

        LeafNode leafNode = uniqueSearch(key);
        boolean isFull = leafNode.getAvailableSpace()
                - (key.size() + row.size()) < 0;
        if (isFull) {
            // Split the bucket
            long parent = leafNode.getParent();
            ArrayList<Pair> pairs = leafNode.getPairs();
            Utility.sortedInsert(pairs, new Pair(key, row));
            int firstNodeSize = (pairs.size()) / 2;

            ArrayList<Pair> firstNodePairs = new ArrayList<>();

            ArrayList<Pair> secondNodePairs = new ArrayList<>();

            for (int i = 0; i < pairs.size(); i++) {
                if (i < firstNodeSize) {
                    firstNodePairs.add(pairs.get(i));
                }else {
                    secondNodePairs.add(pairs.get(i));
                }
            }




            // Write the second node
            file.seek(nextInsert);
            // Next node is a leaf
            file.writeBoolean(true);
            LeafNode nextNode = new LeafNode(blockSize - 1);
            nextNode.setHead(nextInsert + 1);
            nextNode.setPrevNode(leafNode.loc());
            nextNode.setNextNode(leafNode.getNextNode());
            nextNode.setPairs(secondNodePairs);

            // Write the first node
            leafNode.setPairs(firstNodePairs);
            leafNode.setAvailableSpace(blockSize - 1);
            leafNode.setNextNode(nextInsert);

            nextInsert += blockSize;

            splitParents(parent, secondNodePairs.get(0).getKey(),
                    leafNode, nextNode, pairs.size() - 1);


        } else {
            leafNode.add(key, row, file);
        }

    }




    /**
     * Method that inserts a key into parent nodes,
     * it splits parents if needed
     * @param parent parent pointer
     * @param key key to insert
     * @param maxElements max degree of the parents
     * @throws IOException if something goes wrong
     */
    private void splitParents(long parent, Key key, Node child1,
                              Node child2, int maxElements) throws IOException{

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

            child1.setParent(nextInsert);
            child2.setParent(nextInsert);

            nextInsert += blockSize;

        }else {
            // +1 because we already know it is a key
            file.seek(parent + 1);

            // Load parent to memory
            KeyNode keyNode = new KeyNode(keyTypes, file);

            // Check if parent needs to be splitted
            if (keyNode.getKeys().size() == maxElements) {
                ArrayList<Key> keys  = keyNode.getKeys();
                ArrayList<Long> childs = keyNode.getChilds();
                Utility.sortedInsert(keys, childs, key, child2.loc());
                int newSize = (keys.size()) / 2;

                ArrayList<Key> leftNodeKeys = new ArrayList<>();
                ArrayList<Key> rightNodeKeys = new ArrayList<>();
                ArrayList<Long> leftNodeChilds = new ArrayList<>();
                ArrayList<Long> rightNodeChilds = new ArrayList<>();
                leftNodeChilds.add(childs.get(0));
                for (int i = 0; i < keys.size(); i++) {
                    if (i < newSize) {
                        leftNodeKeys.add(keys.get(i));
                        leftNodeChilds.add(childs.get(i + 1));
                    }else if (i > newSize){
                        rightNodeKeys.add(keys.get(i));
                        rightNodeChilds.add(childs.get(i));
                    }
                }
                rightNodeChilds.add(childs.get(childs.size() - 1));
//                leftNodeChilds.add(childs.get(childs.size() - 2));
//
//                rightNodeChilds.add(child2.loc());
                keyNode.setKeys(leftNodeKeys);
                keyNode.setChilds(keyTypes, recordTypes, leftNodeChilds, file);
                keyNode.setAvailableSpace(blockSize - 1);


                file.seek(nextInsert);
                file.writeBoolean(false);
                KeyNode newKeyNode = new KeyNode(blockSize - 1);
                newKeyNode.setHead(nextInsert + 1);

//                if (leftNodeChilds.)

                if (rightNodeChilds.contains(child1.loc())){
                    child1.setParent(newKeyNode.loc());
                }

                if (rightNodeChilds.contains(child2.loc())) {
                    child2.setParent(newKeyNode.loc());
                }else {
                    child2.setParent(keyNode.loc());
                }

                newKeyNode.setKeys(rightNodeKeys);
                newKeyNode.setChilds(keyTypes, recordTypes, rightNodeChilds, file);
                nextInsert += blockSize;


                splitParents(keyNode.getParent(), keys.get(newSize), keyNode,
                        newKeyNode, maxElements);


            }else {
                // Simply insert if not
                child1.setParent(parent);
                child2.setParent(parent);
                keyNode.add(key, child2.loc(), file);
            }
        }

        // Write first node
        file.seek(child1.loc() + 1);
        child1.writeToFile(file);

        file.seek(child2.loc() + 1);

        child2.writeToFile(file);
    }


    /**
     * Method that updates an existing tuple
     * @param key key to update
     * @param tuple tuple to set
     * @return Affected tuple count
     * @throws IOException if something goes bad
     */
    public int updateTuple(Key key, Tuple tuple) throws IOException {
        LeafNode leafNode = search(key);
        return leafNode.update(key, tuple, file);
    }

    /**
     * Method that updates tuples in a range
     * min <= k <= max
     * @param min min key
     * @param max max key
     * @param tuple tuple to enter
     * @return Affected tuple count
     * @throws IOException if something goes wrong
     */
    public int updateRange(Key min, Key max, Tuple tuple) throws IOException {
        int affectedTuples = 0;

        LeafNode leafNode = search(min);

        do {
            leafNode.rangeUpdate(min, max, tuple, file);
            if (leafNode.getNextNode() > 0) {
                file.seek(leafNode.getNextNode() + 1);
                leafNode = new LeafNode(keyTypes, recordTypes, file);
            }else {
                break;
            }
        } while(true);


        return affectedTuples;
    }


    /**
     * Method that updates tuples in a range
     * min < k < max
     * @param min min key
     * @param max max key
     * @param tuple tuple to enter
     * @return Affected tuple count
     * @throws IOException if something goes wrong
     */
    public int strictRangeUpdate(Key min, Key max, Tuple tuple) throws IOException {
        int affectedTuples = 0;

        LeafNode leafNode = search(min);

        do {
            leafNode.strictRangeUpdate(min, max, tuple, file);
            if (leafNode.getNextNode() > 0) {
                file.seek(leafNode.getNextNode() + 1);
                leafNode = new LeafNode(keyTypes, recordTypes, file);
            }else {
                break;
            }
        } while(true);


        return affectedTuples;
    }



    /**
     * Method that updates tuples in a range
     * min <= k < max
     * @param min min key
     * @param max max key
     * @param tuple tuple to enter
     * @return Affected tuple count
     * @throws IOException if something goes wrong
     */
    public int lowerRangeUpdate(Key min, Key max, Tuple tuple) throws IOException {
        int affectedTuples = 0;

        LeafNode leafNode = search(min);

        do {
            leafNode.lowerRangeUpdate(min, max, tuple, file);
            if (leafNode.getNextNode() > 0) {
                file.seek(leafNode.getNextNode() + 1);
                leafNode = new LeafNode(keyTypes, recordTypes, file);
            }else {
                break;
            }
        } while(true);


        return affectedTuples;
    }


    /**
     * Method that updates tuples in a range
     * min <= k
     * @param min min key
     * @param tuple tuple to enter
     * @return Affected tuple count
     * @throws IOException if something goes wrong
     */
    public int lowerRangeUpdate(Key min, Tuple tuple) throws IOException {
        int affectedTuples = 0;

        LeafNode leafNode = search(min);

        do {
            leafNode.lowerRangeUpdate(min, tuple, file);
            if (leafNode.getNextNode() > 0) {
                file.seek(leafNode.getNextNode() + 1);
                leafNode = new LeafNode(keyTypes, recordTypes, file);
            }else {
                break;
            }
        } while(true);


        return affectedTuples;
    }


    /**
     * Method that updates tuples in a range
     * min < k
     * @param min min key
     * @param tuple tuple to enter
     * @return Affected tuple count
     * @throws IOException if something goes wrong
     */
    public int lowerStrictRangeUpdate(Key min, Tuple tuple) throws IOException {
        int affectedTuples = 0;

        LeafNode leafNode = search(min);

        do {
            leafNode.lowerStrictRangeUpdate(min, tuple, file);
            if (leafNode.getNextNode() > 0) {
                file.seek(leafNode.getNextNode() + 1);
                leafNode = new LeafNode(keyTypes, recordTypes, file);
            }else {
                break;
            }
        } while(true);


        return affectedTuples;
    }


    /**
     * Method that updates tuples in a range
     * min < k <= max
     * @param min min key
     * @param max max key
     * @param tuple tuple to enter
     * @return Affected tuple count
     * @throws IOException if something goes wrong
     */
    public int upperRangeUpdate(Key min, Key max, Tuple tuple) throws IOException {
        int affectedTuples = 0;

        LeafNode leafNode = search(min);

        do {
            leafNode.upperRangeUpdate(min, max, tuple, file);
            if (leafNode.getNextNode() > 0) {
                file.seek(leafNode.getNextNode() + 1);
                leafNode = new LeafNode(keyTypes, recordTypes, file);
            }else {
                break;
            }
        } while(true);


        return affectedTuples;
    }


    /**
     * Method that updates tuples in a range
     * k <= max
     * @param max max key
     * @param tuple tuple to enter
     * @return Affected tuple count
     * @throws IOException if something goes wrong
     */
    public int upperRangeUpdate(Key max, Tuple tuple) throws IOException {
        int affectedTuples = 0;

        LeafNode leafNode = search(max);

        do {
            leafNode.upperRangeUpdate(max, tuple, file);
            if (leafNode.getNextNode() > 0) {
                file.seek(leafNode.getNextNode() + 1);
                leafNode = new LeafNode(keyTypes, recordTypes, file);
            }else {
                break;
            }
        } while(true);


        return affectedTuples;
    }


    /**
     * Method that updates tuples in a range
     * k < max
     * @param max max key
     * @param tuple tuple to enter
     * @return Affected tuple count
     * @throws IOException if something goes wrong
     */
    public int upperStrictRangeUpdate(Key max, Tuple tuple) throws IOException {
        int affectedTuples = 0;

        LeafNode leafNode = search(max);

        do {
            leafNode.upperStrictRangeUpdate(max, tuple, file);
            if (leafNode.getNextNode() > 0) {
                file.seek(leafNode.getNextNode() + 1);
                leafNode = new LeafNode(keyTypes, recordTypes, file);
            }else {
                break;
            }
        } while(true);


        return affectedTuples;
    }


//    /**
//     * Deletes tuple from the b+ tree
//     * @param key key to remove
//     */
//    public void delete(Key key) throws IOException{
//        LeafNode leafNode = search(key);
//
//        if (leafNode.getPairs().isEmpty())
//            return;
//
//        long individual = leafNode.getPairs().get(0).size();;
//
//        // Minimum amount of elements must be at least half
//        // of the node capacity
//        long minimumElements = (blockSize - 1) /  (2 * individual);
//
//        // If an element was not removed
//        int removedIndex = leafNode.removeEntry(key, file);
//        if (removedIndex == -1) {
//            return;
//        }
//
//
//
//
//        // If we reached here, we must balance.
//        long parentLoc = leafNode.getParent() + 1;
//        if (parentLoc != -1) {
//            file.seek(parentLoc);
//            // Load to RAM the parent node
//            KeyNode keyNode = new KeyNode(keyTypes, file);
//            ArrayList<Key> keys = keyNode.getKeys();
//            ArrayList<Long> childs = keyNode.getChilds();
//
//
//            if(removedIndex == 0) {
//                int i = keys.indexOf(key);
//                if (i != -1){
//                    Pair newKey = leafNode.getPairs().get(0);
//                    keyNode.replace(i, newKey.getKey(), file);
//                }
//            }
//
//            // Nothing to balance, we're done here
//            if (leafNode.getPairs().size() >= minimumElements) {
//                return;
//            }
//            // Check adjacent nodes for help
//            if (leafNode.getPrevNode() > 0) {
//                file.seek(leafNode.getPrevNode() + 1);
//                LeafNode sibling = new LeafNode(keyTypes, recordTypes, file);
//                if (sibling.getPairs().size() > minimumElements) {
//                    ArrayList<Pair> pairs = sibling.getPairs();
//                    Pair p = sibling.remove(pairs.size() - 1, file);
//                    leafNode.add(p, file);
//                    int keyIndex = keys.indexOf(p.getKey());
//                    keyNode.replace(keyIndex, p.getKey(), file);
//                }else {
//                    // Merge
//                    mergeNodes(leafNode, sibling);
//
//                }
//            } else if (leafNode.getNextNode() > 0) {
//
//            }
//
//        }
//
//    }

    /**
     * Delete
     * @param tupleToEliminate tuple to eliminate
     */
    public void delete(ArrayList<Pair> tupleToEliminate) throws IOException{
        ArrayList<Pair> pairs = all();
        ArrayList<Pair> merged = new ArrayList<>();

        for (Pair p : pairs) {
            if (!tupleToEliminate.contains(p)){
                merged.add(p);
            }
        }

        file.seek(0);
        file.writeInt(blockSize);
        file.writeInt(0);

        file.writeInt(keyTypes.size());
        for (Type t : keyTypes) {
            file.writeInt(t.val());
        }

        file.writeInt(recordTypes.size());
        for (Type t : recordTypes) {
            file.writeInt(t.val());
        }

        root = blockSize;
        file.writeLong(root);
        file.writeLong(root + blockSize);
        nextInsert = root + blockSize;
        file.seek(root);

        file.writeBoolean(true);


        (new LeafNode(blockSize - 1)).writeToFile(file);

        for (Pair p : merged) {
            insert(p.getKey(), p.getTuple());
        }


    }

    /**
     * Method that separates and inserts
     * @param t tuple to seprate
     * @param keySize key size
     * @throws IOException If something goes bad
     */
    public void insert(Tuple t, int keySize) throws IOException{
        Key key = new Key();
        Tuple tt = new Tuple();
        for (int i = 0; i < t.getRecords().size(); i++) {
            if (i < keySize)  {
                key.add(t.getRecords().get(i));
            }else {
                tt.add(t.getRecords().get(i));
            }
        }
        insert(key, tt);

    }


    /**
     * Method to redistribute  the tree after delete
     * @param leafNode leafNode that's getting an element removed
     * @param keyNode parent of the node
     * @param pos position of the element to replace
     * @param minimumElements minimum elements of the tree
     * @throws IOException if something goes bad
     */
    private void balance(LeafNode leafNode, KeyNode keyNode,
                         int pos, long minimumElements, boolean isRight) throws IOException{

        LeafNode sibling = new LeafNode(keyTypes, recordTypes, file);
        int index = (isRight) ? 0 : sibling.getPairs().size();
        if (sibling.getPairs().size() > minimumElements) {
            Pair p = sibling.remove(index, file);
            leafNode.add(p, file);
            Key key = sibling.getPairs().get(index).getKey();
            keyNode.replace(pos, key, file);
        }else {
            // Merge the nodes
            if (isRight) {
                mergeNodes(leafNode, sibling);
                keyNode.getChilds().remove(leafNode.loc());
                balance(keyNode, minimumElements);
            } else {
                mergeNodes(sibling, leafNode);
            }
        }
    }


    private void balance(KeyNode keyNode, long minChilds) throws IOException {
        // We must repair the node
        if (keyNode.getChilds().size() < minChilds && keyNode.getParent() != -1) {
            file.seek(keyNode.getParent() + 1);
            KeyNode parent = new KeyNode(keyTypes, file);
            int index = parent.getChilds().indexOf(keyNode.loc());
            boolean isLeft = index - 1 >= 0;
            long siblingLoc = (isLeft) ? parent.getChilds().get(index - 1)
                    : parent.getChilds().get(index + 1);
            file.seek(siblingLoc + 1);
            KeyNode sibling = new KeyNode(keyTypes, file);
            if (sibling.getChilds().size() > minChilds) {
                // Steal one child
                ArrayList<Key> keys = sibling.getKeys();
                ArrayList<Long> childs = sibling.getChilds();
                long childToSteal = (isLeft) ? childs.get(childs.size() - 1)
                        : childs.get(0);
                childs.remove(childToSteal);
                Key keyToSteal = (isLeft) ? keys.get(keys.size() -1) : keys.get(0);
                keyNode.add(keyToSteal, childToSteal, file);
                keyNode.setChilds(keyTypes, recordTypes, keyNode.getChilds(), file);
                sibling.setChilds(keyTypes, recordTypes, sibling.getChilds(), file);

            }else {
                // Merge keynodes
            }
        }

    }

    /**
     * Merges two siblings
     * @param sibling1 least sibling
     * @param sibling2 max sibling
     * @throws IOException if something goes wrong
     */
    private void mergeNodes(LeafNode sibling1, LeafNode sibling2) throws IOException {
        ArrayList<Pair> pairs = sibling1.getPairs();
        while(!pairs.isEmpty()) {
            Pair p = sibling1.remove(0, file);
            sibling2.add(p, file);
        }
    }







    /**
     * Method that prints the tree to the console
     */
    public void dump() throws IOException{
        System.out.println("===== TREE DUMP =====");
        System.out.println("Blocksize: " + this.blockSize);
        System.out.println("Root location: " + this.root);
        System.out.print("Primary key types: ");
        for (Type t : keyTypes) {
            switch (t) {
                case INT: System.out.print(" INT");break;
                case FLOAT: System.out.print(" FLOAT");break;
                case CHARS: System.out.print(" CHAR[]");break;
                case DATE: System.out.print(" DATE");break;
            }

        }
        System.out.println();
        System.out.print("Row columns types: ");
        for (Type t : recordTypes) {
            switch (t) {
                case INT: System.out.print(" INT");break;
                case FLOAT: System.out.print(" FLOAT");break;
                case CHARS: System.out.print(" CHAR[]");break;
                case DATE: System.out.print(" DATE");break;
            }

        }
        System.out.println("\nNodes:\n");
        file.seek(root);
        if (file.readBoolean()) {
            LeafNode node = new LeafNode(keyTypes, recordTypes, file);
            node.dump("\t\t");
        } else {
            KeyNode node = new KeyNode(keyTypes, file);
            node.dump("\t\t", keyTypes, recordTypes, file);
        }
        System.out.println("=====================");
    }









}
