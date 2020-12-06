package com.ll.arouter_process.element_utils;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;

/**
 * 类文件写入工具
 */
public class FileWriteUtils {

    /**
     * 生成java文件
     *
     * @param pkgName
     * @param typeSpec
     * @return
     */
    public static JavaFile createJavaFile(String pkgName, TypeSpec typeSpec) {
        JavaFile pkgFile = JavaFile.builder(pkgName, typeSpec).build();
        return pkgFile;
    }

    /**
     * 写入生成的类
     *
     * @param pkgName
     * @param typeSpec
     */
    public static void writeJavaFile(String pkgName, TypeSpec typeSpec, Filer filer) {
        writeJavaFile(createJavaFile(pkgName, typeSpec), filer);
    }

    /**
     * 写入生成的类
     *
     * @param file
     * @param filer
     */
    public static void writeJavaFile(JavaFile file, Filer filer) {
        if (null == file || null == filer) {
            return;
        }

        try {
            file.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
