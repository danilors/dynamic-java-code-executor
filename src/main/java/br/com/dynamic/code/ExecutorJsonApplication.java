package br.com.dynamic.code;

import br.com.dynamic.code.compilers.CodeCompiler;
import br.com.dynamic.code.compilers.impl.JavaStringCompiler;
import br.com.dynamic.code.executors.ClassExecutor;
import br.com.dynamic.code.executors.ReflectionClassExecutor;
import br.com.dynamic.code.sources.impl.JsonCodeSource;
import br.com.dynamic.code.strategies.CleanupStrategy;
import br.com.dynamic.code.sources.CodeSource;
import br.com.dynamic.code.strategies.impl.FileCleanupStrategy;

public class ExecutorJsonApplication {
    public static void main(String[] args) {
        CodeSource codeSource = new JsonCodeSource("data.json");
        CodeCompiler compiler = new JavaStringCompiler();
        ClassExecutor executor = new ReflectionClassExecutor();
        CleanupStrategy cleanup = new FileCleanupStrategy();
        String className = null;
        try {
            className = codeSource.getClassName();
            String fullCode = codeSource.getFullCode();
            if (!compiler.compile(className, fullCode)) {
                System.err.println("Compilation failed.");
                return;
            }
            Object result = executor.execute(className, "processValue", new Class[]{String.class}, new Object[]{"exemplo"});
            System.out.println("Execution result: " + result);
        } catch (Exception e) {
            System.err.println("Error during execution: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (className != null) {
                cleanup.cleanup(className);
            }
        }
    }
}