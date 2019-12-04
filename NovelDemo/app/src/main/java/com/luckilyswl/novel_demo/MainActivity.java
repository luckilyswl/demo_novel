package com.luckilyswl.novel_demo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.luckilyswl.novel_demo.adapter.SectionsPagerAdapter;
import com.luckilyswl.novel_demo.fragment.FindBooksFragment;
import com.luckilyswl.novel_demo.fragment.ReadListFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ArrayList<Fragment> datas = new ArrayList<>();
        datas.add(new ReadListFragment());
        datas.add(new FindBooksFragment());
        mSectionsPagerAdapter.setDatas(datas);

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }
}