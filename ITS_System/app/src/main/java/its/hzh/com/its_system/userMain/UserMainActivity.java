package its.hzh.com.its_system.userMain;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import its.hzh.com.its_system.BaseActivity;
import its.hzh.com.its_system.CheckLogin;
import its.hzh.com.its_system.R;
import its.hzh.com.its_system.User_Auto;
import its.hzh.com.its_system.db.Car;
import its.hzh.com.its_system.userMain.fragment.busFragment;
import its.hzh.com.its_system.userMain.fragment.dataFragment;
import its.hzh.com.its_system.userMain.fragment.lightFragment;
import its.hzh.com.its_system.userMain.fragment.manageFragment;
import its.hzh.com.its_system.userMain.fragment.perFragment.historyFragment;
import its.hzh.com.its_system.userMain.fragment.perFragment.messageFragment;
import its.hzh.com.its_system.userMain.fragment.perFragment.valueFragment;
import its.hzh.com.its_system.userMain.fragment.personalFragment;
import its.hzh.com.its_system.view.SlidingMenu;

public class UserMainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_back;
    private Button btn_now;
    private Button btn_manage;
    private TextView title_txt;
    private ImageView menu_home;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermain);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }

        User_Auto user_auto = CheckLogin.getInstance().getUserInfo(this);
        btn_now = findViewById(R.id.front_now);
        btn_now.setText("当前用户：" + user_auto.getUsername());

        //用于第一次插入小车数据
        insertCarData();



        //title启动侧滑按钮监听
        btn_back = findViewById(R.id.front_back);
        btn_back.setBackground(getResources().getDrawable(R.drawable.title_select));
        btn_back.setOnClickListener(this);

        //title主题
        title_txt = findViewById(R.id.front_text);
        title_txt.setText("主界面");



//        Button btn = findViewById(R.id.toggleClick);
//        btn.setOnClickListener(v->{
//            sm.toggle();
//        });

        //设置menu监听
        menu_home = findViewById(R.id.menu_home);
        menu_home.setOnClickListener(this);
        TextView tv1 = findViewById(R.id.menuData);
        tv1.setOnClickListener(this);
        TextView tv2 = findViewById(R.id.menuBus);
        tv2.setOnClickListener(this);
        TextView tv3 = findViewById(R.id.menuAccount);
        tv3.setOnClickListener(this);
        TextView tv4 = findViewById(R.id.menuTraffic);
        tv4.setOnClickListener(this);
        TextView tv5 = findViewById(R.id.menuCenter);
        tv5.setOnClickListener(this);
        //设置title2监听
        Button tv6 = findViewById(R.id.title2_history);
        tv6.setOnClickListener(this);
        Button tv7 = findViewById(R.id.title2_value);
        tv7.setOnClickListener(this);
        Button tv8 = findViewById(R.id.title2_message);
        tv8.setOnClickListener(this);
        //设置title监听
        btn_now.setOnClickListener(this);
        btn_manage = findViewById(R.id.front_manage);
        btn_manage.setOnClickListener(this);
        replaceFragment(new rightInitialTest());



    }

    @Override
    public void onClick(View v) {
        SlidingMenu sm = findViewById(R.id.menu);
        title_txt = findViewById(R.id.front_text);
        View title2 = findViewById(R.id.title2);
        switch (v.getId()) {
            case R.id.menuData :
                replaceFragment(new dataFragment());
                sm.closeMenu();
                title_txt.setText("数据分析");
                title2.setVisibility(View.GONE);
                alterTitle();
                break;
            case R.id.menuBus :
                replaceFragment(new busFragment());
                sm.closeMenu();
                title_txt.setText("公交查询");
                title2.setVisibility(View.GONE);
                alterTitle();
                break;
            case R.id.menuAccount :
                replaceFragment(new manageFragment());
                sm.closeMenu();
                title_txt.setText("账户管理");
                title2.setVisibility(View.GONE);
                btn_now.setText("充值记录");
                btn_manage.setText("批量充值");
                break;
            case R.id.menuTraffic :
                replaceFragment(new lightFragment());
                sm.closeMenu();
                title_txt.setText("红绿灯管理");
                title2.setVisibility(View.GONE);
                alterTitle();
                break;
            case R.id.menuCenter :
                replaceFragment(new personalFragment());
                sm.closeMenu();
                title_txt.setText("个人中心");
                title2.setVisibility(View.VISIBLE);
                alterTitle();
                break;
            case R.id.title2_value :
                replaceFragment(new valueFragment());
                sm.closeMenu();
                title_txt.setText("个人中心");
                title2.setVisibility(View.VISIBLE);
                break;
            case R.id.title2_message :
                replaceFragment(new messageFragment());
                sm.closeMenu();
                title_txt.setText("个人中心");
                title2.setVisibility(View.VISIBLE);
                break;
            case R.id.title2_history :
                replaceFragment(new historyFragment());
                sm.closeMenu();
                title_txt.setText("个人中心");
                title2.setVisibility(View.VISIBLE);
                break;
            case R.id.front_manage :
                largeRecharge();
                Toast.makeText(this,"批量充值",Toast.LENGTH_SHORT).show();
                break;
            case R.id.front_now :
                Toast.makeText(this,"充值记录",Toast.LENGTH_SHORT).show();
                break;
            case R.id.front_back :
                sm.toggle();
                break;
            case R.id.menu_home :
                replaceFragment(new rightInitialTest());
                title_txt.setText("主界面");
                sm.closeMenu();
                break;
            default:
                break;
        }
    }

    public void replaceFragment(Fragment rightTestFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.right_frag, rightTestFragment);
        transaction.commit();
    }


    /**
     * 为用户第一次进入插入小车数据
     */
    public void insertCarData() {
        SharedPreferences shared = getSharedPreferences("isFirstInsert", MODE_PRIVATE);
        boolean isFirst = shared.getBoolean("isFirst", true);
        SharedPreferences.Editor editor = shared.edit();
        if(isFirst) {
            for(int i=1; i<=4; i++) {
                Car car = new Car();
                car.setUserId(i);
                car.setCarId(i);
                car.setCarBalance(100);
                car.save();
            }
            System.out.println("已经成功为您插入小车数据！");
            editor.putBoolean("isFirst", false);
            editor.commit();
        }
    }

    /**
     * 用于更改右上角title文字
     */
    public void alterTitle() {
        User_Auto user_auto = CheckLogin.getInstance().getUserInfo(this);
        btn_now.setText("当前用户：" + user_auto.getUsername());
        btn_manage.setText("");
    }

    /**
     * 用于批量充值的实现
     */
    public void largeRecharge() {
       // manageFragment mf = (manageFragment)getFragmentManager().findFragmentById(R.id.userManage);
        //mf.largerecharge_fr();
    }


}
