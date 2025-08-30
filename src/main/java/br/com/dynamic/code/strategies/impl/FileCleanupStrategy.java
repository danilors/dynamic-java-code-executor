package br.com.dynamic.code.strategies.impl;

import br.com.dynamic.code.strategies.CleanupStrategy;

import java.io.File;

public class FileCleanupStrategy implements CleanupStrategy {
    @Override
    public void cleanup(String className) {
        File compiledClass = new File(className + ".class");
        if (compiledClass.exists() && compiledClass.delete()) {
            System.out.println("Compiled code removed successfully!");
        }
    }
}

