package com.ll.arouter_process.element_utils;

import com.ll.arouter_process.element_utils.beans.ParamBean;
import com.ll.arouter_process.element_utils.beans.StatementBean;
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
    public static MethodSpec createPrivateVoidNullParamsMethod(String methodName,
                                                               List<StatementBean> statementBeans) {
        return createPrivateVoidMethod(methodName,
                null,
                statementBeans);
    }

    /**
     * 创建private无返回值方法
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec createPrivateVoidMethod(String methodName,
                                                     List<ParamBean> paramBeans,
                                                     List<StatementBean> statementBeans) {
        return createPrivateVoidMethodBuilder(methodName,
                paramBeans,
                statementBeans).build();
    }

    /**
     * 创建private无返回值方法构造器
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec.Builder createPrivateVoidMethodBuilder(String methodName,
                                                                    List<ParamBean> paramBeans,
                                                                    List<StatementBean> statementBeans) {
        MethodSpec.Builder builder = createVoidMethodBuilder(methodName,
                paramBeans,
                statementBeans);
        builder.addModifiers(Modifier.PRIVATE);
        return builder;
    }

    /**
     * 创建private static无返回值无参数方法
     *
     * @param methodName
     * @return
     */
    public static MethodSpec createPrivateStaticVoidNullParamsMethod(String methodName,
                                                                     List<StatementBean> statementBeans) {
        return createPrivateStaticVoidMethod(methodName,
                null,
                statementBeans);
    }

    /**
     * 创建private static无返回值方法
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec createPrivateStaticVoidMethod(String methodName,
                                                           List<ParamBean> paramBeans,
                                                           List<StatementBean> statementBeans) {
        return createPrivateStaticVoidMethodBuilder(methodName,
                paramBeans,
                statementBeans).build();
    }

    /**
     * 创建private static无返回值方法构造器
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec.Builder createPrivateStaticVoidMethodBuilder(String methodName,
                                                                          List<ParamBean> paramBeans,
                                                                          List<StatementBean> statementBeans) {
        MethodSpec.Builder builder = createPrivateVoidMethodBuilder(methodName,
                paramBeans,
                statementBeans);
        builder.addModifiers(Modifier.STATIC);
        return builder;
    }

    /**
     * 创建protected无返回值无参数方法
     *
     * @param methodName
     * @return
     */
    public static MethodSpec createProtectedVoidNullParamsMethod(String methodName,
                                                                 List<StatementBean> statementBeans) {
        return createProtectedVoidMethod(methodName,
                null,
                statementBeans);
    }

    /**
     * 创建protected无返回值方法
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec createProtectedVoidMethod(String methodName,
                                                       List<ParamBean> paramBeans,
                                                       List<StatementBean> statementBeans) {
        return createProtectedVoidMethodBuilder(methodName,
                paramBeans,
                statementBeans).build();
    }

    /**
     * 创建protected无返回值方法构造器
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec.Builder createProtectedVoidMethodBuilder(String methodName,
                                                                      List<ParamBean> paramBeans,
                                                                      List<StatementBean> statementBeans) {
        MethodSpec.Builder builder = createVoidMethodBuilder(methodName,
                paramBeans,
                statementBeans);
        builder.addModifiers(Modifier.PROTECTED);
        return builder;
    }

    /**
     * 创建public无返回值无参数方法
     *
     * @param methodName
     * @return
     */
    public static MethodSpec createPublicVoidNullParamsMethod(String methodName,
                                                              List<StatementBean> statementBeans) {
        return createPublicVoidMethod(methodName,
                null,
                statementBeans);
    }

    /**
     * 创建public无返回值方法
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec createPublicVoidMethod(String methodName,
                                                    List<ParamBean> paramBeans,
                                                    List<StatementBean> statementBeans) {
        return createPublicVoidMethodBuilder(methodName,
                paramBeans,
                statementBeans).build();
    }

    /**
     * 创建public无返回值方法构造器
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec.Builder createPublicVoidMethodBuilder(String methodName,
                                                                   List<ParamBean> paramBeans,
                                                                   List<StatementBean> statementBeans) {
        MethodSpec.Builder builder = createVoidMethodBuilder(methodName,
                paramBeans,
                statementBeans);
        builder.addModifiers(Modifier.PUBLIC);
        return builder;
    }

    /**
     * 创建public static无返回值无参数方法
     *
     * @param methodName
     * @return
     */
    public static MethodSpec createPublicStaticVoidNullParamsMethod(String methodName,
                                                                    List<StatementBean> statementBeans) {
        return createPublicStaticVoidMethod(methodName,
                null,
                statementBeans);
    }

    /**
     * 创建public static无返回值方法
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec createPublicStaticVoidMethod(String methodName,
                                                          List<ParamBean> paramBeans,
                                                          List<StatementBean> statementBeans) {
        return createPublicStaticVoidMethodBuilder(methodName,
                paramBeans,
                statementBeans).build();
    }

    /**
     * 创建public static无返回值方法构造器
     *
     * @param methodName
     * @param paramBeans
     * @return
     */
    public static MethodSpec.Builder createPublicStaticVoidMethodBuilder(String methodName,
                                                                         List<ParamBean> paramBeans,
                                                                         List<StatementBean> statementBeans) {
        MethodSpec.Builder builder = createPublicVoidMethodBuilder(methodName,
                paramBeans,
                statementBeans);
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
                                                             List<ParamBean> paramBeans,
                                                             List<StatementBean> statementBeans) {
        MethodSpec.Builder builder = createMethodBuilder(methodName,
                paramBeans,
                statementBeans);
        builder.returns(void.class);
        return builder;
    }

    /**
     * 创建方法构造器
     *
     * @param methodName
     * @param paramBeans
     * @param statementBeans
     * @return
     */
    public static MethodSpec.Builder createMethodBuilder(String methodName,
                                                         List<ParamBean> paramBeans,
                                                         List<StatementBean> statementBeans) {

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
                if (item.getParamModifider() != null) {
                    paramBuilder.addModifiers(item.getParamModifider());
                }
                ParameterSpec parameterSpec = paramBuilder.build();
                builder.addParameter(parameterSpec);
            }
        }
        if (null != statementBeans && !statementBeans.isEmpty()) {
            for (StatementBean item : statementBeans) {
                if (null == item) {
                    continue;
                }

                if (null == item.getArgs() || item.getArgs().length <= 0) {
                    builder.addStatement(item.getStatementFormat());
                } else {
                    builder.addStatement(item.getStatementFormat(),
                            item.getArgs());
                }
            }
        }
        return builder;
    }
}