package com.luckilyswl.novel_demo.utils.bright;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import android.view.WindowManager;

/**
 *   亮度工具类
 */
public class BrightUtil {

    /**
     * 判断是否开启了自动亮度调节
     */
    public static boolean isAutoBrightness(Context context){
        ContentResolver resolver = context.getContentResolver();
        boolean automicBrightness = false;
        try {
            automicBrightness = Settings.System.getInt(resolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        return automicBrightness;
    }


    /**
     * 获取屏幕的亮度
     */
    public static int getScreenBrightness(Context context){
        int nowBrightnessValue = 0;
        ContentResolver resolver = context.getContentResolver();

        try {
            nowBrightnessValue = Settings.System.getInt(resolver,
                    Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        return nowBrightnessValue;
    }

    /**
     * 设置当前Activity显示时的亮度
     * 屏幕亮度最大数值一般为255，各款手机有所不同
     * screenBrightness 的取值范围在[0,1]之间
     */
    public static void setBrightness(Activity activity,int brightness){
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.screenBrightness = Float.valueOf(brightness)*(1f / 255f);
        activity.getWindow().setAttributes(lp);
    }

    /**
     * 开启关闭自动亮度调节
     */
    public static boolean autoBrightness(Context activity,boolean flag){
        int value = 0;
        if(flag){
            value = Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC; // 开启
        }else {
            value = Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL; // 关闭
        }
        return Settings.System.putInt(activity.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                value);
    }

    /**
     * 保存亮度设置状态，退出app也能保持设置状态
     */
    public static void saveBrightness(Context context,int brightness){
        ContentResolver resolver = context.getContentResolver();
        Uri uri = android.provider.Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS);
        android.provider.Settings.System.putInt(resolver,
                Settings.System.SCREEN_BRIGHTNESS,brightness);
        resolver.notifyChange(uri,null);
    }

    /**
     * 获取系统休眠时间
     */
    public static int getDormant(Context context) {
        ContentResolver resolver = context.getContentResolver();
        int result = 0;
        try {
            result = Settings.System.getInt(resolver,
                    Settings.System.SCREEN_OFF_TIMEOUT);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 设置系统的休眠时间
     */
    public static void setDormant(Context context,int time) {
        ContentResolver resolver = context.getContentResolver();
        Settings.System.putInt(resolver,
                Settings.System.SCREEN_OFF_TIMEOUT, time);
        Uri uri = Settings.System
                .getUriFor(Settings.System.SCREEN_OFF_TIMEOUT);
        resolver.notifyChange(uri, null);
    }

}
