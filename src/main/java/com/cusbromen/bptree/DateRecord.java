package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

public class DateRecord implements Record<DateRecord> {


    private Date val;

    public DateRecord() {

    }

    DateRecord(RandomAccessFile file) throws IOException {
        readFromFile(file);
    }

    @Override
    public int compareTo(DateRecord o) {
        return val.compareTo(o.val);
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
