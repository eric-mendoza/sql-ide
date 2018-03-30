package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Tuple {
    protected ArrayList<Record> records;

    /**
     * Default constructor
     */
    public Tuple() {
        records = new ArrayList<>();
    }


    public Tuple(ArrayList<Type> types, RandomAccessFile file) throws IOException{
        readFromFile(types, file);
    }

    /**
     * Writes the records to the current block in the RandomAccessFile
     * @param file File to write
     */
    public void writeToFile(RandomAccessFile file) throws IOException {
        for (Record record :
                records) {
            record.writeToFile(file);
        }
    }

    /**
     * Method to read the tuple from file
     * @param types types of columns
     * @param file file to read from
     * @throws IOException if there is some problem with file
     */
    public void readFromFile(ArrayList<Type> types, RandomAccessFile file) throws IOException{
        records = new ArrayList<>();
        for (Type type : types) {
            switch (type) {
                case INT: {
                    records.add(new IntRecord(file));
                    break;
                }
                case FLOAT:{
                    records.add(new FloatRecord(file));
                    break;
                }
                case CHARS: {
                    records.add(new CharRecord(file));
                    break;
                }
                case DATE: {
                    records.add(new DateRecord(file));
                    break;
                }
            }
        }
    }

    /**
     * Adds record to the row
     * @param record Record to add
     */
    public void add(Record record) {
        records.add(record);
    }
}
