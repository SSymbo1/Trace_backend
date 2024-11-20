package edu.hrbu.trace_backend_business.entity;

public class OnlineContext {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

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
