package its.hzh.com.its_system.viewpage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import its.hzh.com.its_system.R;
import its.hzh.com.its_system.db.User;
import its.hzh.com.its_system.userPage.LoginActivity;

/**
 * Guidence Page
 * Created by ken on 2018/3/8.
 */

public class ViewPageActivity extends FragmentActivity {

    private ViewPager viewPager;
    private LinearLayout linearLayout;

    private PagerAdapter adapter;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        linearLayout = (LinearLayout)findViewById(R.id.ll_indicator);
        date();

        //使屏幕保持横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //创建FRAGMENT
        for(int i = 0; i<3; i++) {
            ContentFragment fragment = new ContentFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int index, float positionOffset, int positionOffsetPixels) {
                for (int i=0; i<fragmentList.size(); i++) {
                    linearLayout.getChildAt(i).setBackgroundResource(index==i?
                            R.drawable.dot_focus : R.drawable.dot_normal);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initIndictor();
    }

    private void initIndictor() {
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f,
                getResources().getDisplayMetrics());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, width);
        lp.rightMargin = 2*width;
        for (int i=0; i<fragmentList.size(); i++) {

            View view = new View(this);
            view.setId(i);
            view.setBackgroundResource(i==0?R.drawable.dot_focus : R.drawable.dot_normal);
            view.setLayoutParams(lp);
            linearLayout.addView(view, i);
        }
    }


    private void date() {
        SharedPreferences shared = getSharedPreferences("newOrOld", MODE_PRIVATE);
        boolean isnew = shared.getBoolean("isnew", true);
        SharedPreferences.Editor editor = shared.edit();
        if (isnew) {

            //第一次进入跳转前先建立数据库
            User user = new User();
            user.setUsername("root");
            user.setPassword("123456");
            user.setMailbox("aaa@aaa.aaa");
            user.save();
            //第一次进入设置isnew为false
            editor.putBoolean("isnew", false);
            editor.commit();
        } else {
            //第二次进入跳转

            startActivity(new Intent(this, LoginActivity.class));
            finish();

        }
    }
}
