package com.adapter.smart.model;


import java.io.Serializable;

/**
 * Created by smart on 2017/4/27.
 */

/*
* M层提供给P层的接口，用于反馈业务结果
* */
public interface IDataResult<T extends Serializable> {
    void success(T bean);
    void failure(String msg);
}
