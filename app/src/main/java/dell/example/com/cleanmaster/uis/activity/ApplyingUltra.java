package dell.example.com.cleanmaster.uis.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.uis.BaseActivity;

public class ApplyingUltra extends BaseActivity {

    DecoView arcView;
    TextView ist, sec, thir, fou, completion, fif;
    ImageView istpic, secpic, thirpic, foupic, fifthpic;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int check = 0;

    @Override
    protected int injectLayout() {
        return R.layout.applying_ultra;
    }

    @Override
    protected void injectView() {

        ist = findViewById(R.id.ist);
        sec = findViewById(R.id.sec);
        thir = findViewById(R.id.thi);
        fou = findViewById(R.id.fou);
        fif = findViewById(R.id.fif);
        istpic = findViewById(R.id.istpic);
        secpic = findViewById(R.id.secpic);
        thirpic = findViewById(R.id.thipic);
        foupic = findViewById(R.id.foupic);
        foupic = findViewById(R.id.foupic);
        fifthpic = findViewById(R.id.fifthpic);
        completion = findViewById(R.id.completion);

        sharedPreferences = getSharedPreferences("was", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

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

                if (v1 >= 10 && v1 < 40) {
                    ist.setTextColor(Color.parseColor("#FFFFFF"));
                    istpic.setImageResource(R.drawable.circle_white);

                } else if (v1 >= 40 && v1 < 65) {
                    sec.setTextColor(Color.parseColor("#FFFFFF"));
                    secpic.setImageResource(R.drawable.circle_white);
                } else if (v1 >= 65 && v1 < 80) {
                    thir.setTextColor(Color.parseColor("#FFFFFF"));
                    thirpic.setImageResource(R.drawable.circle_white);
                } else if (v1 >= 80 && v1 < 90) {
                    fou.setTextColor(Color.parseColor("#FFFFFF"));
                    foupic.setImageResource(R.drawable.circle_white);
                } else if (v1 >= 90 && v1 < 100) {
                    fif.setTextColor(Color.parseColor("#FFFFFF"));
                    fifthpic.setImageResource(R.drawable.circle_white);
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

                check = 1;
                youDesirePermissionCode(ApplyingUltra.this);
                enalblesall();

                editor.putString("mode", "2");

                editor.commit();
            }
        }).build());

    }


    public void enalblesall() {

        //activate and release all system service

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
        }

        WifiManager wifiManager = (WifiManager) getApplication().getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        boolean wifiEnabled = wifiManager.isWifiEnabled();
        if (wifiEnabled) {
            wifiManager.setWifiEnabled(false);
        }

//        PowerSavingComplition.setAutoOrientationEnabled(getApplicationContext(), true);
//        Settings.System.putInt(this.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 255);
//        ContentResolver.setMasterSyncAutomatically(true);

    }

    public void youDesirePermissionCode(Activity context) {
        boolean permission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permission = Settings.System.canWrite(context);
        } else {
            permission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_SETTINGS) == PackageManager.PERMISSION_GRANTED;

            //Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 30);
        }

        if (permission) {
            PowerSavingComplition.setAutoOrientationEnabled(getApplicationContext(), false);

            Settings.System.putInt(this.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 20);

            ContentResolver.setMasterSyncAutomatically(false);


            Intent i = new Intent(ApplyingUltra.this, BatterySaverBlack.class);
            startActivity(i);
            finish();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Intent inten = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                inten.setData(Uri.parse("package:" + context.getPackageName()));
                context.startActivityForResult(inten, 1);
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
            Log.e("TAG", "CODE_WRITE_SETTINGS_PERMISSION success");

            PowerSavingComplition.setAutoOrientationEnabled(getApplicationContext(), false);

            Settings.System.putInt(this.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 20);

            ContentResolver.setMasterSyncAutomatically(false);


            Intent i = new Intent(ApplyingUltra.this, BatterySaverBlack.class);
            startActivity(i);
            finish();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "onRequestPermissionsResult", Toast.LENGTH_LONG).show();

            PowerSavingComplition.setAutoOrientationEnabled(getApplicationContext(), false);

            Settings.System.putInt(this.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 20);

            ContentResolver.setMasterSyncAutomatically(false);


            Intent i = new Intent(ApplyingUltra.this, BatterySaverBlack.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (check == 1) {
            try {
                PowerSavingComplition.setAutoOrientationEnabled(getApplicationContext(), false);

                Settings.System.putInt(this.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 20);

                ContentResolver.setMasterSyncAutomatically(false);
            } catch (Exception ex) {
                Intent i = new Intent(ApplyingUltra.this, BatterySaverBlack.class);
                startActivity(i);
                finish();
            } finally {
                Intent i = new Intent(ApplyingUltra.this, BatterySaverBlack.class);
                startActivity(i);
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void injectVariables() {

    }
}
