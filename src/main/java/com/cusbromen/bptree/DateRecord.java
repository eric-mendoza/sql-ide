package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

public class DateRecord implements Record {


    private Date val;

    public DateRecord(Date val) {
        this.val = val;
    }

    DateRecord(RandomAccessFile file) throws IOException {
        readFromFile(file);
    }

    @Override
    public int compareTo(Record o) {
        DateRecord rec = (DateRecord) o;
        return val.compareTo(rec.val);
    }


    @Override
    public void writeToFile(RandomAccessFile file) throws IOException{
        file.writeLong(val.getTime());
    }

    @Override
    public void readFromFile(RandomAccessFile file) throws IOException{
        val = new Date(file.readLong());
    }
}
