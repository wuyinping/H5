package com.duoku.platform.demo.test;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.DisplayMetrics;
import com.duoku.platform.demo.test.utils.*;
import com.robotium.solo.Solo;
import com.robotium.solo.Solo.Config;

public class test_home1 extends ActivityInstrumentationTestCase2 {


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
	public test_home1() throws ClassNotFoundException {
		super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);

	}

	final String TAG = "Test->N01";

	public void setUp() throws Exception {
		Config config = new Config();
		config.timeout_large = 2000;
		config.timeout_small = 2000;
		solo = new Solo(getInstrumentation(), config);
		getActivity();
		Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H10", 0);

	}

	@Override
	public void tearDown() throws Exception {
//		DataCleanManager.cleanApplicationData(solo.getCurrentActivity().getApplicationContext(), "com.baidu.bdgamesdk.demo");
//		Runtime.getRuntime().exec("rm -rf /sdcard/com.baidu.plaformsdk");
		sendmsg.send();
		solo.sleep(500);
		solo.clickOnView(solo.getView(Constants.BUTTON_CLOSE));
		solo.goBack();
		solo.finishOpenedActivities();
	}

	public void test_home() {
		try {
			// 判断是否有公告
			if (solo.searchText("公  告")) {
				solo.clickOnView(solo.getView("bd_iv_notice_close"));
			}
			solo.clickOnView(solo.getView("login_btn"));
			if (solo.searchText(Constants.TEXT_BAIDU_LOGIN)) {
				BDlogin.login(solo, Constants.USER_BAIDU, Constants.PASS_BAIDU);
			}

			int id;
			Activity activity = solo.getCurrentActivity();
			id = activity.getResources().getIdentifier(Constants.LOGINNOTICE_ID, "id", activity.getPackageName());
			//solo.searchText("活动时间")
			if (solo.waitForView(id)) {
				solo.clickOnView(solo.getView(Constants.LOGINNOTICE_CLOSE));
			}
			assertTrue(solo.getCurrentActivity().toString().contains(Constants.GAME_ACTIVITY));
				DisplayMetrics metircs = new DisplayMetrics();
				getActivity().getWindowManager().getDefaultDisplay().getMetrics(metircs);
				int height = metircs.heightPixels / 2;
				solo.clickOnScreen(40, height);

				if (solo.searchText("礼包") || solo.searchText("排行榜") || solo.searchText("公告")) {
					Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H10", 1);
				}
				solo.scrollToBottom();
				if (!solo.searchText("没有更多了")) {
					Constants.Test_Result = DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H10", 0);
				}

		} catch (Exception e) {
			String data=e.toString();
//			log.writelog(data,getInstrumentation().getContext());


		}


	}
}
