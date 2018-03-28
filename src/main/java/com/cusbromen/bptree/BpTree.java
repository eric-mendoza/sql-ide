/**
 * @author Rodrigo Custodio
 * Description: B+ tree implementation,
 * tree is stored and managed on disk memory.
 */
package com.cusbromen.bptree;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class BpTree {

    private RandomAccessFile file;
    private ArrayList<Type> types;
    private int degree;

    /**
     * Constructor with Random AccessFile
     * @param file File where B+ tree is stored
     */
    public BpTree(RandomAccessFile file, int degree) {
        this.file = file;
        this.degree = degree;
    }

    /**
     * Constructor to make a Random Access File from file name
     * @param fileName File name
     */
    public BpTree(String fileName, int degree) throws FileNotFoundException{
        this.degree = degree;
        file = new RandomAccessFile(fileName, "rw");
    }

    /**
     * Insertion of char leaf into the B+ tree
     * @param val Value to insert
     */
    public void insert(Key key, Row val) {

    }



}
