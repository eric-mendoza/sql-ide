package com.cusbromen.bptree;

import java.io.RandomAccessFile;

public class IntRecord implements Record<IntRecord, Integer> {

    @Override
    public int compareTo(IntRecord o) {
        return 0;
    }

    @Override
    public Integer getValue() {
        return 3;
    }

    @Override
    public void writeToFile(RandomAccessFile file) {

    }

    @Override
    public void readFromFile(RandomAccessFile file) {

    }
}
