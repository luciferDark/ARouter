package com.ll.arouter_process.element_utils;

import com.ll.arouter_process.element_utils.beans.ParamBean;
import com.ll.arouter_process.utils.LogUtils;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;

import java.util.List;

import javax.lang.model.element.Modifier;

/**
 * 方法构造器
 */
public class MethodUtils {
    /**
     * 创建private无返回值无参数方法
     *
     * @param methodName
     * @return
     */
    public static MethodSpec createPrivateVoidNullParamsMethod(String methodName) {
        return createPrivateVoidMethod(methodName, null);
    }

    /**
     * 创建private无返回值方法
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec createPrivateVoidMethod(String methodName,
                                                     List<ParamBean> paramBeans) {
        return createPrivateVoidMethodBuilder(methodName, paramBeans).build();
    }

    /**
     * 创建private无返回值方法构造器
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec.Builder createPrivateVoidMethodBuilder(String methodName,
                                                                    List<ParamBean> paramBeans) {
        MethodSpec.Builder builder = createVoidMethodBuilder(methodName, paramBeans);
        builder.addModifiers(Modifier.PRIVATE);
        return builder;
    }

    /**
     * 创建private static无返回值无参数方法
     *
     * @param methodName
     * @return
     */
    public static MethodSpec createPrivateStaticVoidNullParamsMethod(String methodName) {
        return createPrivateStaticVoidMethod(methodName, null);
    }

    /**
     * 创建private static无返回值方法
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec createPrivateStaticVoidMethod(String methodName,
                                                           List<ParamBean> paramBeans) {
        return createPrivateStaticVoidMethodBuilder(methodName, paramBeans).build();
    }

    /**
     * 创建private static无返回值方法构造器
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec.Builder createPrivateStaticVoidMethodBuilder(String methodName,
                                                                          List<ParamBean> paramBeans) {
        MethodSpec.Builder builder = createPrivateVoidMethodBuilder(methodName, paramBeans);
        builder.addModifiers(Modifier.STATIC);
        return builder;
    }

    /**
     * 创建protected无返回值无参数方法
     *
     * @param methodName
     * @return
     */
    public static MethodSpec createProtectedVoidNullParamsMethod(String methodName) {
        return createProtectedVoidMethod(methodName, null);
    }

    /**
     * 创建protected无返回值方法
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec createProtectedVoidMethod(String methodName,
                                                    List<ParamBean> paramBeans) {
        return createProtectedVoidMethodBuilder(methodName, paramBeans).build();
    }

    /**
     * 创建protected无返回值方法构造器
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec.Builder createProtectedVoidMethodBuilder(String methodName,
                                                                   List<ParamBean> paramBeans) {
        MethodSpec.Builder builder = createVoidMethodBuilder(methodName, paramBeans);
        builder.addModifiers(Modifier.PROTECTED);
        return builder;
    }

    /**
     * 创建public无返回值无参数方法
     *
     * @param methodName
     * @return
     */
    public static MethodSpec createPublicVoidNullParamsMethod(String methodName) {
        return createPublicVoidMethod(methodName, null);
    }

    /**
     * 创建public无返回值方法
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec createPublicVoidMethod(String methodName,
                                                    List<ParamBean> paramBeans) {
        return createPublicVoidMethodBuilder(methodName, paramBeans).build();
    }

    /**
     * 创建public无返回值方法构造器
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec.Builder createPublicVoidMethodBuilder(String methodName,
                                                                   List<ParamBean> paramBeans) {
        MethodSpec.Builder builder = createVoidMethodBuilder(methodName, paramBeans);
        builder.addModifiers(Modifier.PUBLIC);
        return builder;
    }

    /**
     * 创建public static无返回值无参数方法
     *
     * @param methodName
     * @return
     */
    public static MethodSpec createPublicStaticVoidNullParamsMethod(String methodName) {
        return createPublicStaticVoidMethod(methodName, null);
    }

    /**
     * 创建public static无返回值方法
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec createPublicStaticVoidMethod(String methodName,
                                                          List<ParamBean> paramBeans) {
        return createPublicStaticVoidMethodBuilder(methodName, paramBeans).build();
    }

    /**
     * 创建public static无返回值方法构造器
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec.Builder createPublicStaticVoidMethodBuilder(String methodName,
                                                                         List<ParamBean> paramBeans) {
        MethodSpec.Builder builder = createPublicVoidMethodBuilder(methodName, paramBeans);
        builder.addModifiers(Modifier.STATIC);
        return builder;
    }


    /**
     * 创建无返回值方法构造器
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec.Builder createVoidMethodBuilder(String methodName,
                                                             List<ParamBean> paramBeans) {
        MethodSpec.Builder builder = createMethodBuilder(methodName, paramBeans);
        builder.returns(void.class);
        return builder;
    }

    /**
     * 创建方法构造器
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec.Builder createMethodBuilder(String methodName,
                                                         List<ParamBean> paramBeans) {

        LogUtils.logD("createMethodBuilder:", methodName);
        MethodSpec.Builder builder = MethodSpec.methodBuilder(methodName);
        if (null != paramBeans && !paramBeans.isEmpty()) {
            for (ParamBean item : paramBeans) {
                if (null == item) {
                    continue;
                }
                LogUtils.logD("createMethodBuilder:", methodName);
                ParameterSpec.Builder paramBuilder = ParameterSpec.builder(
                        item.getParamType(),
                        item.getParamName());
                if (item.getParamModifider() != null){
                    paramBuilder.addModifiers(item.getParamModifider());
                }
                ParameterSpec parameterSpec = paramBuilder.build();
                builder.addParameter(parameterSpec);
            }
        }
        return builder;
    }
}