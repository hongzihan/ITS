package its.hzh.com.its_system;

/**
 * Created by ken on 2018/3/12.
 */

public class User_Auto {
    private static String username;
    private static String password;
    private static Integer id;
    private static int autoLogin=-1;

    public static int getAutoLogin() {
        return autoLogin;
    }

    public static void setAutoLogin(int autoLogin) {
        User_Auto.autoLogin = autoLogin;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User_Auto.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User_Auto.password = password;
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        User_Auto.id = id;
    }
}
