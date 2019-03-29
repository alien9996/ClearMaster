package dell.example.com.cleanmaster.uis;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    protected View view_home;
    protected int layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = injectLayout();
        view_home = inflater.inflate(layout, container, false);
        injectView();
        injectVariables();
        return view_home;
    }

    protected abstract int injectLayout();

    protected abstract void injectView();

    protected abstract void injectVariables();
}
