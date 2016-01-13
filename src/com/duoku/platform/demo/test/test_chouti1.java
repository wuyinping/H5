package com.duoku.platform.demo.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.DisplayMetrics;
import com.duoku.platform.demo.test.utils.Constants;
import com.duoku.platform.demo.test.utils.DataCleanManager;
import com.duoku.platform.demo.test.utils.DeviceUtil;
import com.duoku.platform.demo.test.utils.sendmsg;
import com.robotium.solo.By;
import com.robotium.solo.Solo;
import com.robotium.solo.Solo.Config;

@SuppressWarnings("rawtypes")
public class test_chouti1 extends ActivityInstrumentationTestCase2 {
    private Solo solo;
    public static int bd_actionnotice_toptitle = 0x7f0a019f;
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.baidu.bdgamesdk.demo.activity.WelcomeActivity";
    public static int height = 0;
    private static Class<?> launcherActivityClass;

    static {
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public test_chouti1() throws ClassNotFoundException {
        super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);

    }

    final String TAG = "Test->N01";

    public void setUp() throws Exception {
        Config config = new Config();
        //config.timeout_large = 3000;
        solo = new Solo(getInstrumentation(), config);
        getActivity();
        // 判断是否有公告

        if (solo.searchText("公  告")) {
            solo.clickOnView(solo.getView("bd_iv_notice_close"));
        }
        solo.clickOnView(solo.getView("login_btn"));
        if (solo.searchText(Constants.TEXT_BAIDU_LOGIN)) {
            login1.login(solo, "duoku9991@126.com", "y2631488");
        }
        solo.waitForText("登录成功");


        if (solo.waitForView(bd_actionnotice_toptitle)) {
            solo.clickOnView(solo.getView(Constants.LOGINNOTICE_CLOSE));
        }
        DisplayMetrics metircs = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metircs);
        height = metircs.heightPixels / 2;
        solo.clickOnScreen(40, height);
        solo.waitForActivity(Constants.CONTAINER_ACTIVITY);

    }

    @Override
    public void tearDown() throws Exception {
        DataCleanManager.cleanApplicationData(solo.getCurrentActivity().getApplicationContext(), "com.baidu.bdgamesdk.demo");
        Runtime.getRuntime().exec("rm -rf /sdcard/com.baidu.plaformsdk");
        solo.finishOpenedActivities();
    }

    public void test_hello() {


        // nav_notice test
        if (solo.waitForWebElement(By.className(Constants.NOTICE), 1, 2000, false)) {
            solo.clickOnWebElement(By.className(Constants.NOTICE));
            // 列表不为空
            if (solo.waitForWebElement(By.className(Constants.NOTICE_ICON), 1, 2000, false)) {
                solo.clickOnWebElement(By.className(Constants.NOTICE_ICON));
                //solo.waitForWebElement(By.className("announcement_title"), 2000, false)
                if (solo.searchText("公告详情")) {
                    // assertTrue(solo.waitForWebElement(By.className(Constants.NOTICE_DETAIL_CONTENT)));
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H81", 1);
                    sendmsg.send();
                } else {
                    // assertTrue(solo.waitForWebElement(By.className(Constants.NOTICE_DETAIL_CONTENT)));
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H81", 0);
                    sendmsg.send();
                }
            } else if (solo.waitForWebElement(By.textContent("暂时没有内容"))) {
                // 列表为空
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H82", 1);
                sendmsg.send();
            } else {
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H82", 0);
                sendmsg.send();
            }
        } else {
            Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H8", 0);
            sendmsg.send();
        }


        solo.clickOnView(solo.getView(Constants.BUTTON_CLOSE));
        solo.clickOnScreen(40, height);
        //攻略
        if (solo.waitForWebElement(By.className(Constants.STRATEGY), 1, 2000, false)) {
            solo.clickOnWebElement(By.className(Constants.STRATEGY));
            if (solo.waitForWebElement(By.className(Constants.STRATEGY_ITEM), 1, 2000, false)) {
                solo.clickOnWebElement(By.className(Constants.STRATEGY_ITEM));
                if (solo.waitForWebElement(By.className(Constants.STRATEGY_CONTENT), 1, 2000, true)) {
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H91", 1);
                    sendmsg.send();
                } else {
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H91", 0);
                    sendmsg.send();
                }
                solo.clickOnView(solo.getView(Constants.BUTTON_BACK));

            } else if (solo.searchText("暂时没有内容", 1, false)) {
                // assertTrue(solo.getWebElements(By.textContent(Constants.NOMORE_TEXT))!=null);
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H92", 1);
                sendmsg.send();
            } else {
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H92", 0);
                sendmsg.send();
            }

        } else {
            Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H9", 0);
            sendmsg.send();
        }

        solo.clickOnView(solo.getView(Constants.BUTTON_CLOSE));
        solo.clickOnScreen(40, height);

        if (!solo.waitForWebElement(By.className(Constants.PERSON_ID))) {
            Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H7", 0);
            sendmsg.send();
        } else {
            solo.clickOnWebElement(solo.getWebElement(By.className(Constants.PERSON_ID), 0));
            if (solo.waitForWebElement(By.className(Constants.PORTAIT_ID)) || solo.waitForWebElement(By.className(Constants.USER_NAME))) {
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H71", 1);
                sendmsg.send();
            } else {
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H71", 0);
                sendmsg.send();
            }

                /*
                 * 进入我的物品页面
                 */
            if (solo.getWebElement(By.textContent("我的物品"), 0) != null) {
                solo.clickOnWebElement(By.textContent("我的物品"));
                // user_photo -- Constants.MYGIFT_ID
                if (solo.waitForWebElement(By.className("user_photo"), 2000, false)) {
                    solo.clickOnWebElement(solo.getWebElement(By.className("user_photo"), 0));
                    // 我的--礼包详情页面，待定---------------------------------
                    // assertTrue(solo.waitForWebElement(By.className(Constants.GITF_CONTENT)));
                    if (solo.searchText("礼包有效期", 1, false)) {
                        Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H72", 1);
                        sendmsg.send();
                        // 从礼包详情页回到礼包列表
                        solo.clickOnView(solo.getView(Constants.BUTTON_BACK));
                    } else {
                        Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H72", 0);
                        sendmsg.send();
                        // 从礼包详情页回到礼包列表
                        solo.clickOnView(solo.getView(Constants.BUTTON_BACK));
                    }

                } else if (solo.searchText("暂时没有内容", 1, false)) {
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H73", 1);
                    sendmsg.send();
                } else {// 加载我的物品页面失败
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H73", 0);
                    sendmsg.send();
                }

                // 激活码页面
                if (solo.getWebElements(By.className("激活码")) != null) {
                    solo.clickOnWebElement(By.textContent("激活码"));
                    // Constants.GAMEKEY
                    if (solo.waitForWebElement(By.className("user_photo"), 2000, false)) {
                        solo.clickOnWebElement(solo.getWebElement(By.className("user_photo"), 0));
                        // 我的--激活码详情页面
                        // assertTrue(solo.waitForWebElement(By.className(Constants.GAMEKEY_DETAIL_CONTENT)));
                        if (solo.searchText("激活码有效期", 1, false)) {
                            Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H74", 1);
                            sendmsg.send();
                        } else {
                            Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H74", 0);
                            sendmsg.send();
                        }
                        // 从激活码详情页回到礼包列表
                        solo.clickOnView(solo.getView(Constants.BUTTON_BACK));
                    } else if (solo.searchText("暂时没有内容", 1, false)) {
                        Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H75", 1);
                        sendmsg.send();
                    } else {// 加载页面失败
                        Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H75", 0);
                        sendmsg.send();
                    }
                }

                // 代金券页面

                solo.clickOnWebElement(By.textContent("代金券"));
                // boolean
                // a=solo.waitForWebElement(By.className(Constants.COUPON));
                if (solo.searchText("暂时没有内容", 1, false)) {
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H77", 1);
                    sendmsg.send();
                    // solo.clickOnView(solo.getView(Constants.BUTTON_BACK));
                } else if (solo.searchText("有效期", 1, false)) {
                    solo.clickOnText("有效期");
                    if (solo.searchText("代金券有效期", 1, false)) {
                        Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H76", 1);
                        sendmsg.send();

                    } else {
                        Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H76", 0);
                        sendmsg.send();
                    }
                    solo.clickOnView(solo.getView(Constants.BUTTON_BACK));
                } else {// 加载页面失败
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H77", 0);
                    sendmsg.send();
                }

            }

                /*
                 * 从我的物品返回到”我“主页面
                 */

            solo.clickOnView(solo.getView(Constants.BUTTON_BACK));
            if (solo.searchText("切换帐号")) {
                solo.clickOnWebElement(solo.getWebElement(By.textContent("切换帐号"), 0));
                solo.sleep(1500);
                if (solo.searchText(Constants.TEXT_BAIDU_LOGIN)) {
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H78", 1);
                    sendmsg.send();
                } else {
                    Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H78", 0);
                    sendmsg.send();
                }
            } else {
                Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H79", 0);
                sendmsg.send();
            }
        }


    }


}
