package dell.example.com.cleanmaster.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dell.example.com.cleanmaster.R;
import dell.example.com.cleanmaster.model.PowerItem;

public class PowerAdapter extends RecyclerView.Adapter<PowerAdapter.MyViewHolder> {


    public List<PowerItem> apps;

    public PowerAdapter(List<PowerItem> getapps) {
        this.apps = getapps;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.power_item_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PowerItem app = apps.get(position);
        holder.size.setText(app.getText());
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView size;

        public MyViewHolder(View view) {
            super(view);
            size = view.findViewById(R.id.items);
        }
    }

}
