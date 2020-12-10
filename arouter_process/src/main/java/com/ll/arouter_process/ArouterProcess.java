package com.ll.arouter_process;

import com.google.auto.service.AutoService;
import com.ll.arouter_annotation.ARouter;
import com.ll.arouter_process.element_utils.ClassUtils;
import com.ll.arouter_process.element_utils.FileWriteUtils;
import com.ll.arouter_process.element_utils.MethodUtils;
import com.ll.arouter_process.element_utils.factories.ListFactory;
import com.ll.arouter_process.element_utils.beans.ParamBean;
import com.ll.arouter_process.element_utils.beans.StatementBean;
import com.ll.arouter_process.element_utils.factories.MethodFactory;
import com.ll.arouter_process.itemProcess.ArouterPathProcess;
import com.ll.arouter_process.utils.Contents;
import com.ll.arouter_process.utils.LogUtils;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

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
import javax.lang.model.element.Modifier;
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
//        test(set, roundEnvironment);
        processArouter(roundEnvironment);
        return true;
    }

    private void processArouter(RoundEnvironment roundEnvironment) {
        ArouterPathProcess process = new ArouterPathProcess(elements,
                types, filer, roundEnvironment);
        process.process();
    }

    private void test(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        LogUtils.logD("开始工作");
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(ARouter.class);
        for (Element item : elements) {
            String clazzName = item.getSimpleName().toString();
            LogUtils.logD("开始处理类:" + clazzName);
            //method
            ListFactory<ParamBean> paramBeanListFactory = new ListFactory<ParamBean>();
            paramBeanListFactory.addBean(new ParamBean(String[].class, "params"))
                    .addBean(new ParamBean(String.class, "name"));

            ListFactory<StatementBean> statementBeanListFactory = new ListFactory<StatementBean>();
            statementBeanListFactory.addBean(
                    new StatementBean("$T.out.println($S)",
                            new Object[]{System.class, "test"}));

            MethodSpec method = MethodUtils.createPublicVoidMethod(
                    "onCreate",
                    paramBeanListFactory.getList(),
                    statementBeanListFactory.getList());

            ListFactory<MethodSpec> methodSpecListFactory = new ListFactory<MethodSpec>();
            methodSpecListFactory.addBean(method)
                    .addBean(new MethodFactory().createPublicStaticVoidMethodBuilder("TestPrint")
//                            .addAnnotation(Override.class)
                            .addParamModife(String.class, "arg1", Modifier.FINAL)
                            .addParam(String.class, "arg2")
                            .addStatementArgs("$T.out.println($S)", System.class, "MethodFactory test  11")
                            .addStatementArgs("$T.out.println($S)", System.class, "MethodFactory test  22")
                            .build());
            //class
            TypeSpec clazz = ClassUtils.createPublicClassBuilder(clazzName + "$$AutoCLazz",
                    methodSpecListFactory.getList()).build();
            FileWriteUtils.writeJavaFile(
                    "com.ll.auto.code",
                    clazz,
                    filer);
        }
    }
}