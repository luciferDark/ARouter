package com.ll.arouter_process.itemProcess;

import com.ll.arouter_process.element_utils.ClassUtils;
import com.ll.arouter_process.element_utils.FileWriteUtils;
import com.ll.arouter_process.element_utils.factories.ListFactory;
import com.ll.arouter_process.element_utils.factories.MethodFactory;
import com.ll.arouter_process.utils.Contents;
import com.ll.arouter_process.utils.LogUtils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.WildcardTypeName;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * 用于生成Group相关内容
 */
public class ArouterGroupProgress {
    private Elements elements;
    private Types types;
    private Filer filer;
    private RoundEnvironment roundEnvironment;

    public Map<String, String> groups;

    TypeElement pathTypeElement;
    TypeElement groupTypeElement;

    public ArouterGroupProgress(Elements elements,
                                Types types, Filer filer,
                                Map<String, String> groups,
                                TypeElement pathTypeElement,
                                RoundEnvironment roundEnvironment) {
        this.elements = elements;
        this.types = types;
        this.filer = filer;
        this.roundEnvironment = roundEnvironment;
        this.groups = groups;
        this.pathTypeElement = pathTypeElement;
        groupTypeElement = elements.getTypeElement(Contents.CLASSNAME_AROUTERGROUP);
        LogUtils.logD("groupTypeElement name is:", Contents.CLASSNAME_AROUTERGROUP);
        if (null == groupTypeElement) {
            LogUtils.logW("groupTypeElement is null");
        } else {
            LogUtils.logD("groupTypeElement is :", groupTypeElement.getSimpleName().toString());
        }
    }

    public void process() {
        if (null == groups || groups.size() <= 0) {
            LogUtils.logW("groups is null or has nothing inside");
            return;
        }
        for (Map.Entry<String, String> entry : groups.entrySet()) {
            String group = entry.getKey();
            String pathClassName = entry.getValue();
            LogUtils.logD("handlerGroupClass group:", group, "pathClassName:", pathClassName);
            handlerGroupMethod(group, pathClassName);
        }
    }

    /**
     * 处理group类
     *
     * @param group
     * @param pathClassName
     */
    private void handlerGroupMethod(String group, String pathClassName) {
        // handler return Map<String, Class<? extend Path>>
        // Class<? extend Path>
        ParameterizedTypeName extendTypeName =
                ParameterizedTypeName.get(
                        ClassName.get(Class.class),
                        WildcardTypeName.subtypeOf(ClassName.get(pathTypeElement)));//WildcardTypeName ? extend Path
        TypeName returnType = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(String.class),
                extendTypeName);

        final MethodFactory methodFactory = new MethodFactory()
                .createPublicMethodBuilder(ArouterGroupProgress.Contants.GroupMethodName)
                .returns(returnType)
                .addAnnotation(Override.class)
                .addStatementArgs("$T<$T, $T> $N = new $T<>()",
                        ClassName.get(Map.class),
                        ClassName.get(String.class),
                        extendTypeName,
                        ArouterGroupProgress.Contants.GroupMethod_Var1,
                        ClassName.get(HashMap.class))
                .addStatementArgs(
                        "$N.put($S, $T.class)",
                        ArouterGroupProgress.Contants.GroupMethod_Var1,
                        group,
                        ClassName.get(Contents.PACKAGE_AROUTE, pathClassName))
                .addStatementArgs("return $N",
                        ArouterGroupProgress.Contants.GroupMethod_Var1);

        handlerGroupClass(group, methodFactory);
    }

    /**
     * 生产类
     * @param group
     * @param methodFactory
     */
    private void handlerGroupClass(String group, MethodFactory methodFactory) {
        String clazzName = ArouterGroupProgress.Contants.GroupClassNameFixed + group;
        LogUtils.logD(">>output group class name is :", clazzName);
        ListFactory<MethodSpec> methodSpecListFactory = new ListFactory<MethodSpec>();
        methodSpecListFactory.addBean(methodFactory.build());
        TypeSpec clazz = ClassUtils.createPublicClassBuilder(
                clazzName,
                methodSpecListFactory.getList())
                .addSuperinterface(ClassName.get(pathTypeElement))
                .build();
        FileWriteUtils.writeJavaFile(
                Contents.PACKAGE_AROUTE,
                clazz,
                filer);
    }

    private final class Contants {
        public final static String GroupMethodName = "getGroupMap";
        public final static String GroupMethod_Var1 = "map";
        public final static String GroupClassNameFixed = "ARouter$$Group$$";
    }
}
