package com.smart.holder_library.callback;

import android.content.Context;
import android.view.View;

import java.util.List;

public interface IViewHolderHelper<BASEVIEWHOLDER extends IViewHolder, BASEBEAN extends java.io.Serializable>  {
        /** 用于初始化ViewHolder
         * param convertView
         */
        IViewHolder initItemViewHolder(BASEVIEWHOLDER viewHolder, View convertView);

        /**用于将集合中的数据设置 item中 的每一个控件
         * param position
         */
        void bindListDataToView(Context context, List<BASEBEAN> iBaseBeanList, BASEVIEWHOLDER viewHolder, int position);
    }
