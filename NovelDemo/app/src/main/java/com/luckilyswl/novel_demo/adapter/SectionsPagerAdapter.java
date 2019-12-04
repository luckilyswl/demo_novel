package com.luckilyswl.novel_demo.adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.luckilyswl.novel_demo.R;

import java.util.ArrayList;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> datas;

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    public SectionsPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    public void setDatas(ArrayList<Fragment> datas){
        this.datas = datas;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

}


 class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
        ,View.OnLongClickListener{

    View tocView;
    private TextView textview_bookFindClass;
    private ImageView imageView;
    private SectionsPagerAdapter.OnItemClickListener listener;

    public ViewHolder(View itemView, SectionsPagerAdapter.OnItemClickListener mlistener) {
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


