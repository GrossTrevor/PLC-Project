package plc.project;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * See the specification for information about what the different visit
 * methods should do.
 */
public final class Analyzer implements Ast.Visitor<Void> {

    public Scope scope;
    private Ast.Function function;

    public Analyzer(Scope parent) {
        scope = new Scope(parent);
        scope.defineFunction("print", "System.out.println", Arrays.asList(Environment.Type.ANY), Environment.Type.NIL, args -> Environment.NIL);
    }

    public Scope getScope() {
        return scope;
    }

    @Override
    public Void visit(Ast.Source ast) {
        throw new UnsupportedOperationException();  // TODO
    }

    @Override
    public Void visit(Ast.Global ast) {
        throw new UnsupportedOperationException();  // TODO
    }

    @Override
    public Void visit(Ast.Function ast) {
        throw new UnsupportedOperationException();  // TODO
    }

    @Override
    public Void visit(Ast.Statement.Expression ast) {
        throw new UnsupportedOperationException();  // TODO
    }

    @Override
    public Void visit(Ast.Statement.Declaration ast) {
        throw new UnsupportedOperationException();  // TODO
    }

    @Override
    public Void visit(Ast.Statement.Assignment ast) {
        throw new UnsupportedOperationException();  // TODO
    }

    @Override
    public Void visit(Ast.Statement.If ast) {
        if(!(((Ast.Expression.Literal)ast.getCondition()).getLiteral() instanceof Boolean) || ast.getThenStatements().equals(Arrays.asList())){
            throw new RuntimeException("if statement does not have the correct format");
        }
        ((Ast.Expression.Literal)ast.getCondition()).setType(Environment.Type.BOOLEAN);
        for(Ast.Statement stmt : ast.getThenStatements()){
            scope = new Scope(scope);
            visit(stmt);
            scope = scope.getParent();
        }
        for(Ast.Statement stmt : ast.getElseStatements()){
            scope = new Scope(scope);
            visit(stmt);
            scope = scope.getParent();
        }
        return null;
    }

    @Override
    public Void visit(Ast.Statement.Switch ast) {
        for(Ast.Statement.Case stmt : ast.getCases()){
            System.out.println(ast.getCondition().getType());
//            if((((Ast.Expression.Literal)ast.getCondition()).getLiteral() instanceof ast.getCondition().getType()){
//
//            }
        }
        return null;
    }

    @Override
    public Void visit(Ast.Statement.Case ast) {
        throw new UnsupportedOperationException();  // TODO
    }

    @Override
    public Void visit(Ast.Statement.While ast) {
        if(!(((Ast.Expression.Literal)ast.getCondition()).getLiteral() instanceof Boolean)){
            throw new RuntimeException("if statement does not have the correct format");
        }
        ((Ast.Expression.Literal)ast.getCondition()).setType(Environment.Type.BOOLEAN);
        for(Ast.Statement stmt : ast.getStatements()){
            scope = new Scope(scope);
            visit(stmt);
            scope = scope.getParent();
        }
        return null;
    }

    @Override
    public Void visit(Ast.Statement.Return ast) {
        throw new UnsupportedOperationException();  // TODO
    }

    @Override
    public Void visit(Ast.Expression.Literal ast) {
        throw new UnsupportedOperationException();  // TODO
    }

    @Override
    public Void visit(Ast.Expression.Group ast) {
        throw new UnsupportedOperationException();  // TODO
    }

    @Override
    public Void visit(Ast.Expression.Binary ast) {
        throw new UnsupportedOperationException();  // TODO
    }

    @Override
    public Void visit(Ast.Expression.Access ast) {
        throw new UnsupportedOperationException();  // TODO
    }

    @Override
    public Void visit(Ast.Expression.Function ast) {
        throw new UnsupportedOperationException();  // TODO
    }

    @Override
    public Void visit(Ast.Expression.PlcList ast) {
        throw new UnsupportedOperationException();  // TODO
    }

    public static void requireAssignable(Environment.Type target, Environment.Type type) {
        if (target == Environment.Type.COMPARABLE && !(type == Environment.Type.CHARACTER || type == Environment.Type.DECIMAL || type == Environment.Type.INTEGER || type == Environment.Type.STRING))
            throw new RuntimeException("runtime exception, illegal assignment to target == COMPARABLE");
        if (target == Environment.Type.CHARACTER && !(type == Environment.Type.CHARACTER))
            throw new RuntimeException("runtime exception, illegal assignment to target == CHARACTER");
        if (target == Environment.Type.DECIMAL && !(type == Environment.Type.DECIMAL))
            throw new RuntimeException("runtime exception, illegal assignment to target == DECIMAL");
        if (target == Environment.Type.INTEGER && !(type == Environment.Type.INTEGER))
            throw new RuntimeException("runtime exception, illegal assignment to target == INTEGER");
        if (target == Environment.Type.STRING && !(type == Environment.Type.STRING))
            throw new RuntimeException("runtime exception, illegal assignment to target == STRING");
        if (target == Environment.Type.BOOLEAN && !(type == Environment.Type.BOOLEAN))
            throw new RuntimeException("runtime exception, illegal assignment to target == BOOLEAN");
    }

}
