package xishuang.floatwindow;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import floatwindow.xishuang.float_lib.FloatActionController;
import floatwindow.xishuang.float_lib.permission.FloatPermissionManager;

/**
 * Author:xishuang
 * Date:2017.08.01
 * Des:测试主页
 * <p>
 * #####################################################
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无bug             #
 * #                                                   #
 * #####################################################
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btOpenFloat = (Button) findViewById(R.id.open_float);
        Button btRedDot = (Button) findViewById(R.id.red_dot);

        assert btOpenFloat != null;
        btOpenFloat.setOnClickListener(this);
        assert btRedDot != null;
        btRedDot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.open_float) {
            boolean isPermission = FloatPermissionManager.getInstance().applyFloatWindow(this);
            //有对应权限或者系统版本小于7.0
            if (isPermission || Build.VERSION.SDK_INT < 24) {
                //开启悬浮窗
                FloatActionController.getInstance().startMonkServer(this);
            }
        } else if (v.getId() == R.id.red_dot) {
            //开启小红点
            FloatActionController.getInstance().setObtainNumber(1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭悬浮窗
        FloatActionController.getInstance().stopMonkServer(this);
    }
}
