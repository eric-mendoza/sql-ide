package com.cusbromen.bptree;

public abstract class Node {

    protected long parent;
    long head;
    long back;

    // Free bytes to write
    long availableSpace;


    /**
     * Gets available space
     * @return available space in bytes
     */
    public long getAvailableSpace() {
        return availableSpace;
    }

    /**
     * Gets a pointer to the node
     * @return pointer to the node
     */
    public long loc() {
        return head - 1;
    }

    /**
     * Get parent
     * @return pointer to the parent node
     */
    public long getParent() {
        return parent;
    }

    public void setParent(long parent) {
        this.parent = parent;
    }

    public void setHead(long head) {
        this.head = head;
    }

    public void setAvailableSpace(long availableSpace) {
        this.availableSpace = availableSpace;
    }
}
