package dell.example.com.cleanmaster.uis.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.service.AlaramJunk;
import dell.example.com.cleanmaster.uis.BaseFragment;
import dell.example.com.cleanmaster.uis.activity.MainActivity;
import dell.example.com.cleanmaster.uis.activity.ScaningJunk;

public class JunkCleaner extends BaseFragment {

    public static ImageView mainbrush, cache, temp, residue, system;
    public static TextView maintext, cachetext, temptext, residuetext, systemtext;
    public static ImageView mainbutton;

    int checkvar = 0;
    public static int alljunk;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

//    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//        }
//    };

    @Override
    protected int injectLayout() {
        return R.layout.junk_cleaner;
    }

    @Override
    protected void injectView() {

        mainbrush = view_home.findViewById(R.id.mainbrush);
        cache = view_home.findViewById(R.id.cache);
        temp = view_home.findViewById(R.id.temp);
        residue = view_home.findViewById(R.id.residue);
        system = view_home.findViewById(R.id.system);

        maintext = view_home.findViewById(R.id.maintext);
        cachetext = view_home.findViewById(R.id.cachetext);
        temptext = view_home.findViewById(R.id.temptext);
        residuetext = view_home.findViewById(R.id.residuetext);
        systemtext = view_home.findViewById(R.id.systemtext);
        mainbutton = view_home.findViewById(R.id.mainbutton);

//        getActivity().registerReceiver(broadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        try {

            sharedPreferences = getActivity().getSharedPreferences("waseem", Context.MODE_PRIVATE);

            if (sharedPreferences.getString("junk", "1").equals("1")) {

                mainbrush.setImageResource(R.drawable.junk_red);
                mainbutton.setImageResource(R.drawable.clean);
                cache.setImageResource(R.drawable.cache);
                temp.setImageResource(R.drawable.temp);
                residue.setImageResource(R.drawable.res);
                system.setImageResource(R.drawable.sys);

                Random random = new Random();

                final int proc1 = random.nextInt(20) + 5;
                final int proc2 = random.nextInt(15) + 10;
                final int proc3 = random.nextInt(30) + 15;
                final int proc4 = random.nextInt(25) + 10;

                alljunk = proc1 + proc2 + proc3 + proc4;

                maintext.setText(alljunk + " MB");
                maintext.setTextColor(Color.parseColor("#F22938"));

                cachetext.setText(proc1 + " MB");
                cachetext.setTextColor(Color.parseColor("#F22938"));

                temptext.setText(proc2 + " MB");
                temptext.setTextColor(Color.parseColor("#F22938"));

                residuetext.setText(proc3 + " MB");
                residuetext.setTextColor(Color.parseColor("#F22938"));

                systemtext.setText(proc4 + " MB");
                systemtext.setTextColor(Color.parseColor("#F22938"));

            } else {
                isJunkCleaned();
            }


        } catch (Exception ex) {
            Log.e("ERR JunkCleaner", ex.getMessage());
        }

        mainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.getString("junk", "1").equals("1")) {
                    final Intent intent = new Intent(getActivity(), ScaningJunk.class);
                    intent.putExtra("junk", alljunk + "");
                    startActivity(intent);

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isJunkCleaned();

                            editor = sharedPreferences.edit();
                            editor.putString("junk", "0");
                            editor.commit();

                            // set pending intent for Alaram
                            Intent intent1 = new Intent(getActivity(), AlaramJunk.class);

                            PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0,
                                    intent1, PendingIntent.FLAG_ONE_SHOT);

                            AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (1000 * 2000), pendingIntent);

                        }
                    }, 2000);
                } else {
                    showCustomToast("No Junk Files ALready Cleaned");
                }
            }
        });
    }


    public void isJunkCleaned() {
        mainbrush.setImageResource(R.drawable.junk_blue);
        mainbutton.setImageResource(R.drawable.cleaned);
        cache.setImageResource(R.drawable.cache2);
        temp.setImageResource(R.drawable.temp2);
        residue.setImageResource(R.drawable.res2);
        system.setImageResource(R.drawable.sys2);


        maintext.setText("CRYSTAL CLEAR");
        maintext.setTextColor(Color.parseColor("#24D149"));

        cachetext.setText(0 + " MB");
        cachetext.setTextColor(Color.parseColor("#24D149"));

        temptext.setText(0 + " MB");
        temptext.setTextColor(Color.parseColor("#24D149"));

        residuetext.setText(0 + " MB");
        residuetext.setTextColor(Color.parseColor("#24D149"));

        systemtext.setText(0 + " MB");
        systemtext.setTextColor(Color.parseColor("#24D149"));
    }

    @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            MainActivity.name.setText("Junk Cleaner");
        }
    }

    @Override
    protected void injectVariables() {

    }
}
