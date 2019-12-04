package com.luckilyswl.novel_demo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.luckilyswl.novel_demo.R;

import java.util.ArrayList;


public class AdapterBookFind extends RecyclerView.Adapter {

    private ArrayList<String> myCategory;

    public AdapterBookFind(ArrayList<String> category){
        this.myCategory = category;
    }

    private AdapterBookFind.OnItemClickListener listener;

    public void setOnItemClickListener(AdapterBookFind.OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
            ,View.OnLongClickListener{

        View tocView;
        private TextView textview_bookFindClass;
        private ImageView imageView;
        private AdapterBookFind.OnItemClickListener listener;

        public ViewHolder(View itemView, AdapterBookFind.OnItemClickListener mlistener) {
            super(itemView);
            tocView = itemView;
            textview_bookFindClass =  itemView.findViewById(R.id.bookFind_class);
            imageView =  itemView.findViewById(R.id.bookFind_image);
            listener = mlistener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener != null){
                listener.onItemClick(v, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(listener != null){
                listener.onItemLongClick(v, getAdapterPosition());
            }
            return false;
        }
    }

    private Context context;

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_find_item, parent, false);
        context = parent.getContext();
        ViewHolder holder = new ViewHolder(view, listener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).textview_bookFindClass.setText(myCategory.get(position));

        switch (position) {
            case 0:
                Glide.with(context).load(R.mipmap.ic_launcher).into(((ViewHolder) holder).imageView);
                break;
            case 1:
                Glide.with(context).load(R.mipmap.ic_launcher).into(((ViewHolder) holder).imageView);
                break;
            case 2:
                Glide.with(context).load(R.mipmap.ic_launcher).into(((ViewHolder) holder).imageView);
                break;
            case 3:
                Glide.with(context).load(R.mipmap.ic_launcher).into(((ViewHolder) holder).imageView);
                break;
            case 4:
                Glide.with(context).load(R.mipmap.ic_launcher).into(((ViewHolder) holder).imageView);
                break;
            case 5:
                Glide.with(context).load(R.mipmap.ic_launcher).into(((ViewHolder) holder).imageView);
                break;
            case 6:
                Glide.with(context).load(R.mipmap.ic_launcher).into(((ViewHolder) holder).imageView);
                break;
            case 7:
                Glide.with(context).load(R.mipmap.ic_launcher).into(((ViewHolder) holder).imageView);
                break;
            default:

                break;
        }
        ((ViewHolder) holder).imageView.setColorFilter(Color.parseColor("#55555555"));

    }

    @Override
     public int getItemCount() {
         return myCategory.size();
     }

}
