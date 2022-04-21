package fi.arcada.sos_projekt_chart_sma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DatasetViewAdapter extends RecyclerView.Adapter<DatasetViewAdapter.DatasetViewHolder> {

    ArrayList<DataItem> dataset;
    Context context;

    // Konstruktor-metod
    public DatasetViewAdapter(ArrayList<DataItem> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    @NonNull
    @Override
    public DatasetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate betyder att koppla recyclerView-layouten till raderna (elementen)

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new DatasetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatasetViewHolder holder, int position) {
        // varje nytt element på skärmen får sitt innehåll här
        DataItem item = dataset.get(position);

        holder.dataName.setText(item.getName());
        holder.dataValue.setText(String.format("%.2f", item.getValue()));
    }

    @Override
    public int getItemCount() {
        return dataset.size(); // storleken på vår ArrayList med data
    }

    // Inre "holder"-class som beskriver ett element i vår lista
    public class DatasetViewHolder extends RecyclerView.ViewHolder {

        TextView dataName;
        TextView dataValue;

        public DatasetViewHolder(@NonNull View itemView) {
            super(itemView);
            dataName = itemView.findViewById(R.id.dataName);
            dataValue = itemView.findViewById(R.id.dataValue);

        }
    }
}
