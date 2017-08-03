package com.adapter.smart.view;

import android.view.View;
import android.widget.Toast;

import com.adapter.smart.R;
import com.adapter.smart.bean.MoocBean;
import com.adapter.smart.presenter.PresenterData;
import com.adapter.smart.viewholder.ListDataViewHolderHelper;
import com.smart.holder.CommonAdapter;

import java.util.List;

import static com.adapter.smart.constants.Data.MUTIL_OBJECT;

public class ListDataActivity extends BaseActivity  implements IShowData<MoocBean> {

    private List<MoocBean.DataBean> mDataBeanList;
    @Override
    public void initPresenter() {
        new PresenterData(this).getJsonLocal(MUTIL_OBJECT);//取本地字符串
    }

    @Override
    @SuppressWarnings("unchecked")
    public void showList(MoocBean beanMutilData) {
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
