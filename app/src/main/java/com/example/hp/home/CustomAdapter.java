package com.example.hp.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by filipp on 9/16/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<MyData> my_data;

    public CustomAdapter(Context context, List<MyData> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        //View  historyView =LayoutInflater.from(parent.getContext()).inflate(R.layout.base_history,parent,false);
        return new ViewHolder(itemView);
        //return new ViewHolder(historyView);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.description.setText(my_data.get(position).getDescription());
        holder.like.setText(my_data.get(position).getlike());
        Glide.with(context).load(my_data.get(position).getImage_link()).into(holder.imageView);
        final String videopath=my_data.get(position).getidd();

        //video webview clickevent
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,secondmain.class);
                i.putExtra("indexvideo", videopath);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

public void setfilter(List<MyData> my_dataa){
        my_data=new ArrayList<>();
        my_data.addAll(my_dataa);
        notifyDataSetChanged();
}

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView description,like;
        public ImageView imageView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.text_view_creator);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
            like=(TextView)itemView.findViewById(R.id.text_view_likes);
            cardView= (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}