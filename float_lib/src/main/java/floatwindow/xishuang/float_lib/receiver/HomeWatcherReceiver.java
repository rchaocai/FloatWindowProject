package floatwindow.xishuang.float_lib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import floatwindow.xishuang.float_lib.FloatActionController;

/**
 * Author:xishuang
 * Date:2017.08.01
 * Des:一些Home建与切换键的广播监听，需要动态注册
 */

public class HomeWatcherReceiver extends BroadcastReceiver {
    private static final String TAG = "HomeWatcherReceiver";
    private static final String SYSTEM_DIALOG_FROM_KEY = "reason";
    private static final String SYSTEM_DIALOG_FROM_RECENT_APPS = "recentapps";
    private static final String SYSTEM_DIALOG_FROM_HOME_KEY = "homekey";
    private static final String SYSTEM_DIALOG_FROM_LOCK = "lock";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(TAG, "onReceive: action: " + action);
        //根据不同的信息进行一些个性操作
        if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
            String from = intent.getStringExtra(SYSTEM_DIALOG_FROM_KEY);
            Log.i(TAG, "from: " + from);
            if (SYSTEM_DIALOG_FROM_HOME_KEY.equals(from)) { //短按Home键
                Log.i(TAG, "Home Key");
                //按home键会直接关闭悬浮窗
                FloatActionController.getInstance().stopMonkServer(context);
            } else if (SYSTEM_DIALOG_FROM_RECENT_APPS.equals(from)) { //长按Home键或是Activity切换键
                Log.i(TAG, "long press home key or activity switch");
            } else if (SYSTEM_DIALOG_FROM_LOCK.equals(from)) { //锁屏操作
                Log.i(TAG, "lock");
            }
        }
    }

}
