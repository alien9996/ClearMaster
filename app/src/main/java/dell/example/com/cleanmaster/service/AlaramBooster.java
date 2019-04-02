package dell.example.com.cleanmaster.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.uis.fragment.PhoneBooster;

public class AlaramBooster extends BroadcastReceiver {

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    @Override
    public void onReceive(Context context, Intent intent) {
        sharedPreferences = context.getSharedPreferences("waseem", Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();
        editor.putString("booster", "1");
        editor.commit();

        try {

            PhoneBooster.optimizebutton.setImageResource(0);
            PhoneBooster.optimizebutton.setBackgroundResource(0);
            PhoneBooster.optimizebutton.setImageResource(R.drawable.optimize);
        } catch (Exception ex) {
            Log.e("ALARAMBOOSTER", "" + ex);
        }

    }
}
