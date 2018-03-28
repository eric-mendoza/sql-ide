package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FloatRecord implements Record<FloatRecord> {


    private Double val;

    public FloatRecord() {

    }

    FloatRecord(RandomAccessFile file) throws IOException{
        readFromFile(file);
    }


    @Override
    public int compareTo(FloatRecord o) {
        return val.compareTo(o.val);
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
