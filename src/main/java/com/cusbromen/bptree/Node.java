package com.cusbromen.bptree;

public abstract class Node {
    // Free bytes to write
    protected long availableSpace;


    public long getAvailableSpace() {
        return availableSpace;
    }
}
