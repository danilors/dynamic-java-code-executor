package br.com.dynamic.code.sources.impl;

import br.com.dynamic.code.sources.CodeSource;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.Map;

public class JsonCodeSource implements CodeSource {
    private final String resourceName;
    private String className;
    private String methodCode;

    public JsonCodeSource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getClassName() {
        if (className == null) {
            className = "DynamicClass";
        }
        return className;
    }

    @Override
    public String getFullCode() throws Exception {
        if (methodCode == null) {
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName)) {
                if (inputStream == null) {
                    throw new IllegalArgumentException(resourceName + " not found in classpath!");
                }
                ObjectMapper mapper = new ObjectMapper();
                Map<String, String> data = mapper.readValue(inputStream, new TypeReference<>() {});
                methodCode = data.get("script");
                if (data.containsKey("className")) {
                    className = data.get("className");
                } else {
                    className = "DynamicClass";
                }
            }
        }
        return "public class " + getClassName() + " { " + methodCode + " }";
    }
}

