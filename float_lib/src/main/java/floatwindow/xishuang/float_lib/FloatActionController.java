package floatwindow.xishuang.float_lib;

import android.content.Context;
import android.content.Intent;

import floatwindow.xishuang.float_lib.service.FloatMonkService;

/**
 * Author:xishuang
 * Date:2017.07.13
 * Des:小和尚每日任务模块路由跳转方法的实现类
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

    private FloatCallBack mCallLittleMonk;

    /**
     * 开启小和尚服务悬浮窗
     */
    public void startMonkServer(Context context) {
        Intent intent = new Intent(context, FloatMonkService.class);
        context.startService(intent);
    }

    /**
     * 关闭小和尚服务悬浮窗
     */
    public void stopMonkServer(Context context) {
        Intent intent = new Intent(context, FloatMonkService.class);
        context.stopService(intent);
    }

    /**
     * 注册小和尚的监听
     */
    public void registerCallLittleMonk(FloatCallBack callLittleMonk) {
        mCallLittleMonk = callLittleMonk;
    }

    /**
     * 调用引导的方法
     */
    public void callGuide(int type) {
        if (mCallLittleMonk == null) return;
        mCallLittleMonk.guideUser(type);
    }

    /**
     * 悬浮窗的显示
     */
    public void show() {
        if (mCallLittleMonk == null) return;
        mCallLittleMonk.show();
    }

    /**
     * 悬浮窗的隐藏
     */
    public void hide() {
        if (mCallLittleMonk == null) return;
        mCallLittleMonk.hide();
    }
    /**
     * 增加可领取的数量
     */
    public void addObtainNumer() {
        if (mCallLittleMonk == null) return;
        mCallLittleMonk.addObtainNumer();
    }
    /**
     * 设置可领取的数量
     */
    public void setObtainNumber(int number) {
        if (mCallLittleMonk == null) return;
        mCallLittleMonk.setObtainNumber(number);
    }
}
