package com.ll.arouter_process.element_utils.factories;

import com.ll.arouter_process.element_utils.MethodUtils;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.MethodSpec;

import java.lang.reflect.Type;

import javax.lang.model.element.Modifier;

/**
 * 方法工厂
 */
public class MethodFactory {
    private MethodSpec.Builder builder = null;

    public MethodFactory createPublicVoidMethodBuilder(String methodName) {
        builder = MethodUtils.createPublicVoidMethodBuilder(methodName, null, null);
        return this;
    }

    public MethodFactory createPrivateVoidMethodBuilder(String methodName) {
        builder = MethodUtils.createPrivateVoidMethodBuilder(methodName, null, null);
        return this;
    }

    public MethodFactory createProtectedVoidMethodBuilder(String methodName) {
        builder = MethodUtils.createProtectedVoidMethodBuilder(methodName, null, null);
        return this;
    }

    public MethodFactory createPublicStaticVoidMethodBuilder(String methodName) {
        builder = MethodUtils.createPublicStaticVoidMethodBuilder(methodName, null, null);
        return this;
    }

    public MethodFactory createPrivateStaticVoidMethodBuilder(String methodName) {
        builder = MethodUtils.createPrivateStaticVoidMethodBuilder(methodName, null, null);
        return this;
    }

    public MethodFactory addParam(Type type, String name) {
        return addParamModife(type, name);
    }

    public MethodFactory addAnnotation(Class<?> type) {
        if (null == builder) {
            return this;
        }
        builder.addAnnotation(AnnotationSpec.builder(type).build());
        return this;
    }

    public MethodFactory addParamModife(Type type, String name, Modifier... modifiers) {
        if (null == builder) {
            return this;
        }
        builder.addParameter(type, name, modifiers);
        return this;
    }

    public MethodFactory addStatement(String statement) {
        return addStatementArgs(statement);
    }

    public MethodFactory addStatementArgs(String statementFormat, Object... args) {
        if (null == builder) {
            return this;
        }
        builder.addStatement(statementFormat, args);
        return this;
    }

    public MethodSpec build() {
        if (null == builder) {
            return null;
        }
        return builder.build();
    }
}
