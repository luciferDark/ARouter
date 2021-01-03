package com.ll.arouter_process.itemProcess;

import com.ll.arouter_annotation.ARouter;
import com.ll.arouter_annotation.beans.ArouterBean;
import com.ll.arouter_process.utils.Contents;
import com.ll.arouter_process.utils.LogUtils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public Map<String, ArouterBean> pathList;

    public ArouterPathProcess(Elements elements,
                              Types types, Filer filer,
                              RoundEnvironment roundEnvironment) {
        this.elements = elements;
        this.types = types;
        this.filer = filer;
        this.roundEnvironment = roundEnvironment;

        pathList = new HashMap<>();
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
                        .setGroup(aRouter.group())
                        .setPath(aRouter.path());

                TypeMirror classTypeMirror = item.asType();
                if (types.isSubtype(classTypeMirror, activity_TypeMirror)){
                    builder.setType(ArouterBean.Type.ACTIVITY);
                } else {

                }
                arouterBean = builder.builder();

                if (!pathList.containsValue(arouterBean)){
                    pathList.put(aRouter.path(), arouterBean);
                    LogUtils.logD("add bean:", arouterBean.toString());
                }

                checkAroutPathFormat(arouterBean);



            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void checkAroutPathFormat(ArouterBean arouterBean) {
        String path = arouterBean.getPath();
        String group = arouterBean.getGroup();
        LogUtils.logD("path:", path);
        LogUtils.logD("group:", group);

        if (null == path || 0 >= path.length() || !path.startsWith("/")){
            LogUtils.logE("input the error path,like:\"/app/Activity\"");
            return;
        }

        if (path.lastIndexOf("/")==0){
            LogUtils.logE("input the error path with error / ,like:\"/app/Activity\"");
            return;
        }

        String finalGroup = path.substring(1, path.indexOf("/", 1));
        LogUtils.logD("finalGroup", finalGroup);
        if(null == group || group.length() <= 0){
            group = finalGroup;
        }
    }
}
