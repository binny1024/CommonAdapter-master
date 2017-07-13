package com.adapter.smart.model;

import com.adapter.smart.presenter.IPresenterDataResult;

/**
 * Created by smart on 2017/4/27.
 */

public interface IModelJsonData  {

    /*
    * 获取本地数据
    * */
    void getLocalDataList(String dataSource, IPresenterDataResult iDataResult);
}
