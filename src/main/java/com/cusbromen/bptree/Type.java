package com.cusbromen.bptree;

public enum Type {
    INT(1),
    FLOAT(2),
    CHARS(3),
    DATE(4);
    private final int val;
    Type(int val){
        this.val = val;
    }

    public int val() {return val;}

    public static Type fromInt(int val) {
        for (Type t : Type.values()) {
            if (t.val ==  val)
                return t;
        }
        return null;
    }
}
