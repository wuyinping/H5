package com.duoku.platform.demo.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.DisplayMetrics;
import com.duoku.platform.demo.test.utils.BDlogin;
import com.duoku.platform.demo.test.utils.Constants;
import com.duoku.platform.demo.test.utils.DeviceUtil;
import com.duoku.platform.demo.test.utils.sendmsg;
import com.robotium.solo.By;
import com.robotium.solo.Solo;
import com.robotium.solo.Solo.Config;

@SuppressWarnings("rawtypes")
public class test_libao extends ActivityInstrumentationTestCase2 {
    private Solo solo;
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

    @SuppressWarnings("unchecked")
    public test_libao() throws ClassNotFoundException {
        super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);

    }

    final String TAG = "Test->N01";

    public void setUp() throws Exception {
        Config config = new Config();
        config.timeout_large = 2000;
        solo = new Solo(getInstrumentation(), config);
        getActivity();
        Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H2", 0);


    }

    @Override
    public void tearDown() throws Exception {
//        DataCleanManager.cleanApplicationData(solo.getCurrentActivity().getApplicationContext(),"com.baidu.bdgamesdk.demo");
//        Runtime.getRuntime().exec("rm -rf /sdcard/com.baidu.plaformsdk");
        //solo.finishOpenedActivities();
        sendmsg.send();
        solo.sleep(500);
        solo.clickOnView(solo.getView(Constants.BUTTON_CLOSE));
        solo.goBack();
        solo.finishOpenedActivities();
    }

public void test_libao(){
    // 判断是否有公告
    if (solo.searchText("公  告")) {
        solo.clickOnView(solo.getView("bd_iv_notice_close"));
    }
    solo.clickOnView(solo.getView("login_btn"));
    if (solo.searchText(Constants.TEXT_BAIDU_LOGIN)) {
        BDlogin.login(solo, Constants.USER_BAIDU1, Constants.PASS_BAIDU1);
    }
//判断是否有登录后广告
    int id;
    android.app.Activity activity=solo.getCurrentActivity();
    id = activity.getResources().getIdentifier(Constants.LOGINNOTICE_ID,"id",activity.getPackageName());
    //solo.searchText("活动时间")
    if (solo.waitForView(id)) {
        solo.clickOnView(solo.getView(Constants.LOGINNOTICE_CLOSE));
    }

    assertTrue(solo.getCurrentActivity().toString().contains(Constants.GAME_ACTIVITY));
    DisplayMetrics metircs = new DisplayMetrics();
    getActivity().getWindowManager().getDefaultDisplay().getMetrics(metircs);
    height = metircs.heightPixels / 2;
    solo.clickOnScreen(40, height);
    solo.waitForActivity(Constants.CONTAINER_ACTIVITY);




    if (solo.searchText("礼包")) {
        solo.clickOnText("礼包");
        if (solo.waitForWebElement(By.className(Constants.GIFT_ICON), 1, 2000, false)) {
            Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H2", 1);
        }
        if (solo.getWebElements(By.textContent("暂时没有内容")) != null) {
            Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H2", 1);
        }
    }
}



}
