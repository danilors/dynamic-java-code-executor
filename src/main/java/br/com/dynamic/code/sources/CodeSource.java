package br.com.dynamic.code.sources;

public interface CodeSource {
    String getClassName();
    String getFullCode() throws Exception;
}
