package dell.example.com.cleanmaster.uis;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;

import dell.example.com.cleanmaster.R;

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

    public void showCustomToast(String message) {

        LayoutInflater inflater = getLayoutInflater(getArguments());
        View layout = inflater.inflate(R.layout.my_toast, null);

        ImageView image = layout.findViewById(R.id.image);

        TextView text = layout.findViewById(R.id.textView1);
        text.setText(message);

        Toast toast = new Toast(getActivity());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 70);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

}
