package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;

public class CharRecord implements Record<CharRecord> {

    private String val;


    public CharRecord() {

    }

    CharRecord(RandomAccessFile file) throws IOException{
        readFromFile(file);
    }

    @Override
    public int compareTo(CharRecord o) {
        return val.compareTo(o.val);
    }

    @Override
    public void writeToFile(RandomAccessFile file) throws IOException{
        file.writeUTF(val);
    }

    @Override
    public void readFromFile(RandomAccessFile file) throws IOException{
        val = file.readUTF();
    }
}
