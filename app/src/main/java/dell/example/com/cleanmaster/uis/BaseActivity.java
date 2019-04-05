package dell.example.com.cleanmaster.uis;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

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
}
