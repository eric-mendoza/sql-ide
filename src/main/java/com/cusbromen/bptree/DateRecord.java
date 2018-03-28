package com.cusbromen.bptree;

import java.io.RandomAccessFile;

public class DateRecord implements Record<DateRecord, String> {


    @Override
    public int compareTo(DateRecord o) {
        return 0;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public void writeToFile(RandomAccessFile file) {

    }

    @Override
    public void readFromFile(RandomAccessFile file) {

    }
}
