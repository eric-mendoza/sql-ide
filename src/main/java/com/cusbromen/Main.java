package com.cusbromen;

import com.cusbromen.antlr.SqlLexer;
import com.cusbromen.antlr.SqlParser;
import com.cusbromen.semanticControl.Visitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CharStream charStream = CharStreams.fromString("CREATE DATABASE Jean;");
        SqlLexer grammarLexer = new SqlLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(grammarLexer);

        SqlParser grammarParser = new SqlParser(commonTokenStream);

        grammarParser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);  // todo Eliminar despues de eliminar cualquier ambiguedad

        ParseTree grammarParseTree = grammarParser.expression();

        // SEMANTIC CONTROL ------------------------------------------------------------------------------------
        Visitor visitor = new Visitor();
        visitor.visit(grammarParseTree);

        List<String> errList = visitor.getSemanticErrorsList();

        System.out.println(errList.toString());
    }
}
