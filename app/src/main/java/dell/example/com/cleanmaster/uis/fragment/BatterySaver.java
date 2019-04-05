package dell.example.com.cleanmaster.uis.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.BatteryManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.uis.BaseFragment;
import dell.example.com.cleanmaster.uis.activity.MainActivity;
import dell.example.com.cleanmaster.uis.activity.NomalMode;
import dell.example.com.cleanmaster.uis.activity.PowerSavingPopup;
import dell.example.com.cleanmaster.uis.activity.UltraPopup;
import me.itangqi.waveloadingview.WaveLoadingView;

public class BatterySaver extends BaseFragment {

    WaveLoadingView mwaveLoadingView;
    ImageView powersaving, ultrasaving, normal;
    TextView hourn, minutes, hourp, minutep, houru, minutesu, hourmain, minutemain;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

            mwaveLoadingView.setProgressValue(level);
            mwaveLoadingView.setCenterTitle(level + "%");
            Random ran = new Random();

            if (level <= 5) {

                int min_n = ran.nextInt(11) + 10;
                hourn.setText(0 + "");
                minutes.setText(min_n + "");

                int hour_p = ran.nextInt(2) + 1;
                int min_p = ran.nextInt(45) + 15;
                hourp.setText(hour_p + "");
                minutep.setText(min_p + "");

                int hour_u = ran.nextInt(2) + 2;
                int min_u = ran.nextInt(59) + 1;
                houru.setText(hour_u + "");
                minutesu.setText(min_u + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(0 + "");
                    minutemain.setText(min_n + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(hour_p + "");
                    minutemain.setText(min_p + "");
                }
            }

            if (level > 5 && level <= 10) {

                int min_n = ran.nextInt(11) + 20;
                hourn.setText(0 + "");
                minutes.setText(min_n + "");

                int hour_p = ran.nextInt(2) + 2;
                int min_p = ran.nextInt(11) + 10;
                hourp.setText(hour_p + "");
                minutep.setText(min_p + "");

                int hour_u = ran.nextInt(2) + 4;
                int min_u = ran.nextInt(21) + 30;
                houru.setText(hour_u + "");
                minutesu.setText(min_u + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(0 + "");
                    minutemain.setText(min_n + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(hour_p + "");
                    minutemain.setText(min_p + "");
                }
            }


            if (level > 10 && level <= 15) {

                int min_n = ran.nextInt(16) + 30;
                hourn.setText(0 + "");
                minutes.setText(min_n + "");

                int hour_p = ran.nextInt(2) + 2;
                int min_p = ran.nextInt(15) + 45;
                hourp.setText(hour_p + "");
                minutep.setText(min_p + "");

                int hour_u = ran.nextInt(2) + 6;
                int min_u = ran.nextInt(21) + 30;
                houru.setText(hour_u + "");
                minutesu.setText(min_u + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(0 + "");
                    minutemain.setText(min_n + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(hour_p + "");
                    minutemain.setText(min_p + "");
                }
            }

            if (level > 15 && level <= 25) {

                int min_n = ran.nextInt(16) + 30;
                hourn.setText(1 + "");
                minutes.setText(min_n + "");

                int hour_p = ran.nextInt(3) + 2;
                int min_p = ran.nextInt(15) + 45;
                hourp.setText(hour_p + "");
                minutep.setText(min_p + "");

                int hour_u = ran.nextInt(3) + 6;
                int min_u = ran.nextInt(30) + 30;
                houru.setText(hour_u + "");
                minutesu.setText(min_u + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(1 + "");
                    minutemain.setText(min_n + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(hour_p + "");
                    minutemain.setText(min_p + "");
                }
            }

            if (level > 25 && level <= 35) {

                int min_n = ran.nextInt(16) + 30;
                hourn.setText(2 + "");
                minutes.setText(min_n + "");

                int hour_p = ran.nextInt(3) + 4;
                int min_p = ran.nextInt(35) + 25;
                hourp.setText(hour_p + "");
                minutep.setText(min_p + "");

                int hour_u = ran.nextInt(5) + 12;
                int min_u = ran.nextInt(30) + 30;
                houru.setText(hour_u + "");
                minutesu.setText(min_u + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(2 + "");
                    minutemain.setText(min_n + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(hour_p + "");
                    minutemain.setText(min_p + "");
                }
            }

            if (level > 35 && level <= 50) {

                int min_n = ran.nextInt(16) + 30;
                hourn.setText(5 + "");
                minutes.setText(min_n + "");

                int hour_p = ran.nextInt(3) + 7;
                int min_p = ran.nextInt(35) + 25;
                hourp.setText(hour_p + "");
                minutep.setText(min_p + "");

                int hour_u = ran.nextInt(4) + 17;
                int min_u = ran.nextInt(35) + 25;
                houru.setText(hour_u + "");
                minutesu.setText(min_u + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(5 + "");
                    minutemain.setText(min_n + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(hour_p + "");
                    minutemain.setText(min_p + "");
                }
            }

            if (level > 50 && level <= 65) {

                int min_n = ran.nextInt(59) + 1;
                hourn.setText(7 + "");
                minutes.setText(min_n + "");

                int hour_p = ran.nextInt(3) + 9;
                int min_p = ran.nextInt(35) + 25;
                hourp.setText(hour_p + "");
                minutep.setText(min_p + "");

                int hour_u = ran.nextInt(4) + 22;
                int min_u = ran.nextInt(35) + 25;
                houru.setText(hour_u + "");
                minutesu.setText(min_u + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(7 + "");
                    minutemain.setText(min_n + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(hour_p + "");
                    minutemain.setText(min_p + "");
                }
            }

            if (level > 65 && level <= 75) {

                int min_n = ran.nextInt(59) + 1;
                hourn.setText(9 + "");
                minutes.setText(min_n + "");

                int hour_p = ran.nextInt(3) + 12;
                int min_p = ran.nextInt(36) + 15;
                hourp.setText(hour_p + "");
                minutep.setText(min_p + "");

                int hour_u = ran.nextInt(4) + 27;
                int min_u = ran.nextInt(35) + 25;
                houru.setText(hour_u + "");
                minutesu.setText(min_u + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(9 + "");
                    minutemain.setText(min_n + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(hour_p + "");
                    minutemain.setText(min_p + "");
                }
            }

            if (level > 75 && level <= 85) {

                int min_n = ran.nextInt(59) + 1;
                hourn.setText(14 + "");
                minutes.setText(min_n + "");

                int hour_p = ran.nextInt(2) + 16;
                int min_p = ran.nextInt(36) + 15;
                hourp.setText(hour_p + "");
                minutep.setText(min_p + "");

                int hour_u = ran.nextInt(4) + 35;
                int min_u = ran.nextInt(35) + 25;
                houru.setText(hour_u + "");
                minutesu.setText(min_u + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(14 + "");
                    minutemain.setText(min_n + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(hour_p + "");
                    minutemain.setText(min_p + "");
                }
            }

            if (level > 85 && level <= 100) {

                int min_n = ran.nextInt(59) + 1;
                hourn.setText(20 + "");
                minutes.setText(min_n + "");

                int hour_p = ran.nextInt(2) + 29;
                int min_p = ran.nextInt(36) + 15;
                hourp.setText(hour_p + "");
                minutep.setText(min_p + "");

                int hour_u = ran.nextInt(6) + 55;
                int min_u = ran.nextInt(35) + 25;
                houru.setText(hour_u + "");
                minutesu.setText(min_u + "");


                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(20 + "");
                    minutemain.setText(min_n + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(hour_p + "");
                    minutemain.setText(min_p + "");
                }
            }


        }
    };

    @Override
    protected int injectLayout() {
        return R.layout.battery_saver;
    }

    @Override
    protected void injectView() {

        mwaveLoadingView = view_home.findViewById(R.id.waveView);
        powersaving = view_home.findViewById(R.id.powersaving);
        ultrasaving = view_home.findViewById(R.id.ultra);
        normal = view_home.findViewById(R.id.normal);
        hourn = view_home.findViewById(R.id.hourn);
        minutes = view_home.findViewById(R.id.minutes);
        hourp = view_home.findViewById(R.id.hourp);
        minutep = view_home.findViewById(R.id.minutesp);
        houru = view_home.findViewById(R.id.houru);
        minutesu = view_home.findViewById(R.id.minutesu);
        hourmain = view_home.findViewById(R.id.hourmain);
        minutemain = view_home.findViewById(R.id.minutesmain);

        sharedpreferences = getActivity().getSharedPreferences("was", Context.MODE_PRIVATE);
        getActivity().registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        try {

            powersaving.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), PowerSavingPopup.class);
                    intent.putExtra("hour", hourp.getText());
                    intent.putExtra("minutes", minutep.getText());
                    intent.putExtra("minutesnormal", minutes.getText());
                    intent.putExtra("hournomal", hourn.getText());
                    startActivity(intent);
                }
            });

            ultrasaving.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), UltraPopup.class);
                    intent.putExtra("hour", houru.getText());
                    intent.putExtra("minutes", minutesu.getText());
                    intent.putExtra("minutesnormal", minutes.getText());
                    intent.putExtra("hournomal", hourn.getText());
                    startActivity(intent);
                }
            });

            normal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), NomalMode.class);
                    startActivity(intent);
                }
            });


            mwaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
            mwaveLoadingView.setCenterTitleColor(Color.parseColor("#FFF"));
            mwaveLoadingView.setBottomTitleColor(Color.parseColor("#FFF"));
            mwaveLoadingView.setBorderWidth(10);
            mwaveLoadingView.setAmplitudeRatio(30);
            mwaveLoadingView.setWaveColor(Color.parseColor("#2499E0"));
            mwaveLoadingView.setBorderColor(Color.parseColor("#FFF"));
            mwaveLoadingView.setTopTitleStrokeColor(Color.BLUE);
            mwaveLoadingView.setTopTitleStrokeWidth(3);
            mwaveLoadingView.startAnimation();


        } catch (Exception ex) {
            Log.e("BATTERY_SAVE", "" + ex.getMessage());
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    public void onStop() {
        super.onStop();

        try {
            getActivity().unregisterReceiver(mBatInfoReceiver);
        } catch (Exception ex) {
            Log.e("ON_STOP", "" + ex.getMessage());

        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            MainActivity.name.setText("Battery Saver");
        }
    }

    @Override
    protected void injectVariables() {

    }
}
