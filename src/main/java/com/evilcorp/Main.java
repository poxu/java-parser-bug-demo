package com.evilcorp;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final var rootDir = "./src/main/java";

        TypeSolver typeSolver = new CombinedTypeSolver(
                new JavaParserTypeSolver(rootDir)
        );
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        StaticJavaParser
                .getConfiguration()
                .setSymbolResolver(symbolSolver);
        final var compilationUnit = StaticJavaParser.parse(new File("./src/main/java/com/evilcorp/service/MyService.java"));
        compilationUnit.findAll(MethodCallExpr.class, methodCallExpr -> {
            // Looking for method calls and then for a variable which owns the method
            final Node firstNode = methodCallExpr.getChildNodes().get(0);
            // If node is not a name then method is probably declared in MyService
            if (firstNode instanceof NameExpr) {
                final var nameExpr = (NameExpr) firstNode;
                try {
                    nameExpr.resolve();
                } catch (UnsolvedSymbolException e) {
                    //Just don't show a bunch of expected exceptions
                } catch (RuntimeException e) {
                    // RuntimeException is generated even though message says that
                    // method cannot be resolved
                    // Points to MyService line 12 for some reason
                    System.out.println("methodCallExpr = " + methodCallExpr);
                    // Correctly points to result variable
                    System.out.println("nameExpr = " + nameExpr);
                    // Error message points to MyService line 10 for some reason
                    System.out.println("e.getMessage() = " + e.getMessage());
                }
            }
            return false;
        });
    }
}
