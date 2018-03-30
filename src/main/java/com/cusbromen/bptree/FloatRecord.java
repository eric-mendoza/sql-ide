package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FloatRecord implements Record {


    private Double val;

    public FloatRecord(Double val) {
        this.val = val;
    }

    FloatRecord(RandomAccessFile file) throws IOException{
        readFromFile(file);
    }


    @Override
    public int compareTo(Record o) {
        FloatRecord rec = (FloatRecord) o;
        return val.compareTo(rec.val);
    }

    @Override
    public void writeToFile(RandomAccessFile file) throws IOException {
        file.writeDouble(val);
    }

    @Override
    public void readFromFile(RandomAccessFile file) throws IOException {
        val = file.readDouble();
    }
}
