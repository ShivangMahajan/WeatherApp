package com.sabbey.weatherapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<CardView> cardViewArrayList;

    public Adapter(ArrayList<CardView> cardViewArrayList) {
        this.cardViewArrayList = cardViewArrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView dTemp;
        TextView dDateTime;
        TextView dTime;
        TextView dCondition;
        ImageView dImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dTemp = itemView.findViewById(R.id.dtemp);
            dDateTime = itemView.findViewById(R.id.date);
            dTime = itemView.findViewById(R.id.time);
            dCondition = itemView.findViewById(R.id.condition);
            dImage = itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_layout, viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        CardView cardView = cardViewArrayList.get(i);
        viewHolder.dTemp.setText(cardView.getmDTemp());
        viewHolder.dDateTime.setText(cardView.getmDDate());
        viewHolder.dTime.setText(cardView.getmDTime());
        viewHolder.dCondition.setText(cardView.getmCondtion());
        viewHolder.dImage.setImageResource(cardView.getmImage());

    }

    @Override
    public int getItemCount() {
        return cardViewArrayList.size();
    }
}
