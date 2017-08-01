package floatwindow.xishuang.float_lib.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.List;
import java.util.Locale;

public class DeviceUtil {

    protected static final String TAG = DeviceUtil.class.getName();

    /**
     * 获取系统的当前的版本
     */
    public static String getDeviceVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取系统当前的SDK版本号
     */
    public static int getDeviceSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机型号
     */
    public static String getDeviceModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机厂商
     */
    public static String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取当前设备所在的地区
     */
    public static String getDeviceCountry(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        return locale.getCountry();
    }

    /**
     * 获取设备当前的使用的语言信息
     */
    public static String getDeviceLanguage(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        return locale.getLanguage();
    }

    /**
     * 获取运营商的名字
     */
    public static String getNetworkOperatorName(Context var0) {
        try {
            TelephonyManager tm = (TelephonyManager) var0.getSystemService(Context.TELEPHONY_SERVICE);
            return tm == null ? "Unknown" : tm.getNetworkOperatorName();
        } catch (Exception e) {
            e.printStackTrace();
            return "Unknown";
        }
    }

    /**
     * 获取Java Object Heap的大小
     */
    public static int getMemorySize(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        return manager.getMemoryClass();
    }

    /**
     * 检测是否有这个权限
     */
    private static boolean checkPermission(Context context, String permission) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager.checkPermission(permission, context.getPackageName()) == 0;
    }

    /**
     * 隐藏键盘
     */
    public void hideInputKeyboard(Activity context) {
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 判断 用户是否安装微信客户端
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获得设备的屏幕高度
     */
    public static int getDeviceHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return manager.getDefaultDisplay().getHeight();
    }

    /**
     * 获取状态栏高度＋标题栏高度
     */
    public static int getTopBarHeight(Activity activity) {
        return activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
    }

    /**
     * 获取状态栏高度
     */
    public static int getOnlyTopBarHeight(Context context) {
        int statusBarHeight;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        } else {
            //拿不到给一个默认值
            statusBarHeight = DensityUtil.dip2px(context, 20);
        }
        return statusBarHeight;
    }
}
