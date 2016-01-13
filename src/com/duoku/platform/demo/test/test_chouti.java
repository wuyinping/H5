package com.duoku.platform.demo.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.DisplayMetrics;
import android.util.Log;
import com.duoku.platform.demo.test.utils.Constants;
import com.duoku.platform.demo.test.utils.DataCleanManager;
import com.duoku.platform.demo.test.utils.DeviceUtil;
import com.duoku.platform.demo.test.utils.sendmsg;
import com.robotium.solo.By;
import com.robotium.solo.Solo;
import com.robotium.solo.Solo.Config;

@SuppressWarnings("rawtypes")
public class test_chouti extends ActivityInstrumentationTestCase2 {
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
	public test_chouti() throws ClassNotFoundException {
		super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);

	}

	final String TAG = "Test->N01";

	public void setUp() throws Exception {
		Config config = new Config();
		config.timeout_large = 2000;
		Log.d(TAG, "开始setup");
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
		 DataCleanManager.cleanApplicationData(solo.getCurrentActivity().getApplicationContext(),"com.baidu.bdgamesdk.demo");
		 Runtime.getRuntime().exec("rm -rf /sdcard/com.baidu.plaformsdk");
		 solo.finishOpenedActivities();
	}
	
	public void test01_libao() {

		// 点击导航“礼包�?，进入礼包列表页
		try {
			if (solo.searchText("礼包")) {
				solo.clickOnText("礼包");
				// solo.clickOnWebElement(solo.getWebElement(By.id(Constants.GIFT_ID),
				// 0));
				solo.sleep(3000);
				if (solo.waitForWebElement(By.className(Constants.GIFT_ICON), 1, 2000, true)) {
					solo.clickOnWebElement(By.className(Constants.GIFT_ICON));
					solo.sleep(2000);
					if (solo.waitForWebElement(By.className(Constants.GITF_CONTENT), 1, 2000, true)) {
						Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H21", 1);
						sendmsg.send();
					} else {
						Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H21", 0);
						sendmsg.send();
					}
				}
				if (solo.getWebElements(By.textContent("暂时没有内容")) != null) {
					Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H22", 1);
					sendmsg.send();
				} else {
					Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H22", 0);
					sendmsg.send();
				}
			} else {
				Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H2", 0);
				sendmsg.send();
			}

		} catch (Exception e) {
			// DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(),
			// "", 0);
		}

		solo.clickOnView(solo.getView(Constants.BUTTON_CLOSE));
		Log.d(TAG, "height:"+height);
		solo.clickOnScreen(40, height);

		try {
			if (solo.waitForText("消息")) {
				// solo.clickOnWebElement(solo.getWebElement(By.id(Constants.MSG_ID),
				// 0));
				solo.clickOnText("消息");
				if (solo.waitForWebElement(By.className(Constants.MSG_ICON), 2000, false)) {
					solo.clickOnWebElement(By.className(Constants.MSG_ICON));
					solo.sleep(2000);
					if (solo.waitForWebElement(By.className(Constants.MSG_DETAIL), 1, 2000, true)) {
						Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H31", 1);
						sendmsg.send();
					} else {
						Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H31", 0);
						sendmsg.send();
					}
				} else if (solo.getWebElements(By.textContent("暂时没有内容")) != null) {
					Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H32", 1);
					sendmsg.send();
				} else {
					Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H32", 0);
					sendmsg.send();
				}
			} else {
				Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H3", 0);
				sendmsg.send();
			}

		} catch (Exception e) {
			DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "", 0);
		}
		solo.clickOnView(solo.getView(Constants.BUTTON_CLOSE));
		Log.d(TAG, "height:"+height);
		solo.clickOnScreen(40, height);

		try {
			if (solo.waitForWebElement(By.id(Constants.PAIHANGBANG_ID), 1, 2000, false)) {
				solo.clickOnWebElement(solo.getWebElement(By.id(Constants.PAIHANGBANG_ID), 0));
				// solo.sleep(3000);
				if (solo.waitForWebElement(By.className(Constants.GAME_ICON), 2000, false)) {
					solo.clickOnWebElement(By.className(Constants.GAME_ICON));
					// solo.sleep(2000);
					if (solo.waitForWebElement(By.className(Constants.GAME_DESC), 2000, false)) {
						Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H41", 1);
						sendmsg.send();
						solo.clickOnView(solo.getView(Constants.BUTTON_BACK));
					} else {
						Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H41", 0);
						solo.clickOnView(solo.getView(Constants.BUTTON_BACK));
					}
				} else if (solo.getWebElements(By.textContent("暂时没有内容")) != null) {
					Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H42", 1);
				} else {
					Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H42", 0);
				}
			} else {
				Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H4", 0);
			}

			// 排行�?-热门
			solo.scrollToTop();
			if (solo.getWebElement(By.textContent("热门"), 0) != null) {
				solo.clickOnWebElement(By.textContent("热门"), 1, false);
				if (solo.waitForWebElement(By.className(Constants.GAME_ICON), 2000, false)) {
					solo.clickOnWebElement(By.className(Constants.GAME_ICON));
					// solo.sleep(2000);
					if (solo.waitForWebElement(By.className(Constants.GAME_DESC), 2000, false)) {
						Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H51", 1);
						sendmsg.send();
						solo.clickOnView(solo.getView(Constants.BUTTON_BACK));
					} else {
						Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H51", 0);
						sendmsg.send();
						solo.clickOnView(solo.getView(Constants.BUTTON_BACK));
					}
				} else if (solo.getWebElements(By.textContent("暂时没有内容")) != null) {
					Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H52", 1);
					sendmsg.send();
				} else {
					Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H52", 0);
					sendmsg.send();
				}
			} else {
				Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H5", 0);
				sendmsg.send();
			}

			// 排行�?-封测
			solo.scrollToTop();
			if (solo.getWebElement(By.textContent("封测"), 0) != null) {
				solo.clickOnWebElement(By.textContent("封测"), 1, false);
				if (solo.waitForWebElement(By.id(Constants.GAME_ICON), 2000, false)) {
					solo.clickOnWebElement(By.className(Constants.GAME_ICON));
					// solo.sleep(2000);
					if (solo.waitForWebElement(By.className(Constants.GAME_DESC), 2000, true)) {
						Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H61", 1);
						sendmsg.send();
						solo.clickOnView(solo.getView(Constants.BUTTON_BACK));
					} else {
						Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H61", 0);
						sendmsg.send();
						solo.clickOnView(solo.getView(Constants.BUTTON_BACK));
					}
				} else if (solo.getWebElements(By.textContent("暂时没有内容")) != null) {
					Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H62", 1);
					sendmsg.send();
				} else {
					Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H62", 0);
					sendmsg.send();
				}
			} else {
				Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H6", 0);
				sendmsg.send();
			}

		} catch (Exception e) {
			Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H6", 0);
		}



	}

	

}
