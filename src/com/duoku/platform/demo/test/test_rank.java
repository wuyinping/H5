package com.duoku.platform.demo.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.DisplayMetrics;
import com.duoku.platform.demo.test.utils.Constants;
import com.duoku.platform.demo.test.utils.DeviceUtil;
import com.duoku.platform.demo.test.utils.login1;
import com.duoku.platform.demo.test.utils.sendmsg;
import com.robotium.solo.Solo;

@SuppressWarnings("rawtypes")
public class test_rank extends ActivityInstrumentationTestCase2 {

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

    public test_rank() {
        super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);
    }

    public void setUp() throws Exception {
        Solo.Config config = new Solo.Config();
        config.timeout_large = 3000;
        solo = new Solo(getInstrumentation(), config);
        getActivity();
        Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H4", 0);
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

    }

    @Override
    public void tearDown() throws Exception {
//        DataCleanManager.cleanApplicationData(solo.getCurrentActivity().getApplicationContext(),"com.baidu.bdgamesdk.demo");
//        Runtime.getRuntime().exec("rm -rf /sdcard/com.baidu.plaformsdk");
        //solo.finishOpenedActivities();

        solo.clickOnView(solo.getView(Constants.BUTTON_CLOSE));
        solo.goBack();
        solo.finishOpenedActivities();
    }

    public void test_rank(){
        if (solo.searchText("排行榜",1,false)){
            solo.clickOnText("排行榜");
            //solo.waitForWebElement(By.className(Constants.GAME_ICON), 2000, false)

            if (solo.searchText("下载",1,false)){
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H4",1);
                sendmsg.send();
            }
            else if (solo.searchText("暂时没有内容")){
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H4",1);
                sendmsg.send();
            }else{
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H4",0);
                sendmsg.send();
            }


            Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H5",0);
            //solo.scrollToTop();
            solo.clickOnText("热门");

            if (solo.searchText("下载",1,false)){
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H5",1);
                sendmsg.send();
            } else if (solo.searchText("暂时没有内容")){
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H5",1);
                sendmsg.send();
            }else{
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H5",0);
                sendmsg.send();
            }

            Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H6",0);
            //solo.scrollToTop();
            solo.clickOnText("封测");

            if (solo.searchText("下载",1,false)){
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H6",1);
                sendmsg.send();
            } else if (solo.searchText("暂时没有内容")){
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H6",1);
                sendmsg.send();
            }else{
                sendmsg.send();
            }

        }else{
            sendmsg.send();
        }

    }



}
