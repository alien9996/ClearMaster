package dell.example.com.cleanmaster.uis.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.uis.BaseActivity;

public class PickApps extends Activity {

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    ImageView addcontact, addplaystore, addcalculator, addcamera, addclock, addmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_apps);

        sharedpreferences = getSharedPreferences("waseembest", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        addcontact = findViewById(R.id.addcontacts);
        addplaystore = findViewById(R.id.addplaystore);
        addcalculator = findViewById(R.id.addcalculator);
        addcamera = findViewById(R.id.addcamera);
        addclock = findViewById(R.id.addclock);
        addmap = findViewById(R.id.addmap);

        addcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(sharedpreferences.getString("button1", "l").equals("4") || sharedpreferences.getString("button2", "l").equals("4") || sharedpreferences.getString("button3", "l").equals("4") || sharedpreferences.getString("button4", "l").equals("4"))) {
                    if (sharedpreferences.getString("button", "1").equals("1")) {
                        editor.putString("button1", "4");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("2")) {
                        editor.putString("button2", "4");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("3")) {
                        editor.putString("button3", "4");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("4")) {
                        editor.putString("button4", "4");
                        editor.commit();
                    }

                    finish();
                } else {
                    showToast();

                }
            }
        });

        addplaystore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(sharedpreferences.getString("button1", "l").equals("1") || sharedpreferences.getString("button2", "l").equals("1") || sharedpreferences.getString("button3", "l").equals("1") || sharedpreferences.getString("button4", "l").equals("1"))) {

                    if (sharedpreferences.getString("button", "1").equals("1")) {
                        editor.putString("button1", "1");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("2")) {
                        editor.putString("button2", "1");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("3")) {
                        editor.putString("button3", "1");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("4")) {
                        editor.putString("button4", "1");
                        editor.commit();
                    }

                    finish();
                } else {
                    showToast();
                }
            }
        });

        addcalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(sharedpreferences.getString("button1", "l").equals("2") || sharedpreferences.getString("button2", "l").equals("2") || sharedpreferences.getString("button3", "l").equals("2") || sharedpreferences.getString("button4", "l").equals("2"))) {

                    if (sharedpreferences.getString("button", "1").equals("1")) {
                        editor.putString("button1", "2");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("2")) {
                        editor.putString("button2", "2");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("3")) {
                        editor.putString("button3", "2");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("4")) {
                        editor.putString("button4", "2");
                        editor.commit();
                    }

                    finish();
                } else {
                    showToast();
                }

            }
        });

        addclock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(sharedpreferences.getString("button1", "l").equals("3") || sharedpreferences.getString("button2", "l").equals("3") || sharedpreferences.getString("button3", "l").equals("3") || sharedpreferences.getString("button4", "l").equals("3"))) {

                    if (sharedpreferences.getString("button", "1").equals("1")) {
                        editor.putString("button1", "3");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("2")) {
                        editor.putString("button2", "3");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("3")) {
                        editor.putString("button3", "3");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("4")) {
                        editor.putString("button4", "3");
                        editor.commit();
                    }

                    finish();
                } else {
                    showToast();
                }

            }
        });

        addmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!(sharedpreferences.getString("button1", "l").equals("5") || sharedpreferences.getString("button2", "l").equals("5") || sharedpreferences.getString("button3", "l").equals("5") || sharedpreferences.getString("button4", "l").equals("5"))) {

                    if (sharedpreferences.getString("button", "1").equals("1")) {
                        editor.putString("button1", "5");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("2")) {
                        editor.putString("button2", "5");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("3")) {
                        editor.putString("button3", "5");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("4")) {
                        editor.putString("button4", "5");
                        editor.commit();
                    }

                    finish();
                } else {
                    showToast();
                }

            }
        });

        addcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(sharedpreferences.getString("button1", "l").equals("6") || sharedpreferences.getString("button2", "l").equals("6") || sharedpreferences.getString("button3", "l").equals("6") || sharedpreferences.getString("button4", "l").equals("6"))) {

                    if (sharedpreferences.getString("button", "1").equals("1")) {
                        editor.putString("button1", "6");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("2")) {
                        editor.putString("button2", "6");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("3")) {
                        editor.putString("button3", "6");
                        editor.commit();
                    } else if (sharedpreferences.getString("button", "1").equals("4")) {
                        editor.putString("button4", "6");
                        editor.commit();
                    }

                    finish();
                } else {
                    showToast();
                }

            }
        });
    }


    public void showToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.my_toast, null);
        ImageView image = layout.findViewById(R.id.image);

        TextView text = layout.findViewById(R.id.textView1);
        text.setText("This App Is Already Added");

        Toast toast = new Toast(PickApps.this);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 70);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
