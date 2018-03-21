package its.hzh.com.its_system.userMain.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import its.hzh.com.its_system.R;
import its.hzh.com.its_system.db.Car;

/**
 * Created by ken on 2018/3/19.
 */

public class CustomDialog extends Dialog {
    Context mContext;
    private String str = "";
    private TextView target_car;
    private Button btn_cancel;
    private Button btn_recharge;
    private TextView target_num;
    private String num="0";
    private int num_in=0;

    List<Integer> carIdList = new ArrayList<>();
    List<String> carNameList = new ArrayList<>();

    public CustomDialog(@NonNull Context context, List<String> carNameList, List<Integer> carIdList) {
        super(context);
        this.mContext = context;
        this.str = str;
        this.carIdList = carIdList;
        this.carNameList = carNameList;
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.recharge_dialog, null);

        //设置小车标题显示
        target_car = layout.findViewById(R.id.dialog_name_target);
        for(String strI : carNameList) {
            str = str + strI + "  ";
        }
        target_car.setText(str);

        //取消按钮
        btn_cancel = layout.findViewById(R.id.recharge_dialog_cancel);
        btn_cancel.setOnClickListener(v -> {
            dismiss();
        });

        //充值按钮
        btn_recharge = layout.findViewById(R.id.recharge_dialog_recharge);
        btn_recharge.setOnClickListener(v -> {
            //充值金额
            target_num = findViewById(R.id.dialog_edit);
            //获取EditText内容并转化为int类型
            num = target_num.getText().toString().trim();
            num_in = Integer.parseInt(num);


            //遍历小车ID,
            for(int i : carIdList) {
                //查询该id的小车
                List<Car> car = DataSupport.select("carBalance").where("carId=?", String.valueOf(i)).find(Car.class);
                //执行充值
                Car car2 = new Car();
                car2.setCarBalance(car.get(0).getCarBalance() + num_in);
                car2.updateAll("carId=?",String.valueOf(i).toString());
                Toast.makeText(getContext(),"成功充值" + num + "元",Toast.LENGTH_SHORT).show();
            }
            dismiss();
        });

        this.setContentView(layout);
    }
}
