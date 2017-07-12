package com.smart.holder_library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.smart.holder_library.callback.IViewHolder;
import com.smart.holder_library.callback.IViewHolderHelper;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by xubinbin on 2017/4/24.
 * function 封装adapter，是viewholder和adapter分离
 *
 */

public class CommonAdapter<BEAN extends java.io.Serializable> extends BaseAdapter {
    protected final int mItemViewLayout;//item布局文件
    protected Context mContext;
    protected IViewHolder mBaseViewHolder;
    protected IViewHolderHelper mHolderHelper;
    protected List<BEAN> mIBaseBeanList;

    /** 传过来一个数据实体类时，当你用Gson时，你可以不用写list
     * 直接将json数据，转换为bean对象；然后将bean对象传递进来
     * param context 上下文
     * param iBaseBean 数据集（内含list）
     * param itemViewLayout （item的布局文件）
     * param listDataSize(bean 中 包含的list的大小,如果该bean里含有list,则需将list的大小传递进来；
     * 如果没有，则传 1)
     * param dataItemViewHolderHelper （viewholder的接口）
     */
    public CommonAdapter(Context context, BEAN iBaseBean,  int itemViewLayout, IViewHolderHelper iViewHolderHelper) {
        mContext = context;

        mIBaseBeanList = new ArrayList<>();
        mIBaseBeanList.add(iBaseBean);

        mItemViewLayout = itemViewLayout;
        mHolderHelper = iViewHolderHelper;

    }


    /**
     * param context 上下文
     * param iBaseBeanList 数据集（list的形式传递过来）
     * param itemViewLayout （item的布局文件）
     * param iViewHolderHelper （viewholder的接口）
     */
    public CommonAdapter(Context context, List<BEAN> iBaseBeanList, int itemViewLayout, IViewHolderHelper iViewHolderHelper) {
        mContext = context;
        mIBaseBeanList = iBaseBeanList;
        mItemViewLayout = itemViewLayout;
        mHolderHelper = iViewHolderHelper;
    }

    @Override
    public int getCount() {
        return mIBaseBeanList==null?0:mIBaseBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    @SuppressWarnings("unchecked")
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mItemViewLayout,parent,false);
            mBaseViewHolder =  mHolderHelper.initItemViewHolder(mBaseViewHolder,convertView);
            convertView.setTag(mBaseViewHolder);
        }else {
            mBaseViewHolder = (IViewHolder)convertView.getTag();
        }
        mHolderHelper.bindListDataToView(mContext, mIBaseBeanList,mBaseViewHolder,position);
        return convertView;
    }

}
