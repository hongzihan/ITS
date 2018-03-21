package its.hzh.com.its_system.userMain.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import its.hzh.com.its_system.R;
import its.hzh.com.its_system.userMain.bean.GroupBean;
import its.hzh.com.its_system.userMain.bean.ItemBean;

/**
 * Created by ken on 2018/3/15.
 */

public class MyExAdapter extends BaseExpandableListAdapter implements ExpandableListAdapter {

    List<GroupBean> groupList; //父级列表数据
    List<List> childrenList; //子级列表数据
    Context context; //应用上下文

    int groupLayout; //父级列表布局
    int childrenLayout; //子级列表布局

    public MyExAdapter(Context context, int groupLayoutId, int childrenLayoutId,
                        List<GroupBean> groupList, List<List> childrenList) {
        this.context = context;
        this.groupLayout = groupLayoutId;
        this.childrenLayout = childrenLayoutId;
        this.groupList = groupList;
        this.childrenList = childrenList;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        List<ItemBean> itemList = childrenList.get(groupPosition);

        return itemList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        List<ItemBean> itemList = childrenList.get(groupPosition);

        return itemList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        //加载Group布局
        View view;
        if (convertView == null) {
            //convertView是布局的缓存，如果缓存为空的话，就加载布局到view
            view = LayoutInflater.from(context).inflate(groupLayout, parent, false);
        } else {
            //如果convert不为空，说明有缓存，直接把缓存赋值给view
            view = convertView;
        }


        //读取groupBean对象，也就是部门数据
        GroupBean groupBean = (GroupBean) getGroup(groupPosition);

        TextView textView = (TextView) view.findViewById(R.id.bus_view_title);
        //为TextView设置text, 这里就是我们读取到的groupBean对象中的部门名称变量
        textView.setText(groupBean.getTitle());


        //设置完成，把view返回给ExpandableList
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //加载Group布局
        View view;
        if (convertView == null) {
            //convertView是布局的缓存，如果缓存为空的话，就加载布局到view
            view = LayoutInflater.from(context).inflate(childrenLayout, parent, false);
        } else {
            //如果convert不为空，说明有缓存，直接把缓存赋值给view
            view = convertView;
        }


        ItemBean itemBean = (ItemBean) getChild(groupPosition, childPosition);

        TextView textView = (TextView) view.findViewById(R.id.bus_view_content);
        TextView textView2 = (TextView) view.findViewById(R.id.bus_view_content_two);
        ImageView img = view.findViewById(R.id.bus_img);
        //为TextView设置text, 这里就是我们读取到的groupBean对象中的部门名称变量
        textView.setText(itemBean.getContent());
        textView2.setText(itemBean.getContentTwo()); //距离
        img.setImageResource(itemBean.getImg()); //公交图标


        //设置完成，把view返回给ExpandableList
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            notifyDataSetChanged();//更新数据
            super.handleMessage(msg);
        }
    };



    /*供外界更新数据的方法*/
    public void refresh(ExpandableListView expandableListView, int groupPosition){
        handler.sendMessage(new Message());
        //必须重新伸缩之后才能更新数据
        if(!expandableListView.isGroupExpanded(groupPosition)) {
            expandableListView.expandGroup(groupPosition);
        } else {
            expandableListView.collapseGroup(groupPosition);
        }


    }


    public void refresh_on(ExpandableListView expandableListView, int groupPosition) {
        handler.sendMessage(new Message());
        expandableListView.expandGroup(groupPosition);
    }

    public void refresh_off(ExpandableListView expandableListView, int groupPosition) {
        handler.sendMessage(new Message());
        expandableListView.collapseGroup(groupPosition);
    }

}
