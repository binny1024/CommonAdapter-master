package com.smart.holder_library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.Serializable;
import java.util.List;


/**
 * Created by xubinbin on 2017/4/24.
 * function 封装adapter，是viewholder和adapter分离
 *
 */

public class CommonAdapter<BEAN extends CommonAdapter.IBaseBean> extends BaseAdapter{
    private final int mItemViewLayout;//item布局文件
    private Context mContext;
    private IBaseItemViewHolder mBaseViewHolder;
    private IDataItemViewHolderHelper mHolderCallback;
    private IListDataViewHolderHelper mIListDataViewHolderHelper;
    private IBaseBean mIBaseBean;
    private List<BEAN> mIBaseBeanList;
    private int listSize;

    /** 传过来一个数据实体类时，当你用Gson时，你可以不用写list
     * 直接将json数据，转换为bean对象；然后将bean对象传递进来
     * param context 上下文
     * param iBaseBean 数据集（内含list）
     * param itemViewLayout （item的布局文件）
     * param listDataSize(bean 中 包含的list的大小,如果该bean里含有list,则需将list的大小传递进来；
     * 如果没有，则传 1)
     * param dataItemViewHolderHelper （viewholder的接口）
     */
    public CommonAdapter(Context context, IBaseBean iBaseBean,int listDataSize,int itemViewLayout, IDataItemViewHolderHelper dataItemViewHolderHelper) {
        mContext = context;
        mIBaseBean = iBaseBean;
        mItemViewLayout = itemViewLayout;
        mHolderCallback = dataItemViewHolderHelper;
        listSize = listDataSize;
    }

    /**
     * param context 上下文
     * param iBaseBeanList 数据集（list的形式传递过来）
     * param itemViewLayout （item的布局文件）
     * param iListDataViewHolderHelper （viewholder的接口）
     */
    public CommonAdapter(Context context, List<BEAN> iBaseBeanList, int itemViewLayout, IListDataViewHolderHelper iListDataViewHolderHelper) {
        mContext = context;
        mIBaseBeanList = iBaseBeanList;
        mItemViewLayout = itemViewLayout;
        mIListDataViewHolderHelper = iListDataViewHolderHelper;
    }
    @Override
    public int getCount() {
        return mIBaseBeanList==null?listSize:mIBaseBeanList.size();
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
            if (mIListDataViewHolderHelper == null) {
                mBaseViewHolder =  mHolderCallback.initItemViewHolder(mBaseViewHolder,convertView);
            }else {
                mBaseViewHolder =  mIListDataViewHolderHelper.initItemViewHolder(mBaseViewHolder,convertView);
            }

            convertView.setTag(mBaseViewHolder);
        }else {
            mBaseViewHolder = (IBaseItemViewHolder)convertView.getTag();
        }
        if (mIBaseBeanList == null) {
            mHolderCallback.bindDataToView(mContext, mIBaseBean,mBaseViewHolder,position);
        }else {
            mIListDataViewHolderHelper.bindListDataToView(mContext, mIBaseBeanList,mBaseViewHolder,position);
        }
        return convertView;
    }

    /*
    * 当你的数据只有一个bean对象而不是一个list的时候，你的viewholderhelper需要实现这个接口
    * */
    public interface IDataItemViewHolderHelper<BASEVIEWHOLDER extends IBaseItemViewHolder, BASEBEAN extends IBaseBean>{
        /** 用于初始化ViewHolder
         * param convertView
         */
        IBaseItemViewHolder initItemViewHolder(BASEVIEWHOLDER viewHolder, View convertView);

        /**用于设置 item中 的每一个控件
         * param position
         */
        void bindDataToView(Context context,BASEBEAN basebean, BASEVIEWHOLDER viewHolder, int position);
    }
    public interface IListDataViewHolderHelper<BASEVIEWHOLDER extends IBaseItemViewHolder, BASEBEAN extends IBaseBean>{
        /** 用于初始化ViewHolder
         * param convertView
         */
        IBaseItemViewHolder initItemViewHolder(BASEVIEWHOLDER viewHolder, View convertView);

        /**用于将集合中的数据设置 item中 的每一个控件
         * param position
         */
        void bindListDataToView(Context context, List<BASEBEAN> iBaseBeanList, BASEVIEWHOLDER viewHolder, int position);
    }
    /*
    * 你所写的viewholder要继承这个BaseViewHolder
    * */
    public interface IBaseItemViewHolder {

    }
    /**
     * Created by smart on 2017/4/27.
     */
    public interface IBaseBean extends Serializable {
    }
}
