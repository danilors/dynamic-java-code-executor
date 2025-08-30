package br.com.dynamic.code.sources.impl;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

public class StringSource extends SimpleJavaFileObject {

    private final String code;

    public StringSource(String name, String code) {
        super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }
}