package its.hzh.com.its_system.util;

/**
 * Created by ken on 2018/3/12.
 */

public class Constant {

    //正则
    public static final String reg_user = "^[a-zA-Z]\\w{3,21}$";//以a-z,A-Z开头，最少4个最多20个的字符串
    public static final String reg_pass = "^[0-9a-zA-Z]\\w{5,21}$";//以a-z,A-Z，0-9开头，最少6个最多20个的字符串
    public static final String reg_mail = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:" +
            "[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";//符合邮箱格式即可

    //other
        //小车id,name
        public static final int ID1=1, ID2=2, ID3=3, ID4=4;
        public static final String NAME1="小车一",NAME2="小车二",NAME3="小车三",NAME4="小车四";
}
