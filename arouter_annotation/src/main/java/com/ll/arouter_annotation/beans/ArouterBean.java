package com.ll.arouter_annotation.beans;

import javax.lang.model.element.Element;

/**
 *  ARouter封裝集合
 */
public class ArouterBean {
    private Type type;//类型
    private Element element;//类操作符
    private Class<?> clazz;//跳转类
    private String group;//路由组
    private String path;//路由地址

    public Type getType() {
        return type;
    }

    public ArouterBean setType(Type type) {
        this.type = type;
        return this;
    }

    public Element getElement() {
        return element;
    }

    public ArouterBean setElement(Element element) {
        this.element = element;
        return this;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public ArouterBean setClazz(Class<?> clazz) {
        this.clazz = clazz;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public ArouterBean setGroup(String group) {
        this.group = group;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ArouterBean setPath(String path) {
        this.path = path;
        return this;
    }

    public ArouterBean() {
    }

    public ArouterBean(Type type, Element element, Class<?> clazz, String group, String path) {
        this.type = type;
        this.element = element;
        this.clazz = clazz;
        this.group = group;
        this.path = path;
    }

    public enum Type {
        ACTIVITY,
        FRAGMENT,
        SERVICE,
        PROVIDER,
    }
}
