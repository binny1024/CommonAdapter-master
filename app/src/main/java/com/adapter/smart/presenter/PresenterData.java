package com.adapter.smart.presenter;

import com.adapter.smart.model.IModelJsonData;
import com.adapter.smart.model.ModelJsonData;
import com.adapter.smart.view.IShowData;

import java.io.Serializable;

/**
 * Created by smart on 2017/4/27.
 */

/*
* 中间层 负责 任务 路由
* */
public class PresenterData implements IPresenterDataResult{
    private IShowData mIShowData;//view层接口。成功获取数据后调用
    private IModelJsonData mIModelJsonData;//model层接口。发起业务逻辑，读取数据等

    public PresenterData(IShowData IShowData ) {
        mIShowData = IShowData;
        mIModelJsonData = new ModelJsonData ();
    }

    /*
    * 从本地文档读取数据*/
    public void getJsonLocal(String dataSource){
        mIModelJsonData.getLocalDataList(dataSource, this);
    }
    @Override
    public void success(Serializable bean) {
        mIShowData.showList(bean);
    }

    @Override
    public void failure(String msg) {
        mIShowData.showError(msg);
    }
}
