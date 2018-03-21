package its.hzh.com.its_system.viewpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import its.hzh.com.its_system.R;
import its.hzh.com.its_system.userPage.LoginActivity;

/**
 * Image fragment
 * Created by ken on 2018/3/8.
 */

public class ContentFragment extends Fragment {

    private int[] bgRes = {R.drawable.viewpager_1, R.drawable.viewpager_2, R.drawable.viewpager_3};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content, null);
        Button fcbtn = (Button)view.findViewById(R.id.fcbtn);
        RelativeLayout fcrl = (RelativeLayout)view.findViewById(R.id.fcrl);

        int index = getArguments().getInt("index");
        fcrl.setBackgroundResource(bgRes[index]);

        //set the click monitor method
        fcbtn.setOnClickListener(v->{
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });

        fcbtn.setVisibility(index==2?View.VISIBLE:View.GONE);

        return view;

    }
}
