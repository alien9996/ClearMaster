package dell.example.com.cleanmaster.uis.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.uis.BaseActivity;

public class PowerSavingComplition extends BaseActivity {

    DecoView arcView;
    TextView ist, sec, thi, fou, completion;
    ImageView istpic, secpic, thrpic, foupic;
    int check = 0;
    InterstitialAd mInterstitialAd;

    @Override
    protected int injectLayout() {
        return R.layout.powersaving_completion;
    }

    @Override
    protected void injectView() {

        ist = findViewById(R.id.ist);
        sec = findViewById(R.id.sec);
        thi = findViewById(R.id.thi);
        fou = findViewById(R.id.fou);
        completion = findViewById(R.id.completion);
        istpic = findViewById(R.id.istpic);
        secpic = findViewById(R.id.secpic);
        thrpic = findViewById(R.id.thipic);
        foupic = findViewById(R.id.foupic);

        // call to Interstitil load
        mInterstitialAd = new InterstitialAd(getApplicationContext());
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial));
        AdRequest adRequestInter = new AdRequest.Builder().build();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
            }
        });
        mInterstitialAd.loadAd(adRequestInter);

        //// DEcoView Library For Progress Completion a circle Drrawn using this library

        arcView = findViewById(R.id.dynamicArcView2);

        arcView.addSeries(new SeriesItem.Builder(Color.parseColor("#27282D"))
                .setRange(0, 100, 100)
                .setInitialVisibility(false)
                .setLineWidth(12f)
                .build());

        // create data series track
        SeriesItem seriesItem2 = new SeriesItem.Builder(Color.parseColor("#FFFFFF"))
                .setRange(0, 100, 0)
                .setLineWidth(10f)
                .build();

        int series1Index2 = arcView.addSeries(seriesItem2);

        seriesItem2.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float v, float v1) {
                Float obj = new Float(v1);
                int i = obj.intValue();
                completion.setText(i + "%");

                if (v1 >= 10 && v1 < 50) {
                    ist.setTextColor(Color.parseColor("#FFFFFF"));
                    istpic.setImageResource(R.drawable.circle_white);
                } else if (v1 >= 50 && v1 < 75) {
                    sec.setTextColor(Color.parseColor("#FFFFFF"));
                    secpic.setImageResource(R.drawable.circle_white);
                } else if (v1 >= 75 && v1 < 90) {
                    thi.setTextColor(Color.parseColor("#FFFFFF"));
                    thrpic.setImageResource(R.drawable.circle_white);
                } else if (v1 >= 90 && v1 < 100) {
                    fou.setTextColor(Color.parseColor("#FFFFFF"));
                    foupic.setImageResource(R.drawable.circle_white);
                }

            }

            @Override
            public void onSeriesItemDisplayProgress(float v) {

            }
        });


        arcView.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                .setDelay(0)
                .setDuration(0)
                .setListener(new DecoEvent.ExecuteEventListener() {
                    @Override
                    public void onEventStart(DecoEvent decoEvent) {
                        // start
                    }

                    @Override
                    public void onEventEnd(DecoEvent decoEvent) {
                        // end
                    }
                })
                .build()
        );

        arcView.addEvent(new DecoEvent.Builder(100).setIndex(series1Index2).setDelay(1000).setListener(new DecoEvent.ExecuteEventListener() {
            @Override
            public void onEventStart(DecoEvent decoEvent) {

            }

            @Override
            public void onEventEnd(DecoEvent decoEvent) {
                try {
                    mInterstitialAd.show();
                } catch (Exception ex) {
                    Log.e("AD", ex.getMessage());
                }

                mInterstitialAd.show();
                check = 1;
                youDesirePermissionCode(PowerSavingComplition.this);

                closesall();

            }
        }).build());

    }


    public void closesall() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
        }

//        WifiManager wifiManager = (WifiManager) getApplication().getSystemService(Context.WIFI_SERVICE);
//
//
//        boolean wifiEnabled = wifiManager.isWifiEnabled();
//        if(wifiEnabled)
//        {
//            wifiManager.setWifiEnabled(false);
//        }

        ContentResolver.setMasterSyncAutomatically(false);

    }

    public void youDesirePermissionCode(Activity context) {
        boolean permission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permission = Settings.System.canWrite(context);
        } else {
            permission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_SETTINGS) == PackageManager.PERMISSION_GRANTED;
//            Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 30);
//            setAutoOrientationEnabled(context, false);
        }
        if (permission) {
            //do your code
            Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 30);
            setAutoOrientationEnabled(context, false);

            finish();
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                context.startActivityForResult(intent, 1);
            } else {
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_SETTINGS}, 1);
            }
        }
    }

    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && Settings.System.canWrite(this)) {
            Log.d("TAG", "CODE_WRITE_SETTINGS_PERMISSION success");


//            Toast.makeText(getApplicationContext(),"onActivityResult",Toast.LENGTH_LONG).show();
            //do your code
            Settings.System.putInt(this.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 30);
            setAutoOrientationEnabled(this, false);

            finish();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //do your code

            Toast.makeText(getApplicationContext(), "onRequestPermissionsResult", Toast.LENGTH_LONG).show();

            Settings.System.putInt(this.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 30);
            setAutoOrientationEnabled(this, false);

            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (check == 1) {
            try {
                Settings.System.putInt(this.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 30);
                setAutoOrientationEnabled(this, false);
            } catch (Exception e) {
                finish();
            }
            finish();
        }
    }


    public static void setAutoOrientationEnabled(Context context, boolean enabled) {
        Settings.System.putInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, enabled ? 1 : 0);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void injectVariables() {

    }
}
