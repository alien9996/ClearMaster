package dell.example.com.cleanmaster.uis;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dell.example.com.cleanmaster.R;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progress_dialog;
    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(injectLayout());

        context = BaseActivity.this;

        injectVariables();
        injectView();
    }

    protected abstract int injectLayout();

    protected abstract void injectView();

    protected abstract void injectVariables();

    public void showProgressDialog(String title){
        try{
            if(progress_dialog == null) {
                progress_dialog.setTitle(title);
                progress_dialog = new ProgressDialog(context);
                progress_dialog.show();
                progress_dialog.setCancelable(false);
            }
        } catch (Exception ex) {
            progress_dialog = new ProgressDialog(this.getParent());
            progress_dialog.show();
            progress_dialog.setCancelable(false);
            ex.printStackTrace();
        }

    }

    public void closeProgressDialog() {
        try{
            if(progress_dialog != null) {
                progress_dialog.cancel();
                progress_dialog = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showCustomToast(String message) {

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.my_toast, null);

        ImageView image = layout.findViewById(R.id.image);

        TextView text = layout.findViewById(R.id.textView1);
        text.setText(message);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 70);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
