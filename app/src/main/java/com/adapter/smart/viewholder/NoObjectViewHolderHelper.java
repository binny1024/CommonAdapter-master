package com.adapter.smart.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.adapter.smart.R;
import com.adapter.smart.bean.BeanNoObjI;
import com.adapter.smart.utils.UtilWidget;
import com.smart.holder_library.callback.IViewHolder;
import com.smart.holder_library.callback.IViewHolderHelper;

import java.util.List;

/**
 * Created by smart on 2017/4/26.
 */

public class NoObjectViewHolderHelper implements IViewHolderHelper<NoObjectViewHolder,BeanNoObjI> {
    @Override
    public IViewHolder initItemViewHolder(NoObjectViewHolder viewHolder, @NonNull View convertView) {
        viewHolder = new NoObjectViewHolder();
        viewHolder.name = UtilWidget.getView(convertView, R.id.id_name);
        viewHolder.age = UtilWidget.getView(convertView,R.id.id_age);
        viewHolder.msg = UtilWidget.getView(convertView,R.id.id_msg);
        viewHolder.status = UtilWidget.getView(convertView,R.id.id_status);

        Log.i("xxx", "initItemViewHolder: ");
        return viewHolder;
    }

    @Override
    public void bindListDataToView(Context context, List<BeanNoObjI> iBaseBeanList, NoObjectViewHolder viewHolder, int position) {
        viewHolder.name.setText("名字："+ iBaseBeanList.get(position).getName());
        viewHolder.age.setText("年龄："+ iBaseBeanList.get(position).getAge());
        Log.i("xxx", "bindListDataToView: "+ iBaseBeanList.get(position).getAge());
        viewHolder.status.setText("状态："+ iBaseBeanList.get(position).getStatus());
        viewHolder.msg.setText("结果："+ iBaseBeanList.get(position).getMsg());
    }

}
