package its.hzh.com.its_system.userPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import its.hzh.com.its_system.BaseActivity;
import its.hzh.com.its_system.CheckLogin;
import its.hzh.com.its_system.R;
import its.hzh.com.its_system.db.User;
import its.hzh.com.its_system.userMain.UserMainActivity;
import its.hzh.com.its_system.util.Constant;

/**
 * It is used for logining in the UserMainActivity
 * Created by ken on 2018/3/8.
 */

public class LoginActivity extends BaseActivity {

    private EditText usernametext;
    private EditText passwordtext;
    private Button login;
    private Button register;
    private Button forgetpass;
    private boolean autoLoginCheck = false;
    private CheckBox autoCheckBox;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //判断自动登录
        autoLogin_check();
        autoCheckBox = findViewById(R.id.checkbox);

        usernametext = findViewById(R.id.login_username);
        passwordtext = findViewById(R.id.login_password);
        login = findViewById(R.id.login_login);
        register = findViewById(R.id.login_register);
        forgetpass = findViewById(R.id.forgetpassword);

        //设置按钮监听
        login.setOnClickListener(v -> {
            loginConfirm();
        });

        register.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        forgetpass.setOnClickListener(v -> {
            startActivity(new Intent(this, FindPasswordActivity.class));
        });



    }

    //登录事件
    private void loginConfirm() {
        usernametext = findViewById(R.id.login_username);
        passwordtext = findViewById(R.id.login_password);

        String myUsername = usernametext.getText().toString().trim();
        String myPassword = passwordtext.getText().toString().trim();

        //模式
        Pattern p_user = Pattern.compile(Constant.reg_user);
        Pattern p_pass = Pattern.compile(Constant.reg_pass);

        //匹配
        Matcher m_user = p_user.matcher(myUsername);
        Matcher m_pass = p_pass.matcher(myPassword);

        //数据库查询
        boolean check_username = false;
        boolean check_password = false;
        List<User> user = DataSupport.where("username=?",myUsername).find(User.class);
        if(user.isEmpty()) {
            check_username = false;
        } else {
            check_username = true;
            for(User user_s : user) {
                if(myPassword.equals( user_s.getPassword() ) ) {
                    check_password = true;
                } else {
                    check_password = false;
                }
            }
        }


        if(m_user.find() && m_pass.find()) {

            if(check_username && check_password) {
                if(autoLoginCheck) {
                    CheckLogin.getInstance().saveUserInfo(this, myUsername, myPassword);
                }
                startActivity(new Intent(this, UserMainActivity.class));
                Toast.makeText(this,"登入成功",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"账号或密码错误",Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this,"输入不合法，请检查输入是否正确",Toast.LENGTH_LONG).show();
        }

    }


    public void autoLogin(View v) {
        if(autoCheckBox.isChecked()) {
            autoLoginCheck = true;
        } else {
            autoLoginCheck = false;
        }
    }


    private void autoLogin_check() {
        if(CheckLogin.getInstance().hasUserInfo(this)){
            startActivity(new Intent(this, UserMainActivity.class));
        }else;
    }

}
