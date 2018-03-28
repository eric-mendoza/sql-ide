package com.cusbromen.bptree;

import java.io.RandomAccessFile;

public class CharRecord implements Record<CharRecord> {

    @Override
    public int compareTo(CharRecord o) {
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
