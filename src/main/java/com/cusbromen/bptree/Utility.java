package com.cusbromen.bptree;

import java.util.ArrayList;

public class Utility {

    /**
     * Inserts a pair in sorted order
     * @param pairs pair list to insert sorted
     * @param p pair to insert
     */
    public static void sortedInsert(ArrayList<Pair> pairs, Pair p) {
        for (int i = 0; i < pairs.size(); i++) {
            if (p.compareTo(pairs.get(i)) >= 0) continue;
            pairs.add(i, p);
            return;
        }
        pairs.add(p);
    }



    /**
     * Inserts a key in sorted order
     * @param k key to insert
     * @param child child to insert
     */
    public static void sortedInsert(ArrayList<Key> keys, ArrayList<Long> childs, Key k, long child) {
        for (int i = 0; i < keys.size(); i++) {
            if (k.compareTo(keys.get(i)) >= 0) continue;
            keys.add(i, k);
            childs.add(i + 1, child);
            return;
        }
        keys.add(k);
        childs.add(child);
    }

}
