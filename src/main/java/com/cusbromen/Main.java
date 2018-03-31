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
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CharStream charStream = CharStreams.fromString("CREATE DATABASE prueba;" +
                "USE DATABASE prueba;" +
                "CREATE TABLE colegio(zona INT, id INT CONSTRAINT PK_colegio PRIMARY KEY (zona, id) NOT NULL, name CHAR, no_estudiantes INT);" +
                "" +
                "CREATE TABLE" +
                " clase(" +
                "  id INT CONSTRAINT PK_clase PRIMARY KEY NOT NULL," +
                "  id2 CHAR(40) CONSTRAINT CH_lol CHECK(id > 0 AND id < 100) NOT NULL," +
                "  idColegio INT CONSTRAINT FK_clase FOREIGN KEY REFERENCES colegio (id) NOT NULL," +
                "  no_estudiantes INT," +
                "  no_tareas INT CONSTRAINT FK_clase3 FOREIGN KEY REFERENCES colegio (zona) NOT NULL" +
                ");" +
                "ALTER TABLE clase ADD CONSTRAINT FK_clase7 FOREIGN KEY (no_tareas) REFERENCES colegio (id);");
        SqlLexer grammarLexer = new SqlLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(grammarLexer);

        try {
            // Example of the B+ tree
            ArrayList<Type> types = new ArrayList<>();
            types.add(Type.CHARS);
            types.add(Type.INT);
            ArrayList<Type> primaryTypes = new ArrayList<>();
            primaryTypes.add(Type.FLOAT);
            BpTree bpTree = new BpTree("test.bin", primaryTypes,
                    types, 100);
            bpTree.close();

            // File already created
            BpTree bpTree1 = new BpTree("test.bin");

            char[] bb = new char[3];
            bb[0] = 'A';
            bb[1] = 'C';
            bb[2] = 'D';

            Tuple row = new Tuple();
            row.add(new CharRecord(bb));
            row.add(new IntRecord(4));

            Key k = new Key();
            k.add(new FloatRecord(4.0));
            bpTree1.insert(k, row);

            Key k1 = new Key();
            k1.add(new FloatRecord(4.0));
            bpTree1.insert(k1, row);

            Key k2 = new Key();
            k2.add(new FloatRecord(5.0));
            bpTree1.insert(k2, row);

            Key k3 = new Key();
            k3.add(new FloatRecord(6.0));
            bpTree1.insert(k3, row);

            Key k4 = new Key();
            k4.add(new FloatRecord(7.0));
            bpTree1.insert(k4, row);

            Key k5 = new Key();
            k5.add(new FloatRecord(8.0));
            bpTree1.insert(k5, row);
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
