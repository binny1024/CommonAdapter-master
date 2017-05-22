package com.adapter.smart.view;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.adapter.smart.R;
import com.adapter.smart.bean.BeanMutilObjImp;
import com.adapter.smart.presenter.PresenterJsonData;
import com.adapter.smart.viewholder.ListDataViewHolderHelper;
import com.smart.holder_library.CommonAdapter;
import com.smart.holder_library.utils.UtilSP;

import java.util.List;

import static com.adapter.smart.constants.ConstantUrl.MOCO_URL;
import static com.adapter.smart.constants.DataType.DATA_TYPE_MUTIL;

public class ListDataActivity extends BaseActivity  implements IShowData<BeanMutilObjImp> {

    private List<BeanMutilObjImp.DataBean> mDataBeanList;
    @Override
    public void initPresenter() {
//       mListView = UtilWidget.getView(this,R.id.id_listview);
//        new PresenterJsonData(this).getJsonLocal(DATA_TYPE_MUTIL,MUTIL_OBJECT);//取本地字符串
        new PresenterJsonData(this).getJsonNet(DATA_TYPE_MUTIL,MOCO_URL);//取本地字符串
//        new PresenterJsonData(this).getJsonNet(DATA_TYPE_MUTIL,MOCO_URL);//取本地字符串
    }

    @Override
    public void showList(BeanMutilObjImp beanMutilData) {
        mAnimationDrawable.stop();
        mImageView.setVisibility(View.GONE);
        UtilSP.getInstance(this)
                .setFileName("default")
                .putBean("test",beanMutilData)
                .submit();
        mDataBeanList = beanMutilData.getData();
        mListView.setAdapter(new CommonAdapter(mContext, mDataBeanList, R.layout.list_view_item,new ListDataViewHolderHelper()));
        beanMutilData = UtilSP.getInstance(this)
                .setFileName("default")
                .getBean("test");
        if (beanMutilData != null) {
            for (int i = 0; i <beanMutilData.getData().size(); i++) {
                Log.i("xxx", "showList" + beanMutilData.getData().get(i).getName());
            }
        }
    }

    @Override
    public void showError(String msg) {
        mAnimationDrawable.stop();
        mImageView.setVisibility(View.GONE);
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }
}
