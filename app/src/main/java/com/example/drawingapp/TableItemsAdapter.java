package com.example.drawingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TableItemsAdapter extends RecyclerView.Adapter<TableItemsAdapter.ViewHolder> {
    public ArrayList<TableItem> tableItems;
    private final TableItemClickListener tableItemClickListener;

    public TableItemsAdapter(ArrayList<TableItem> tableItems, TableItemClickListener tableItemClickListener) {
        this.tableItems = tableItems;
        this.tableItemClickListener = tableItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.table_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.shapeTypeTV.setText(tableItems.get(position).getShapeName());
        holder.xStartTV.setText(tableItems.get(position).getxStart());
        holder.yStartTV.setText(tableItems.get(position).getyStart());
        holder.xEndTV.setText(tableItems.get(position).getxEnd());
        holder.yEndTv.setText(tableItems.get(position).getyEnd());

        holder.itemView.setOnLongClickListener(view -> {
            tableItemClickListener.onTableItemClick(tableItems.get(position), position);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return tableItems.size();
    }

    public interface TableItemClickListener{
        void onTableItemClick(TableItem tableItem, int itemPosition);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView shapeTypeTV;
        TextView xStartTV;
        TextView yStartTV;
        TextView xEndTV;
        TextView yEndTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            shapeTypeTV = itemView.findViewById(R.id.shape_type_value);
            xStartTV = itemView.findViewById(R.id.shape_xStart_value);
            yStartTV = itemView.findViewById(R.id.shape_yStart_value);
            xEndTV = itemView.findViewById(R.id.shape_xEnd_value);
            yEndTv = itemView.findViewById(R.id.shape_yEnd_value);
        }
    }
}
