package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;

public class IntRecord implements Record {

    private Integer val;
    private boolean isNull;


    public IntRecord() {
        isNull = true;
    }

    public IntRecord(int val) {
        this.val = val;
        isNull = false;
    }

    IntRecord(RandomAccessFile file) throws IOException{
        readFromFile(file);
    }

    public Integer getVal() {
        return val;
    }

    @Override
    public int compareTo(Record o) {
        IntRecord rec = (IntRecord) o;
        return val.compareTo(rec.val);
    }


    @Override
    public void writeToFile(RandomAccessFile file) throws IOException {
        file.writeInt(val);
        file.writeBoolean(isNull);
    }

    @Override
    public void readFromFile(RandomAccessFile file) throws IOException {
        val = file.readInt();
        isNull = file.readBoolean();
    }

    @Override
    public long size() {
        return 5;
    }

    @Override
    public boolean isNull() {
        return isNull;
    }
}
