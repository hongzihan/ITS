package its.hzh.com.its_system.userMain.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import its.hzh.com.its_system.BaseFragment;
import its.hzh.com.its_system.R;
import its.hzh.com.its_system.db.Car;
import its.hzh.com.its_system.userMain.dialog.CustomDialog;
import its.hzh.com.its_system.util.Constant;

/**
 * Created by ken on 2018/3/10.
 */

public class manageFragment extends BaseFragment {

    private LinearLayout ll1;
    private LinearLayout ll2;
    private LinearLayout ll3;
    private LinearLayout ll4;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private CheckBox check_1;
    private CheckBox check_2;
    private CheckBox check_3;
    private CheckBox check_4;
    private TextView account_1;
    private TextView account_2;
    private TextView account_3;
    private TextView account_4;

    private Button largeBtn;

    //用于单一
    List<Integer> carIdList = new ArrayList<>();
    List<String> carNameList = new ArrayList<>();

    //用于批量
    List<Integer> carLargeIdList = new ArrayList<>();
    List<String> carLargeNameList = new ArrayList<>();

    public static int minBalance = 100;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_usermanage, container, false);


        //id获取
        ll1 = view.findViewById(R.id.car_layout_1);
        ll2 = view.findViewById(R.id.car_layout_2);
        ll3 = view.findViewById(R.id.car_layout_3);
        ll4 = view.findViewById(R.id.car_layout_4);
        check_1 = view.findViewById(R.id.manage_check_one);
        check_2 = view.findViewById(R.id.manage_check_two);
        check_3 = view.findViewById(R.id.manage_check_three);
        check_4 = view.findViewById(R.id.manage_check_four);
        account_1 = view.findViewById(R.id.manage_account_one);
        account_2 = view.findViewById(R.id.manage_account_two);
        account_3 = view.findViewById(R.id.manage_account_three);
        account_4 = view.findViewById(R.id.manage_account_four);
        btn_1 = view.findViewById(R.id.recharge_btn_one);
        btn_2 = view.findViewById(R.id.recharge_btn_two);
        btn_3 = view.findViewById(R.id.recharge_btn_three);
        btn_4 = view.findViewById(R.id.recharge_btn_four);


        //初始化界面
        initialize();


        //启动定时器
        cdt.start();


        //CheckBox事件部分
        check_1.setOnClickListener(v -> {
            check_1();
        });
        check_2.setOnClickListener(v -> {
            check_2();
        });
        check_3.setOnClickListener(v -> {
            check_3();
        });
        check_4.setOnClickListener(v -> {
            check_4();
        });


        //按钮点击事件部分
        btn_1.setOnClickListener(v -> {
            recharge(Constant.ID1, Constant.NAME1);
            List<Car> car1 = DataSupport.findAll(Car.class);
            account_1.setText("余额：" + car1.get(0).getCarBalance());
            if(car1.get(0).getCarBalance() <= minBalance) {
                ll1.setBackgroundColor(getResources().getColor(R.color.layout_min_back));
            } else {
                ll1.setBackgroundColor(Color.WHITE);
            }
        });

        btn_2.setOnClickListener(v -> {
            recharge(Constant.ID2, Constant.NAME2);
            List<Car> car2 = DataSupport.findAll(Car.class);
            account_2.setText("余额：" + car2.get(1).getCarBalance());
            if(car2.get(1).getCarBalance() <= minBalance) {
                ll2.setBackgroundColor(getResources().getColor(R.color.layout_min_back));
            } else {
                ll2.setBackgroundColor(Color.WHITE);
            }
        });

        btn_3.setOnClickListener(v -> {
            recharge(Constant.ID3, Constant.NAME3);
            List<Car> car3 = DataSupport.findAll(Car.class);
            account_3.setText("余额：" + car3.get(2).getCarBalance());
            if(car3.get(2).getCarBalance() <= minBalance) {
                ll3.setBackgroundColor(getResources().getColor(R.color.layout_min_back));
            } else {
                ll3.setBackgroundColor(Color.WHITE);
            }
        });

        btn_4.setOnClickListener(v -> {
            recharge(Constant.ID4, Constant.NAME4);
            List<Car> car4 = DataSupport.findAll(Car.class);
            account_4.setText("余额：" + car4.get(3).getCarBalance());
            if(car4.get(3).getCarBalance() <= minBalance) {
                ll4.setBackgroundColor(getResources().getColor(R.color.layout_min_back));
            } else {
                ll4.setBackgroundColor(Color.WHITE);
            }
        });


        //批量充值监听器
        largeBtn = getActivity().findViewById(R.id.front_manage);
        largeBtn.setOnClickListener(v -> {
            largerecharge_fr();
        });


        return view;
    }


    /**
     * 充值方法
     * @param ID 小车ID
     * @param NAME 小车名字
     */
    public void recharge(final int ID, final String NAME) {
        carIdList.clear();
        carIdList.add(ID);
        carNameList.clear();
        carNameList.add(NAME);
        CustomDialog cd = new CustomDialog(getActivity(), carNameList, carIdList);
        //去除dialog自带标题栏
        cd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        cd.setCancelable(false);
        cd.show();
    }


    /**
     * 用于批量充值的检查
     */
    public void check_1() {
        if(check_1.isChecked()) {
            carLargeIdList.add(Constant.ID1);
            carLargeNameList.add(Constant.NAME1);
        } else {
            for(int flag1=0; flag1<carLargeIdList.size(); flag1++) {
                if(carLargeIdList.get(flag1) == Constant.ID1) {
                    carLargeIdList.remove(flag1);
                    break;
                }
            }
            for(int flag2=0; flag2<carLargeNameList.size(); flag2++) {
                if(carLargeNameList.get(flag2).equals(Constant.NAME1)) {
                    carLargeNameList.remove(flag2);
                    break;
                }
            }
        }
    }

    public void check_2() {
        if(check_2.isChecked()) {
            carLargeIdList.add(Constant.ID2);
            carLargeNameList.add(Constant.NAME2);
        } else {
            for(int flag1=0; flag1<carLargeIdList.size(); flag1++) {
                if(carLargeIdList.get(flag1) == Constant.ID2) {
                    carLargeIdList.remove(flag1);
                    break;
                }
            }
            for(int flag2=0; flag2<carLargeNameList.size(); flag2++) {
                if(carLargeNameList.get(flag2).equals(Constant.NAME2)) {
                    carLargeNameList.remove(flag2);
                    break;
                }
            }
        }
    }

    public void check_3() {
        if(check_3.isChecked()) {
            carLargeIdList.add(Constant.ID3);
            carLargeNameList.add(Constant.NAME3);
        } else {
            for(int flag1=0; flag1<carLargeIdList.size(); flag1++) {
                if(carLargeIdList.get(flag1) == Constant.ID3) {
                    carLargeIdList.remove(flag1);
                    break;
                }
            }
            for(int flag2=0; flag2<carLargeNameList.size(); flag2++) {
                if(carLargeNameList.get(flag2).equals(Constant.NAME3)) {
                    carLargeNameList.remove(flag2);
                    break;
                }
            }
        }
    }

    public void check_4() {
        if(check_4.isChecked()) {
            carLargeIdList.add(Constant.ID4);
            carLargeNameList.add(Constant.NAME4);
        } else {
            for(int flag1=0; flag1<carLargeIdList.size(); flag1++) {
                if(carLargeIdList.get(flag1) == Constant.ID4) {
                    carLargeIdList.remove(flag1);
                    break;
                }
            }
            for(int flag2=0; flag2<carLargeNameList.size(); flag2++) {
                if(carLargeNameList.get(flag2).equals(Constant.NAME4)) {
                    carLargeNameList.remove(flag2);
                    break;
                }
            }
        }
    }

    /**
     * 批量充值方法
     */
    public void largerecharge_fr() {
        CustomDialog cd = new CustomDialog(getActivity(), carLargeNameList, carLargeIdList);
        //去除dialog自带标题栏
        cd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        cd.setCancelable(false);
        cd.show();
    }


    //定时器
    public CountDownTimer cdt  = new CountDownTimer(12000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            List<Car> cars = DataSupport.findAll(Car.class);
            //警告标识部分
            if(cars.get(0).getCarBalance() <= minBalance) {
                ll1.setBackgroundColor(getResources().getColor(R.color.layout_min_back));
            } else {
                ll1.setBackgroundColor(Color.WHITE);
            }
            if(cars.get(1).getCarBalance() <= minBalance) {
                ll2.setBackgroundColor(getResources().getColor(R.color.layout_min_back));
            } else {
                ll2.setBackgroundColor(Color.WHITE);
            }
            if(cars.get(2).getCarBalance() <= minBalance) {
                ll3.setBackgroundColor(getResources().getColor(R.color.layout_min_back));
            } else {
                ll3.setBackgroundColor(Color.WHITE);
            }
            if(cars.get(3).getCarBalance() <= minBalance) {
                ll4.setBackgroundColor(getResources().getColor(R.color.layout_min_back));
            } else {
                ll4.setBackgroundColor(Color.WHITE);
            }


            //余额显示部分
            account_1.setText("余额：" + cars.get(0).getCarBalance());
            account_2.setText("余额：" + cars.get(1).getCarBalance());
            account_3.setText("余额：" + cars.get(2).getCarBalance());
            account_4.setText("余额：" + cars.get(3).getCarBalance());

        }

        @Override
        public void onFinish() {
            cdt.start();
        }
    };

    public void initialize() {
        //查询小车数据表
        List<Car> cars = DataSupport.findAll(Car.class);

        //警告标识部分
        if(cars.get(0).getCarBalance() <= minBalance) {
            ll1.setBackgroundColor(getResources().getColor(R.color.layout_min_back));
        } else {
            ll1.setBackgroundColor(Color.WHITE);
        }
        if(cars.get(1).getCarBalance() <= minBalance) {
            ll2.setBackgroundColor(getResources().getColor(R.color.layout_min_back));
        } else {
            ll2.setBackgroundColor(Color.WHITE);
        }
        if(cars.get(2).getCarBalance() <= minBalance) {
            ll3.setBackgroundColor(getResources().getColor(R.color.layout_min_back));
        } else {
            ll3.setBackgroundColor(Color.WHITE);
        }
        if(cars.get(3).getCarBalance() <= minBalance) {
            ll4.setBackgroundColor(getResources().getColor(R.color.layout_min_back));
        } else {
            ll4.setBackgroundColor(Color.WHITE);
        }


        //余额显示部分
        account_1.setText("余额：" + cars.get(0).getCarBalance());
        account_2.setText("余额：" + cars.get(1).getCarBalance());
        account_3.setText("余额：" + cars.get(2).getCarBalance());
        account_4.setText("余额：" + cars.get(3).getCarBalance());
    }
}
