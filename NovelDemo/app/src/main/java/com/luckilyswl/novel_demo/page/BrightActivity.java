package com.luckilyswl.novel_demo.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.luckilyswl.novel_demo.R;
import com.luckilyswl.novel_demo.utils.bright.BrightUtil;
import com.luckilyswl.novel_demo.utils.bright.ScreenLightUtils;

public class BrightActivity extends AppCompatActivity implements View.OnClickListener {


    private SeekBar mCurrentScreenSeekBar;
    private TextView mCurrentNum;
    private SeekBar mSystemScreenSeekBar;
    private TextView mSystemNum;
    private EditText mScreenOffNum;
    private Button mSure;
    private Button mKeepScreen;
    private Button mRelaseScreen;
    private Button mFollowSystem;

    private int currentScreenNum = 0;
    private int screenOffTime = 0;
    private final int REQUEST_CODE_WRITE_SETTINGS = 100;
    private boolean isSetKeepLight = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bright);

        requestWriteSettings();
        initView();
        initData();
    }

    private void initData() {
        currentScreenNum = ScreenLightUtils.getScreenBrightness(this);

        mCurrentScreenSeekBar.setProgress(currentScreenNum);
        mSystemScreenSeekBar.setProgress(currentScreenNum);
        mCurrentNum.setText(currentScreenNum + "");
        mSystemNum.setText(currentScreenNum + "");
        mCurrentScreenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentScreenNum = progress;
                mCurrentNum.setText(currentScreenNum + "");
                ScreenLightUtils.setScreenBrightness(BrightActivity.this, currentScreenNum);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //对于系统的屏幕亮度调节，必须是手动调节才有效果
//        if(ScreenLightUtils.getScreenMode()==1){
//            ScreenLightUtils.setScreenMode(0);
//        }
        mSystemScreenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentScreenNum = progress;
                mSystemNum.setText(currentScreenNum + "");
                ScreenLightUtils.changSystemBrightness(BrightActivity.this,currentScreenNum);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //获取当前息屏时间
        screenOffTime = BrightUtil.getDormant(this);
        mScreenOffNum.setText(screenOffTime + "");

    }

    private void initView() {
        mCurrentScreenSeekBar = findViewById(R.id.currentScreenSeekBar);
        mCurrentNum = findViewById(R.id.currentNum);
        mSystemScreenSeekBar = findViewById(R.id.systemScreenSeekBar);
        mSystemNum = findViewById(R.id.systemNum);
        mScreenOffNum = findViewById(R.id.screenOffNum);
        mSure = findViewById(R.id.Sure);
        mSure.setOnClickListener(this);
        mKeepScreen = findViewById(R.id.keepScreen);
        mKeepScreen.setOnClickListener(this);
        mRelaseScreen = findViewById(R.id.relaseScreen);
        mRelaseScreen.setOnClickListener(this);
        mFollowSystem = findViewById(R.id.followSystem);
        mFollowSystem.setOnClickListener(this);
    }

    /**
     * 申请权限
     */
    private void requestWriteSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //大于等于23 请求权限
            if (!Settings.System.canWrite(getApplicationContext())) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_CODE_WRITE_SETTINGS);
            }
        } else {
            //小于23直接设置
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_WRITE_SETTINGS) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //Settings.System.canWrite方法检测授权结果
                if (Settings.System.canWrite(getApplicationContext())) {
                    Toast.makeText(BrightActivity.this, "获取了权限", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BrightActivity.this, "您拒绝了权限", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isSetKeepLight) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Sure:
                String s = mScreenOffNum.getText().toString();
                if (!TextUtils.isEmpty(s)) {
                    screenOffTime = Integer.parseInt(s);
                    BrightUtil.setDormant(this,screenOffTime);
                }
                break;
            case R.id.keepScreen:
                isSetKeepLight = true;
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                break;
            case R.id.relaseScreen:
                isSetKeepLight = false;
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                break;
            case R.id.followSystem:
                if(flag){
                    BrightUtil.autoBrightness(this,flag);
                    mFollowSystem.setBackgroundResource(R.color.red);
                    flag = false;
                }else {
                    BrightUtil.autoBrightness(this,flag);
                    mFollowSystem.setBackgroundResource(R.color.white);
                    flag = true;
                }

                break;
        }
    }

    boolean flag = true;

    public static void start(FragmentActivity activity) {
        Intent intent = new Intent(activity,BrightActivity.class);
        activity.startActivity(intent);
    }

}
