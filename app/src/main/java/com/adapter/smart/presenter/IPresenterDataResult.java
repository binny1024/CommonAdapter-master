package com.adapter.smart.presenter;


import java.io.Serializable;

/**
 * Created by smart on 2017/4/27.
 */

/*
* M处理结果的回调 接口
* */
public interface IPresenterDataResult {
    void success(Serializable bean);
    void failure(String msg);
}
