package com.cusbromen.bptree;

import java.io.RandomAccessFile;

public class IntRecord implements Record<IntRecord> {

    @Override
    public int compareTo(IntRecord o) {
        Record t = new CharRecord();
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
