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
    private ArrayList<Type> types;
    private int degree;
    private int root;


    /**
     * Open a file that already exists, and extract tree
     * characteristics from header
     * @param fileName name of the file
     */
    public BpTree(String fileName) {
        File f = new File(fileName);
        if (f.exists()) {

        }
    }


    /**
     * Constructor with Random AccessFile
     * creates the header in the file
     * @param file File where B+ tree is stored
     * @param degree Degree of the BpTree, it must match
     *               the block size for efficient storage
     */
    public BpTree(RandomAccessFile file, int degree) throws IOException{
        this.file = file;
        this.degree = degree;
    }

    /**
     * Constructor to make a Random Access File
     * from file name, creates the file and
     * inserts the tree header
     * @param fileName File name
     */
    public BpTree(String fileName, int degree) throws IOException {
        this.degree = degree;
        file = new RandomAccessFile(fileName, "rw");

        // Write the header of the tree
        // Header length is: 8 bytes
        file.writeInt(degree);
        file.seek(4);
        file.writeInt(root);

    }

    /**
     * Insertion of char leaf into the B+ tree
     * @param val Table row to insert
     */
    public void insert(Key key, Tuple val) {

    }



}
