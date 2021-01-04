package com.ll.arouter_process.itemProcess;

import com.ll.arouter_annotation.ARouter;
import com.ll.arouter_annotation.beans.ArouterBean;
import com.ll.arouter_process.element_utils.factories.MethodFactory;
import com.ll.arouter_process.utils.Contents;
import com.ll.arouter_process.utils.LogUtils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * 用于生成Path相关内容
 */
public class ArouterPathProcess {
    private Elements elements;
    private Types types;
    private Filer filer;
    private RoundEnvironment roundEnvironment;

    public Map<String, List<ArouterBean>> paths;

    TypeElement pathTypeElement;

    public ArouterPathProcess(Elements elements,
                              Types types, Filer filer,
                              RoundEnvironment roundEnvironment) {
        this.elements = elements;
        this.types = types;
        this.filer = filer;
        this.roundEnvironment = roundEnvironment;

        paths = new HashMap<>();
        pathTypeElement = this.elements.getTypeElement(Contents.CLASSNAME_AROUTERPATH);
    }

    public void process() {
        LogUtils.logD("ArouterPathProcess start process");
        //获取activity类信息
        TypeElement activity_TypeElement = elements.getTypeElement(Contents.CLASSNAME_ACTIVITY);
        TypeMirror activity_TypeMirror = activity_TypeElement.asType();

        Set<? extends Element> annotatedWithARouter =
                roundEnvironment.getElementsAnnotatedWith(ARouter.class);
        ArouterBean arouterBean = null;
        for (Element item : annotatedWithARouter) {
            ARouter aRouter = item.getAnnotation(ARouter.class);
            String packageName = elements.getPackageOf(item).getQualifiedName().toString();
            String className = item.getSimpleName().toString();
            TypeName typeName = ClassName.get(item.asType());
            String classFullName = typeName.toString();

            try {
                LogUtils.logD("item packageName:", packageName,
                        "className:", className,
                        "classFullName:", classFullName,
                        "router group:", aRouter.group(),
                        "router path:", aRouter.path());
                ArouterBean.Builder builder = new ArouterBean.Builder()
                        .setElement(item)
                        .setClazz(typeName.getClass())
                        .setGroup(aRouter.group())
                        .setPath(aRouter.path());

                TypeMirror classTypeMirror = item.asType();
                if (types.isSubtype(classTypeMirror, activity_TypeMirror)) {
                    builder.setType(ArouterBean.Type.ACTIVITY);
                } else {
                    //并非是注解在activity，或者其子类上
                    LogUtils.logE("ArouterPath Exception:", "please use the arouter annotation on the activity class or subClass!");
                }
                arouterBean = builder.builder();

                if (checkArouterPathFormat(arouterBean)) {
                    boolean isNewList = false;
                    List<ArouterBean> beanList = paths.get(arouterBean.getGroup());
                    if (beanList == null) {
                        beanList = new ArrayList<>();
                        isNewList = true;
                    }
                    if (!beanList.contains(arouterBean)) {
                        beanList.add(arouterBean);
                        LogUtils.logD("add bean:", arouterBean.toString());
                        if (isNewList) {
                            paths.put(arouterBean.getGroup(), beanList);
                        }
                    } else {
                        LogUtils.logE("ArouterPath Exception:", "please check arouter path format,maybe something wrong!");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.logE("ArouterPath Exception:", e.getMessage());
            }
        }

        //already add all
        handlerPathList();
    }

    /**
     * 处理获取的集合，生成动态类
     */
    private void handlerPathList() {
        if (paths.isEmpty()) {
            return;
        }

        for (Map.Entry<String, List<ArouterBean>> entry : paths.entrySet()) {
            String group = entry.getKey();
            List<ArouterBean> arouterBeanList = entry.getValue();
            handlerArouterBean(arouterBeanList);
        }
    }

    /**
     * 处理每个routerBean
     *
     * @param arouterBean
     * @override public  Map<String, ArouterBean> getPathMap(){
     * Map<String, ArouterBean> map = new HashMap<>();
     * map.put();
     * return map;
     * }
     */
    private void handlerArouterBean(List<ArouterBean> arouterBean) {
        //  创建返回值
        TypeName returnType = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(String.class),
                ClassName.get(ArouterBean.class));
        final MethodFactory methodFactory = new MethodFactory()
                .createPublicMethodBuilder(Contants.PathMethodName)
                .returns(returnType)
                .addAnnotation(Override.class)
                .addStatementArgs("$T<$T, $T> $N = new $T<>()",
                        ClassName.get(Map.class),
                        ClassName.get(String.class),
                        ClassName.get(ArouterBean.class),
                        Contants.PathMethod_Var1,
                        ClassName.get(HashMap.class));
        arouterBean.forEach(new Consumer<ArouterBean>() {
            @Override
            public void accept(ArouterBean arouterBean) {
                if (arouterBean != null) {
//                    new ArouterBean.Builder()
//                            .setElement(item)
//                            .setGroup(aRouter.group())
//                            .setPath(aRouter.path());

                    LogUtils.logD("handlerArouterBean:", arouterBean.toString());
                    methodFactory.addStatementArgs("$N.put($S, new $T" +
                                    ".setClazz($T.class).setGroup($S).setPath($S).setType($T.$L).builder())",
                            Contants.PathMethod_Var1,
                            arouterBean.getGroup(),
                            ClassName.get(ArouterBean.Builder.class),
                            ClassName.get((Type) arouterBean.getElement()),
                            arouterBean.getGroup(),
                            arouterBean.getPath(),
                            ClassName.get(ArouterBean.Type.class),
                            arouterBean.getType());
                }
            }
        });

        methodFactory.addStatementArgs("return $N", Contants.PathMethod_Var1);
    }

    /**
     * 检查bean是否合格
     *
     * @param arouterBean
     * @return
     */
    public boolean checkArouterPathFormat(ArouterBean arouterBean) {
        String path = arouterBean.getPath();
        String group = arouterBean.getGroup();
        LogUtils.logD("path:", path);
        LogUtils.logD("group:", group);

        if (null == path || 0 >= path.length() || !path.startsWith("/")) {
            LogUtils.logE("input the error path,like:\"/app/Activity\"");
            return false;
        }

        if (path.lastIndexOf("/") == 0) {
            LogUtils.logE("input the error path with error / ,like:\"/app/Activity\"");
            return false;
        }

        String finalGroup = path.substring(1, path.indexOf("/", 1));
        LogUtils.logD("finalGroup", finalGroup);
        if (null == group || group.length() <= 0) {
            group = finalGroup;
        }
        return true;
    }


    private final class Contants {
        public final static String PathMethodName = "getPathMap";
        public final static String PathMethod_Var1 = "mBeans";
    }
}
