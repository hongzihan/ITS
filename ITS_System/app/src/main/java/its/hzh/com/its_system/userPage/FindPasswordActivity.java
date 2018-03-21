package its.hzh.com.its_system.userPage;

import android.os.Bundle;
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
 * used for finding password
 * Created by ken on 2018/3/8.
 */

public class FindPasswordActivity extends BaseActivity {

    private EditText forget_username;
    private EditText forget_mailbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);

        //设置title
        TextView textView = findViewById(R.id.front_text);
        textView.setText("找回密码");

        //设置返回
        Button btn_back = findViewById(R.id.front_back);
        btn_back.setOnClickListener(v -> {
            finish();
        });

        //找回监听
        Button register = findViewById(R.id.forget_confirm);
        register.setOnClickListener(v -> {
            fogetConfirm();
        });
    }

    //找回事件
    private void fogetConfirm() {
        forget_username = findViewById(R.id.forget_username);
        forget_mailbox = findViewById(R.id.forget_mailbox);
        String username = forget_username.getText().toString().trim();
        String mailbox = forget_mailbox.getText().toString().trim();

        //模式
        Pattern p_username = Pattern.compile(Constant.reg_user);
        Pattern p_mailbox = Pattern.compile(Constant.reg_mail);
        //匹配
        Matcher m_username = p_username.matcher(username);
        Matcher m_mailbox = p_mailbox.matcher(mailbox);

        //数据库查询
        List<User> user = DataSupport.select("password").where("username=?",username).find(User.class);
        boolean check_find = false;
        if(user.isEmpty()) {
            check_find = false;
        } else {
            check_find = true;
        }

        if(m_username.find() && m_mailbox.find()) {
            if(check_find) {
                    Toast.makeText(this,"找回成功"+"您的密码是" + user.get(0).getPassword(),Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"无此用户，请检查用户名是否正确",Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this,"输入不合法，请检查输入",Toast.LENGTH_LONG).show();
        }
    }
}
