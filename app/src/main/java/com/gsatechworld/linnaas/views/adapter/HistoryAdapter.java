package com.gsatechworld.linnaas.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.linnaas.R;
import com.gsatechworld.linnaas.utils.history.HistoryResult;
import com.gsatechworld.linnaas.views.ui.fragments.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter {

    Context context;
    List<HistoryResult> historyResultList = new ArrayList<>();
    public HistoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder)holder).bindView(position);
        ((ListViewHolder) holder).verse_name.setText(historyResultList.get(position).getVerse_name());
        ((ListViewHolder) holder).txt_date.setText(historyResultList.get(position).getData());
        ((ListViewHolder) holder).points.setText(historyResultList.get(position).getRead_count());

    }

    @Override
    public int getItemCount() {
        return historyResultList.size();
    }

    public void setData(List<HistoryResult> list){
        historyResultList.clear();
        historyResultList.addAll(list);
        notifyDataSetChanged();
    }
    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView verse_name;
        TextView txt_date;
        TextView points;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            verse_name = (itemView).findViewById(R.id.verse_name);
            txt_date = (itemView).findViewById(R.id.date);
            points = (itemView).findViewById(R.id.verse_points);
        }

        public void bindView(int position){
        }

        @Override
        public void onClick(View view) {
        }
    }

    /*public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        historyList.clear();
        if (charText.length() == 0) {
            historyList.addAll(arraylist);
        } else {
            for (Message wp : arraylist) {

                //String name = wp.getManf_name() + wp.getModel_name();
                String name2 = wp.getPhoneNumber();
                String name3 = wp.getGuestName();

                if (wp.getPhoneNumber().toLowerCase(Locale.getDefault())
                        .contains(charText) || wp.getPhoneNumber().toLowerCase(Locale.getDefault())
                        .contains(charText) || name2.toLowerCase(Locale.getDefault())
                        .contains(charText) || wp.getGuestName().toLowerCase(Locale.getDefault())
                        .contains(charText) || name3.toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    historyList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }*/
}
