package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

public class DateRecord implements Record {

    private boolean isNull;
    private Date val;

    public DateRecord() {
        val = new Date();
        isNull = true;
    }


    public DateRecord(Date val) {
        this.val = val;
        isNull = false;
    }


    DateRecord(RandomAccessFile file) throws IOException {
        readFromFile(file);
    }

    public Date getVal() {
        return val;
    }

    @Override
    public int compareTo(Record o) {
        DateRecord rec = (DateRecord) o;
        return val.compareTo(rec.val);
    }


    @Override
    public void writeToFile(RandomAccessFile file) throws IOException{
        file.writeLong(val.getTime());
        file.writeBoolean(isNull);
    }

    @Override
    public void readFromFile(RandomAccessFile file) throws IOException{
        val = new Date(file.readLong());
        isNull = file.readBoolean();
    }

    @Override
    public long size() {
        return 9;
    }

    @Override
    public boolean isNull() {
        return isNull;
    }
}
