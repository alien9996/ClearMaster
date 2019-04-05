package dell.example.com.cleanmaster.uis.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.adapter.PowerAdapter;
import dell.example.com.cleanmaster.model.PowerItem;
import dell.example.com.cleanmaster.uis.BaseActivity;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class PowerSavingPopup extends BaseActivity {

    RecyclerView recyclerView;
    PowerAdapter mAdapter;
    List<PowerItem> items;
    ImageView applied;
    TextView extendedtime, extendedtimedetail;

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    int hour;
    int min;

    @Override
    protected int injectLayout() {
        return R.layout.powersaving_popup;
    }

    @Override
    protected void injectView() {

        Bundle get_iten = getIntent().getExtras();

        sharedpreferences = getSharedPreferences("was", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        extendedtime = findViewById(R.id.addedtime);
        extendedtimedetail = findViewById(R.id.addedtimedetail);

        // get Intent from BatterySever
        try {
            hour = Integer.parseInt(get_iten.getString("hour").replaceAll("[^0-9]", ""))
                    - Integer.parseInt(get_iten.getString("hournomal").replaceAll("[^0-9]", ""));
            min = Integer.parseInt(get_iten.getString("minutes").replaceAll("[^0-9]", ""))
                    - Integer.parseInt(get_iten.getString("minutesnormal").replaceAll("[^0-9]", ""));
        } catch (Exception ex) {
            Log.e("GET_INTENT", ex.getMessage());
            hour = 3;
            min = 5;
        }

        if(hour == 0 && min == 0) {
            hour = 3;
            min = 5;
        }
        extendedtime.setText("(+" + hour + " h " + Math.abs(min) + " m)");
        extendedtimedetail.setText("Extended Battery Up to "+ Math.abs(hour) + "h " + Math.abs(min) + "m");

        // Create List app
        items = new ArrayList<>();
        applied = findViewById(R.id.applied);
        applied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("mode", "1");
                editor.commit();

                Intent intent = new Intent(getApplicationContext(), PowerSavingComplition.class);
                startActivity(intent);

                finish();
            }
        });

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setItemAnimator(new SlideInLeftAnimator());
        recyclerView.getItemAnimator().setAddDuration(200);

        mAdapter = new PowerAdapter(items);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f)));
        recyclerView.computeHorizontalScrollExtent();
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                add("Limit Brightness Upto 80%", 0);
            }
        }, 1000);

        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                add("Decrease Device Performance", 1);
            }
        }, 2000);


        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                add("Close All Battery Consuming Apps", 2);
            }
        }, 3000);

        final Handler handler4 = new Handler();
        handler4.postDelayed(new Runnable() {
            @Override
            public void run() {
                add("Closes System Services like Bluetooth,Screen Rotation,Sync etc.", 3);
            }
        }, 4000);

    }


    public void add(String text, int position) {
        PowerItem item = new PowerItem();
        item.setText(text);
        items.add(item);
        mAdapter.notifyItemInserted(position);
    }
    @Override
    protected void injectVariables() {

    }
}
