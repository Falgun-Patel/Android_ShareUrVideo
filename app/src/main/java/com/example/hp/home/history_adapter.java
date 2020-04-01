package com.example.hp.home;

/**
 * Created by hp on 27-02-2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by filipp on 9/16/2016.
 */
public class history_adapter extends RecyclerView.Adapter<history_adapter.ViewHolder> {

    private Context context;
    private List<MyData> my_data;

    public history_adapter(Context context, List<MyData> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View  historyView =LayoutInflater.from(parent.getContext()).inflate(R.layout.base_history,parent,false);
        return new ViewHolder(historyView);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.description.setText(my_data.get(position).getDescription());
        Glide.with(context).load(my_data.get(position).getImage_link()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView description,txt_history,text_owner;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.text_view_creator);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
            txt_history=(TextView)itemView.findViewById(R.id.txt_history);
            text_owner=(TextView)itemView.findViewById(R.id.text_owner);
        }
    }
}


