package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;

public class CharRecord implements Record {

    private boolean isNull;
    private char[] val;

    public CharRecord() {
        val = new char[0];
        isNull = true;
    }


    public CharRecord(char[] val) {
        this.val = val;
        isNull = false;
    }

    CharRecord(RandomAccessFile file) throws IOException{
        readFromFile(file);
    }


    public char[] getVal() {return val;}

    @Override
    public int compareTo(Record o) {
        CharRecord rec = (CharRecord) o;
        for (int i = 0; i < val.length && i < rec.val.length; ++i){
            if (val[i] < rec.val[i])
                return -1;
            if (val[i] > rec.val[i])
                return 1;
        }
        if (val.length < rec.val.length)
            return -1;
        else if(val.length > rec.val.length)
            return 1;
        return 0;
    }

    @Override
    public void writeToFile(RandomAccessFile file) throws IOException{
        file.writeInt(val.length);
        file.writeChars(new String(val));
        file.writeBoolean(isNull);
    }

    @Override
    public void readFromFile(RandomAccessFile file) throws IOException{
        int size = file.readInt();
        val = new char[size];
        for (int i = 0; i < size; i++) {
            val[i] = file.readChar();
        }
        isNull = file.readBoolean();
    }

    @Override
    public long size() {
        return 2 * val.length + 5;
    }


    @Override
    public boolean isNull() {
        return isNull;
    }

    @Override
    public String getStringVal() {
        return String.valueOf(getVal());
    }
}
