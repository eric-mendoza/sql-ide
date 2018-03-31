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
            primaryTypes.add(Type.CHARS);
            primaryTypes.add(Type.FLOAT);
            BpTree bpTree = new BpTree("test.bin", primaryTypes,
                    types, 128);
            bpTree.close();

            // File already created
            BpTree bpTree1 = new BpTree("test.bin");
            Key k = new Key();
            char[] bb = new char[3];
            bb[0] = 'A';
            bb[1] = 'C';
            bb[2] = 'D';
            k.add(new CharRecord(bb));
            k.add(new FloatRecord(4.0));
            Tuple row = new Tuple();
            row.add(new CharRecord(bb));
            row.add(new IntRecord(4));
            bpTree1.insert(k, row);
            bpTree1.insert(k, row);
            bpTree1.insert(k, row);
            bpTree1.insert(k, row);
            bpTree1.insert(k, row);
            bpTree1.insert(k, row);
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
