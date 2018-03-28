package com.cusbromen.bptree;

import java.io.RandomAccessFile;

public class CharRecord implements Record<CharRecord, char[]> {

    private char[] val;

    @Override
    public int compareTo(CharRecord o) {
//        for (int i = 0; i < ; i++) {
//
//        }
        return 0;
    }

    @Override
    public char[] getValue() {
        return val;
    }

    @Override
    public void writeToFile(RandomAccessFile file) {

    }

    @Override
    public void readFromFile(RandomAccessFile file) {

    }
}
