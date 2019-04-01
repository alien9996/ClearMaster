package dell.example.com.cleanmaster.uis.fragment;

import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.InterstitialAd;
import com.hookedonplay.decoviewlib.DecoView;

import java.util.TimerTask;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.uis.BaseFragment;

public class PhoneBooster extends BaseFragment {

    private DecoView arcView;
    private TextView scanning, centree, totalram, usedram, appused, appsfreed,
            processes, top, bottom, ramperct;
    private LinearLayout scanlay, optimizelay;
    public static ImageView optimizebutton;

    TimerTask timer = null;
    TimerTask timer2 = null;
    int x, y;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    InterstitialAd mInterstitialAd;

    @Override
    protected int injectLayout() {
        return R.layout.phone_booster;
    }

    @Override
    protected void injectView() {

    }

    @Override
    protected void injectVariables() {

    }
}
