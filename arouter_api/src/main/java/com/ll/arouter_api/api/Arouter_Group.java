package com.ll.arouter_api.api;

import com.ll.arouter_annotation.beans.ArouterBean;

import java.util.Map;

public interface Arouter_Group {
    /**
     * 获取注册的Group集合
     *
     * @return
     */
    Map<String, Class<? extends Arouter_Path>> getGroupMap();
}
