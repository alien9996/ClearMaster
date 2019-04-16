package dell.example.com.cleanmaster.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.model.Apps;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    public List<Apps> apps;

    public RecyclerAdapter(List<Apps> apps) {
        this.apps = apps;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_apps, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Apps app = apps.get(position);
        holder.size.setText(app.getSize());
        holder.image.setImageDrawable(app.getImage());
    }

    @Override
    public int getItemCount() {
        if(apps != null) {
            return apps.size();
        } else {
            return 0;
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView size;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            size = itemView.findViewById(R.id.apptext);
            image = itemView.findViewById(R.id.appimage);

        }
    }
}
