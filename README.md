## 具体用法请下载demo查看里面的注释说明
    引用方式 compile 'com.smart.holder_library:holder_library:1.0.7'


## 使用方法
##### 一、编写 JavaBean（其他形式的json数据使用，请下载demo查看）实现 IBaseBean 接口即可。

	public class BeanMutilObj implements IBaseBean{
	//这里面是数据实体类；
	//如果，你所需的实体类只是该类中的一个内部list则该内部类实现 IBaseBean 接口；
    }

##### 二、自定义viewholder

 这一步跟你用传统的方式是一样的，里面封装了 item 控件的引用；

   但是，要实现 CommonAdapter.IBaseViewHolder 接口。

   示例代码：MocoViewHolder

        public class MocoViewHolder implements CommonAdapter.IBaseViewHolder {
            public TextView name;
            public TextView description;
            public TextView learner;
            public ImageView picSmall;
        }

##### 三、自定义 MutilObjViewHolderHelper，分两种情况
#### 3.1 传递一个简单的实体类对象

######  继承自CommonAdapter.`IViewHolderHelperCallback< T，B >`，来实现 viewholder的实例化和数据绑定。
#### 3.2 传递一个List对象

######  继承自CommonAdapter.`IListViewHolderHelperCallback< T，B >`，来实现 viewholder的实例化和数据绑定。
##### 3.3 泛型参数说明



#####   3.3.1 要传递一个泛型参数 T ，也就是你自定义的自定义viewholder；

#####   3.3.2 数据集的实体类 B ，也就是你的实体类；

#####   List对象 的 示例代码：MocoViewHolderHelper


    import android.content.Context;
    import android.support.annotation.NonNull;
    import android.view.View;

    import com.adapter.smart.R;
    import com.adapter.smart.bean.BeanMutilObjI;
    import com.adapter.smart.utils.UtilImageloader;
    import com.adapter.smart.utils.UtilWidget;
    import com.smart.holder_library.CommonAdapter;

    import java.util.List;

    /**
     * Created by smart on 2017/4/26.
     */

    /*
    * 实例化你的viewholder
    * 将数据和viewholder的控件绑定
    * */
    public class ListDataViewHolderHelper implements CommonAdapter.IListViewHolderHelperCallback<ListDataViewHolder,BeanMutilObjI.DataBean> {

    @Override
    public CommonAdapter.IBaseViewHolder initViewHolder(ListDataViewHolder viewHolder, @NonNull View convertView) {
    viewHolder = new ListDataViewHolder();

    viewHolder.name = UtilWidget.getView(convertView, R.id.id_name);
    viewHolder.description = UtilWidget.getView(convertView,R.id.id_description);
    viewHolder.learner = UtilWidget.getView(convertView,R.id.id_learner);
    viewHolder.picSmall = UtilWidget.getView(convertView,R.id.id_picSmall);

    return viewHolder;
    }

    @Override
    public void bindDataToView(Context context, List<BeanMutilObjI.DataBean> iBaseBeanList, ListDataViewHolder viewHolder, int position) {
    viewHolder.name.setText(iBaseBeanList.get(position).getName());//这个地方自己可以优化的，不必要每次获取list
    viewHolder.description.setText(iBaseBeanList.get(position).getDescription());
    viewHolder.learner.setText("人数："+iBaseBeanList.get(position).getLearner());
    UtilImageloader.setImage(context,iBaseBeanList.get(position).getPicSmall(),viewHolder.picSmall);
    }

     }


### 4、给AdapterView(如 ListView)配置 Adapter

 	  mListView.setAdapter(new CommonAdapter(mContext, mDataBeanList,mDataBeanList.size() , R.layout.list_view_item,new ListDataViewHolderHelper()));

参数列表分别为 ：

#####4.1、 第一种构造函数（本demo使用的就是这种）

    /**
     * @param context 上下文
     * @param iBaseBean 数据集
     * @param listSize  数据集的大小
     * @param itemViewLayout （item的布局文件）
     * @param viewHolderCallback （viewholder的接口）
     */

#####4.2、第二种构造函数（自己的项目中用到了这个）

    /**
     * @param context 上下文
     * @param iBaseBeanList 数据集（list的形式传递过来）
     * @param listSize  数据集的大小
     * @param itemViewLayout （item的布局文件）
     * @param iListViewHolderHelperCallback （viewholder的接口）
     */



示例代码：ListDataActivity

     mListView.setAdapter(new CommonAdapter(mContext, mDataBeanList,mDataBeanList.size() , R.layout.list_view_item,new ListDataViewHolderHelper()));

![](https://github.com/xubinbin1024/CommonAdapter/blob/master/img/list.png)
![](https://github.com/xubinbin1024/CommonAdapter/blob/master/img/grid.png)
