package dell.example.com.cleanmaster.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.model.Apps;

import java.util.List;

/**
 * Created by intag pc on 2/25/2017.
 */

public class ScanCpuApp extends RecyclerView.Adapter<ScanCpuApp.MyViewHolder> {


    /// Get List of Apps Causing Junk Files

    public List<Apps> apps;

    public ScanCpuApp(List<Apps> getapps) {
        apps = getapps;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.scan_cpu_apps, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        Apps app = apps.get(position);
        holder.size.setText(app.getSize());
        holder.image.setImageDrawable(app.getImage());
    }

    @Override
    public int getItemCount() {
        if (apps != null) {
            return apps.size();
        } else return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView size;
        ImageView image;

        public MyViewHolder(View view) {
            super(view);
            size = view.findViewById(R.id.apptext);
            image = view.findViewById(R.id.appimage);

        }
    }
}
