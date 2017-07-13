package com.adapter.smart.model;

import com.adapter.smart.bean.MoocBean;
import com.adapter.smart.presenter.IPresenterDataResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by smart on 2017/4/27.
 */

public class ModelJsonData  implements IModelJsonData  {

    private MoocBean mBeanMutilObj;//含有数组的json

    @Override
    public void getLocalDataList(String s, IPresenterDataResult iDataResult) {
        resultMutil(s,iDataResult);
    }

    private void resultMutil(String s, final IPresenterDataResult iDataResult) {
        Gson gson = new Gson();
        mBeanMutilObj = gson.fromJson(s, new TypeToken<MoocBean>(){}.getType());
        if (mBeanMutilObj == null) {
            iDataResult.failure("数据为空");
        }else {
            iDataResult.success(mBeanMutilObj);
        }
    }

}
