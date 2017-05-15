# 具体用法请下载demo查看里面的注释说明
    引用方式 compile 'com.smart.holder_library:holder_library:1.0.5'


# 使用方法
### 1、编写 JavaBean（其他形式的json数据使用，请下载demo查看）实现 IBaseBean 接口即可。

	public class BeanMutilObj implements IBaseBean{
	//这里面是数据实体类；
	//如果，你所需的实体类只是该类中的一个内部list则该内部类实现 IBaseBean 接口即可；
    }

### 2、自定义viewholder

 这一步跟你用传统的方式是一样的，里面封装了 item 控件的引用；

   但是，要实现 CommonAdapter.IBaseViewHolder 接口。

   示例代码：MocoViewHolder

        public class MocoViewHolder implements CommonAdapter.IBaseViewHolder {
            public TextView name;
            public TextView description;
            public TextView learner;
            public ImageView picSmall;
        }

### 3、自定义 MutilObjViewHolderHelper

#####  继承自CommonAdapter.ViewHolderHelperCallback< T，B >，来实现 viewholder的实例化和数据绑定。

#####   要传递一个泛型参数 T （MocoViewHolder），也就是你自定义的自定义viewholder；

#####   数据集的实体类 B( MocoBean)，也就是你的实体类，如果没有实体类，传Void即可

#####   示例代码：MocoViewHolderHelper

	/**
	 * Created by smart on 2017/4/26.
	 */

	/*
	* 实例化你的viewholder
	* 将数据和viewholder的控件绑定
	* */
	public class MutilObjViewHolderHelper implements CommonAdapter.ViewHolderHelperCallback<MutilObjViewHolder,BeanMutilObj> {

	    private List<BeanMutilObj.DataBean> mDataBeanList;

	    @Override
	    public CommonAdapter.IBaseViewHolder initViewHolder(MutilObjViewHolder viewHolder, @NonNull View convertView) {
	        viewHolder = new MutilObjViewHolder();

	        viewHolder.name = UtilWidget.getView(convertView, R.id.id_name);
	        viewHolder.description = UtilWidget.getView(convertView,R.id.id_description);
	        viewHolder.learner = UtilWidget.getView(convertView,R.id.id_learner);
	        viewHolder.picSmall = UtilWidget.getView(convertView,R.id.id_picSmall);

	        return viewHolder;
	    }

	    @Override
	    public void bindDataToView(Context context, List<CommonAdapter.IBaseBean> IBaseBeanList, BeanMutilObj beanDataList, MutilObjViewHolder viewHolder, int position) {
	       //绑定数据，设置监听
	         if (mDataBeanList == null) {
	            mDataBeanList =   beanDataList.getData();
	        }
	        viewHolder.name.setText(mDataBeanList.get(position).getName());//这个地方自己可以优化的，不必要每次获取list
	        viewHolder.description.setText(mDataBeanList.get(position).getDescription());
	        viewHolder.learner.setText("人数："+mDataBeanList.get(position).getLearner());
	        UtilImageloader.setImage(context,mDataBeanList.get(position).getPicSmall(),viewHolder.picSmall);
	    }
	}



### 4、给AdapterView(如 ListView)配置 Adapter

#此次更新后的用法（1.0.5版本）：
 	 mListView.setAdapter(new CommonAdapter(mContext, beanMutilData,beanMutilData.getData().size() ,R.layout.list_view_item,new MutilObjViewHolderHelper()));

参数列表分别为 ：

4.1、 第一种构造函数（本demo使用的就是这种）

        /**
         * @param context 上下文
         * @param IBaseBean 数据集（直接以bean的形式传递过来）
         * @param listSize  数据集的大小
         * @param itemViewLayout （item的布局文件）
         * @param viewHolderCallback （viewholder的接口）
         */

4.2、第二种构造函数（自己的项目中用到了这个）

    /**
     * @param context 上下文
     * @param IBaseBeanList 数据集（list<IBaseBean>的形式传递过来，里面是你的实体类）
     * @param listSize  数据集的大小
     * @param itemViewLayout （item的布局文件）
     * @param viewHolderCallback （viewholder的接口）
     */


示例代码：MutilObjActivity

    mListView.setAdapter(new CommonAdapter(mContext, beanMutilData,beanMutilData.getData().size() ,R.layout.list_view_item,new MutilObjViewHolderHelper()));

![](https://github.com/xubinbin1024/CommonAdapter/blob/master/img/list.png)
![](https://github.com/xubinbin1024/CommonAdapter/blob/master/img/grid.png)
