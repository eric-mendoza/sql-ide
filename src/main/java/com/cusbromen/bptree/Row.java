/**
 * @author Rodrigo Custodio
 * Description: Class that represents a Row of the table
 */
package com.cusbromen.bptree;

import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Row {
    private ArrayList<Record<?>> records;


    public Row() {
        records = new ArrayList<>();
    }

    /**
     * Writes the records to the current block in the RandomAccessFile
     * @param file File to write
     */
    public void writeToFile(RandomAccessFile file) {
        for (Record<?> record :
                records) {
            record.writeToFile(file);
        }
    }

    /**
     * Adds record to the row
     * @param record Record to add
     */
    public void add(Record<?> record) {
        records.add(record);
    }
}
