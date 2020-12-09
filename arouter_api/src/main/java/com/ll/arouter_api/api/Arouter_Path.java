package com.ll.arouter_api.api;

import com.ll.arouter_annotation.beans.ArouterBean;

import java.util.Map;

public interface Arouter_Path {
    /**
     * 获取注册的path集合
     *
     * @return
     */
    Map<String, ArouterBean> getPathMap();
}
