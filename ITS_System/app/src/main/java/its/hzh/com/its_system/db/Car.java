package its.hzh.com.its_system.db;

import org.litepal.crud.DataSupport;

/**
 * the table of Car
 * Created by ken on 2018/3/8.
 */

public class Car extends DataSupport{


    private int id;
    private int userId;
    private int carId;
    private int carBalance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getCarBalance() {
        return carBalance;
    }

    public void setCarBalance(int carBalance) {
        this.carBalance = carBalance;
    }


}
