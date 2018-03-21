package its.hzh.com.its_system.userMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import its.hzh.com.its_system.R;

/**
 * Created by ken on 2018/3/10.
 */

public class rightInitialTest extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.right_initial_fragment, container, false);
        return view;
    }
}
