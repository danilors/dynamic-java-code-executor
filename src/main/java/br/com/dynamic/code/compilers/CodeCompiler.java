package br.com.dynamic.code.compilers;

public interface CodeCompiler {
    public boolean compile(String className, String fullCode) throws Exception;
}
