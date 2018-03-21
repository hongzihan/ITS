package its.hzh.com.its_system.userMain.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import its.hzh.com.its_system.BaseFragment;
import its.hzh.com.its_system.R;

/**
 * Created by ken on 2018/3/10.
 */

public class lightFragment extends BaseFragment {

    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_redgreen, container, false);

        spinner = (Spinner) view.findViewById(R.id.redgreen_spinner);
        //数据
        data_list = new ArrayList<String>();
//        路口升序、路口降序、红灯升序、红灯降序、绿灯升序、绿灯降序、黄灯升序和黄灯降序”
        data_list.add("路口升序");
        data_list.add("路口降序");
        data_list.add("红灯升序");
        data_list.add("红灯降序");
        data_list.add("绿灯升序");
        data_list.add("绿灯降序");
        data_list.add("黄灯升序");
        data_list.add("黄灯降序");

        //适配器
        arr_adapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);


        return view;
    }




}
