package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;

public interface Record extends Comparable<Record>{


    /**
     * Writes the record to the current memory address
     * @param file File to write in
     */
    void writeToFile(RandomAccessFile file) throws IOException;

    /**
     * Populates the record with the values in the memory address
     * @param file File to read from
     */
    void readFromFile(RandomAccessFile file) throws IOException;


}
