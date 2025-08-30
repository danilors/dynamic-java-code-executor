package br.com.dynamic.code.executors;

public interface ClassExecutor {
    Object execute(String className, String methodName, Class<?>[] paramTypes, Object[] args) throws Exception;
}
