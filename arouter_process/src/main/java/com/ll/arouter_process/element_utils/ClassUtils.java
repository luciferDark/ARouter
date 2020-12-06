package com.ll.arouter_process.element_utils;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.List;

import javax.lang.model.element.Modifier;

/**
 * 类构造器
 */
public class ClassUtils {

    /**
     * 创建public final class
     * @param clazzName
     * @param methodSpecs
     * @return
     */
    public static TypeSpec.Builder createPublicFinalClassBuilder(String clazzName,
                                                      List<MethodSpec> methodSpecs) {
        TypeSpec.Builder builder = createPublicClassBuilder(clazzName, methodSpecs);
        builder.addModifiers(Modifier.FINAL);
        return builder;
    }

    /**
     * 创建public class
     * @param clazzName
     * @param methodSpecs
     * @return
     */
    public static TypeSpec.Builder createPublicClassBuilder(String clazzName,
                                                      List<MethodSpec> methodSpecs) {
        TypeSpec.Builder builder = createClassBuilder(clazzName, methodSpecs);
        builder.addModifiers(Modifier.PUBLIC);
        return builder;
    }

    /**
     * 创建类构造器
     * @param clazzName
     * @param methodSpecs
     * @return
     */
    public static TypeSpec.Builder createClassBuilder(String clazzName,
                                                      List<MethodSpec> methodSpecs) {
        TypeSpec.Builder builder = TypeSpec.classBuilder(clazzName);
        if (null != methodSpecs && !methodSpecs.isEmpty()) {
            for (MethodSpec item : methodSpecs) {
                if (null == item){
                    continue;
                }

                builder.addMethod(item);
            }
        }
        return builder;
    }
}
