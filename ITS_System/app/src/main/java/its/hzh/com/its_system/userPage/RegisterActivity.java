package its.hzh.com.its_system.userPage;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import its.hzh.com.its_system.BaseActivity;
import its.hzh.com.its_system.R;
import its.hzh.com.its_system.db.User;
import its.hzh.com.its_system.util.Constant;

/**
 * It is used for registering the user data
 * Created by ken on 2018/3/8.
 */

public class RegisterActivity extends BaseActivity {

    private EditText username;
    private EditText mailbox;
    private EditText password;
    private EditText repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //设置title
        TextView textView = findViewById(R.id.front_text);
        textView.setText("注册");

        //设置返回
        Button btn_back = findViewById(R.id.front_back);
        btn_back.setOnClickListener(v -> {
            finish();
        });

        //注册监听
        Button register = findViewById(R.id.register_confirm);
        register.setOnClickListener(v -> {
            registerConfirm();
        });
    }

    //注册事件
    private void registerConfirm() {
        username = findViewById(R.id.register_username);
        mailbox = findViewById(R.id.register_mailbox);
        password = findViewById(R.id.register_password);
        repassword = findViewById(R.id.register_repassword);
        String myUsername = username.getText().toString().trim();
        String myMailbox = mailbox.getText().toString().trim();
        String myPassword = password.getText().toString().trim();
        String myRepassword = repassword.getText().toString().trim();



        //模板
        Pattern p_user = Pattern.compile(Constant.reg_user);
        Pattern p_pass = Pattern.compile(Constant.reg_pass);
        Pattern p_mail = Pattern.compile(Constant.reg_mail);

        //匹配器
        Matcher m_user = p_user.matcher(myUsername);
        Matcher m_pass = p_pass.matcher(myPassword);
        Matcher m_repass = p_pass.matcher(myRepassword);
        Matcher m_mail = p_mail.matcher(myMailbox);

        //检查是否已经存在
        boolean check_user = false;
//        boolean check_mailbox = false;
        List<User> user1 = DataSupport.where("username=?", myUsername).find(User.class);
//        List<User_Auto> user2 = DataSupport.where("mailbox=?", myMailbox).find(User_Auto.class);
        if(user1.size()>0) {
            check_user = true;
        } else {
            check_user = false;
        }
//        if(user2.size()>0) {
//            check_mailbox = true;
//        }

//        else if(check_mailbox) {
//            Toast.makeText(this,"该邮箱已经存在，请重新输入",Toast.LENGTH_LONG).show();
//        }

        //注册按钮事件
         if(m_user.find() && m_pass.find() && m_repass.find() && m_mail.find()) {
             if(check_user) {
                 Toast.makeText(this,"该用户已经存在，请重新输入",Toast.LENGTH_LONG).show();
             } else if(!myPassword.equals(myRepassword)) {
                 Toast.makeText(this,"账号密码不一致，请重新输入",Toast.LENGTH_LONG).show();
             } else {
                 User user = new User();
                 user.setUsername(myUsername);
                 user.setPassword(myPassword);
                 user.setMailbox(myMailbox);
                 user.save();
                 Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
             }
        } else{
            Toast.makeText(this,"输入非法，请检查输入是否正确",Toast.LENGTH_SHORT).show();
        }
    }
}
