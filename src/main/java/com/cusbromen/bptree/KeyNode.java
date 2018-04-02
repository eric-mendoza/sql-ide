package com.cusbromen.bptree;

import org.atmosphere.cpr.Action;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class KeyNode extends Node {


    private ArrayList<Key> keys;
    private ArrayList<Long> childs;


    /**
     * Empty key node
     */
    public KeyNode(long freeBytes) {
        keys = new ArrayList<>();
        childs = new ArrayList<>();
        parent = -1;
        availableSpace = freeBytes;
    }


    /**
     * Constructor to restore Node
     * @param types Types of the primary key
     * @param file File to read from
     * @throws IOException if something goes wrong
     */
    public KeyNode(ArrayList<Type> types, RandomAccessFile file) throws IOException {
        keys = new ArrayList<>();
        childs = new ArrayList<>();
        readFromFile(types, file);
    }


    /**
     * Method to write the key node to a file
     * @param file file to write to
     * @throws IOException if something goes wrong
     */
    public void writeToFile(RandomAccessFile file) throws IOException {
        head = file.getFilePointer();
        file.writeLong(availableSpace);
        file.writeLong(parent);
        file.writeLong(head);
        file.writeInt(keys.size());
        for (int i = 0; i < keys.size(); i++) {
            file.writeLong(childs.get(i));
            keys.get(i).writeToFile(file);
        }
        file.writeLong(childs.get(childs.size() -1));

        long prev = file.getFilePointer();
        // Bytes written
        long diff = prev - head;
        file.seek(head);
        availableSpace -= diff;
        file.writeLong(availableSpace);
        file.seek(prev);
    }

    /**
     * Method to restore node from file
     * @param types types of the primary key
     * @param file file to restore from
     * @throws IOException if something goes wrong
     */
    @Override
    public void readFromFile(ArrayList<Type> types, RandomAccessFile file) throws IOException {
        availableSpace = file.readLong();
        parent = file.readLong();
        head = file.readLong();
        // Load the keys
        int size = file.readInt();
        for (int i = 0; i < size; i++) {
            childs.add(file.readLong());
            keys.add(new Key(types, file));
        }
        childs.add(file.readLong());

    }


    /**
     * Adds key to the node
     * @param k Key to add
     * @throws IOException if something goes wrong
     */
    public void add(Key k, long child, RandomAccessFile file) throws IOException{
        Utility.sortedInsert(keys, childs, k, child);
        file.seek(head);
        availableSpace -= k.size() + 8;
        file.writeLong(availableSpace);
        file.seek(file.getFilePointer() + 16);
        file.writeInt(keys.size());
        for (int i = 0; i < keys.size(); i++) {
            file.writeLong(childs.get(i));
            keys.get(i).writeToFile(file);
        }
        file.writeLong(childs.get(childs.size() -1));

    }


    /**
     * Removes a key from the node
     * @param pos pos of the element
     * @param k key to remove
     * @param file file to write changes to
     * @throws IOException if something goes wrong
     */
    public void replace(int pos, Key k, RandomAccessFile file) throws IOException {
        keys.set(pos, k);
        file.seek(head + 36 + pos * k.size());
        k.writeToFile(file);
    }

    public void remove(long child, RandomAccessFile file) {
        int index = childs.indexOf(child);

        childs.remove(child);
    }



    public void dump(String ident, ArrayList<Type> keyTypes,
                     ArrayList<Type> recordTypes,
                     RandomAccessFile file) throws IOException{
        System.out.println(ident + "----- KEY NODE " + this.loc() + " -----");
        System.out.println(ident + "Location: " + this.loc());
        System.out.println(ident + "Number of Records: " + this.getKeys().size());
        System.out.println(ident + "Available Space: " + this.availableSpace);
        System.out.println(ident + "Parent: " + this.parent);
        System.out.println(ident + "Number of childs: " + this.getChilds().size());
        System.out.println(ident + "---------------------------");
        ident += "\t\t";
        for (Long c : childs) {
            if (c > 0) {
                file.seek(c);
                if(file.readBoolean()) {
                    (new LeafNode(keyTypes, recordTypes, file)).dump(ident);
                }else {
                    (new KeyNode(keyTypes, file)).dump(ident, keyTypes, recordTypes, file);
                }
            }
        }
    }

    public ArrayList<Key> getKeys() {
        return keys;
    }

    public ArrayList<Long> getChilds() {
        return childs;
    }

    public void setKeys(ArrayList<Key> keys) {
        this.keys = keys;
    }

    public void setChilds(ArrayList<Type> keyTypes, ArrayList<Type> recordTypes,
                          ArrayList<Long> childs, RandomAccessFile file) throws IOException{
        this.childs = childs;
        for (long child :
                childs) {
            file.seek(child + 9);
            file.writeLong(head -1);
        }
    }
}
