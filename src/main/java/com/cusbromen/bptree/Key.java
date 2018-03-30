package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Key extends Tuple implements Comparable<Key>{

    public Key(){

    }

    public Key(ArrayList<Type> types, RandomAccessFile file) throws IOException{
        super(types, file);
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
