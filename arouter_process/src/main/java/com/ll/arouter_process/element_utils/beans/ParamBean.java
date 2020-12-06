package com.ll.arouter_process.element_utils.beans;

import java.lang.reflect.Type;

import javax.lang.model.element.Modifier;

/**
 * 参数bean
 */
public class ParamBean {
    private Type paramType;
    private String paramName;
    private Modifier[] paramModifider;

    public Type getParamType() {
        return paramType;
    }

    public void setParamType(Type paramType) {
        this.paramType = paramType;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Modifier[] getParamModifider() {
        return paramModifider;
    }

    public void setParamModifider(Modifier[] paramModifider) {
        this.paramModifider = paramModifider;
    }

    public ParamBean() {
    }

    public ParamBean(Type paramType, String paramName) {
        this.paramType = paramType;
        this.paramName = paramName;
        this.paramModifider = null;

    }

    public ParamBean(Type paramType, String paramName, Modifier[] paramModifider) {
        this.paramType = paramType;
        this.paramName = paramName;
        this.paramModifider = paramModifider;
    }
}
