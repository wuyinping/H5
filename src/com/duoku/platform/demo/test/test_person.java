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
public class test_person extends ActivityInstrumentationTestCase2 {
    private Solo solo;
    public static int bd_actionnotice_toptitle = 0x7f0a019f;
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.baidu.bdgamesdk.demo.activity.WelcomeActivity";
    private static Class<?> launcherActivityClass;
    public static int height = 0;

    static {
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public test_person() throws ClassNotFoundException {
        super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);

    }

    final String TAG = "Test";

    public void setUp() throws Exception {
        Config config = new Config();
        config.timeout_large = 2000;
        config.timeout_small=2000;
        solo = new Solo(getInstrumentation(), config);
        getActivity();

    }

    @Override
    public void tearDown() throws Exception {
//        DataCleanManager.cleanApplicationData(solo.getCurrentActivity().getApplicationContext(),"com.baidu.bdgamesdk.demo");
//        Runtime.getRuntime().exec("rm -rf /sdcard/com.baidu.plaformsdk");
        //solo.finishOpenedActivities();
        //sendmsg.send();
//        if(solo.waitForText("百度账号登录")){
//          solo.finishOpenedActivities();
//        }else{
//            solo.clickOnView(solo.getView(Constants.BUTTON_CLOSE));
//            solo.goBack();
//        }
        solo.finishOpenedActivities();


    }

    public void test_person() {
        // 判断是否有公告
        if (solo.searchText("公  告")) {
            solo.clickOnView(solo.getView("bd_iv_notice_close"));
        }
        solo.clickOnView(solo.getView("login_btn"));
        if (solo.searchText(Constants.TEXT_BAIDU_LOGIN)) {
            BDlogin.login(solo, Constants.USER_BAIDU2, Constants.PASS_BAIDU2);
        }

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
        int height = metircs.heightPixels / 2;
        solo.clickOnScreen(40, height);




        if (solo.searchText("我", 1, false)) {
            solo.clickOnText("我");
            if (solo.waitForWebElement(By.className(Constants.PORTAIT_ID)) || solo.waitForWebElement(By.className(Constants.USER_NAME))) {
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H71", 1);
                sendmsg.send();
            } else {
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H71", 0);
                sendmsg.send();
            }

            //我的物品
            if (solo.searchText("我的物品", 1, false)) {
                solo.clickOnText("我的物品");
                //检查礼包列表
                if (solo.waitForWebElement(By.className("user_photo"), 2000, false)) {
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H72", 1);
                    sendmsg.send();
                } else if (solo.searchText("暂时没有内容")) {
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H72", 1);
                    sendmsg.send();
                } else {
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H72", 0);

                    sendmsg.send();
                }

                solo.clickOnText("激活码");
                if (solo.waitForWebElement(By.className("user_photo"), 2000, false)) {
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H73", 1);
                    sendmsg.send();
                } else if (solo.searchText("暂时没有内容")) {
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H73", 1);
                    sendmsg.send();
                } else {
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H73", 0);

                    sendmsg.send();
                }

                solo.clickOnText("代金券");
                if (solo.searchText("有效期", 1, false)) {
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H74", 1);
                    sendmsg.send();
                } else if (solo.searchText("暂时没有内容")) {
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H74", 1);
                    sendmsg.send();
                } else {
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H74", 0);

                    sendmsg.send();
                }
            }
            solo.goBack();
            //切换账号
            if(solo.searchText("切换帐号")){
                solo.clickOnText("切换帐号");
                if (solo.searchText(Constants.TEXT_BAIDU_LOGIN)) {
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H75", 1);
                    sendmsg.send();
                } else {
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H75", 0);
                    sendmsg.send();
                }
            }

        } else {
            Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H7", 0);

            sendmsg.send();
        }

    }

}
