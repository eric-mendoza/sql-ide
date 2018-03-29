package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Key implements Comparable<Key>{

    private ArrayList<Record> records;

    public Key() {
        records = new ArrayList<>();
    }

    public void writeToFile(RandomAccessFile file) throws IOException{
        for (Record<?> rec : records) {
            rec.writeToFile(file);
        }
    }

    public void readFromFile(ArrayList<Type> types, RandomAccessFile file) throws IOException{
        for (Type type : types) {
            switch (type) {
                case INT: {
                    records.add(new IntRecord(file));
                    break;
                }
                case FLOAT:{
                    records.add(new FloatRecord(file));
                    break;
                }
                case CHARS: {
                    records.add(new CharRecord(file));
                    break;
                }
                case DATE: {
                    records.add(new DateRecord(file));
                    break;
                }
            }
        }
    }

    @Override
    public int compareTo(Key o) {
        for (int i = 0; i < records.size(); i++) {
            int cmp = records.get(i).compareTo(o.records.get(i));
            if (cmp != 0)
                return cmp;
        }
        return 0;
    }
}
