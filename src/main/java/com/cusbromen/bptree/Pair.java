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

    public Pair(Key k, Tuple t) {
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

    /**
     * This is a setter that modifies tuple records
     * if they are not nullt
     * @param t Tuple with the values to modify
     */
    public void updateTuple(Tuple t, RandomAccessFile file) throws IOException{
        ArrayList<Record> records = tuple.getRecords();
        ArrayList<Record> recordsToInsert = t.getRecords();
        for (int i = 0; i < records.size(); i++) {
            if (!recordsToInsert.get(i).isNull()){
                records.set(i, recordsToInsert.get(i));
            }
        }
        tuple.writeToFile(file);
    }

    @Override
    public boolean equals(Object obj) {
        Pair p = (Pair) obj;
        return this.compareTo(p) == 0;
    }

    @Override
    public int compareTo(Pair o) {
        return key.compareTo(o.key);
    }
}
