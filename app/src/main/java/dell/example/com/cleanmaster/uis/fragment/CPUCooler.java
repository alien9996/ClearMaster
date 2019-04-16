package dell.example.com.cleanmaster.uis.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.adapter.RecyclerAdapter;
import dell.example.com.cleanmaster.model.Apps;
import dell.example.com.cleanmaster.uis.BaseFragment;
import dell.example.com.cleanmaster.uis.activity.CpuScaner;
import dell.example.com.cleanmaster.uis.activity.MainActivity;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class CPUCooler extends BaseFragment {

    TextView batterytemp, showmain, showsec, nooverheating;
    float temp;
    ImageView coolbutton, tempimg;
    RecyclerView recyclerView;
    RecyclerAdapter mAdapter;
    public static List<Apps> apps;
    public  static String tempcpuend;
    List<Apps> apps2;
    int check = 0;

    // create broadcastReceiver
    BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            try {
                // get *C of CPU
                int level = intent.getIntExtra("level", 0);
                temp = ((float) intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)) / 10;

                batterytemp.setText(temp + "°C");

                if (temp >= 24.0) {

                    apps = new ArrayList<>();
                    apps2 = new ArrayList<>();
                    tempimg.setImageResource(R.drawable.red_cooler);
                    showmain.setText("OVERHEADTED");
                    showmain.setTextColor(Color.parseColor("#F22938"));
                    showsec.setText("Apps are causing problem hit cool down");
                    nooverheating.setText("");

                    coolbutton.setImageResource(R.drawable.cool_button_blue);
                    coolbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getActivity(), CpuScaner.class);
                            startActivity(i);

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    nooverheating.setText("Currently No App causing Overheating");
                                    showmain.setText("NORMAL");
                                    showmain.setTextColor(Color.parseColor("#24D149"));
                                    showsec.setText("CPU Temperature is GOOD");
                                    showsec.setTextColor(Color.parseColor("#24D149"));
                                    coolbutton.setImageResource(R.drawable.cooled);
                                    tempimg.setImageResource(R.drawable.blue_cooler);

                                    Locale locale = new Locale("en", "EN");
                                    DecimalFormat twoDecimalForm = (DecimalFormat)
                                            NumberFormat.getNumberInstance(locale);
                                    twoDecimalForm.applyPattern("##.#°C");

                                    tempcpuend = twoDecimalForm.format(temp - 3.3);
                                    batterytemp.setText(tempcpuend);
                                    recyclerView.setAdapter(null);

                                }
                            }, 2000);

                            coolbutton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showCustomToast("CPU Temperature is Already Normal.");
                                }
                            });
                        }
                    });
                }

                if (Build.VERSION.SDK_INT < 23) {
                    showsec.setTextAppearance(context, android.R.style.TextAppearance_Medium);
                    showsec.setTextColor(Color.parseColor("#F22938"));
                } else {
                    showsec.setTextAppearance(context, android.R.style.TextAppearance_Small);
                    showsec.setTextColor(Color.parseColor("#F22938"));
                }


                // create recyclerView
                recyclerView.setItemAnimator(new SlideInLeftAnimator());

                recyclerView.getItemAnimator().setAddDuration(10000);

                mAdapter = new RecyclerAdapter(apps);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f)));
                recyclerView.computeHorizontalScrollExtent();
                recyclerView.setAdapter(mAdapter);
                getAllIcons();


            } catch (Exception ex) {
                Log.e("ERR BroadCast ", ex.getMessage());
            }
        }
    };

    @Override
    protected int injectLayout() {
        return R.layout.cpu_cooler;
    }


    @Override
    protected void injectView() {

        try {
            getActivity().registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            recyclerView = view_home.findViewById(R.id.recycler_view);

            tempimg = view_home.findViewById(R.id.tempimg);
            showmain = view_home.findViewById(R.id.showmain);
            showsec = view_home.findViewById(R.id.showsec);
            coolbutton = view_home.findViewById(R.id.coolbutton);
            nooverheating = view_home.findViewById(R.id.nooverheating);
            batterytemp = view_home.findViewById(R.id.batterytemp);

            showmain.setText("NORMAL");
            showsec.setText("CPU Temperature is GOOD");
            nooverheating.setText("Currently No App causing Overheating");
            coolbutton.setImageResource(R.drawable.cooled);

            coolbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCustomToast("CPU Temperature is Already Normal.");
                }
            });

            tempimg.setImageResource(R.drawable.blue_cooler);


        } catch (Exception ex) {
            Log.e("MAIM CPUCooler", ex.getMessage());
        }

    }

    public void getAllIcons() {

        PackageManager pm = getActivity().getPackageManager();

        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        if (packages != null) {
            for (int k = 0; k < packages.size(); k++) {
                String packageName = packages.get(k).packageName;

                if (!packageName.equals("dell.example.com.cleanmaster")) {

                    Drawable icon = null;
                    try {
                        String pName = (String) pm.getApplicationLabel(pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA));
                        Apps app = new Apps();

                        File file = new File(pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA).publicSourceDir);
                        long size = file.length();

                        app.setSize(size / 1000000 + 20 + "MB");

                        ApplicationInfo a = pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
                        app.setImage(icon = getActivity().getPackageManager().getApplicationIcon(packages.get(k).packageName));
                        getActivity().getPackageManager();

                        if (((a.flags & ApplicationInfo.FLAG_SYSTEM) == 0)) {
                            if (check <= 9) {
                                check++;
                                apps.add(app);
                            } else {
                                getActivity().unregisterReceiver(batteryReceiver);
                                break;
                            }
                        }

                    } catch (Exception ex) {
                        Log.e("GET ALL ICON", ex.getMessage());
                    }
                }
            }

            if (apps.size() > 1) {
                mAdapter = new RecyclerAdapter(apps);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {

            getActivity().unregisterReceiver(batteryReceiver);
        } catch (Exception e) {

        }
    }

    @Override
    public boolean getUserVisibleHint() {

        MainActivity.name.setText("CPU Cooler");
        return getUserVisibleHint();

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);


        if (isVisibleToUser) {
            MainActivity.name.setText("CPU Cooler");

        } else {

        }
    }


    @Override
    protected void injectVariables() {

    }
}
