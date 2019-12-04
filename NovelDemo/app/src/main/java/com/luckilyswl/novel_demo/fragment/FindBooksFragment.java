package com.luckilyswl.novel_demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.luckilyswl.novel_demo.adapter.AdapterBookFind;
import com.luckilyswl.novel_demo.R;
import com.luckilyswl.novel_demo.page.BackgroundActivity;
import com.luckilyswl.novel_demo.page.BrightActivity;
import com.luckilyswl.novel_demo.page.FontActivity;

import java.util.ArrayList;

public class FindBooksFragment extends Fragment {

    private static final String TAG = "FindBooksFragment";

    View rootView;
    public FindBooksFragment(){}
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private AdapterBookFind bookAdapterBookFind;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final ArrayList<String> bookClass = new ArrayList<>();
        bookClass.add("亮度调节");
        bookClass.add("改变字体");
        bookClass.add("切换主题");
        bookClass.add("历史");
        bookClass.add("游戏");
        bookClass.add("科幻");
        bookClass.add("女生");
        bookClass.add("所有");

        rootView  = inflater.inflate(R.layout.pager_book_find,container,false);

        recyclerView = rootView.findViewById(R.id.recyler_view_find_book);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        bookAdapterBookFind = new AdapterBookFind(bookClass);
        recyclerView.setAdapter(bookAdapterBookFind);

        bookAdapterBookFind.setOnItemClickListener(new AdapterBookFind.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {    // 点击
                /*Log.e(TAG, ""+position );
                Toast.makeText(getActivity(),"111", Toast.LENGTH_SHORT).show();*/
                switch (position){
                    case 0:
                        BrightActivity.start(getActivity());
                        break;
                    case 1:
                        FontActivity.start(getActivity());
                        break;
                    case 2:
                        BackgroundActivity.start(getActivity());
                        break;
                    case 3:
                        Toast.makeText(getActivity(),"历史", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(getActivity(),"游戏", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(getActivity(),"科幻", Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        Toast.makeText(getActivity(),"女生", Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        Toast.makeText(getActivity(),"所有", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {  // 长按
                Log.e(TAG, "onItemLongClick: 222" );
                Toast.makeText(getActivity(),"222", Toast.LENGTH_SHORT).show();

            }
        });

        return  rootView ;
    }
}
