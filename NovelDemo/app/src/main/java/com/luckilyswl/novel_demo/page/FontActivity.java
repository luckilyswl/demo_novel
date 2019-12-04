package com.luckilyswl.novel_demo.page;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.luckilyswl.novel_demo.MainActivity;
import com.luckilyswl.novel_demo.R;
import com.luckilyswl.novel_demo.utils.font.CommonUtils;
import com.luckilyswl.novel_demo.utils.font.SpUtils;
import com.luckilyswl.novel_demo.view.SliderFont;
import com.zhouyou.view.seekbar.SignSeekBar;

public class FontActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private TextView FzlthFont,MaoFont;
    private SeekBar mSlider;
    private TextView ChangeFont;
    private String[] areas = new String[]{"小号字体","中号字体","大号字体"/*"16","18", "20", "22", "24", "26", "28", "30"*/};

    private  Dialog dialog;



    public static void start(FragmentActivity activity) {
        Intent intent = new Intent(activity,FontActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonUtils.initData(this);
        setContentView(R.layout.activity_font);

        initView();
    }

    private void initView() {
        MaoFont = findViewById(R.id.mao_font);
        MaoFont.setOnClickListener(new AlertClickListener());
//        MaoFont.setOnClickListener(this);

        FzlthFont = findViewById(R.id.fzlth_font);
        FzlthFont.setOnClickListener(new AlertClickListener());
        FzlthFont.setOnClickListener(this);

        ChangeFont = findViewById(R.id.change_font);

        mSlider = findViewById(R.id.slider);
        mSlider.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mao_font:
                Typeface newFont = Typeface.createFromAsset(FontActivity.this.getAssets(),
                        "fonts/mao.ttf");
                ChangeFont.setTypeface(newFont);
                break;
            case R.id.fzlth_font:
                Typeface Font = Typeface.createFromAsset(FontActivity.this.getAssets(),
                        "fonts/fzlth.ttf");
                ChangeFont.setTypeface(Font);
                break;
        }
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if(ChangeFont != null){
            ChangeFont.setTextSize(seekBar.getProgress());
        }
    }


    class AlertClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            dialog = new AlertDialog.Builder(FontActivity.this).setTitle("选择区域")
                    .setItems(areas, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("zhsy", " which = "+which);
                            switch (which) {
                                case 0:
                                    Log.d("zhsy", " case 1 which = " + which);
                                    SpUtils.spSaveFontSize(FontActivity.this, "fontSize", 1);
                                    break;
                                case 1:
                                    Log.d("zhsy", " case 2 which = " + which);
                                    SpUtils.spSaveFontSize(FontActivity.this, "fontSize", 2);
                                    break;
                                case 2:
                                    Log.d("zhsy", " case 3 which = " + which);
                                    SpUtils.spSaveFontSize(FontActivity.this, "fontSize", 3);
                                    break;
                                default:
                                    break;
                            }
                            Intent intent = new Intent(FontActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            finish();
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    }).show();
        }
    }
}
