package its.hzh.com.its_system;

/**
 * Created by ken on 2018/3/12.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import its.hzh.com.its_system.userPage.LoginActivity;


public class CheckLogin {

    private static CheckLogin instance;

    private CheckLogin() {
    }

    public static CheckLogin getInstance() {
        if (instance == null) {
            instance = new CheckLogin();
        }
        return instance;
    }


    /**
     * 保存自动登录的用户信息
     */
    public void saveUserInfo(Context context, String username, String password) {
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        //Context.MODE_PRIVATE表示SharePrefences的数据只有自己应用程序能访问。
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("USER_NAME", username);
        editor.putString("PASSWORD", password);
        editor.commit();
    }


    /**
     * 获取用户信息model
     *
     * @param context
     * @param
     * @param
     */
    public User_Auto getUserInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        User_Auto userAuto = new User_Auto();
        userAuto.setUsername(sp.getString("USER_NAME", ""));
        userAuto.setPassword(sp.getString("PASSWORD", ""));
        return userAuto;
    }


    /**
     * userInfo中是否有数据
     * @param context
     */
    public boolean hasUserInfo(LoginActivity context) {
        User_Auto userAuto = getUserInfo(context);
        if (userAuto != null) {
            if ((!TextUtils.isEmpty(userAuto.getUsername())) && (!TextUtils.isEmpty(userAuto.getPassword()))) {//有数据
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
