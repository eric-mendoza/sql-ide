package com.cusbromen.bptree;

import java.io.RandomAccessFile;
import java.util.ArrayList;

public class PrimaryKey implements Record{

    private ArrayList<Record> keys;

    public PrimaryKey(Record singleKey) {
        keys.add(singleKey);
    }

    public PrimaryKey(ArrayList<Record> keys) {
        this.keys = keys;
    }

    @Override
    public int compareTo(Record o) {
        return 0;
    }

    @Override
    public Record getPrimaryKey() {
        return null;
    }

    @Override
    public void writeToFile(RandomAccessFile file) {

    }

    @Override
    public void readFromFile(RandomAccessFile file) {

    }
}
