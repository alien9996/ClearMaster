package dell.example.com.cleanmaster.uis.activity;

import android.content.Context;
import android.provider.Settings;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.uis.BaseActivity;

public class PowerSavingComplition extends BaseActivity {
    @Override
    protected int injectLayout() {
        return R.layout.powersaving_completion;
    }

    @Override
    protected void injectView() {

    }


    public static void setAutoOrientationEnabled(Context context, boolean enabled)
    {
        Settings.System.putInt( context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, enabled ? 1 : 0);
    }
    @Override
    protected void injectVariables() {

    }
}
