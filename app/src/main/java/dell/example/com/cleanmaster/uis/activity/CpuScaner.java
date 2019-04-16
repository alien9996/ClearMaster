package dell.example.com.cleanmaster.uis.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;
import java.util.List;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.adapter.ScanCpuApp;
import dell.example.com.cleanmaster.model.Apps;
import dell.example.com.cleanmaster.uis.BaseActivity;
import dell.example.com.cleanmaster.uis.fragment.CPUCooler;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class CpuScaner extends BaseActivity {

    ImageView scanner, img_animation, cpu, backcpuscan;
    ScanCpuApp mAdapter;
    RecyclerView recyclerView;
    List<Apps> apps = null;
    TextView cooledcpu;
    RelativeLayout rel;
    InterstitialAd mInterstitialAd;

    @Override
    protected int injectLayout() {
        return R.layout.cpu_scanner;
    }

    @Override
    protected void injectView() {

        scanner = findViewById(R.id.scann);
        cpu = findViewById(R.id.cpu);
        backcpuscan = findViewById(R.id.backcpuscan);
        cooledcpu = findViewById(R.id.cpucooler);
        img_animation = findViewById(R.id.heart);
        rel = findViewById(R.id.rel);
        apps = new ArrayList<>();

        // create ad
        mInterstitialAd = new InterstitialAd(getApplicationContext());
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial));
        AdRequest adRequestInter = new AdRequest.Builder().build();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

            }
        });
        mInterstitialAd.loadAd(adRequestInter);

        // set rotate animation for scanner image
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1500);
        rotate.setRepeatCount(3);
        rotate.setInterpolator(new LinearInterpolator());
        scanner.setAnimation(rotate);

        // set Translate Animation for img_animation
        TranslateAnimation animation = new TranslateAnimation(0.0f, 1000f, 0.0f, 0.0f);
        animation.setDuration(5500);
        animation.setRepeatCount(0);
        animation.setInterpolator(new LinearInterpolator());
        animation.setFillAfter(true);

        img_animation.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                img_animation.setImageResource(0);
                img_animation.setBackgroundResource(0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        // create recycler view
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setItemAnimator(new SlideInLeftAnimator());

        mAdapter = new ScanCpuApp(CPUCooler.apps);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator()));
        recyclerView.computeHorizontalScrollExtent();
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        try {

            final Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    add(0);
                }
            }, 0);

            final Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    remove(0);
                    add(1);


                }
            }, 900);

            final Handler handler3 = new Handler();
            handler3.postDelayed(new Runnable() {
                @Override
                public void run() {
                    remove(0);
                    add(2);


                }
            }, 1800);

            final Handler handler4 = new Handler();
            handler4.postDelayed(new Runnable() {
                @Override
                public void run() {
                    remove(0);
                    add(3);


                }
            }, 2700);

            final Handler handler5 = new Handler();
            handler5.postDelayed(new Runnable() {
                @Override
                public void run() {
                    remove(0);
                    add(4);
                }
            }, 3700);
//
            final Handler handler6 = new Handler();
            handler6.postDelayed(new Runnable() {
                @Override
                public void run() {
                    remove(0);
                    add(5);
                }
            }, 4400);

            final Handler handler7 = new Handler();
            handler7.postDelayed(new Runnable() {
                @Override
                public void run() {
                    remove(0);
                    add(6);

                    final RippleBackground rippleBackground = findViewById(R.id.content);
                    ImageView imageView = findViewById(R.id.centerImage);
                    //imageView.setImageResource(R.drawable.task_complete);
                    rippleBackground.startRippleAnimation();

                    img_animation.setImageResource(0);
                    img_animation.setBackgroundResource(0);
                    cpu.setImageResource(R.drawable.green_circle);
                    scanner.setImageResource(R.drawable.task_complete);

                    ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flipping);
                    anim.setTarget(scanner);
                    anim.setDuration(3000);
                    anim.start();

                    rel.setVisibility(View.GONE);

                    cooledcpu.setText("Cooled CPU to " + CPUCooler.tempcpuend);
                    anim.addListener(new AnimatorListenerAdapter() {

                        @Override
                        public void onAnimationStart(Animator animation) {
                            img_animation.setImageResource(0);
                            img_animation.setBackgroundResource(0);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            rippleBackground.stopRippleAnimation();
                            mInterstitialAd.show();

                            final Handler handler = new Handler();

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 1000);

                        }

                    });

                }
            }, 5500);


        } catch (Exception ex) {
            Log.e("ERR CPUScaner", ex.getMessage());
        }

    }


    public void add(int position) {

        try {

            mAdapter.notifyItemInserted(position);
        } catch (Exception e) {

        }
    }


    public void remove(int position) {
//        mDataSet.add(position, text);
        mAdapter.notifyItemRemoved(position);
        try {
            CPUCooler.apps.remove(position);
        } catch (Exception e) {

        }
    }

    @Override
    protected void injectVariables() {

    }
}
