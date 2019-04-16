package dell.example.com.cleanmaster.uis.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.skyfishjy.library.RippleBackground;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.adapter.JunkAppAdapter;
import dell.example.com.cleanmaster.model.Apps;
import dell.example.com.cleanmaster.uis.BaseActivity;
import dell.example.com.cleanmaster.uis.fragment.CPUCooler;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class ScaningJunk extends BaseActivity {

    AVLoadingIndicatorView avi1, avi2, avi3, avi4, avi5, avi6;
    ImageView front, back;
    int check = 0;
    TextView files;
    List<ApplicationInfo> packages;
    int prog = 0;
    Timer T2;
    JunkAppAdapter mAdapter;
    RecyclerView recyclerView;
    public List<Apps> apps;
    PackageManager pm;
    ImageView mImgCheck;
    TextView scanning;
    InterstitialAd mInterstitialAd;

    // Scanning for any junk file existance inorder to clean it

    @Override
    protected int injectLayout() {
        return R.layout.scanning_junk;
    }

    @Override
    protected void injectView() {

        final Bundle junk = getIntent().getExtras();

        avi1 = findViewById(R.id.scan1);
        avi2 = findViewById(R.id.scan2);
        avi3 = findViewById(R.id.scan3);
        avi4 = findViewById(R.id.scan4);
        avi5 = findViewById(R.id.scan5);
        avi6 = findViewById(R.id.scan6);

        files = findViewById(R.id.files);
        back = findViewById(R.id.back);
        scanning = findViewById(R.id.scanning1);

        apps = new ArrayList<>();

        //create ad
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

        // add Animation for image

        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1500);
        rotate.setRepeatCount(4);
        rotate.setInterpolator(new LinearInterpolator());

        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                T2.cancel();
                T2.purge();
                avi1.hide();
                avi2.hide();
                avi3.hide();
                avi4.hide();
                avi5.hide();
                avi6.hide();

                files.setText("");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                check++;
                startAnim(check);
            }
        });

        front = findViewById(R.id.front);
        front.startAnimation(rotate);

        pm = getPackageManager();
        packages = pm.getInstalledApplications(0);

        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        // new Timer
        T2 = new Timer();
        T2.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (prog < packages.size()) {
                            files.setText("" + packages.get(prog).sourceDir);
                            prog++;
                        } else {
                            T2.cancel();
                            T2.purge();
                        }
                    }
                });
            }
        }, 80, 80);

        // create RecyclerView

        recyclerView = findViewById(R.id.recycler_view);

//        recyclerView.setItemAnimator(new SlideInLeftAnimator());
//        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        mAdapter = new JunkAppAdapter(apps);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator()));
        recyclerView.computeHorizontalScrollExtent();
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));


        // do something after 100ms

        try {

            final Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    add(0);
                }
            }, 1000);

            final Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    add(1);


                }
            }, 2000);

            final Handler handler3 = new Handler();
            handler3.postDelayed(new Runnable() {
                @Override
                public void run() {
                    add(2);


                }
            }, 3000);

            final Handler handler4 = new Handler();
            handler4.postDelayed(new Runnable() {
                @Override
                public void run() {
                    add(3);


                }
            }, 4000);

            final Handler handler5 = new Handler();
            handler5.postDelayed(new Runnable() {
                @Override
                public void run() {
                    remove(0);
                }
            }, 5000);
//
            final Handler handler6 = new Handler();
            handler6.postDelayed(new Runnable() {
                @Override
                public void run() {
                    remove(0);
                }
            }, 6000);


            final Handler handler7 = new Handler();
            handler7.postDelayed(new Runnable() {
                @Override
                public void run() {
                    remove(0);
                }
            }, 7000);

            final Handler handler8 = new Handler();
            handler8.postDelayed(new Runnable() {
                @Override
                public void run() {
                    remove(0);

                    final RippleBackground rippleBackground = findViewById(R.id.content);
                    rippleBackground.startRippleAnimation();

                    front.setImageResource(R.drawable.task_complete);
                    back.setImageResource(R.drawable.green_circle);

                    ProgressBar progressBar = findViewById(R.id.spin_kit);
                    ThreeBounce doubleBounce = new ThreeBounce();
                    progressBar.setIndeterminateDrawable(doubleBounce);
                    progressBar.setVisibility(View.GONE);

                    scanning.setPadding(20, 0, 0, 0);

                    if (Build.VERSION.SDK_INT < 23) {
                        scanning.setTextAppearance(getApplicationContext(), android.R.style.TextAppearance_Medium);
                        scanning.setText(junk.getString("junk") + " MB of Junk Files Are Cleared");
                    } else {
                        scanning.setTextAppearance(android.R.style.TextAppearance_Medium);
                        scanning.setText(junk.getString("junk") + " MB of Junk Files Are Cleared");
                    }

                    ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flipping);
                    anim.setTarget(front);
                    anim.setDuration(3000);
                    anim.start();

                    anim.addListener(new AnimatorListenerAdapter() {

                        @Override
                        public void onAnimationStart(Animator animation) {
                            scanning.setText("Cleared " + junk.getString("junk") + " MB");
                            scanning.setTextColor(Color.parseColor("#FFFFFF"));
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mInterstitialAd.show();

                            rippleBackground.stopRippleAnimation();

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 1000);
                        }
                    });

                    files.setText("");
                }
            }, 8000);


        } catch (Exception ex) {
            Log.e("ERR ScaningJunk", ex.getMessage());
        }


    }

    public void startAnim(int i1) {

        if (i1 == 1) {
            avi1.show();
            avi3.show();
            avi5.show();

            avi2.hide();
            avi4.hide();
            avi6.hide();
        } else if (i1 == 2) {
            avi2.show();
            avi4.show();
            avi6.show();

            avi1.hide();
            avi3.hide();
            avi5.hide();
        } else if (i1 == 3) {
            avi2.show();
            avi4.show();
            avi6.show();

            avi1.show();
            avi3.show();
            avi5.show();
        } else if (i1 == 4) {
            avi2.show();
            avi3.show();
            avi5.show();

            avi1.show();
            avi2.show();
            avi6.show();
        }
    }

    public void add(int position) {
        int p = 0 + (int) (Math.random() * ((packages.size() - 1 - 0) + 1));

        Apps app = new Apps();

        //String packageName = packages.get(p).packageName;

        try {
//            String pName = (String) pm.getApplicationLabel(pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA));
//            ApplicationInfo a = pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            app.setImage(getPackageManager().getApplicationIcon(packages.get(p).packageName));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        app.setSize(packages.get(p).dataDir);
        apps.add(app);

        mAdapter.notifyItemInserted(position);
    }

    public void remove(int positon) {
        mAdapter.notifyItemRemoved(positon);
        apps.remove(positon);
    }

    @Override
    protected void injectVariables() {

    }

    //  class
    public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration(Context context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mDivider = context.getResources().getDrawable(R.drawable.line_divvide, context.getTheme());
            } else {
                mDivider = context.getResources().getDrawable(R.drawable.line_divvide);
            }
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
