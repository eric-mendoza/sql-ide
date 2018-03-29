package com.cusbromen;

import com.cusbromen.antlr.CustomErrorListener;
import com.cusbromen.antlr.SqlLexer;
import com.cusbromen.antlr.SqlParser;
import com.cusbromen.semanticControl.Visitor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CharStream charStream = CharStreams.fromString("DROP TABLE clase;");
        SqlLexer grammarLexer = new SqlLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(grammarLexer);

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
