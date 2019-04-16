package dell.example.com.cleanmaster.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;

import java.util.Random;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.uis.fragment.JunkCleaner;

public class AlaramJunk extends BroadcastReceiver {

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;

    @Override
    public void onReceive(Context context, Intent intent) {
        sharedPreferences  = context.getSharedPreferences("waseem", Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();
        editor.putString("junk", "1");
        editor.commit();

        try {

            JunkCleaner.mainbrush.setImageResource(R.drawable.junk_red);
            JunkCleaner.mainbutton.setImageResource(R.drawable.clean);
            JunkCleaner.cache.setImageResource(R.drawable.cache);
            JunkCleaner.temp.setImageResource(R.drawable.temp);
            JunkCleaner.residue.setImageResource(R.drawable.res);
            JunkCleaner.system.setImageResource(R.drawable.sys);

            Random random = new Random();

            final int proc1 = random.nextInt(20) + 5;
            final int proc2 = random.nextInt(15) + 10;
            final int proc3 = random.nextInt(30) + 15;
            final int proc4 = random.nextInt(25) + 10;

            JunkCleaner.alljunk = proc1 + proc2 + proc3 + proc4;

            JunkCleaner.maintext.setText(JunkCleaner.alljunk + " MB");
            JunkCleaner.maintext.setTextColor(Color.parseColor("#F22938"));

            JunkCleaner.cachetext.setText(proc1 + " MB");
            JunkCleaner.cachetext.setTextColor(Color.parseColor("#F22938"));

            JunkCleaner.temptext.setText(proc2 + " MB");
            JunkCleaner.temptext.setTextColor(Color.parseColor("#F22938"));

            JunkCleaner.residuetext.setText(proc3 + " MB");
            JunkCleaner.residuetext.setTextColor(Color.parseColor("#F22938"));

            JunkCleaner.systemtext.setText(proc4 + " MB");
            JunkCleaner.systemtext.setTextColor(Color.parseColor("#F22938"));
        } catch (Exception ex) {
            Log.e("ERR AlaramJunk", ex.getMessage());
        }

    }
}
