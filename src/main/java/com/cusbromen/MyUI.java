package com.cusbromen;

import javax.print.PrintException;
import javax.servlet.annotation.WebServlet;

import com.cusbromen.antlr.CustomErrorListener;
import com.cusbromen.antlr.SqlLexer;
import com.cusbromen.antlr.SqlParser;
import com.cusbromen.semanticControl.Visitor;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.*;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Window;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.misc.Utils;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.v4.runtime.tree.Trees;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * IDE UI for the Decaf Compiler
 * @author Gabriel Brolo
 * Universidad del Valle de Guatemala, Construccion de Compiladores
 */
@Theme("mytheme")
public class MyUI extends UI {
    private String editorInput; // actual input to compile
    private ParseTree grammarParseTree; // the generated parse tree
    private SqlParser grammarParser; // the generated parser
    private SqlLexer grammarLexer; // the generated lexer
    private static final String endOfLine = "<br/>"; // EOF for tree visualization
    private int level = 0; // tree begin index level for tree visualization
    private String prettyFileTree; // a pretty tree visualization in text
    private JSONObject metadata;
    private com.vaadin.ui.Tree<String> dbsTree;
    private TreeData<String> dbsTreeData;
    private TreeDataProvider<String> inMemoryDataProvider;
    private Visitor visitor;
    private VerticalLayout consolePanelLayout;
    private boolean verboseMode;

    // Grid to show data from queries
    private Grid<String> showDataGrid;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        visitor = new Visitor();
        showDataGrid = new Grid<>();
        verboseMode = false;
        /* horizontal layout for input and console */
        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.setSpacing(false);
        hLayout.setMargin(false);
        hLayout.setSizeFull();

        Label editorLbl = new Label("<strong>Code Editor</strong>", ContentMode.HTML);

        /* layout for the input area */
        final VerticalLayout layout = new VerticalLayout();
        consolePanelLayout = new VerticalLayout();
        consolePanelLayout.setSpacing(false);

        final VerticalLayout consoleLayout = new VerticalLayout();
        consoleLayout.setHeight(100.0f, Sizeable.Unit.PERCENTAGE);

        final VerticalLayout leftOptionsLayout = new VerticalLayout();
        leftOptionsLayout.setSpacing(false);

        // file tree for databases
        dbsTree = new com.vaadin.ui.Tree<>();
        dbsTreeData = new TreeData<>();
        dbsTreeData.addItem(null, "DBMS Current databases");

        dbsTree.setItemIconGenerator(item -> {
            if (item.equals("DBMS Current databases")) {
                return VaadinIcons.DATABASE;
            }
            return null;
        });

        inMemoryDataProvider = new TreeDataProvider<>(dbsTreeData);
        dbsTree.setDataProvider(inMemoryDataProvider);
        dbsTree.expand("DBMS Current databases");

        // read metadata from jsons
        readMetadataFromJSON();
        updateDbsTree();

        Label dbsTreeLbl = new Label("<strong>Current created databases</strong>", ContentMode.HTML);
        dbsTreeLbl.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);

        leftOptionsLayout.addComponents(dbsTreeLbl, dbsTree);

        /* console panel */
        Panel consolePanel = new Panel("Console");
        consolePanel.setHeight(500.0f, Unit.PIXELS);

        /* the actual input area */
        TextArea editor = new TextArea();
        editor.setSizeFull();
        editor.setWidth(100.0f, Unit.PERCENTAGE);
        editor.setHeight(420.0f, Unit.PIXELS);
        editor.addValueChangeListener(event -> {
           editorInput = String.valueOf(event.getValue());
        });

        /* Buttons for compilation, tree and to clear the console */
        Button compileBtn = new Button();
        compileBtn.setIcon(VaadinIcons.PLAY);
        compileBtn.setDescription("Execute query");
        compileBtn.setSizeFull();

        Button clearEditorBtn = new Button();
        clearEditorBtn.setIcon(VaadinIcons.TRASH);
        clearEditorBtn.setDescription("Clear editor");
        clearEditorBtn.setSizeFull();

        Button clearConsoleBtn = new Button();
        clearConsoleBtn.setIcon(VaadinIcons.DEL);
        clearConsoleBtn.setDescription("Clear console");
        clearConsoleBtn.setSizeFull();

        Button verboseBtn = new Button();
        verboseBtn.setIcon(VaadinIcons.BUG);
        verboseBtn.setDescription("Verbose script");
        verboseBtn.setSizeFull();

        /* button listeners */
        compileBtn.addClickListener(e -> {
            visitor = new Visitor();
            visitor.setLayout(hLayout);
            if (editorInput != null) {
                setFocusedComponent(consolePanel);
                // Create visitor
                dbsTree.select(visitor.getDbInUse());

                /* clear the console */
                consolePanelLayout.removeAllComponents();

                /* generate a stream from input and create tree */
                CharStream charStream = CharStreams.fromString(editorInput);
                grammarLexer = new SqlLexer(charStream);
                grammarLexer.removeErrorListeners();
                grammarLexer.addErrorListener(new CustomErrorListener(consolePanelLayout, visitor));
                CommonTokenStream commonTokenStream = new CommonTokenStream(grammarLexer);

                grammarParser = new SqlParser(commonTokenStream);
                grammarParser.removeErrorListeners();
                grammarParser.addErrorListener(new CustomErrorListener(consolePanelLayout, visitor));

                grammarParser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);  // todo Eliminar despues de eliminar cualquier ambiguedad

                grammarParseTree = grammarParser.expression();
                Label lbl1 = new Label("<strong>TREE>> </strong>" + grammarParseTree.toStringTree(grammarParser)
                        + "<br><i>For a cleaner tree, click the \"Tree representation \" button.<i>", ContentMode.HTML);
                lbl1.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);
                //consolePanelLayout.addComponent(lbl1);

                // SEMANTIC CONTROL ------------------------------------------------------------------------------------
                visitor.visit(grammarParseTree);

                List<String> errList = visitor.getSemanticErrorsList();
                List<String> successMsgs = visitor.getSuccessMessages();

                consolePanelLayout.removeAllComponents();

                visitor.addToVerboseParser();

                if (!visitor.getVerboseParser().isEmpty() && verboseMode) {
                    for (String verbose : visitor.getVerboseParser()) {
                        Label errLbl = new Label(verbose, ContentMode.HTML);
                        errLbl.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);
                        consolePanelLayout.addComponent(errLbl);
                    }
                }

                // error list
                if (!errList.isEmpty()) {
                    for (String error : errList) {
                        Label errLbl = new Label("<strong>ERROR>> </strong>" + error, ContentMode.HTML);
                        errLbl.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);
                        consolePanelLayout.addComponent(errLbl);
                    }
                    Notification notification = new Notification("Compiled with errors", "See console for details",
                            Notification.Type.ERROR_MESSAGE, true);
                    notification.setDelayMsec(4000);
                    notification.setPosition(Position.BOTTOM_RIGHT);
                    notification.show(Page.getCurrent());
                }

                // success output list
                if (!successMsgs.isEmpty()) {
                    for (String msg : successMsgs) {
                        Label successLbl = new Label("<strong>SUCCESS!>> </strong>" + msg, ContentMode.HTML);
                        successLbl.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);
                        consolePanelLayout.addComponent(successLbl);
                    }
                }

                // refresh dbs filesystem
                readMetadataFromJSON();
                updateDbsTree();

                Notification notification = new Notification("Compilation done!", "Execution terminated!");
                notification.setDelayMsec(2000);
                notification.setPosition(Position.TOP_CENTER);
                notification.show(Page.getCurrent());

            } else {
                Notification notification = new Notification("Empty code", "The editor is empty",
                        Notification.Type.WARNING_MESSAGE, true);
                notification.setDelayMsec(4000);
                notification.setPosition(Position.BOTTOM_RIGHT);
                notification.show(Page.getCurrent());
            }

        });

        clearEditorBtn.addClickListener(event -> {
            editor.clear();
            consolePanelLayout.removeAllComponents();
            Notification notification = new Notification("Cleared the editor", "Success!",
                    Notification.Type.ASSISTIVE_NOTIFICATION, true);
            notification.setDelayMsec(1000);
            notification.setPosition(Position.TOP_CENTER);
            notification.show(Page.getCurrent());
        });

        clearConsoleBtn.addClickListener(event -> {
            consolePanelLayout.removeAllComponents();
            Notification notification = new Notification("Cleared the console", "Success!",
                    Notification.Type.ASSISTIVE_NOTIFICATION, true);
            notification.setDelayMsec(1000);
            notification.setPosition(Position.TOP_CENTER);
            notification.show(Page.getCurrent());
        });

        verboseBtn.addClickListener(event -> {
            // TODO add flag or something to verbose
            String onOff = "";
            if (!verboseMode) {
                verboseMode = true;
                verboseBtn.setCaption("ON");
                onOff = "ON";
            } else {
                verboseMode = false;
                verboseBtn.setCaption("OFF");
                onOff = "OFF";
            }

            Notification notification = new Notification("Verbose mode " + onOff, "Success!",
                    Notification.Type.ASSISTIVE_NOTIFICATION, true);
            notification.setDelayMsec(1000);
            notification.setPosition(Position.TOP_CENTER);
            notification.show(Page.getCurrent());
        });

        // LAYOUT
        VerticalLayout pagelayout = new VerticalLayout();
        pagelayout.addComponents(hLayout, consoleLayout);
        pagelayout.setSpacing(false);
        pagelayout.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);

        HorizontalLayout editorButtonsLayout = new HorizontalLayout();
        editorButtonsLayout.setSizeFull();
        editorButtonsLayout.addComponents(compileBtn, clearEditorBtn, clearConsoleBtn, verboseBtn);

        consolePanel.setContent(consolePanelLayout);
        consoleLayout.addComponent(consolePanel);
        layout.addComponents(editorLbl, editorButtonsLayout, editor);
        hLayout.addComponents(leftOptionsLayout, layout);
        
        setContent(pagelayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    /**
     * Generates an indented string tree
     * Based on: https://github.com/grosenberg/SnippetsTest/blob/master/src/test/java/net/certiv/remark/test/TestBase.java
     * @param tree
     * @param rules
     * @return
     */
    public String prettyTree(final Tree tree, final List<String> rules) {
        level = 0;
        return process(tree, rules).replaceAll("(?m)^\\s+$", "").replaceAll("\\r?\\n\\r?\\n", endOfLine);
    }

    /**
     * The actual process to generate the indented tree
     * Based on: https://github.com/grosenberg/SnippetsTest/blob/master/src/test/java/net/certiv/remark/test/TestBase.java
     * @param tree
     * @param rules
     * @return
     */
    private String process(final Tree tree, final List<String> rules) {
        if (tree.getChildCount() == 0) return Utils.escapeWhitespace(Trees.getNodeText(tree, rules), false);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(lead(level));
        level++;
        String n = Utils.escapeWhitespace(Trees.getNodeText(tree, rules), false);
        stringBuilder.append(n + ' ');
        for (int i = 0; i < tree.getChildCount(); i++) {
            stringBuilder.append(process(tree.getChild(i), rules));
        }
        level--;
        stringBuilder.append(lead(level));
        return stringBuilder.toString();
    }

    /**
     * Adds indentations
     * Based on: https://github.com/grosenberg/SnippetsTest/blob/master/src/test/java/net/certiv/remark/test/TestBase.java
     * @param level
     * @return
     */
    private String lead(int level) {
        StringBuilder sb = new StringBuilder();
        if (level > 0) {
            sb.append(endOfLine);
            for (int cnt = 0; cnt < level; cnt++) {
                sb.append("-");
            }
        }
        return sb.toString();
    }

    private void updateDbsTree() {
        dbsTreeData.clear();
        inMemoryDataProvider.refreshAll();

        dbsTreeData.addItem(null, "DBMS Current databases");
        dbsTree.expand("DBMS Current databases");

        Set<?> keys = metadata.keySet();

        for (Object key : keys) {
            dbsTreeData.addItem("DBMS Current databases", "DB: " + key.toString());
            JSONObject dbData = new JSONObject();
            try {
                File dbs = new File("metadata/" + key.toString() + "/" + key.toString() + ".json");
                if (dbs.exists()) {
                    JSONParser jsonParser = new JSONParser();
                    dbData = (JSONObject) jsonParser.parse(new FileReader("metadata/" + key.toString() + "/" + key.toString() + ".json"));

                    Set<?> keysInside = dbData.keySet();

                    for (Object keyInside : keysInside) {
                        dbsTreeData.addItem("DB: " + key.toString(), keyInside.toString());
                    }
                }
            } catch (Exception e) { }

            dbsTree.addItemClickListener(event -> {
                System.out.println(event.getItem().toString());
                if (event.getItem().toString().contains("DB: ")) {
                    String dbName = event.getItem().toString().replace("DB: ", "");
                    visitor.setDbInUse(dbName);

                    Notification notification = new Notification("Selected database " + dbName, "Click to dismiss");
                    notification.setDelayMsec(500);
                    notification.setPosition(Position.TOP_CENTER);
                    notification.show(Page.getCurrent());
                }
            });
        }

        inMemoryDataProvider.refreshAll();
    }

    private void readMetadataFromJSON() {
        try {
            File dbs = new File("metadata/dbs.json");
            if (dbs.exists()) {
                JSONParser jsonParser = new JSONParser();
                metadata = (JSONObject) jsonParser.parse(new FileReader("metadata/dbs.json"));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    // TODO to show data from trees
    private void addDataToGrid() {
        showDataGrid = new Grid<>();
        showDataGrid.setWidth("100%");
        showDataGrid.setHeight("100%");

        List<String> lst = Arrays.asList("pupusa", "kakusa", "pepolio");
        showDataGrid.clearSortOrder();
        showDataGrid.setItems(lst);
        showDataGrid.clearSortOrder();

        showDataGrid.addColumn(String::toString).setCaption("Col1");
        consolePanelLayout.addComponent(showDataGrid);
    }
}
