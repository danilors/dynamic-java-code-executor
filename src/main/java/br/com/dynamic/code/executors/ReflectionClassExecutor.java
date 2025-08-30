package br.com.dynamic.code.executors;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ReflectionClassExecutor implements ClassExecutor {
    @Override
    public Object execute(String className, String methodName, Class<?>[] paramTypes, Object[] args) throws Exception {
        try (URLClassLoader classLoader = new URLClassLoader(new URL[]{new File(".").toURI().toURL()})) {
            Class<?> dynamicClass = classLoader.loadClass(className);
            Method method = dynamicClass.getMethod(methodName, paramTypes);
            return method.invoke(null, args);
        }
    }
}
