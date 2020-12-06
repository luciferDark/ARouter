package com.ll.arouter_process.element_utils.factories;

import java.util.ArrayList;

public class ListFactory<E> {
    private ArrayList<E> list = null;

    public ListFactory() {
        this.list = new ArrayList<E>();
    }

    public ListFactory addBean(E bean) {
        if (null == list){
            this.list = new ArrayList<E>();
        }

        if (!list.contains(bean)){
            list.add(bean);
        }
        return this;
    }

    public ArrayList<E> getList() {
        return list;
    }
}
