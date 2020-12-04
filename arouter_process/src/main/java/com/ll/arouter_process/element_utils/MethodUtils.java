package com.ll.arouter_process.element_utils;

import com.ll.arouter_process.element_utils.beans.ParamBean;
import com.squareup.javapoet.MethodSpec;

import java.util.List;

import javax.lang.model.element.Modifier;

public class MethodUtils {

    public static MethodSpec.Builder createPublicVoidMethd(String methodName,
                                                           List<ParamBean> paramBeans){
        MethodSpec.Builder builder = MethodSpec.methodBuilder(methodName)
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class);

        if (null != paramBeans && !paramBeans.isEmpty()){

        }
        return builder;
    }
}
