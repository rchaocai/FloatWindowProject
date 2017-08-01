package floatwindow.xishuang.float_lib.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

import floatwindow.xishuang.float_lib.FloatCallBack;
import floatwindow.xishuang.float_lib.FloatWindowManager;
import floatwindow.xishuang.float_lib.FloatActionController;
import floatwindow.xishuang.float_lib.receiver.HomeWatcherReceiver;

public class FloatMonkService extends Service implements FloatCallBack {
    /**
     * 时间周期
     */
    private static final long PERIOD = 1000 * 60;
    /**
     * home键监听
     */
    private HomeWatcherReceiver mHomeKeyReceiver;
    private Handler mHandler;
    private Timer mTimer;

    @Override
    public void onCreate() {
        super.onCreate();
        FloatActionController.getInstance().registerCallLittleMonk(this);
        mHandler = new Handler();
        //注册广播接收者
        mHomeKeyReceiver = new HomeWatcherReceiver();
        final IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(mHomeKeyReceiver, homeFilter);
        //初始化悬浮窗UI
        initWindowData();
        //开始佛语
        startFoYU();
    }

    /**
     * 执行定时弹出佛语的任务
     */
    private void startFoYU() {
        mTimer = new Timer();
        ShowFoYuTask task = new ShowFoYuTask();
        mTimer.schedule(task, PERIOD, PERIOD);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 初始化WindowManager
     */
    private void initWindowData() {
        FloatWindowManager.createFloatWindow(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //移除悬浮窗
        FloatWindowManager.removeFloatWindowManager();
        //注销广播接收者
        if (null != mHomeKeyReceiver) {
            unregisterReceiver(mHomeKeyReceiver);
        }
        //销毁的时候取消定时器
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    /////////////////////////////////////////////////////////实现接口////////////////////////////////////////////////////
    @Override
    public void guideUser(int type) {
        FloatWindowManager.updataRedAndDialog(this);
    }


    /**
     * 悬浮窗的隐藏
     */
    @Override
    public void hide() {
        FloatWindowManager.hide();
    }

    /**
     * 悬浮窗的显示
     */
    @Override
    public void show() {
        FloatWindowManager.show();
    }

    /**
     * 添加可领取的数量
     */
    @Override
    public void addObtainNumer() {
        FloatWindowManager.addObtainNumer(this);
        guideUser(4);
    }

    /**
     * 减少可领取的数量
     */
    @Override
    public void setObtainNumber(int number) {
        FloatWindowManager.setObtainNumber(this, number);
    }

    /**
     * 定时任务，隔一段时间弹出佛语
     */
    public class ShowFoYuTask extends TimerTask {
        @Override
        public void run() {
            Message msg = Message.obtain();
            msg.what = 1;
            mShowFoHandler.sendMessage(msg);
        }
    }

    private Handler mShowFoHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                guideUser(5);
            }
        }
    };
}
