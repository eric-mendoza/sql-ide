package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;

public class IntRecord implements Record {

    private Integer val;

    public IntRecord(int val) {
        this.val = val;
    }

    IntRecord(RandomAccessFile file) throws IOException{
        readFromFile(file);
    }


    @Override
    public int compareTo(Record o) {
        IntRecord rec = (IntRecord) o;
        return val.compareTo(rec.val);
    }


    @Override
    public void writeToFile(RandomAccessFile file) throws IOException {
        file.writeInt(val);
    }

    @Override
    public void readFromFile(RandomAccessFile file) throws IOException {
        val = file.readInt();
    }

    @Override
    public long size() {
        return 4;
    }
}
