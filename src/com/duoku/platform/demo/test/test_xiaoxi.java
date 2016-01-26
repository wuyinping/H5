package com.duoku.platform.demo.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.DisplayMetrics;
import com.duoku.platform.demo.test.utils.Constants;
import com.duoku.platform.demo.test.utils.DeviceUtil;
import com.duoku.platform.demo.test.utils.login1;
import com.duoku.platform.demo.test.utils.sendmsg;
import com.robotium.solo.By;
import com.robotium.solo.Solo;
import android.util.Log;

@SuppressWarnings("rawtypes")
public class test_xiaoxi extends ActivityInstrumentationTestCase2 {

    Solo solo;
    public static int bd_actionnotice_toptitle = 0x7f0a019f;
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.baidu.bdgamesdk.demo.activity.WelcomeActivity";
    private static Class<?> launcherActivityClass;
    public static int height=0;
    static {
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    final String TAG = "Test->N01";


    public test_xiaoxi() {
        super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);
    }

    public void setUp() throws Exception {
        Solo.Config config = new Solo.Config();
        config.timeout_large = 2000;
        config.timeout_small=2000;
        solo = new Solo(getInstrumentation(), config);
        getActivity();
        Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H3", 0);

    }

    @Override
    public void tearDown() throws Exception {
//        DataCleanManager.cleanApplicationData(solo.getCurrentActivity().getApplicationContext(),"com.baidu.bdgamesdk.demo");
//        Runtime.getRuntime().exec("rm -rf /sdcard/com.baidu.plaformsdk");
        //solo.finishOpenedActivities();
        Log.d(TAG, "Test_Result"+Constants.Test_Result);

        sendmsg.send();
        solo.clickOnView(solo.getView(Constants.BUTTON_CLOSE));
        solo.goBack();
        solo.finishOpenedActivities();
    }

    public void test_xiaoxi(){
        // 判断是否有公告
        if (solo.searchText("公  告")) {
            solo.clickOnView(solo.getView("bd_iv_notice_close"));
        }
        solo.clickOnView(solo.getView("login_btn"));
        if (solo.searchText(Constants.TEXT_BAIDU_LOGIN)) {
            login1.login(solo, Constants.USER_BAIDU1, Constants.PASS_BAIDU1);

        }

        if (solo.waitForView(bd_actionnotice_toptitle)) {
            solo.clickOnView(solo.getView(Constants.LOGINNOTICE_CLOSE));
        }

        DisplayMetrics metircs = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metircs);
        height = metircs.heightPixels / 2;
        solo.clickOnScreen(40, height);

        if (solo.searchText("消息")) {
            solo.clickOnText("消息");
            if (solo.getWebElements(By.textContent("暂时没有内容")) != null) {
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H3", 1);
            }else if (solo.waitForWebElement(By.className(Constants.MSG_ICON), 1, 2000, false)) {
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H3", 1);
            }

        }
    }



}
