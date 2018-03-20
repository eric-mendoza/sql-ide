/*
package com.cusbromen.semanticControl;

import com.cusbromen.antlr.decafBaseListener;
import com.cusbromen.antlr.decafParser;
import com.cusbromen.antlr.sqlParser;
import org.antlr.v4.runtime.TokenStream;

import java.util.LinkedList;
import java.util.List;

public class SemanticListener extends decafBaseListener {
    private sqlParser parser;
    private boolean foundMain; // control for existance of 'main' method

    private List<String> semanticErrorsList; // list for semantic errors found
    private List<MethodElement> methodFirms; // a list for the found methods
    private List<VarElement> varList;        // a list for the variables
    private List<Operation> operationList;   // list for operations found in expressions
    List<String> arithOperatorsList;

    private MethodElement currentMethodContext; // to check the current context of variable declarations and operations

    public SemanticListener(sqlParser parser) {
        this.parser = parser;
        this.foundMain = false;
        this.semanticErrorsList = new LinkedList<>();
        this.methodFirms = new LinkedList<>();
        this.varList = new LinkedList<>();
        this.operationList = new LinkedList<>();

        this.arithOperatorsList = new LinkedList<>();
        arithOperatorsList.add("+");
        arithOperatorsList.add("-");
        arithOperatorsList.add("*");
        arithOperatorsList.add("/");
        arithOperatorsList.add("%");
        arithOperatorsList.add("&&");
        arithOperatorsList.add("||");
        arithOperatorsList.add("==");
        arithOperatorsList.add("!=");
        arithOperatorsList.add("<");
        arithOperatorsList.add(">");
        arithOperatorsList.add("<=");
        arithOperatorsList.add(">=");

        currentMethodContext = new MethodElement("void", "global", new LinkedList<>());
    }

    public void operateExpression(decafParser.ExpressionContext ctx) {
        System.out.println(">>found expression!");
        System.out.println(ctx.getText());

        String operation = ctx.getText();

        // TODO check this for the future in case something goes wrong
        // evaluate if operation is possible methodcall
        if (operation.matches("(.)*[(](.)*[)]")) {
            // it has methodcall form
            String type = "";
            String[] split = operation.split("\\(");
            for (MethodElement me : methodFirms) {
                if (me.getFirm().equals(split[0])) {
                    type = me.getType();
                }
            }

            operationList.add(new Operation(type, type));
        } else {
            if (!operation.contains("(")) {
                if (getNumberOfOperators(operation) == 1) {
                    String operator = "";

                    if (operation.contains("+")) { operator =  "+"; }
                    if (operation.contains("-")) { operator =  "-"; }
                    if (operation.contains("/")) { operator =  "/"; }
                    if (operation.contains("*")) { operator =  "*"; }
                    if (operation.contains("%")) { operator =  "%"; }
                    if (operation.contains("&&")) { operator =  "&&"; }
                    if (operation.contains("||")) { operator =  "||"; }
                    if (operation.contains("==")) { operator =  "=="; }
                    if (operation.contains("!=")) { operator =  "!="; }
                    if (operation.contains("<")) { operator =  "<"; }
                    if (operation.contains("<=")) { operator =  "<="; }
                    if (operation.contains(">")) { operator =  ">"; }
                    if (operation.contains(">=")) { operator =  ">="; }

                    String[] splits = operation.split("\\+|-|\\*|/|%|&&|\\|\\||==|!=|<|<=|>|>=");
                    String[] splitsTypes = new String[splits.length];

                    int i = 0;
                    for (String str : splits) {
                        // check for variable type
                        for (VarElement ve : varList) {
                            if (ve.getID().equals(str) && ve.getContext().equals(currentMethodContext)) {
                                splitsTypes[i] = ve.getVarType();
                                break;
                            }
                        }

                        // check for method type
                        if (splitsTypes[i] == null) {
                            for (MethodElement me : methodFirms) {
                                if (me.getFirm().equals(str)) {
                                    splitsTypes[i] = me.getType();
                                    break;
                                }
                            }
                        }

                        // check for type of literal
                        if (splitsTypes[i] == null) {
                            if (str.equals("true") || str.equals("false")) {
                                // bool_literal
                                splitsTypes[i] = "boolean";
                            } else {
                                // int_literal
                                try {
                                    int parse = Integer.parseInt(str);
                                    splitsTypes[i] = "int";
                                } catch (Exception e) {
                                    // its a char_literal
                                    splitsTypes[i] = "char";
                                }
                            }
                        }
                        i++;
                    }

                    // now we have types and operator
                    // method that returns type of operation
                    String operationType = typeSystemOperations(operator, splitsTypes);
                    if (operationType.equals("illegal")) {
                        semanticErrorsList.add("Illegal operation <i>" + operation + "</i>, <strong>"
                                + splitsTypes[0] + operator
                                + splitsTypes[1] + "</strong>");
                    } else {
                        operationList.add(new Operation(operation, operationType));
                    }

                    // TODO replace type in operations list elements

                } else if (getNumberOfOperators(operation) > 1){
                    operationList.add(new Operation(operation));
                } else if (getNumberOfOperators(operation) < 1) {
                    String type = "";
                    boolean isNotAVar = true;

                    for (VarElement ve : varList) {
                        if (ve.getID().equals(operation)) {
                            isNotAVar = false;
                            type = ve.getVarType();
                            break;
                        }
                    }

                    if (isNotAVar) {
                        if (operation.equals("true") || operation.equals("false")) {
                            // bool_literal
                            type = "boolean";
                        } else {
                            // int_literal
                            try {
                                int parse = Integer.parseInt(operation);
                                type = "int";
                            } catch (Exception e) {
                                // its a char_literal
                                type = "char";
                            }
                        }
                    }

                    if (!type.equals("")) {
                        if (operationList.size() == 0) {
                            Operation newOp = new Operation(operation, type);
                            operationList.add(newOp);
                        }
                        // make replacement in list
                        List<Operation> tmp = new LinkedList<>();
                        //System.out.println("operationList antes " + operationList.toString());
                        for (Operation op : operationList) {
                            //System.out.println("entre a verificar la op " + op.toString());
                            String opOperation = op.getOperation();
                            String opType = op.getType();
                            if (opOperation.contains(operation)) {
                                //System.out.println("la operacion contiene la sub");
                                opOperation = opOperation.replace(operation, type);
                                //System.out.println("reemplazo " + opOperation);
                                Operation newOp = new Operation(opOperation, opType);
                                //System.out.println("Reemplazo a agregar " + newOp.toString());
                                tmp.add(newOp);
                            } else {
                                //System.out.println("No la contiene, copio lo anterior " + op.toString());
                                tmp.add(op);
                            }
                        }
                        //System.out.println("operationList antes de clear " + operationList.toString());
                        operationList.clear();
                        operationList.addAll(tmp);
                        //System.out.println("operationList despues de llenarla " + operationList.toString());
                    } else {
                        Operation newOp = new Operation(operation, type);
                        operationList.add(newOp);
                    }
                } else if (getNumberOfOperators(operation) == 0) {
                    System.out.println("no operadores");
                }
            } else {
                System.out.println(operationList.toString());
                operationList.add(new Operation(operation));
            }
        }
    }

    @Override
    public void enterExpression(decafParser.ExpressionContext ctx) {
        if (ctx.methodCall() != null) {
            System.out.println("is method");
        } else {
            operateExpression(ctx);
        }
    }

    public String getTypeOfExpression(String ctx) {
        String finalType = "";
        //TODO add verification for methods
        List<Operation> tmp2 = new LinkedList<>();
        tmp2.addAll(operationList);
        for (Operation op : operationList) {
            if (op.getType() != null) {
                List<Operation> tmp1 = new LinkedList<>();
                for (Operation opInterno : tmp2) {
                    if (opInterno.getOperation().contains(op.getOperation()) &&
                            !opInterno.getOperation().equals(op.getOperation())) {
                        // do replacement
                        String newOperation = opInterno.getOperation().replace(op.getOperation(), op.getType());
                        Operation newOp = new Operation(newOperation, opInterno.getType());
                        tmp1.add(newOp);
                    } else tmp1.add(opInterno);
                }
                if (tmp1.size() != 1) {
                    tmp1.remove(op);
                }
                tmp2.clear();
                tmp2.addAll(tmp1);
            }
        }
        operationList.clear();
        operationList.addAll(tmp2);

        // verify if expressions still have variables that can be changed by the type
        for (VarElement ve : varList) {
            List<Operation> tmp = new LinkedList<>();
            for (Operation op : operationList) {
                if (op.getOperation().contains(ve.getID()) && ve.getContext().equals(currentMethodContext)
                        && (!op.getOperation().matches("(boolean||int||char)"))) {
                    String newOperation = op.getOperation().replace(ve.getID(), ve.getVarType());
                    Operation newOp = new Operation(newOperation, op.getType());
                    tmp.add(newOp);
                } else tmp.add(op);
            }
            operationList.clear();
            operationList.addAll(tmp);
        }

        System.out.println("printing" + operationList.toString());

        // reduce expressions in operations list
        boolean stop = false;
        while (!stop) {
            // find atomic expressions and give them a type
            List<Operation> tmp = new LinkedList<>();
            for (Operation op : operationList) {
                String operation = op.getOperation();
                if (getNumberOfOperators(operation) == 1 && op.getType()==null) {
                    String operator = "";

                    if (operation.contains("+")) { operator =  "+"; }
                    if (operation.contains("-")) { operator =  "-"; }
                    if (operation.contains("/")) { operator =  "/"; }
                    if (operation.contains("*")) { operator =  "*"; }
                    if (operation.contains("%")) { operator =  "%"; }
                    if (operation.contains("&&")) { operator =  "&&"; }
                    if (operation.contains("||")) { operator =  "||"; }
                    if (operation.contains("==")) { operator =  "=="; }
                    if (operation.contains("!=")) { operator =  "!="; }
                    if (operation.contains("<")) { operator =  "<"; }
                    if (operation.contains("<=")) { operator =  "<="; }
                    if (operation.contains(">")) { operator =  ">"; }
                    if (operation.contains(">=")) { operator =  ">="; }

                    String[] splits = operation.replaceAll("\\(|\\)", "").split("\\+|-|\\*|/|%|&&|\\|\\||==|!=|<|<=|>|>=");
                    String typeOfOperation = typeSystemOperations(operator, splits);

                    if (typeOfOperation.equals("illegal")) {
                        semanticErrorsList.add("Expression <strong>" + operation + "</strong> is an invalid expression. <br>At <strong>"+
                                ctx + "</strong>");
                    }

                    Operation newOperation = new Operation(operation, typeOfOperation);
                    tmp.add(newOperation);
                } else {
                    if (operation.matches("\\((boolean||int||char)\\)")) {
                        Operation newOperation =
                                new Operation(operation, operation.replace("(", "").replace(")",""));
                        tmp.add(newOperation);
                    }else tmp.add(op);
                }
            }

            operationList.clear();
            operationList.addAll(tmp);

            // now make replacements

            tmp2 = new LinkedList<>();
            tmp2.addAll(operationList);
            for (Operation op : operationList) {
                if (op.getType() != null) {
                    List<Operation> tmp1 = new LinkedList<>();
                    for (Operation opInterno : tmp2) {
                        if (opInterno.getOperation().contains(op.getOperation()) &&
                                !opInterno.getOperation().equals(op.getOperation())) {
                            // do replacement
                            String newOperation = opInterno.getOperation().replace(op.getOperation(), op.getType());
                            Operation newOp = new Operation(newOperation, opInterno.getType());
                            tmp1.add(newOp);
                        } else tmp1.add(opInterno);
                    }
                    if (operationList.size() == 1) {
                        System.out.println("tmp1 es " + tmp1.toString());
                        finalType = tmp1.get(0).getType();
                    }
                    tmp1.remove(op);
                    tmp2.clear();
                    tmp2.addAll(tmp1);
                } else {
                    if (tmp2.size() == 1) {
                        finalType = tmp2.get(0).getType();
                        stop = true;
                    }
                }
            }
            operationList.clear();
            operationList.addAll(tmp2);

            System.out.println("size: " + operationList.size());
            // check for flag
            if (operationList.size() == 0) {
                stop = true;
                if (finalType.equals("illegal")) {
                    semanticErrorsList.add("Expression <strong>" + ctx + "</strong> " +
                            "is illegal.");
                }
                System.out.println("type is " + finalType);
            }
        }
        System.out.println("printing after while " + operationList.toString());
        return finalType;
    }

    @Override
    public void exitStatement(sqlParser.StatementContext ctx) {
        String typeOf = "";
        if (ctx.methodCall() != null) {
            System.out.println("is method from stmt");
        } else {
            typeOf = getTypeOfExpression(ctx.getText());
        }

        System.out.println("printing statement from exitStatement " + ctx.getText());

        if (ctx.location() != null) {
            if (ctx.location().location() != null) {
                decafParser.LocationContext primeLocation = ctx.location();
                decafParser.LocationContext lastLocation = ctx.location().location();
                boolean stopIterationOnLocations = false;

                while (!stopIterationOnLocations) {
                    if (lastLocation.location() != null) {
                        primeLocation = lastLocation;
                        decafParser.LocationContext tmpLocation = lastLocation.location();
                        lastLocation = tmpLocation;
                    } else {
                        stopIterationOnLocations = true;
                    }
                }

                // TODO
                // TODO important!
                // TODO get Type of primeLocation
                // TODO keep track of context for primeLocation
                // TODO only if context of primeLocation is the previous location of primeLocation, then assign type
                String primeLocId = primeLocation.getText();
                String[] primeLocSplits = primeLocId.split("\\.");
                primeLocId = primeLocSplits[0];
                String structType = "";

                for (VarElement ve : varList) {
                    if (ve.getID().equals(primeLocId)) {
                        structType = ve.getVarType();
                    }
                }

                List<VarElement> primeLocationVars = new LinkedList<>();

                for (VarElement ve : varList) {
                    if (ve.getContext().getFirm().equals(structType)) {
                        primeLocationVars.add(ve);
                    }
                }

                for (VarElement ve : primeLocationVars) {
                    if (ve.getID().equals(lastLocation.getText())) {
                        if (ve.getVarType().equals(typeOf)) {
                            System.out.println("types matched");
                        } else if (!ve.getVarType().equals(typeOf)) {
                            if (typeOf != null) {
                                if (!typeOf.equals("")) {
                                    System.out.println("types didnt match");
                                    semanticErrorsList.add("Types don't match at <strong>" + ctx.getText() + "</strong> <i>" +
                                            ve.getVarType() + "</i> is not equal to </i><i>" + typeOf + "</i>");
                                }
                            }
                        }
                    }
                }
            } else {
                for (VarElement ve : varList) {
                    if (ve.getID().equals(ctx.location().getText())) {
                        if (ve.getVarType().equals(typeOf)) {
                            System.out.println("types matched");
                        } else if (!ve.getVarType().equals(typeOf)) {
                            if (typeOf != null) {
                                if (!typeOf.equals("")) {
                                    System.out.println("types didnt match");
                                    semanticErrorsList.add("Types don't match at <strong>" + ctx.getText() + "</strong> <i>" +
                                            ve.getVarType() + "</i> is not equal to </i><i>" + typeOf + "</i>");
                                }
                            }
                        }
                    }
                }
            }
        }


    }

    public String typeSystemOperations(String operator, String[] types) {
        System.out.println("checked types for operations");
        // operations with ints
        if (types[0].equals("int") && types[1].equals("int")) {
            if ((operator.equals("+")) || (operator.equals("-"))
                    || (operator.equals("/")) ||(operator.equals("*"))
                    || (operator.equals("%"))){
                return "int";
            } else if ((operator.equals("<")) || (operator.equals(">"))
                    || (operator.equals("<=")) || (operator.equals(">="))) {
                return "boolean";
            } else if (operator.equals("=")) {
                return "void";
            } else if (operator.equals("==") || operator.equals("!=")) {
                return "boolean";
            } else return "illegal";
        } else if (types[0].equals("boolean") && types[1].equals("boolean")) {
            if ((operator.equals("&&")) || (operator.equals("||"))) {
                return "boolean";
            } else return "illegal";
        } else if (types[0].equals(types[1])) {
            if (operator.equals("=")) {
                return "void";
            } else if (operator.equals("==") || operator.equals("!=")) {
                return "boolean";
            } else return "illegal";
        } else return "illegal";
    }

    public int getNumberOfOperators(String operation) {
        int operators = 0;
//        for (int i = 0; i < operation.length(); i++) {
//            if (arithOperatorsList.contains(Character.toString(operation.charAt(i)))) {
//                operators++;
//            }
//        }
        String[] splits = operation.split("\\+|-|\\*|/|%|&&|\\|\\||==|!=|<|<=|>|>=");
        operators = splits.length - 1;
        return operators;
    }

    @Override
    public void enterBlock(decafParser.BlockContext ctx) {
        if (ctx.varDeclaration() != null || ctx.statement() != null) {

        }
    }

    @Override
    public void enterStatement(decafParser.StatementContext ctx) {
//        if (!currentMethodContext.getType().equals("void")) {
//            String txt = ctx.getText();
//            if (!ctx.getText().contains("return")) {
//                semanticErrorsList.add("Method is not returning " + currentMethodContext.getType() + " type.");
//            }
//        }

        if (ctx.getText().contains("return")) {
            String[] lineSplit = ctx.getText().replace(";", "").split("return");
            String returnVal = lineSplit[lineSplit.length-1];

            if (returnVal.matches("[(](.)*[)]")) {
                String rv = returnVal.replace("(", "").replace(")", "");
                returnVal = rv;
            }

            System.out.println("the return expression is: " + returnVal);

            if (ctx.expressionA() != null) {
                if (ctx.expressionA().expression() != null) {
                    System.out.println("LA EXPRESION EN RETURN ES " + ctx.expressionA().expression().getText());
                    operateExpression(ctx.expressionA().expression());
                    // check if it is a method call
                    String opType = "";
                    if (returnVal.contains("(")) {
                        String[] split = returnVal.split("\\(");
                        for (MethodElement me : methodFirms) {
                            if (me.getFirm().equals(split[0])) {
                                opType = me.getType();
                                break;
                            }
                        }
                    } else {
                        try {
                            int returnInt = Integer.parseInt(returnVal);
                            opType = "int";
                        } catch (Exception e) {
                            if (returnVal.matches("'.'")) {
                                opType = "char";
                            } else {
                                opType = getTypeOfExpression(ctx.getText());
                            }
                        }
                    }

                    if (!opType.equals(currentMethodContext.getType())) {
                        semanticErrorsList.add("Method <strong>" + currentMethodContext.getFirm() + "</strong> " +
                                "is returning an invalid type. <br>Expecting <strong>" + currentMethodContext.getType() + "" +
                                "</strong>, got <strong>"
                                + opType + "</strong>");
                    }
                }
            }

            if (currentMethodContext.getType().equals("void")) {
                // should not return nothing
                semanticErrorsList.add("Method <strong>" + currentMethodContext.getFirm() + "</strong> " +
                        "declared as void. Invalid return statement.");
            }
        }

        // location = expression type
        if (ctx.location() != null && ctx.expression() != null) {
            String txt = ctx.location().getText();
            if (ctx.location().getText().matches("(.)*(\\[(.)*\\])")) {
                // it is refering to an array[NUM] = something
                if (!ctx.location().getText().matches("(.)*(\\[([0-9])*\\])")) {
                    semanticErrorsList.add(ctx.location().getText() + " is not of type int.");
                }
            }
            // check first if the assign is a method call
            // check if its a method_call
            String operation = ctx.expression().getText();
            String[] split = operation.split("\\(.\\)");
            String type = "";
            for (MethodElement me : methodFirms) {
                if (me.getFirm().equals(split[0])) {
                    type = me.getType();
                    break;
                }
            }

            if (!type.equals("")) {
                //get location type
                String locType = "";
                for (VarElement ve : varList) {
                    if (ve.getID().equals(ctx.location().getText())) {
                        locType = ve.getVarType();
                        break;
                    }
                }

                String[] types = new String[2];
                types[0] = locType;
                types[1] = type;

                System.out.println("type of locType " + locType);
                System.out.println("type of type " + type);

                String operationType = typeSystemOperations("=", types);
                if (operationType.equals("illegal")) {
                    semanticErrorsList.add("Illegal operation <i>" + ctx.location().getText() + "=" + operation +
                            "</i>, <strong>"
                            + types[0] + "="
                            + types[1] + "</strong>");
                }
            } else {
                // TODO didnt find a method but the expression can be an operation
                if (ctx.expression() != null) {
                    System.out.println("operating " + ctx.expression().getText());
                    System.out.println("calculating type " + ctx.getText());
//                    operateExpression(ctx.expression());
//                    String opType = getTypeOfExpression(ctx);
//                    System.out.println("type of expression from location = expression, location: " + ctx.location().getText()
//                            + ctx.expression().getText() + " is: " + opType);
                }
            }
        }

        // if and while
        if (ctx.getText().contains("if(") || ctx.getText().contains("while(")){
            System.out.println("found if or while statement");
            operateExpression(ctx.expression());
            String opType = getTypeOfExpression(ctx.getText());
            System.out.println("type inside if or while is " + opType);
            if (!opType.equals("boolean")) {
                semanticErrorsList.add("Expression <strong>" + ctx.expression().getText() + "</strong> is not " +
                        "of type boolean. Found type <i>" + opType + "</i>.");
            }
        }
    }

    @Override
    public void enterParameter(decafParser.ParameterContext ctx) {
        String parameterType = "";
        String ID = "";

        if (ctx.parameterType() != null) {
            parameterType = ctx.parameterType().getText();
        }

        if (ctx.ID() != null) {
            ID = ctx.ID().getText();
        }

        // Check if variable has already been declared in the same context
        VarElement newVar = new VarElement(parameterType, ID, currentMethodContext);

        boolean hasVarBeenDeclared = false;

        if (varList.isEmpty()) {
            hasVarBeenDeclared = true;
            varList.add(newVar);
        } else {
            for (VarElement listVar : varList) {
                if (listVar.equals(newVar)) {
                    hasVarBeenDeclared = true;
                    semanticErrorsList.add("Variable <strong>\"" + ID + "\"</strong> has " +
                            "already been declared in the context of <strong>" + currentMethodContext.getFirm() + "</strong>");
                    break;
                }
            }
        }

        if (!hasVarBeenDeclared) { varList.add(newVar); }

    }

    @Override
    public void enterLocation(decafParser.LocationContext ctx) {
        String ID = "";

        if (ctx.ID() != null) {
            ID = ctx.ID().getText();
        }

        // verify if ID exits. If not, the location is invalid, since the variable does not exist.
        boolean isLocationDefined = false;
        // location can be a variable
        for (VarElement listVar : varList) {
            if ((listVar.getID().equals(ID)) && (listVar.getContext().equals(currentMethodContext))) {
                // the location exists
                isLocationDefined = true;
                break;
            } else if ((listVar.getID().equals(ID)) && (
                    (listVar.getContext().getFirm().equals("global")) || (listVar.getContext().getType().equals("struct"))
                    )) {
                // the location refers to a global variable
                isLocationDefined = true;
                break;
            }
        }

        if (!isLocationDefined) {
            semanticErrorsList.add("Variable <strong>\"" + ID + "\"</strong> is not defined. ");
        }
    }

    @Override
    public void enterVarDeclaration(decafParser.VarDeclarationContext ctx) {
        String varType = "";    // type
        String ID = "";         // name of var
        String num = "";        // if var is array, num is the number of elements
        boolean isStruct = false;

        if (ctx.varType() != null) {
            // check if varDeclaration is made with a struct type
            if (ctx.varType().ID() != null) {
                isStruct = true;
                varType = ctx.varType().ID().getText();
            } else {
                varType = ctx.varType().getText();
            }
        }

        if (ctx.ID() != null) {
            ID = ctx.ID().getText();
        }

        if (ctx.NUM() != null) {
            num = ctx.NUM().getText();
        }

        // Check if variable has already been declared in the same context
        VarElement newVar = new VarElement(varType, ID, currentMethodContext);
        newVar.setStruct(isStruct);

        if (!num.equals("")) {
            try {
                int numInt = Integer.parseInt(num);
                if (!num.equals("")) {
                    newVar.setNUM(num);
                    if (numInt == 0) {
                        semanticErrorsList.add("<strong>\"" + ID + "\"</strong> length " +
                                "has to be bigger than 0.");
                    }
                }
            } catch (Exception e){
                semanticErrorsList.add("<strong>\"" + ID + "\"</strong> length " +
                        "is not of type int");
            }
        }

        boolean hasVarBeenDeclared = false;

        if (varList.isEmpty()) {
            hasVarBeenDeclared = true;
            varList.add(newVar);
        } else {
            for (VarElement listVar : varList) {
                if (listVar.equals(newVar)) {
                    hasVarBeenDeclared = true;
                    semanticErrorsList.add("Variable <strong>\"" + ID + "\"</strong> has " +
                            "already been declared in the context of <strong>" + currentMethodContext.getFirm() + "</strong>");
                    break;
                }
            }
        }

        if (!hasVarBeenDeclared) { varList.add(newVar); }

    }

    @Override
    public void enterMethodCall(decafParser.MethodCallContext ctx) {
        String firm = ""; // method firm
        if (ctx.ID() != null) {
            firm = ctx.ID().getText();
        }

        // verify if method call is valid, this means if the method has been correctly declared before
        boolean isMethodDeclared = false;
        List<decafParser.ArgContext> argList = ctx.arg1().arg2().arg();
        for (MethodElement listMethod : methodFirms) {
            if (listMethod.getFirm().equals(firm)) {
                if (argList.size() != listMethod.getArgs().size()) {
                    semanticErrorsList.add("Method call for <strong>" + firm + "</strong> has more parameters <br> than the method definition");
                }

                isMethodDeclared = true;
                // verify that arguments match the type of the parameters in method
                int i = 0;
                for (decafParser.ParameterContext pc : listMethod.getArgs()) {
                    System.out.println("PARAMETER TYPE IS: " + pc.parameterType().getText());
                    System.out.println("ARG at i = " + i + " is: " + argList.get(i).getText());

                    String argType = "";
                    for (VarElement ve : varList) {
                        if (ve.getID().equals(argList.get(i).getText())) {
                            argType = ve.getVarType();
                        }
                    }

                    try {
                        decafParser.ExpressionContext eCtx = argList.get(i).expression();
                        String thing = eCtx.getText();
                        operateExpression(eCtx);
                        argType = getTypeOfExpression(eCtx.getText());
                        if (argType == null) {
                            argType = "";
                        }
                    } catch (Exception e) {
                        argType = "";
                    }

                    if (argType.equals("")) {
                        String argument = argList.get(i).getText();
                        System.out.println(argument);

                        try {
                            int parsedArg = Integer.parseInt(argument);
                            argType = "int";
                        } catch (Exception e) {
                            if (argument.equals("true") || argument.equals("false")) {
                                argType = "boolean";
                            } else if (argument.matches("'.'")) {
                                argType = "char";
                            }
                        }
                    }

                    if (!pc.parameterType().getText().equals(argType)) {
                        semanticErrorsList.add("Parameter " + (i+1) + " type <strong>" + pc.parameterType().getText()
                                + "</strong> in " + firm + " does not match <br>argument type <strong>" + argType
                                + "</strong> in method call.");
                    }
                    i++;
                }
                break;
            }
        }

        if (!isMethodDeclared) { semanticErrorsList.add("Can't make call to undeclared method " + firm); }
    }

    @Override
    public void enterStructDeclaration(decafParser.StructDeclarationContext ctx) {
        String firm = "";
        List<decafParser.ParameterContext> args = new LinkedList<>();

        if (ctx.ID() != null) {
            firm = ctx.ID().getText();
        }

        MethodElement newMethod = new MethodElement("struct", firm, args);
        currentMethodContext = newMethod;
        //System.out.println("nuevo metodo " + firm);
        //System.out.println(methodFirms.toString());

        boolean foundMethod = false;

        for (MethodElement listMethod : methodFirms) {
            if (listMethod.equals(newMethod)) {
                semanticErrorsList.add("Struct " + firm + " has " +
                        "already been declared.");
                foundMethod = true;
                break;
            }
        }

        if (!foundMethod) {
            methodFirms.add(newMethod);
        }

        // now check with vars
        VarElement newVar = new VarElement("struct", firm, newMethod);
        boolean foundVar = false;

        for (VarElement ve : varList) {
            if (ve.equals(newVar)) {
                semanticErrorsList.add("Struct " + firm + " has " +
                        "already been declared.");
                foundVar = true;
                break;
            }
        }

        if (!foundVar) {
            varList.add(newVar);
        }
    }

    @Override
    public void enterMethodDeclaration(decafParser.MethodDeclarationContext ctx) {
        // gather values
        TokenStream tokens = parser.getTokenStream();
        String type = ""; // method type
        String firm = ""; // method firm
        List<decafParser.ParameterContext> args = new LinkedList<>(); // args
        if (ctx.methodType()!= null) {
            type = tokens.getText(ctx.methodType());
        }

        if (ctx.ID() != null) {
            firm = ctx.ID().getText();
        }

        if (!ctx.parameter().isEmpty()) {
            args = ctx.parameter();
        }

        // existance of 'main' method
        if ((type.equals("void")) && firm.equals("main") && args.isEmpty()) {
            if (!foundMain) {
                foundMain = true;
                currentMethodContext = new MethodElement(type, firm, args);
                methodFirms.add(currentMethodContext); // add method to method list
                System.out.println(ctx.getRuleContext().toString());
            } else {
                semanticErrorsList.add("Method " + firm + " with arguments: " + args.toString() + " has" +
                        "already been declared.");
            }
        }
        if ((!type.equals("void")) && firm.equals("main") && args.isEmpty()) {
            foundMain = true;
            semanticErrorsList.add("'main' method must have a 'void' return type.");
        }
        if ((type.equals("void")) && firm.equals("main") && !args.isEmpty()) {
            foundMain = true;
            semanticErrorsList.add("'main' method can't have arguments.");
        }

        if (!type.equals("void")) {
            if (ctx.block() != null) {
                String txt = ctx.block().getText();
                if (!ctx.block().getText().contains("return")) {
                    semanticErrorsList.add("Method " + firm + " has no return expression.");
                }
            } else {
                semanticErrorsList.add("Method " + firm + " has no return expression.");
            }
        }

        // rules for other methods that are not 'main'
        if (!firm.equals("main")) {
            MethodElement newMethod = new MethodElement(type, firm, args);
            currentMethodContext = newMethod;
            //System.out.println("nuevo metodo " + firm);
            //System.out.println(methodFirms.toString());

            boolean foundMethod = false;

            for (MethodElement listMethod : methodFirms) {
                if (listMethod.equals(newMethod)) {
                    semanticErrorsList.add("Method " + firm + " has " +
                            "already been declared.");
                    foundMethod = true;
                    break;
                }
            }

            if (!foundMethod) {
                methodFirms.add(newMethod);
            }

//            if (!methodFirms.contains(newMethod)) { methodFirms.add(newMethod); } else {
//                semanticErrorsList.add("Method " + firm + " with arguments: " + args.toString() + " has " +
//                        "already been declared.");
//            }
        }
    }

    public List<String> getSemanticErrorsList() {
        // existance of 'main' method
        if (!foundMain) { semanticErrorsList.add("No 'main' method declared."); }

        return semanticErrorsList;
    }
}
*/