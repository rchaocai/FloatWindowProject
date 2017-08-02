package floatwindow.xishuang.float_lib;

import android.content.Context;
import android.content.Intent;

import floatwindow.xishuang.float_lib.service.FloatMonkService;

/**
 * Author:xishuang
 * Date:2017.08.01
 * Des:与悬浮窗交互的控制类，真正的实现逻辑不在这
 */
public class FloatActionController {

    private FloatActionController() {
    }

    public static FloatActionController getInstance() {
        return LittleMonkProviderHolder.sInstance;
    }

    // 静态内部类
    private static class LittleMonkProviderHolder {
        private static final FloatActionController sInstance = new FloatActionController();
    }

    private FloatCallBack mFloatCallBack;

    /**
     * 开启服务悬浮窗
     */
    public void startMonkServer(Context context) {
        Intent intent = new Intent(context, FloatMonkService.class);
        context.startService(intent);
    }

    /**
     * 关闭悬浮窗
     */
    public void stopMonkServer(Context context) {
        Intent intent = new Intent(context, FloatMonkService.class);
        context.stopService(intent);
    }

    /**
     * 注册监听
     */
    public void registerCallLittleMonk(FloatCallBack callLittleMonk) {
        mFloatCallBack = callLittleMonk;
    }

    /**
     * 调用引导的方法
     */
    public void callGuide(int type) {
        if (mFloatCallBack == null) return;
        mFloatCallBack.guideUser(type);
    }

    /**
     * 悬浮窗的显示
     */
    public void show() {
        if (mFloatCallBack == null) return;
        mFloatCallBack.show();
    }

    /**
     * 悬浮窗的隐藏
     */
    public void hide() {
        if (mFloatCallBack == null) return;
        mFloatCallBack.hide();
    }
    /**
     * 增加数量
     */
    public void addObtainNumer() {
        if (mFloatCallBack == null) return;
        mFloatCallBack.addObtainNumer();
    }
    /**
     * 设置数量
     */
    public void setObtainNumber(int number) {
        if (mFloatCallBack == null) return;
        mFloatCallBack.setObtainNumber(number);
    }
}
