package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

public class Tuple {
    private long size;
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
                    IntRecord rec = new IntRecord(file);
                    records.add(rec);
                    size += rec.size();
                    break;
                }
                case FLOAT:{
                    FloatRecord rec = new FloatRecord(file);
                    records.add(rec);
                    size += rec.size();
                    break;
                }
                case CHARS: {
                    CharRecord rec = new CharRecord(file);
                    records.add(rec);
                    size += rec.size();
                    break;
                }
                case DATE: {
                    DateRecord rec = new DateRecord(file);
                    records.add(rec);
                    size += rec.size();
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
        size += record.size();
    }

    public void setRecord(int i, Record record){
        records.remove(i);
        records.add(record);
    }


    /**
     * Get column as int
     * @param columnIndex  index of the column to extract
     * @return column as int
     */
    public int getInt(int columnIndex) {
        return ((IntRecord) records.get(columnIndex)).getVal();
    }

    /**
     * Get column as char
     * @param columnIndex  index of the column to extract
     * @return column as char array
     */
    public char[] getChars(int columnIndex) {
        return ((CharRecord) records.get(columnIndex)).getVal();
    }



    /**
     * Get column as  date
     * @param columnIndex  index of the column to extract
     * @return column as date record
     */
    public Date getDate(int columnIndex) {
        return ((DateRecord) records.get(columnIndex)).getVal();
    }


    /**
     * Get column as float
     * @param columnIndex  index of the column to extract
     * @return column as  double
     */
    public double getFloat(int columnIndex) {
        return ((FloatRecord) records.get(columnIndex)).getVal();
    }


    public ArrayList<Record> getRecords() {
        return records;
    }

    public long size() {
        return size;
    }
}
