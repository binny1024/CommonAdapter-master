package com.adapter.smart.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.adapter.smart.R;
import com.adapter.smart.bean.BeanOneObjI;
import com.adapter.smart.utils.UtilWidget;
import com.smart.holder_library.CommonAdapter;

/**
 * Created by smart on 2017/4/26.
 */

public class OneObjIViewHolderHelper implements CommonAdapter.IHolderHelperCallback<NoObjViewHolder,BeanOneObjI> {
    @Override
    public CommonAdapter.IBaseViewHolder  initViewHolder(NoObjViewHolder viewHolder, @NonNull View convertView) {
        viewHolder = new NoObjViewHolder();
        viewHolder.name = UtilWidget.getView(convertView, R.id.id_name);
        viewHolder.age = UtilWidget.getView(convertView,R.id.id_age);
        viewHolder.msg = UtilWidget.getView(convertView,R.id.id_msg);
        viewHolder.status = UtilWidget.getView(convertView,R.id.id_status);

        return viewHolder;
    }

    @Override
    public void bindDataToView(Context context, BeanOneObjI beanDataList, NoObjViewHolder viewHolder, int position) {
        viewHolder.name.setText("名字："+ beanDataList.getData().getName());
        viewHolder.age.setText("年龄："+ beanDataList.getData().getAge());
        viewHolder.status.setText("状态："+ beanDataList.getStatus());
        viewHolder.msg.setText("结果："+ beanDataList.getMsg());
    }
}
