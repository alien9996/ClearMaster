package dell.example.com.cleanmaster.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class AlaramJunk extends BroadcastReceiver {

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;

    @Override
    public void onReceive(Context context, Intent intent) {
        sharedPreferences  = context.getSharedPreferences("waseem", Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();
        editor.putString("junk", "1");
        editor.commit();
    }
}
