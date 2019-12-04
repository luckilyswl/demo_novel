package com.luckilyswl.novel_demo.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luckilyswl.novel_demo.R;

public class BackgroundActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout mLinear;
    private TextView mtitle;
    private Button mChange;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);

        initView();
    }

    private void initView() {
        mLinear = findViewById(R.id.linear);
        mtitle = findViewById(R.id.tv_title);
        mChange = findViewById(R.id.button_change);
        mChange.setOnClickListener(this);
    }


    public static void start(FragmentActivity activity) {
        Intent intent = new Intent(activity,BackgroundActivity.class);
        activity.startActivity(intent);
    }

    boolean isSelect = true;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_change:
                if(isSelect){
                    mLinear.setBackgroundResource(R.color.bg_color);
                    mtitle.setTextColor(Color.parseColor("#ffffff"));



                    isSelect = false;
                }else{
                    mLinear.setBackgroundResource(R.color.bg_white);
                    mtitle.setTextColor(Color.parseColor("#000000"));
                    isSelect = true;
                }
                break;
        }
    }
}
