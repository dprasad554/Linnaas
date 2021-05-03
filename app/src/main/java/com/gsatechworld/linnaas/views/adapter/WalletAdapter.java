package com.gsatechworld.linnaas.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.linnaas.R;
import com.gsatechworld.linnaas.utils.wallet.WalletResult;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter {

    Context context;
    private List<WalletResult> list = new ArrayList<>();
    public WalletAdapter(Context context,List<WalletResult> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallet_list_,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder)holder).bindView(position);
        ((ListViewHolder)holder).txt_name.setText(list.get(position).getVerse_name());
        ((ListViewHolder)holder).txt_data.setText(list.get(position).getData());
        ((ListViewHolder)holder).txt_points.setText(list.get(position).getPoints());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<WalletResult> resultList){
        list.clear();
        list.addAll(resultList);
        notifyDataSetChanged();
    }
    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txt_name;
        TextView txt_data;
        TextView txt_points;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = (itemView).findViewById(R.id.verse_name);
            txt_data = (itemView).findViewById(R.id.verse_data);
            txt_points = (itemView).findViewById(R.id.verse_points);
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
