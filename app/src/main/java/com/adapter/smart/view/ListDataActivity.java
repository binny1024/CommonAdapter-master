package com.adapter.smart.view;

import android.view.View;
import android.widget.Toast;

import com.adapter.smart.R;
import com.adapter.smart.bean.BeanMutilObjI;
import com.adapter.smart.presenter.PresenterJsonData;
import com.adapter.smart.viewholder.ListDataViewHolderHelper;
import com.smart.holder_library.CommonAdapter;

import java.util.List;

import static com.adapter.smart.constants.ConstantUrl.MUTIL_OBJECT;
import static com.adapter.smart.constants.DataType.DATA_TYPE_MUTIL;

public class ListDataActivity extends BaseActivity  implements IShowData<BeanMutilObjI> {

    private List<BeanMutilObjI.DataBean> mDataBeanList;
    @Override
    public void initPresenter() {
//       mListView = UtilWidget.getView(this,R.id.id_listview);
        new PresenterJsonData(this).getJsonLocal(DATA_TYPE_MUTIL,MUTIL_OBJECT);//取本地字符串
//        new PresenterJsonData(this).getJsonNet(DATA_TYPE_MUTIL,MOCO_URL);//取本地字符串
    }

    @Override
    public void showList(BeanMutilObjI beanMutilData) {
        mAnimationDrawable.stop();
        mImageView.setVisibility(View.GONE);
        mDataBeanList = beanMutilData.getData();
        mListView.setAdapter(new CommonAdapter(mContext, mDataBeanList, R.layout.list_view_item,new ListDataViewHolderHelper()));
    }

    @Override
    public void showError(String msg) {
        mAnimationDrawable.stop();
        mImageView.setVisibility(View.GONE);
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }
}
