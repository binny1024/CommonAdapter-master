## 具体用法请下载demo查看里面的注释说明
    引用方式 compile 'com.smart.holder_library:holder_library:1.1.5'
## 通用 viewholder的 CommonAdapter 使用
### 1、准备数据：编写 JavaBean，必须实现 Serializable 接口。
	package com.adapter.smart.bean;
	import java.io.Serializable;
	import java.util.List;
	/**
	 * Created by smart on 2017/4/24.
	 */

	public class MoocBean implements Serializable {
	    private int status;
	    private String msg;
	    private List<DataBean> data;

	    public int getStatus() {
	        return status;
	    }

	    public void setStatus(int status) {
	        this.status = status;
	    }

	    public String getMsg() {
	        return msg;
	    }

	    public void setMsg(String msg) {
	        this.msg = msg;
	    }

	    public List<DataBean> getData() {
	        return data;
	    }

	    public void setData(List<DataBean> data) {
	        this.data = data;
	    }

	    public static class DataBean implements Serializable {

	        private int id;
	        private String name;
	        private String picSmall;
	        private String picBig;
	        private String description;
	        private int learner;

	        public int getId() {
	            return id;
	        }

	        public void setId(int id) {
	            this.id = id;
	        }

	        public String getName() {
	            return name;
	        }

	        public void setName(String name) {
	            this.name = name;
	        }

	        public String getPicSmall() {
	            return picSmall;
	        }

	        public void setPicSmall(String picSmall) {
	            this.picSmall = picSmall;
	        }

	        public String getPicBig() {
	            return picBig;
	        }

	        public void setPicBig(String picBig) {
	            this.picBig = picBig;
	        }

	        public String getDescription() {
	            return description;
	        }

	        public void setDescription(String description) {
	            this.description = description;
	        }

	        public int getLearner() {
	            return learner;
	        }

	        public void setLearner(int learner) {
	            this.learner = learner;
	        }
	    }
	}


### 2、自定义viewholder，这一步跟你用传统的方式是一样的，里面封装了 item 控件的引用；但是，要实现 IViewHolder 接口。
	package com.adapter.smart.viewholder;

	import android.widget.ImageView;
	import android.widget.TextView;

	import com.smart.holder_library.iinterface.IViewHolder;


	/**
	 * Created by smart on 2017/4/25.
	 */

	public class ListDataViewHolder implements IViewHolder {
	    public TextView name;
	    public TextView description;
	    public TextView learner;
	    public ImageView picSmall;
	}
### 3、自定义 ViewHolderHelper， 传递一个List对象,IViewHolderHelper< VH，BEAN >，来实现 viewholder的实例化和数据绑定。
#### 参数说明：
##### T ，也就是你自定义的自定义viewholder；
##### B ，也就是实现序列化接口的实体类；
	package com.adapter.smart.viewholder;

	import android.content.Context;
	import android.support.annotation.NonNull;
	import android.view.View;

	import com.adapter.smart.R;
	import com.adapter.smart.bean.MoocBean;
	import com.adapter.smart.utils.UtilImageloader;
	import com.smart.holder_library.iinterface.IViewHolder;
	import com.smart.holder_library.iinterface.IViewHolderHelper;
	import com.adapter.smart.utils.UtilWidget;

	import java.util.List;

	/**
	 * Created by smart on 2017/4/26.
	 */

	/*
	* 实例化你的viewholder
	* 将数据和viewholder的控件绑定
	* */
	public class ListDataViewHolderHelper implements IViewHolderHelper<ListDataViewHolder,MoocBean.DataBean> {

	    @Override
	    public IViewHolder initItemViewHolder(ListDataViewHolder viewHolder, @NonNull View convertView) {
	        viewHolder = new ListDataViewHolder();

	        viewHolder.name = UtilWidget.getView(convertView, R.id.id_name);
	        viewHolder.description = UtilWidget.getView(convertView,R.id.id_description);
	        viewHolder.learner = UtilWidget.getView(convertView,R.id.id_learner);
	        viewHolder.picSmall = UtilWidget.getView(convertView,R.id.id_picSmall);

	        return viewHolder;
	    }

	    @Override
	    public void bindListDataToView(Context context, List<MoocBean.DataBean> iBaseBeanList, ListDataViewHolder viewHolder, int position) {
	        viewHolder.name.setText(iBaseBeanList.get(position).getName());//这个地方自己可以优化的，不必要每次获取list
	        viewHolder.description.setText(iBaseBeanList.get(position).getDescription());
	        viewHolder.learner.setText("人数："+iBaseBeanList.get(position).getLearner());
	        UtilImageloader.setImage(context,iBaseBeanList.get(position).getPicSmall(),viewHolder.picSmall);
	    }
	}

#### 4、配置适配器
##### 4.1、函数说明
    /**
     * param context 上下文
     * param iBaseBeanList 数据集（list的形式传递过来）
     * param itemViewLayout （item的布局文件）
     * param iViewHolderHelper （viewholder的接口）
     */
    public CommonAdapter(Context context, List<BEAN> iBaseBeanList, int itemViewLayout, IViewHolderHelper iViewHolderHelper) {}
##### 4.2、使用
	mListView.setAdapter(new CommonAdapter(mContext, mDataBeanList, R.layout.list_view_item,new ListDataViewHolderHelper()));

### 5、亲们，如果觉得还行，鼓励一下码农吧，一分一毛也是爱呢
![](https://github.com/xubinbin1024/CommonAdapter-master/blob/master/img/pay.png)

