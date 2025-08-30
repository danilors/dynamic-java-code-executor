package br.com.dynamic.code.compilers.impl;

import br.com.dynamic.code.compilers.CodeCompiler;
import br.com.dynamic.code.sources.impl.StringSource;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.util.Arrays;
import java.util.Collections;

public class JavaStringCompiler implements CodeCompiler {
    @Override
    public boolean compile(String className, String fullCode) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException("JDK not found. JDK is required for runtime compilation.");
        }
        JavaFileObject file = new StringSource(className, fullCode);
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        Iterable<String> options = Arrays.asList("-classpath", System.getProperty("java.class.path"));
        JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, options, null, Collections.singletonList(file));
        boolean success = task.call();
        if (!success) {
            diagnostics.getDiagnostics().forEach(d -> System.err.println("Compilation error: " + d));
        }
        return success;
    }
}