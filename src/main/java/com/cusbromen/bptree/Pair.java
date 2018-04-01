package com.cusbromen.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Pair implements Comparable<Pair>{

    private Key key;
    private Tuple tuple;

    Pair() {
        key = new Key();
        tuple = new Tuple();
    }

    Pair(Key k, Tuple t) {
        key = k;
        tuple = t;
    }

    public void writeToFile(RandomAccessFile file) throws IOException {
        key.writeToFile(file);
        tuple.writeToFile(file);
    }


    public void readFromFile(ArrayList<Type> keyTypes, ArrayList<Type> recordTypes,
                             RandomAccessFile file) throws IOException {
        key.readFromFile(keyTypes, file);
        tuple.readFromFile(recordTypes, file);
    }

    /**
     * Method that joins a Key with a Tuple
     * @return joined tuple
     */
    public Tuple getCombined() {
        Tuple temp = new Tuple();
        for (Record r :
                key.getRecords()) {
            temp.getRecords().add(r);
        }
        for (Record r : tuple.getRecords()) {
            temp.getRecords().add(r);
        }
        return temp;
    }


    public long size() {return key.size() + tuple.size();}

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Tuple getTuple() {
        return tuple;
    }

    public void setTuple(Tuple tuple) {
        this.tuple = tuple;
    }

    @Override
    public int compareTo(Pair o) {
        return key.compareTo(o.key);
    }
}
