package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FloatRecord implements Record {


    private boolean isNull;
    private Double val;

    public FloatRecord() {
        val = -2147483647.0;
        isNull = true;
    }

    public FloatRecord(Double val) {
        this.val = val;
        isNull = false;
    }

    FloatRecord(RandomAccessFile file) throws IOException{
        readFromFile(file);
    }

    public Double getVal() {
        return val;
    }

    @Override
    public int compareTo(Record o) {
        FloatRecord rec = (FloatRecord) o;
        return val.compareTo(rec.val);
    }

    @Override
    public void writeToFile(RandomAccessFile file) throws IOException {
        file.writeDouble(val);
        file.writeBoolean(isNull);
    }

    @Override
    public void readFromFile(RandomAccessFile file) throws IOException {
        val = file.readDouble();
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

    public String getStringVal() {
        return String.valueOf(getVal());
    }
}
