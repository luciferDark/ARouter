package com.ll.arouter_process;

import com.google.auto.service.AutoService;
import com.ll.arouter_annotation.ARouter;
import com.ll.arouter_process.element_utils.ClassUtils;
import com.ll.arouter_process.element_utils.FileWriteUtils;
import com.ll.arouter_process.element_utils.MethodUtils;
import com.ll.arouter_process.element_utils.beans.ParamBean;
import com.ll.arouter_process.utils.Contents;
import com.ll.arouter_process.utils.LogUtils;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Arouter 注解处理服务
 */
@SupportedOptions("arguments")
@AutoService(Processor.class) //这个注释是用来启用服务的
@SupportedSourceVersion(SourceVersion.RELEASE_7)//服务版本
@SupportedAnnotationTypes({Contents.ANNOTATIONS_AROUTER})
public class ArouterProcess extends AbstractProcessor {

    private Elements elements;
    private Types types;
    private Filer filer;

    private Map<String, String> args = null;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        //初始化工具类
        elements = processingEnvironment.getElementUtils();
        types = processingEnvironment.getTypeUtils();
        filer = processingEnvironment.getFiler();
        args = processingEnvironment.getOptions();
        LogUtils.init(processingEnvironment.getMessager());
        LogUtils.logD("ArouterProcess init ok!");

        if (null != args) {
            String arg_appName = args.get("appName");
            LogUtils.logD("get argument", arg_appName);
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (null == set || set.isEmpty()) {
            LogUtils.logD("注解编辑器获取的set集合为空，停止工作");
            return true;
        }

        test(set, roundEnvironment);
        return true;
    }

    private void test(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        LogUtils.logD("开始工作");
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(ARouter.class);
        for (Element item : elements) {
            String clazzName = item.getSimpleName().toString();
            LogUtils.logD("开始处理类:" + clazzName);

            ParamBean paramBean = new ParamBean();
            paramBean.setParamType(String[].class);
            paramBean.setParamName("params");

            List params = new ArrayList();
            params.add(paramBean);

            MethodSpec method = MethodUtils.createPublicVoidMethod(
                    "onCreate",
                    params,null);

            List methods = new ArrayList();
            methods.add(method);

            TypeSpec clazz = ClassUtils.createPublicClassBuilder(clazzName + "$$AutoCLazz",
                    methods).build();
            FileWriteUtils.writeJavaFile(
                    "com.ll.auto.code",
                    clazz,
                    filer);
        }


    }
}