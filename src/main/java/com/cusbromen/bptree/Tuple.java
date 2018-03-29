package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Tuple {
    protected ArrayList<Record> records;

    public Tuple() {
        records = new ArrayList<>();
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

    public void readFromFile(ArrayList<Type> types, RandomAccessFile file) throws IOException{
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
