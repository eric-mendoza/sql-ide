package com.cusbromen.bptree;

import java.io.RandomAccessFile;
import java.util.ArrayList;

public class PrimaryKey implements Record<PrimaryKey, ArrayList<Record<?, ?>>>{

    private ArrayList<Record<?, ?>> keys;

    public PrimaryKey(Record<?, ?> singleKey) {
        keys.add(singleKey);
    }

    public PrimaryKey(ArrayList<Record<?, ?>> keys) {
        this.keys = keys;
    }

    public PrimaryKey() {

    }

    @Override
    public ArrayList<Record<?, ?>> getValue() {
        return keys;
    }

    @Override
    public int compareTo(PrimaryKey o) {
        return 0;
    }


    @Override
    public void writeToFile(RandomAccessFile file) {

    }

    @Override
    public void readFromFile(RandomAccessFile file) {

    }
}