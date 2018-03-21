package its.hzh.com.its_system.userMain.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import its.hzh.com.its_system.R;

/**
 * Created by ken on 2018/3/16.
 */

public class DialogItemAdapter extends BaseAdapter {


    public int maxPeople = 0;
    public ViewHolder holder;
    public List<DataHolder> list;
    LayoutInflater inflater;

//    String[] randomDataArr = new String[15];

    public DialogItemAdapter(Context context, List<DataHolder> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
        cdt.start();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public DataHolder getItem(int position) {
        if(position==getCount()||list==null) {
            return null;
        }
        return list.get(position);
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.bus_dialog_item, null);
            holder.txt1_view = convertView.findViewById(R.id.item_txt1);
            holder.txt2_view = convertView.findViewById(R.id.item_txt2);
            holder.txt3_view = convertView.findViewById(R.id.item_txt3);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txt1_view.setText(getItem(position).txt1_data);
        holder.txt2_view.setText(getItem(position).txt2_data);
        holder.txt3_view.setText(getItem(position).txt3_data);
        return convertView;
    }


    public static class ViewHolder{
        public TextView txt1_view;
        public TextView txt2_view;
        public TextView txt3_view;
    }
    public static class DataHolder {
        public String txt1_data;
        public String txt2_data;
        public String txt3_data;

        public DataHolder(String txt1_data, String txt2_data, String txt3_data) {
            this.txt1_data = txt1_data;
            this.txt2_data = txt2_data;
            this.txt3_data = txt3_data;
        }
    }

    //定时器
    public CountDownTimer cdt  = new CountDownTimer(12000, 3000) {
        @Override
        public void onTick(long millisUntilFinished) {

            for(int i=1; i<list.size(); i++) {
                int value = randomDatas(0,100);
                maxPeople+=value;
                list.set(i, new DialogItemAdapter.DataHolder(String.valueOf(i),String.valueOf(i), String.valueOf(value) ));
            }
            Message msg = new Message();
            mHandler.sendMessage(msg);

        }

        @Override
        public void onFinish() {
            cdt.start();
        }
    };



    //改变条件
    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //update
            notifyDataSetChanged();
        }
    };

    //随机函数
    public int randomDatas(int minnum, int maxnum){
        return (int)(minnum+Math.random()*(maxnum-minnum+1));
    }
}
