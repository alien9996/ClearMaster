package dell.example.com.cleanmaster.uis.fragment;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

import java.io.RandomAccessFile;
import java.math.RoundingMode;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.service.AlaramBooster;
import dell.example.com.cleanmaster.uis.BaseFragment;
import dell.example.com.cleanmaster.uis.activity.MainActivity;
import dell.example.com.cleanmaster.uis.activity.NomalMode;

public class PhoneBooster extends BaseFragment {

    DecoView arcView;
    TextView scanning, centree, totalram, usedram, appsused, appsfreed,
            processes, top, bottom, ramperct;
    LinearLayout scanlay, optimizelay;
    public static ImageView optimizebutton;
    ImageView img_animation;

    TimerTask timer = null;
    int counter = 0;
    int x, y;
    int used_memory_size = 0;
    String total_ram = "";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    InterstitialAd mInterstitialAd;

    @Override
    protected int injectLayout() {
        return R.layout.phone_booster;
    }

    @Override
    protected void injectView() {

        used_memory_size = (int) getUsedMemorySize();
        total_ram = getTotalRam();

        // DecoView
        arcView = view_home.findViewById(R.id.dynamicArcView2);

        // TextView
        scanning = view_home.findViewById(R.id.scaning);
        centree = view_home.findViewById(R.id.centree);
        totalram = view_home.findViewById(R.id.totalram);
        usedram = view_home.findViewById(R.id.usedram);
        appsused = view_home.findViewById(R.id.appsused);
        appsfreed = view_home.findViewById(R.id.appsfreed);
        processes = view_home.findViewById(R.id.processes);
        top = view_home.findViewById(R.id.top);
        bottom = view_home.findViewById(R.id.bottom);
        ramperct = view_home.findViewById(R.id.ramperct);

        // LinearLayout
        scanlay = view_home.findViewById(R.id.scanlay);
        optimizelay = view_home.findViewById(R.id.optimizelay);

        // ImageView
        optimizebutton = view_home.findViewById(R.id.optbutton);
        img_animation = view_home.findViewById(R.id.waves);

//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                img_animation.setImageResource(R.drawable.running_bar);
//            }
//        });

        //  Create sharedPreferences
        sharedpreferences = getActivity().getSharedPreferences("waseem", Context.MODE_PRIVATE);

        // Create google AD
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial));
        AdRequest adRequestInter = new AdRequest.Builder().build();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show(); // show google ad
            }
        });
        mInterstitialAd.loadAd(adRequestInter);


        // Loading is Start app
        try {
            MainActivity.name.setText("Charge Booster");

//            Random ran3 = new Random();
//            ramperct.setText(ran3.nextInt(60) + 40 + "%");

            DecimalFormat fm = new DecimalFormat("##");
            Float totalram = Float.parseFloat(total_ram.substring(0, 3));
            double ram1 = 1 + 100 * (used_memory_size / 1024.0) / totalram;
            if (ram1 < 60) {
                ram1 = ram1 + 10;
                used_memory_size = (int) (used_memory_size + totalram * 0.1);
            } else {
                ram1 = ram1 + 5;
                used_memory_size = (int) (used_memory_size + totalram * 0.05);
            }

            String ram_str = fm.format(ram1);
            ramperct.setText(ram_str + "%");

            optimizebutton.setBackgroundResource(0);
            optimizebutton.setImageResource(0);
            optimizebutton.setImageResource(R.drawable.optimize);


            if (sharedpreferences.getString("booster", "1").equals("0")) {
                optimizebutton.setImageResource(0);
                optimizebutton.setImageResource(R.drawable.optimize);

                centree.setText(sharedpreferences.getString("value", "0 MB"));
            }
            start(ram_str);

            // when click is button optimize
            optimizebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sharedpreferences.getString("booster", "1").equals("1")) {
                        optimize();

                        editor = sharedpreferences.edit();
                        editor.putString("booster", "0");
                        editor.commit();

                        Intent intent = new Intent(getActivity(), AlaramBooster.class);

                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),
                                0, intent, PendingIntent.FLAG_ONE_SHOT);
                        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (100 * 1000), pendingIntent);

                    } else {
                        // show alert to screen
                        showCustomToast("Phone is Aleady Optimized");
                    }
                }
            });


        } catch (Exception ex) {
            Log.e("MAIN ", "" + ex);
        }

    }

    // method start
    public void start(String ram1) {

        final Timer t = new Timer();
        timer = new TimerTask() {
            @Override
            public void run() {
                try {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            counter++;
                            centree.setText(counter + " MB");
                        }
                    });
                } catch (Exception ex) {
                    Log.e("TIMER ERR: ", "" + ex);
                }
            }
        };
        t.schedule(timer, 30, 30);


//        Random ran2 = new Random();
//        final int proc = ran2.nextInt(60) + 30;

        arcView.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 0)
                .setInterpolator(new AccelerateInterpolator())
                .build());

        arcView.addSeries(new SeriesItem.Builder(Color.parseColor("#F22938"))
                .setRange(0, 100, 100)
                .setInitialVisibility(false)
                .setLineWidth(32f)
                .build()
        );

        // Create data series track

        SeriesItem seriesItem2 = new SeriesItem.Builder(Color.parseColor("#2499E0"))
                .setRange(0, 100, 0)
                .setLineWidth(32f)
                .build();

        int series1Index2 = arcView.addSeries(seriesItem2);

        arcView.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                .setDelay(0)
                .setDuration(600)
                .build()
        );

        arcView.addEvent(new DecoEvent.Builder(Integer.parseInt(ram1)).setIndex(series1Index2).
                setDelay(2000).
                setListener(new DecoEvent.ExecuteEventListener() {
                    @Override
                    public void onEventStart(DecoEvent decoEvent) {

                    }

                    @Override
                    public void onEventEnd(DecoEvent decoEvent) {
                        t.cancel();
                        timer.cancel();
                        t.purge();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                centree.setText(used_memory_size + " MB");
                            }
                        }, 50);


                        if (sharedpreferences.getString("booster", "1").equals("0")) {
                            centree.setText(sharedpreferences.getString("value", "50 MB"));
                        }

                    }
                }).build());


        totalram.setText(total_ram);
        usedram.setText(used_memory_size + " MB/ ");
        appsfreed.setText(total_ram);
        appsused.setText(used_memory_size - x - 33 + " MB/ ");

        Random ran = new Random();
        y = ran.nextInt(50) + 15;

        processes.setText(y + "");

    }

    // method optimize
    public void optimize() {
        try {

            Random ran = new Random();
            x = ran.nextInt(100) + 30;

            final int memory_size = used_memory_size - x;

            DecimalFormat fm = new DecimalFormat("##");
            Float totalram1 = Float.parseFloat(total_ram.substring(0, 3));
            double percen = 1 + 100 * (memory_size / 1024.0) / totalram1;
            final String ram_str = fm.format(percen);

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    RotateAnimation rotate = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    rotate.setDuration(5000);
                    rotate.setInterpolator(new LinearInterpolator());

                    ImageView image = view_home.findViewById(R.id.circulalines);

                    image.startAnimation(rotate);
                }
            });


            arcView.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                    .setRange(0, 100, 0)
                    .setInterpolator(new AccelerateInterpolator())
                    .build());

            arcView.addSeries(new SeriesItem.Builder(Color.parseColor("#F22938"))
                    .setRange(0, 100, 100)
                    .setInitialVisibility(false)
                    .setLineWidth(32f)
                    .build());


            // create data series track
            SeriesItem seriesItem2 = new SeriesItem.Builder(Color.parseColor("#2499E0"))
                    .setRange(0, 100, 0)
                    .setLineWidth(32f)
                    .build();

            int series1Index2 = arcView.addSeries(seriesItem2);

            arcView.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                    .setDelay(500)
                    .setDuration(2000)
                    .setListener(new DecoEvent.ExecuteEventListener() {
                        @Override
                        public void onEventStart(DecoEvent decoEvent) {
                            bottom.setText("");
                            top.setText("");
                            centree.setText("Optimizing...");
                        }

                        @Override
                        public void onEventEnd(DecoEvent decoEvent) {

                        }
                    }).build());

            arcView.addEvent(new DecoEvent.Builder(Integer.parseInt(ram_str)).setIndex(series1Index2).setDelay(4000).setListener(new DecoEvent.ExecuteEventListener() {
                @Override
                public void onEventStart(DecoEvent decoEvent) {
                    bottom.setText("");
                    top.setText("");
                    centree.setText("Optimizing...");
                }

                @Override
                public void onEventEnd(DecoEvent decoEvent) {
                    mInterstitialAd.show();
                    bottom.setText("Found");
                    top.setText("Storage");

//                Random ran3 = new Random();
//                ramperct.setText(ran3.nextInt(40) + 20 + "%");
                    ramperct.setText(ram_str + "%");
                }
            }).build());

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TranslateAnimation animation = new TranslateAnimation(0, 600, 0.0f, 0.0f);
                    animation.setDuration(5000);
                    animation.setRepeatCount(0);
                    animation.setInterpolator(new LinearInterpolator());
                    //        animation.setRepeatMode(2);   // repeat animation (left to right, right to left )
                    animation.setFillAfter(true);

                    img_animation.startAnimation(animation);

                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            scanlay.setVisibility(View.VISIBLE);
                            optimizelay.setVisibility(View.GONE);
                            scanning.setText("SCANNING...");
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            scanlay.setVisibility(View.GONE);
                            optimizelay.setVisibility(View.VISIBLE);

                            optimizebutton.setImageResource(R.drawable.optimized);

                            Random ran2 = new Random();
                            int proc = ran2.nextInt(10) + 5;

                            centree.setText(memory_size + " MB");

                            editor = sharedpreferences.edit();
                            editor.putString("values", memory_size + " MB");
                            editor.commit();

                            totalram.setText(total_ram);
                            usedram.setText(memory_size + " MB/ ");

                            appsfreed.setText(total_ram);
                            appsused.setText(Math.abs(memory_size - 33) + " MB/ ");

                            processes.setText(y - proc + "");

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            });

        } catch (Exception ex) {
            Log.e("OPTIMIZE", "" + ex);
        }
    }


    // method get used memory size
    public long getUsedMemorySize() {
        try {
            ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
            ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.getMemoryInfo(mi);
            long availableMegs = mi.availMem / 1048576L;
            return availableMegs;

        } catch (Exception ex) {
            return 200;
        }
    }

    // method get total ram
    public String getTotalRam() {

        RandomAccessFile render = null;
        String load = null;
        Locale locale = new Locale("en", "EN");
        DecimalFormat twoDecimalForm = (DecimalFormat)
                NumberFormat.getNumberInstance(locale);
        twoDecimalForm.applyPattern("#.##");
        double totRam = 0;
        String lastValue = "";

        try {

            try {
                render = new RandomAccessFile("/proc/meminfo", "r");
                load = render.readLine();
            } catch (Exception ex) {
                Log.e("ERR getTotalRam", "" + ex);
            }

            // get the number values from the string
            Pattern p = Pattern.compile("(\\d+)");
            Matcher m = p.matcher(load);
            String value = "";
            while (m.find()) {
                value = m.group(1);
            }

            try {
                render.close();
            } catch (Exception ex) {
                Log.e("ERR Close render", "" + ex);
            }

            totRam = Double.parseDouble(value);

            double mb = totRam / 1024.0;
            double gb = totRam / 1048576.0;
            double tb = totRam / 1073741824.0;


            if (tb > 1) {
                lastValue = twoDecimalForm.format(tb).concat(" TB");
            } else if (gb > 1) {
                lastValue = twoDecimalForm.format(gb).concat(" GB");
            } else if (mb > 1) {
                lastValue = twoDecimalForm.format(mb).concat(" MB");
            } else {
                lastValue = twoDecimalForm.format(totRam).concat(" KB");
            }


        } catch (Exception ex) {
            Log.e("C", "" + ex);
        }

        return lastValue;
    }

    @Override
    protected void injectVariables() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            MainActivity.name.setText("Charge Booster");
        } else {

        }
    }
}
