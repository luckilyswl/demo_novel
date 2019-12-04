package com.luckilyswl.novel_demo.fragment;

import android.app.UiModeManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.luckilyswl.novel_demo.R;

public class ReadListFragment extends Fragment implements View.OnClickListener {

    private Button night_btn,not_night_btn,auto_btn;
    public ReadListFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pager_book_list,container,false);

        initView(rootView);

        return rootView;
    }

    private void initView(View rootView) {
        night_btn = rootView.findViewById(R.id.night_btn);
        night_btn.setOnClickListener(this);
        not_night_btn = rootView.findViewById(R.id.not_night_btn);
        not_night_btn.setOnClickListener(this);
        auto_btn = rootView.findViewById(R.id.auto_btn);
        auto_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        final UiModeManager uiModeManager = (UiModeManager) getActivity().getSystemService(Context.UI_MODE_SERVICE);
        switch (v.getId()){
            case R.id.night_btn:
                uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
                break;
            case R.id.not_night_btn:
                uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
                break;
            case R.id.auto_btn:
                uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_AUTO);
                break;
        }
    }
}
