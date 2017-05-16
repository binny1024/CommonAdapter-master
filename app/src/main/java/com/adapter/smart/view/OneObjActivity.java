package com.adapter.smart.view;

import com.adapter.smart.R;
import com.adapter.smart.bean.BeanOneObjI;
import com.adapter.smart.presenter.PresenterJsonData;
import com.adapter.smart.viewholder.OneObjIViewHolderHelper;
import com.smart.holder_library.CommonAdapter;

import static com.adapter.smart.constants.ConstantUrl.ONE_OBJECT;
import static com.adapter.smart.constants.DataType.DATA_TYPE_ONE;

public class OneObjActivity extends BaseActivity implements IShowData<BeanOneObjI> {

    @Override
    public void initPresenter() {
        new PresenterJsonData(this).getJsonLocal(DATA_TYPE_ONE,ONE_OBJECT);//取本地字符串
    }

    @Override
    public void showList(BeanOneObjI bean) {
        mListView.setAdapter(new CommonAdapter(mContext, bean, R.layout.no_obj_item, new OneObjIViewHolderHelper()));
    }

    @Override
    public void showError(String msg) {

    }
}
