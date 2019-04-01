package dell.example.com.cleanmaster.adapter.PagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import dell.example.com.cleanmaster.uis.fragment.BatterySaver;
import dell.example.com.cleanmaster.uis.fragment.CPUCooler;
import dell.example.com.cleanmaster.uis.fragment.JunkCleaner;
import dell.example.com.cleanmaster.uis.fragment.PhoneBooster;

public class MyPageAdapter extends FragmentStatePagerAdapter {

    int num_of_tab;

    public MyPageAdapter(FragmentManager fm, int num_of_tab) {
        super(fm);
        this.num_of_tab = num_of_tab;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                PhoneBooster phoneBooster = new PhoneBooster();
                return phoneBooster;
            case 1:
                BatterySaver batterySaver = new BatterySaver();
                return batterySaver;
            case 2:
                CPUCooler cpuCooler = new CPUCooler();
                return cpuCooler;
            case 3:
                JunkCleaner junkCleaner = new JunkCleaner();
                return junkCleaner;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return num_of_tab;
    }
}
