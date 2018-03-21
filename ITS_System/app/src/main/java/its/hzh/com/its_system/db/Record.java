package its.hzh.com.its_system.db;

import org.litepal.crud.DataSupport;

/**
 * the table of Record
 * Created by ken on 2018/3/8.
 */

public class Record extends DataSupport{
    private int id;
    private int rechargeMoney;
    private int balance;
    private String timeText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(int rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getTimeText() {
        return timeText;
    }

    public void setTimeText(String timeText) {
        this.timeText = timeText;
    }


}
