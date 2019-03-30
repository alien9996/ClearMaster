package dell.example.com.cleanmaster.uis.activity;

import android.os.Build;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.uis.BaseActivity;

public class BatterySaverBlack extends BaseActivity {
    @Override
    protected int injectLayout() {
        if (Build.VERSION.SDK_INT >= 17) {
            return R.layout.powersaving_maintextclock;
        } else {
            return R.layout.powersaving_main;
        }
    }

    @Override
    protected void injectView() {

    }

    @Override
    protected void injectVariables() {

    }
}
