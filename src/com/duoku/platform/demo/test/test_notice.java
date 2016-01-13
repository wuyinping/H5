package com.duoku.platform.demo.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.DisplayMetrics;
import com.duoku.platform.demo.test.utils.Constants;
import com.duoku.platform.demo.test.utils.DeviceUtil;
import com.duoku.platform.demo.test.utils.login1;
import com.duoku.platform.demo.test.utils.sendmsg;
import com.robotium.solo.By;
import com.robotium.solo.Solo;

@SuppressWarnings("rawtypes")
public class test_notice extends ActivityInstrumentationTestCase2 {

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

    public test_notice() {
        super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);
    }

    public void setUp() throws Exception {
        Solo.Config config = new Solo.Config();
        config.timeout_large = 20;
        solo = new Solo(getInstrumentation(), config);
        getActivity();
        Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H8", 0);
        // 判断是否有公告
        if (solo.searchText("公  告")) {
            solo.clickOnView(solo.getView("bd_iv_notice_close"));
        }
        solo.clickOnView(solo.getView("login_btn"));
        if (solo.searchText(Constants.TEXT_BAIDU_LOGIN)) {
            login1.login(solo, "duoku9991@126.com", "y2631488");
        }

        if (solo.waitForView(bd_actionnotice_toptitle)) {
            solo.clickOnView(solo.getView(Constants.LOGINNOTICE_CLOSE));
        }
        DisplayMetrics metircs = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metircs);
        height = metircs.heightPixels / 2;
        solo.clickOnScreen(40, height);

    }

    @Override
    public void tearDown() throws Exception {
//        DataCleanManager.cleanApplicationData(solo.getCurrentActivity().getApplicationContext(),"com.baidu.bdgamesdk.demo");
//        Runtime.getRuntime().exec("rm -rf /sdcard/com.baidu.plaformsdk");
        //solo.finishOpenedActivities();
        sendmsg.send();
        solo.clickOnView(solo.getView(Constants.BUTTON_CLOSE));
        solo.goBack();
        solo.finishOpenedActivities();
    }

    public void test_notice(){
        if (solo.searchText("公告",1,false)) {
            solo.clickOnText("公告");
            if (solo.waitForWebElement(By.className(Constants.NOTICE_ICON), 1, 2000, false)) {
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H8", 1);
            }
            if (solo.getWebElements(By.textContent("暂时没有内容")) != null) {
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H8", 1);
            }
        }
    }



}
