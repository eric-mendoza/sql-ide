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
        CharStream charStream = CharStreams.fromString("SHOW COLUMNS FROM pruebaConexion223;");
        SqlLexer grammarLexer = new SqlLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(grammarLexer);

        try {
            ArrayList<Type> types = new ArrayList<>();
            types.add(Type.CHARS);
            types.add(Type.INT);
            ArrayList<Type> primaryTypes = new ArrayList<>();
            primaryTypes.add(Type.CHARS);
            primaryTypes.add(Type.FLOAT);
            BpTree bpTree = new BpTree("test.bin", primaryTypes,
                    types, 4096);
            bpTree.close();
            BpTree bpTree1 = new BpTree("test.bin");
            Key k = new Key();
            char[] bb = new char[3];
            bb[0] = 'A';
            bb[1] = 'C';
            bb[2] = 'D';
            k.add(new CharRecord(bb));
            k.add(new IntRecord(4));
            Tuple row = new Tuple();
            row.add(new IntRecord(4));
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
