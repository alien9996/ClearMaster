package dell.example.com.cleanmaster.uis.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.model.Apps;
import dell.example.com.cleanmaster.uis.BaseFragment;
import dell.example.com.cleanmaster.uis.activity.MainActivity;


public class ListenerSetting extends BaseFragment {


    public static List<Apps> apps;

    // create broadcastReceiver
    BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            try {

            } catch (Exception ex) {
                Log.e("MAIN Listener Setting", ex.getMessage());
            }

        }
    };

    @Override
    protected int injectLayout() {
        return R.layout.listener_setting;
    }


    @Override
    protected void injectView() {

        try {

        } catch (Exception ex) {
            Log.e("MAIN Listener Setting", ex.getMessage());
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {

        } catch (Exception e) {

        }
    }

    @Override
    public boolean getUserVisibleHint() {

        MainActivity.name.setText("Listener Setting");
        return getUserVisibleHint();

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);


        if (isVisibleToUser) {
            MainActivity.name.setText("Listener Setting");

        } else {

        }
    }


    @Override
    protected void injectVariables() {

    }

}
