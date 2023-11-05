package com.example.mycalculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {


        private List<HistoryItem> historyItems;

        public HistoryAdapter(List<HistoryItem> historyItems) {
            this.historyItems = historyItems;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history, parent, false);
            return new ViewHolder(view);
        }


    @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            HistoryItem item = historyItems.get(position);
            holder.historyText.setText(item.getCalculation() + " = " + item.getResult());
        }

        @Override
        public int getItemCount() {
            return historyItems.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView historyText;

            public ViewHolder(View itemView) {
                super(itemView);
                historyText = itemView.findViewById(R.id.historyText);
            }
        }


}
