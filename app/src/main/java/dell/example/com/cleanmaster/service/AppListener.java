package dell.example.com.cleanmaster.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dell.example.com.cleanmaster.R;

public class AppListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Uri data = intent.getData();
        String installedPackageName = data.getEncodedSchemeSpecificPart();

        if (!(installedPackageName.equals("dell.example.com.cleanmaster"))) {
            final String packageName = installedPackageName;
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            try {
                String appName = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA));
                LayoutInflater inflater = LayoutInflater.from(context);
                View layout = inflater.inflate(R.layout.my_toast, null);

                // ImageView image =  layout.findViewById(R.id.image);

                TextView text = layout.findViewById(R.id.textView1);
                text.setText(appName + " Is Optimized by Fast Cleaner & Battery Saver.");

                Toast toast = new Toast(context);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 120);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            } catch (Exception ex) {
                Log.e("ERR AppListener", ex.getMessage());
            }
        }
    }
}
