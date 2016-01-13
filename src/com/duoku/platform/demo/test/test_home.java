package com.duoku.platform.demo.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.DisplayMetrics;
import com.duoku.platform.demo.test.utils.Constants;
import com.duoku.platform.demo.test.utils.DeviceUtil;
import com.duoku.platform.demo.test.utils.login1;
import com.duoku.platform.demo.test.utils.sendmsg;
import com.robotium.solo.Solo;
import com.robotium.solo.Solo.Config;
public class test_home extends ActivityInstrumentationTestCase2 {

	public test_home(Class activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}

	private Solo solo;
	public static int bd_actionnotice_toptitle = 0x7f0a019f;
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.baidu.bdgamesdk.demo.activity.WelcomeActivity";

	private static Class<?> launcherActivityClass;
	static {
		try {
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public test_home() throws ClassNotFoundException {
		super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);

	}

	final String TAG = "Test->N01";

	public void setUp() throws Exception {
		Config config = new Config();
		config.timeout_large = 20;
		solo = new Solo(getInstrumentation(), config);
		getActivity();
		Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H10", 0);
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
		int height = metircs.heightPixels / 2;
		solo.clickOnScreen(40, height);
		solo.waitForActivity(Constants.CONTAINER_ACTIVITY);

	}

	@Override
	public void tearDown() throws Exception {
//		DataCleanManager.cleanApplicationData(solo.getCurrentActivity().getApplicationContext(), "com.baidu.bdgamesdk.demo");
//		Runtime.getRuntime().exec("rm -rf /sdcard/com.baidu.plaformsdk");
		sendmsg.send();
		solo.clickOnView(solo.getView(Constants.BUTTON_CLOSE));
		solo.goBack();
		solo.finishOpenedActivities();
	}

	public void test_home() {

//		if (solo.getWebElement(By.id(Constants.GIFT_ID), 0) != null || solo.getWebElement(By.id(Constants.MSG_ID), 0) != null || solo.getWebElement(By.id(Constants.PAIHANGBANG_ID), 0) != null
//				|| solo.getWebElements(By.className(Constants.PERSON_ID)) != null || solo.getWebElement(By.className(Constants.NOTICE), 0) != null) {
//			if (solo.getWebElement(By.className(Constants.HOME_FORUM_ITEM), 0) != null || solo.getWebElement(By.className(Constants.HOME_NOTICE_ITEM), 0) != null
//					|| solo.getWebElement(By.className(Constants.HOME_VOUCHER_ITEM), 0) != null) {
//				Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H10", 1);
//				sendmsg.send();
//			} else if (solo.searchText("暂时没有内容")) {
//				Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H10", 1);
//				sendmsg.send();
//			} else {
//				Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H10", 0);
//				sendmsg.send();
//			}
//
//		} else {
//			Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H10", 0);
//			sendmsg.send();
//		}

		if (solo.searchText("礼包")||solo.searchText("排行榜")||solo.searchText("公告")){
			Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H10", 1);
		}
	}

}
