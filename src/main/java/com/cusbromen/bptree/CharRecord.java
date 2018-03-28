package com.cusbromen.bptree;

import java.io.RandomAccessFile;

public class CharRecord implements Record<CharRecord, char[]> {

    private char[] array;

    @Override
    public int compareTo(CharRecord o) {

        return 0;
    }

    @Override
    public char[] getValue() {
        return new char[0];
    }

    @Override
    public void writeToFile(RandomAccessFile file) {

    }

    @Override
    public void readFromFile(RandomAccessFile file) {

    }
}
