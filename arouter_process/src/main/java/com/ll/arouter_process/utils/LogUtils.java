package com.ll.arouter_process.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

public class LogUtils {
    private static SimpleDateFormat format =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");
    private static LogUtils mInstance;

    private static Messager mMessager = null;

    private LogUtils() {

    }

    public static LogUtils getInstance() {
        if (null == mInstance) {
            synchronized (LogUtils.class) {
                if (null == mInstance) {
                    mInstance = new LogUtils();
                }
            }
        }
        return mInstance;
    }

    public static void init(Messager messager) {
        LogUtils.getInstance().mMessager = messager;
    }

    private static String getTimeSubfix() {
        return format.format(new Date(System.currentTimeMillis())) + ":  ";
    }

    private static void log(Diagnostic.Kind kind, String msg) {
        LogUtils.getInstance().mMessager.printMessage(
                kind,
                getTimeSubfix() + msg);
    }

    public static void logD(String msg) {
        log(Diagnostic.Kind.NOTE, msg);
    }

    public static void logW(String msg) {
        log(Diagnostic.Kind.WARNING, msg);
    }

    public static void logE(String msg) {
        log(Diagnostic.Kind.ERROR, msg);
    }

    private static void log(Diagnostic.Kind kind, String... msg) {
        if (null == msg || msg.length <=0){
            return;
        }
        StringBuffer buffer = new StringBuffer();
        for (String item : msg){
            buffer.append(item + "  ");
        }
        log(kind, buffer.toString());
    }

    public static void logD(String... msg) {
        log(Diagnostic.Kind.NOTE, msg);
    }

    public static void logW(String... msg) {
        log(Diagnostic.Kind.WARNING, msg);
    }

    public static void logE(String... msg) {
        log(Diagnostic.Kind.ERROR, msg);
    }
}
