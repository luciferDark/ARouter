package com.ll.arouter_process;

import com.google.auto.service.AutoService;
import com.ll.arouter_process.utils.LogUtils;

import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedOptions;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Arouter 注解处理服务
 */
@SupportedOptions("arguments")
@AutoService(Processor.class) //这个注释是用来启用服务的
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

        if (null != args){
            String arg_appName = args.get("appName");
            LogUtils.logD("get argument", arg_appName);
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return true;
    }
}