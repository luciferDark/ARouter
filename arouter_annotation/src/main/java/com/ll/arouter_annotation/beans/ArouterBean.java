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

    private ArouterBean() {
    }

    public Type getType() {
        return type;
    }

    public Element getElement() {
        return element;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public String getGroup() {
        return group;
    }

    public String getPath() {
        return path;
    }

    private ArouterBean(Builder builder) {
        this.type = builder.type;
        this.element = builder.element;
        this.clazz = builder.clazz;
        this.group = builder.group;
        this.path = builder.path;
    }

    @Override
    public String toString() {
        return "ArouterBean{" +
                "element=" + element +
                ", clazz='" + clazz.toString() + '\'' +
                ", group='" + group + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public enum Type {
        ACTIVITY,
        FRAGMENT,
        SERVICE,
        PROVIDER,
    }

    public static  class Builder{
        private Type type;//类型
        private Element element;//类操作符
        private Class<?> clazz;//跳转类
        private String group;//路由组
        private String path;//路由地址

        public Type getType() {
            return type;
        }

        public Builder setType(Type type) {
            this.type = type;
            return this;
        }

        public Element getElement() {
            return element;
        }

        public Builder setElement(Element element) {
            this.element = element;
            return this;
        }

        public Class<?> getClazz() {
            return clazz;
        }

        public Builder setClazz(Class<?> clazz) {
            this.clazz = clazz;
            return this;
        }

        public String getGroup() {
            return group;
        }

        public Builder setGroup(String group) {
            this.group = group;
            return this;
        }

        public String getPath() {
            return path;
        }

        public Builder setPath(String path) {
            this.path = path;
            return this;
        }

        public ArouterBean builder(){
            if (null == path || 0 >= path.length()){
                throw new RuntimeException("you must set path first,just like \"/app/activity1\"");
            }

            return new ArouterBean(this);
        }
    }
}
