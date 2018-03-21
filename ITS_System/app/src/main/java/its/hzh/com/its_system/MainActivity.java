package its.hzh.com.its_system;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import org.litepal.tablemanager.Connector;

import its.hzh.com.its_system.db.User;
import its.hzh.com.its_system.userPage.LoginActivity;
import its.hzh.com.its_system.viewpage.ViewPageActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Connector.getDatabase();

        date();
    }

    private void date() {
        SharedPreferences shared = getSharedPreferences("newOrOld", MODE_PRIVATE);
        boolean isnew = shared.getBoolean("isnew", true);
        SharedPreferences.Editor editor = shared.edit();
        if (isnew) {

            //第一次进入跳转前先建立数据库
            //管理员用户
            User user = new User();
            user.setUsername("root");
            user.setPassword("123456");
            user.setMailbox("aaa@aaa.aaa");
            user.save();
            //小车账户




            //第一次进入跳转
            startActivity(new Intent(MainActivity.this, ViewPageActivity.class));
            finish();
            editor.putBoolean("isnew", false);
            editor.commit();
        } else {
            //第二次进入跳转
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();


        }
    }






}
