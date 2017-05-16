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

public class CommonAdapter<BEAN extends CommonAdapter.IBaseBean> extends BaseAdapter{
    private final int mItemViewLayout;//item布局文件
    protected Context mContext;
    private IBaseViewHolder mBaseViewHolder;
    private IHolderHelperCallback mHolderCallback;
    private IListHolderHelperCallback mIListHolderHelperCallback;
    private IBaseBean mIBaseBean;
    private List<BEAN> mIBaseBeanList;
    private int listSize;

    /**
     * @param context 上下文
     * @param iBaseBean 数据集
     * @param listSize  数据集的大小
     * @param itemViewLayout （item的布局文件）
     * @param iHolderHelperCallback （viewholder的接口）
     */
    public CommonAdapter(Context context, IBaseBean iBaseBean, Integer listSize, int itemViewLayout, IHolderHelperCallback iHolderHelperCallback) {
        mContext = context;
        mIBaseBean = iBaseBean;
        mItemViewLayout = itemViewLayout;
        mHolderCallback = iHolderHelperCallback;
        if (listSize != null) {
            this.listSize = listSize;
        }else {
            this.listSize = 1;
        }

    }

    /**
     * @param context 上下文
     * @param iBaseBeanList 数据集（list的形式传递过来）
     * @param listSize  数据集的大小
     * @param itemViewLayout （item的布局文件）
     * @param iListHolderHelperCallback （viewholder的接口）
     */
    public CommonAdapter(Context context, List<BEAN> iBaseBeanList, Integer listSize, int itemViewLayout, IListHolderHelperCallback iListHolderHelperCallback) {
        mContext = context;
        mIBaseBeanList = iBaseBeanList;
        mItemViewLayout = itemViewLayout;
        mIListHolderHelperCallback = iListHolderHelperCallback;
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
            if (mIListHolderHelperCallback == null) {
                mBaseViewHolder =  mHolderCallback.initViewHolder(mBaseViewHolder,convertView);
            }else {
                mBaseViewHolder =  mIListHolderHelperCallback.initViewHolder(mBaseViewHolder,convertView);
            }

            convertView.setTag(mBaseViewHolder);
        }else {
            mBaseViewHolder = (IBaseViewHolder)convertView.getTag();
        }
        if (mIBaseBeanList == null) {
            mHolderCallback.bindDataToView(mContext, mIBaseBean,mBaseViewHolder,position);
        }else {
            mIListHolderHelperCallback.bindListDataToView(mContext, mIBaseBeanList,mBaseViewHolder,position);
        }

        return convertView;
    }


    public interface IHolderHelperCallback<BASEVIEWHOLDER extends IBaseViewHolder, BASEBEAN extends IBaseBean>{
        /** 用于初始化ViewHolder
         * @param convertView
         */
        IBaseViewHolder initViewHolder(BASEVIEWHOLDER viewHolder, View convertView);

        /**用于设置 item中 的每一个控件
         * @param position
         */
       void bindDataToView(Context context,BASEBEAN basebean, BASEVIEWHOLDER viewHolder, int position);
    }
    public interface IListHolderHelperCallback<BASEVIEWHOLDER extends IBaseViewHolder, BASEBEAN extends IBaseBean>{
        /** 用于初始化ViewHolder
         * @param convertView
         */
        IBaseViewHolder initViewHolder(BASEVIEWHOLDER viewHolder, View convertView);

        /**用于将集合中的数据设置 item中 的每一个控件
         * @param position
         */
       void bindListDataToView(Context context, List<BASEBEAN> iBaseBeanList, BASEVIEWHOLDER viewHolder, int position);
    }

    /*
    * 你所写的viewholder要继承这个BaseViewHolder
    * */
    public interface IBaseViewHolder {

    }
    /**
     * Created by smart on 2017/4/27.
     */

    public interface IBaseBean extends Serializable {
    }
}
