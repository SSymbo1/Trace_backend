package edu.hrbu.trace_backend.entity;

public class OnlineContext {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setCurrent(String parse) {
        threadLocal.set(parse);
    }

    public static String getCurrent() {
        return threadLocal.get();
    }

    public static void removeCurrent() {
        threadLocal.remove();
    }
}
