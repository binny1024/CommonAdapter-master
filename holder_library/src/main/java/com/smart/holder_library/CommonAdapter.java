package com.smart.holder_library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.Serializable;
import java.util.List;


/**
 * Created by smart on 2017/4/24.
 */

public class CommonAdapter extends BaseAdapter{
    private final int mItemViewLayout;//item布局文件
    protected Context mContext;
    private IBaseViewHolder mBaseViewHolder;
    private ViewHolderHelperCallback mViewHolderCallback;
    private BaseBean mBaseBean;
    private List<BaseBean> mBaseBeanList;
    private int listSize;

    /**
     * @param context 上下文
     * @param baseBean 数据集
     * @param listSize  数据集的大小
     * @param itemViewLayout （item的布局文件）
     * @param viewHolderCallback （viewholder的接口）
     */
    public CommonAdapter(Context context, BaseBean baseBean, Integer listSize, int itemViewLayout, ViewHolderHelperCallback viewHolderCallback) {
        mContext = context;
        mBaseBean = baseBean;
        mItemViewLayout = itemViewLayout;
        mViewHolderCallback = viewHolderCallback;
        if (listSize != null) {
            this.listSize = listSize;
        }else {
            this.listSize = 1;
        }

    }

    /**
     * @param context 上下文
     * @param baseBeanList 数据集（list的形式传递过来）
     * @param listSize  数据集的大小
     * @param itemViewLayout （item的布局文件）
     * @param viewHolderCallback （viewholder的接口）
     */
    public CommonAdapter(Context context, List<BaseBean> baseBeanList, Integer listSize, int itemViewLayout, ViewHolderHelperCallback viewHolderCallback) {
        mContext = context;
        mBaseBeanList = baseBeanList;
        mItemViewLayout = itemViewLayout;
        mViewHolderCallback = viewHolderCallback;
        if (listSize != null) {
            this.listSize = listSize;
        }else {
            this.listSize = 1;
        }

    }
    @Override
    public int getCount() {
        return listSize;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mItemViewLayout,parent,false);
            mBaseViewHolder =  mViewHolderCallback.initViewHolder(mBaseViewHolder,convertView);
            convertView.setTag(mBaseViewHolder);
        }else {
            mBaseViewHolder = (IBaseViewHolder)convertView.getTag();
        }
        mViewHolderCallback.bindDataToView(mContext,mBaseBeanList,mBaseBean,mBaseViewHolder,position);
        return convertView;
    }


    public interface ViewHolderHelperCallback<BASEVIEWHOLDER extends IBaseViewHolder, BASEBEAN extends BaseBean>{
        /** 用于初始化ViewHolder
         * @param convertView
         */
        IBaseViewHolder initViewHolder(BASEVIEWHOLDER viewHolder, View convertView);

        /**用于设置 item中 的每一个控件
         * @param position
         */
       void bindDataToView(Context context, List<CommonAdapter.BaseBean> baseBeanList, BASEBEAN beanDataList, BASEVIEWHOLDER viewHolder, int position);
    }

    /*
    * 你所写的viewholder要继承这个BaseViewHolder
    * */
    public interface IBaseViewHolder {

    }
    /**
     * Created by smart on 2017/4/27.
     */

    public interface BaseBean extends Serializable {
    }
}
