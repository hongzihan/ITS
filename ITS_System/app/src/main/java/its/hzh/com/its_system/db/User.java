package its.hzh.com.its_system.db;

import org.litepal.crud.DataSupport;

/**
 * the table of User_Auto
 * Created by ken on 2018/3/8.
 */

public class User extends DataSupport{
    private int id;
    private String username;
    private String password;
    private String mailbox;

    public int getUserId() {
        return id;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }
}
