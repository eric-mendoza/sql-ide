package com.cusbromen.bptree;

import java.io.RandomAccessFile;

public class FloatRecord implements Record<FloatRecord> {

    @Override
    public int compareTo(FloatRecord o) {
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
