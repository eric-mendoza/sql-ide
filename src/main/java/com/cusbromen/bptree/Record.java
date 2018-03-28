package com.cusbromen.bptree;

import java.io.RandomAccessFile;

public interface Record<T> extends Comparable<T>{


    /**
     * Get the record that represents the primary key
     * Must implement comparable
     * @return Record that represents comparable
     */
    Record getPrimaryKey();

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
}
