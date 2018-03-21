package its.hzh.com.its_system.userMain.fragment.perFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import its.hzh.com.its_system.BaseFragment;
import its.hzh.com.its_system.R;

/**
 * Created by ken on 2018/3/18.
 */

public class valueFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_per_value, container, false);
        return view;
    }
}
