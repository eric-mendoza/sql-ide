package com.cusbromen;

import com.cusbromen.antlr.CustomErrorListener;
import com.cusbromen.antlr.SqlLexer;
import com.cusbromen.antlr.SqlParser;
import com.cusbromen.bptree.*;
import com.cusbromen.semanticControl.Visitor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        CharStream charStream = CharStreams.fromString("CREATE DATABASE prueba;" +
                "USE DATABASE prueba;" +
                "CREATE TABLE colegio(zona INT, id INT CONSTRAINT PK_colegio PRIMARY KEY (zona, id) NOT NULL, name CHAR, no_estudiantes INT);" +
                        "ALTER DATABASE prueba RENAME TO noPrueba;" +
                        "ALTER TABLE colegio RENAME TO universidad;"
                );
        SqlLexer grammarLexer = new SqlLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(grammarLexer);

        try {

            // Example of the B+ tree
            // Table
            // id(int) nombre(char(4))
            // with primary key id.


//            // First we create an array with the primary Key types
//            ArrayList<Type> primaryTypes = new ArrayList<>();
//            primaryTypes.add(Type.INT);
//
//            // Then we create an array with the row types
//            ArrayList<Type> rowType = new ArrayList<>();
//            rowType.add(Type.CHARS);
//
//
//            // If table is not created CREATE TABLE
//            BpTree bpTree = new BpTree("Persona", primaryTypes,
//                    rowType, 4096);
//
//            bpTree.close();
//
//            // If table was already created INSERT INTO
//            bpTree = new BpTree("Persona");
//
//            Key key = new Key();
//            key.add(new IntRecord(1));
//
//            Tuple row = new Tuple();
//            // Assuming the parser generated a char[] of size 4
//            char[] val = new char[4];
//            val[0] = 'E';
//            val[1] = 'R';
//            val[2] = 'I';
//            val[3] = 'C';
//            row.add(new CharRecord(val));
//
//            bpTree.insert(key, row);
//
//            key = new Key();
//            key.add(new IntRecord(2));
//            bpTree.insert(key, row);
//
//            ArrayList<Tuple> rrr = bpTree.all();
//
//            bpTree.close();
//
//            bpTree = new BpTree("Persona");
//
//            key = new Key();
//            key.add(new IntRecord(1));
//            // This a search
//            Tuple rows = bpTree.equalSearch(key);


            // Create array with the row types
            ArrayList<Type> rowTypes = new ArrayList<>();
            rowTypes.add(Type.CHARS);
            rowTypes.add(Type.INT);

            // Create array with the primary key types
            ArrayList<Type> primaryTypes = new ArrayList<>();
            primaryTypes.add(Type.FLOAT);

            // Creating a new table
            BpTree bpTree = new BpTree("test.bin", primaryTypes,
                    rowTypes, 100); //180 rare case
            // Close the tree
            bpTree.close();

            // File already created
            BpTree bpTree1 = new BpTree("test.bin");

            char[] bb = new char[3];
            bb[0] = 'A';
            bb[1] = 'C';
            bb[2] = 'D';

            // Create a row
            Tuple row = new Tuple();
            row.add(new CharRecord(bb));
            row.add(new IntRecord(4));

            Key k = new Key();
            k.add(new FloatRecord(3.0));
            bpTree1.insert(k, row);

            Key k1 = new Key();
            k1.add(new FloatRecord(7.0));
            bpTree1.insert(k1, row);

            Key k2 = new Key();
            k2.add(new FloatRecord(4.0));
            bpTree1.insert(k2, row);

            Tuple rr = new Tuple();
            rr.add(new CharRecord(bb));
            rr.add(new IntRecord(5));
            Key k3 = new Key();
            k3.add(new FloatRecord(6.0));
            bpTree1.insert(k3, rr);

//            bpTree1.dump();

            Key k4 = new Key();
            k4.add(new FloatRecord(9.0));
            bpTree1.insert(k4, row);

            Key k5 = new Key();
            k5.add(new FloatRecord(5.0));
            bpTree1.insert(k5, row);

//            bpTree1.dump();

            Key k6 = new Key();
            k6.add(new FloatRecord(10.0));
            bpTree1.insert(k6, row);
//            bpTree1.dump();
            k6 = new Key();
            k6.add(new FloatRecord(12.0));
            bpTree1.insert(k6, row);
//            bpTree1.dump();
            k6 = new Key();
            k6.add(new FloatRecord(11.0));
            bpTree1.insert(k6, row);
//            bpTree1.dump();

            k6 = new Key();
            k6.add(new FloatRecord(8.0));
            bpTree1.insert(k6, row);
            bpTree1.dump();

            ArrayList<Tuple> rows = bpTree1.all();
            Tuple tt = bpTree1.equalSearch(k3);

            int x = 0;
        }catch (Exception ex) {
            ex.printStackTrace();
        }


        SqlParser grammarParser = new SqlParser(commonTokenStream);


        grammarParser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);  // todo Eliminar despues de eliminar cualquier ambiguedad
        Visitor visitor = new Visitor();
        grammarParser.removeErrorListeners();
        grammarParser.addErrorListener(new CustomErrorListener(null, visitor));

        ParseTree grammarParseTree = grammarParser.expression();
        if (!visitor.hasSyntaxError()) visitor.visit(grammarParseTree);

        List<String> errList = visitor.getSemanticErrorsList();
        List<String> messagesList = visitor.getSuccessMessages();

        System.err.println(errList.toString());
        System.out.println(messagesList.toString());
    }
}
