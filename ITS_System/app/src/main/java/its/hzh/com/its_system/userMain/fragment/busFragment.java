package its.hzh.com.its_system.userMain.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import its.hzh.com.its_system.BaseFragment;
import its.hzh.com.its_system.R;
import its.hzh.com.its_system.userMain.adapter.DialogItemAdapter;
import its.hzh.com.its_system.userMain.adapter.MyExAdapter;
import its.hzh.com.its_system.userMain.bean.GroupBean;
import its.hzh.com.its_system.userMain.bean.ItemBean;

/**
 * 公交查询碎片--调用MyExAdapter实现
 * Created by ken on 2018/3/10.
 */

public class busFragment extends BaseFragment {

    ArrayList<GroupBean> groupList; //存储部门数据
    ArrayList<List> childList; //存储装有人员信息的List
    String[] randomDataArr = new String[15];


    public int maxPeople = 0;
    private TextView max_now;
    private ExpandableListView expandableListView;
    private MyExAdapter adapter;
    private Button btn;
    private AlertDialog.Builder builder;
    private DialogItemAdapter dialogAdapter;

    //公交车当前人数
    private int man_now_one = 0;
    private int man_now_two = 0;
    private int man_now_three = 0;
    private int man_now_four = 0;

    //到达时间
    private String time_one;
    private String time_two;
    private String time_three;
    private String time_four;

    //距离站台位置
    private String distance_one = "5000";
    private String distance_two = "5000";
    private String distance_three = "5000";
    private String distance_four = "5000";
    private Integer speed = 5*60;


    private boolean expand_one = false;
    private boolean expand_two = false;
    private int isOn_one = 0;
    private int isOn_two = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_businquiry, container, false);

        max_now = view.findViewById(R.id.nowMaxPeople);

        //定时器
        cdt.start();
        initData();


        expandableListView = (ExpandableListView) view.findViewById(R.id.expand_bus_button);

        adapter = new MyExAdapter(getActivity(), R.layout.bus_item_group_exlist,
                R.layout.bus_item_exlist,
                groupList,
                childList);


        expandableListView.setAdapter(adapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if(groupPosition == 0) {
                    isOn_one++;
                    if(isOn_one % 2 != 0) {
                        expand_one = true;
                    } else {
                        expand_one = false;
                    }
                    //Toast.makeText(getActivity(),"你点击了1", Toast.LENGTH_SHORT).show();
                }

                if(groupPosition == 1) {
                    isOn_two++;
                    if(isOn_two % 2 != 0) {
                        expand_two = true;
                    } else {
                        expand_two = false;
                    }
                    //Toast.makeText(getActivity(),"你点击了2", Toast.LENGTH_SHORT).show();
                }



                return false;
            }
        });



        //dialog
        btn = view.findViewById(R.id.bus_button);
        btn.setOnClickListener(v -> {
            createDialog();
            builder.show();
            //dialog_on = true;
        });


//        expandableListView.setOnClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                ItemBean itemBean = (ItemBean) childList.get(groupPosition).get(childPosition);
//                Toast.makeText(getActivity(),"你点击了" + itemBean.getContent(), Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });


        return view;
    }

    public void initData() {
        //初始化变量
        groupList = new ArrayList();
        childList = new ArrayList();

        //添加站台信息
        groupList.add(new GroupBean("中医院站"));
        groupList.add(new GroupBean("联想大厦站"));

        //添加公交信息
        ArrayList zy = new ArrayList();
        if( Integer.parseInt(distance_one) < Integer.parseInt(distance_two) ) {
            zy.add(new ItemBean("1号（" + String.valueOf(man_now_one) + "人）", time_one +
                    "分钟到达    距离站台" + distance_one + "米", R.mipmap.bus_car_number));
            zy.add(new ItemBean("2号（" + String.valueOf(man_now_two) + "人）", time_two +
                    "分钟到达    距离站台" + distance_two + "米", R.mipmap.bus_car_number));
        } else {
            zy.add(new ItemBean("2号（" + String.valueOf(man_now_two) + "人）", time_two +
                    "分钟到达    距离站台" + distance_two + "米", R.mipmap.bus_car_number));
            zy.add(new ItemBean("1号（" + String.valueOf(man_now_one) + "人）", time_one +
                    "分钟到达    距离站台" + distance_one + "米", R.mipmap.bus_car_number));
        }

        childList.add(zy);

        ArrayList lx = new ArrayList();
        if( Integer.parseInt(distance_three) < Integer.parseInt(distance_four) ) {
            lx.add(new ItemBean("1号（" + String.valueOf(man_now_three) + "人）", time_three +
                    "分钟到达    距离站台" + distance_three + "米", R.mipmap.bus_car_number));
            lx.add(new ItemBean("2号（" + String.valueOf(man_now_four) + "人）", time_four +
                    "分钟到达    距离站台" + distance_four + "米", R.mipmap.bus_car_number));
        } else {
            lx.add(new ItemBean("2号（" + String.valueOf(man_now_four) + "人）", time_four +
                    "分钟到达    距离站台" + distance_four + "米", R.mipmap.bus_car_number));
            lx.add(new ItemBean("1号（" + String.valueOf(man_now_three) + "人）", time_three +
                    "分钟到达    距离站台" + distance_three + "米", R.mipmap.bus_car_number));
        }

        childList.add(lx);
    }

    CountDownTimer cdt = new CountDownTimer(12000, 3000) {
        @Override
        public void onTick(long millisUntilFinished) {


            //当前人数随机
            man_now_one = randomDatas(0,100);
            man_now_two = randomDatas(0,100);
            man_now_three = randomDatas(0,100);
            man_now_four = randomDatas(0,100);
            //设置当前承载人数
            maxPeople = man_now_one + man_now_three + man_now_two + man_now_four;
            max_now.setText("当前承载 :  " + String.valueOf(maxPeople));

            Integer bus_one, bus_two, bus_three, bus_four;

            //汽车距离
            bus_one = randomDatas(0,5000);
            bus_two = randomDatas(0,5000);
            bus_three = randomDatas(0,5000);
            bus_four = randomDatas(0,5000);
            distance_one = bus_one.toString();
            distance_two = bus_two.toString();
            distance_three = bus_three.toString();
            distance_four = bus_four.toString();

            //到站时间
            time_one = String.valueOf((bus_one/speed));
            time_two = String.valueOf((bus_two/speed));
            time_three = String.valueOf((bus_three/speed));
            time_four = String.valueOf((bus_four/speed));

            //初始化数据
            initData();
            adapter = new MyExAdapter(getActivity(), R.layout.bus_item_group_exlist,
                    R.layout.bus_item_exlist,
                    groupList,
                    childList);

            expandableListView.setAdapter(adapter);
            if(expand_one) {
                adapter.refresh_on(expandableListView, 0);
            } else {
                adapter.refresh_off(expandableListView,0);
            }

            if(expand_two) {
                adapter.refresh_on(expandableListView, 1);
            } else {
                adapter.refresh_off(expandableListView,1);
            }
            //expandableListView.expandGroup(0);

            //adapter.refresh(expandableListView, 0);









        }
        @Override
        public void onFinish() {
            cdt.start();
        }
    };

    //随机函数
    public Integer randomDatas(Integer minnum, Integer maxnum){
        return (int)(minnum+Math.random()*(maxnum-minnum+1));
    }


    public List<DialogItemAdapter.DataHolder> initDatas() {
        List<DialogItemAdapter.DataHolder> list = new ArrayList<DialogItemAdapter.DataHolder>();
        DialogItemAdapter.DataHolder dataHolder0 = new DialogItemAdapter.DataHolder("序号","公交车编号","承载人数");
        list.add(dataHolder0);
        for(int i=1; i<=15; i++) {
            list.add(new DialogItemAdapter.DataHolder(String.valueOf(i),String.valueOf(i), randomDataArr[0]));
        }
        return list;
    }


    public void createDialog() {
        dialogAdapter = new DialogItemAdapter(getActivity(),initDatas());
        builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("公交车载客情况统计");
        builder.setCancelable(false);
        builder.setNegativeButton("合计：1000", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("返回",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.setAdapter(dialogAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
    }



}
