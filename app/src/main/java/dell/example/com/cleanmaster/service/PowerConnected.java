package dell.example.com.cleanmaster.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import dell.example.com.cleanmaster.uis.activity.MainActivity;


public class PowerConnected extends BroadcastReceiver{

    // Broad cast that listen for charger connecd Events
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent1 = new Intent(context, MainActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
