package com.cusbromen.bptree;

import java.io.RandomAccessFile;

public interface Record<T, V> extends Comparable<T>{


    /**
     * Writes the record to the current memory address
     * @param file File to write in
     */
    void writeToFile(RandomAccessFile file);

    /**
     * Populates the record with the values in the memory address
     * @param file File to read from
     */
    void readFromFile(RandomAccessFile file);

    /**
     * Gets the value stored in the record
     * @return Value stored
     */
    V getValue();
}
