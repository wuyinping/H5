package com.duoku.platform.demo.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.DisplayMetrics;
import android.widget.EditText;
import com.duoku.platform.demo.test.utils.Constants;
import com.duoku.platform.demo.test.utils.DeviceUtil;
import com.duoku.platform.demo.test.utils.sendmsg;
import com.robotium.solo.Solo;

public class login extends ActivityInstrumentationTestCase2 {
	private Solo solo;
	Process process = null;
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.baidu.bdgamesdk.demo.activity.WelcomeActivity";

	private static Class<?> launcherActivityClass;
	static {
		try {
			launcherActivityClass = Class
					.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public login() throws ClassNotFoundException {
		super(LAUNCHER_ACTIVITY_FULL_CLASSNAME, launcherActivityClass);
	}

	final String TAG = "Test->N01";

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation());
		getActivity();
		if (solo.searchText("公  告")) {
			solo.clickOnView(solo.getView("bd_iv_notice_close"));
		}
		
	
	
	}

	@Override
	public void tearDown() throws Exception {
		// DataCleanManager.cleanApplicationData(solo.getCurrentActivity().getApplicationContext(),"com.baidu.bdgamesdk.demo");
		// Runtime.getRuntime().exec("rm -rf /sdcard/com.baidu.plaformsdk");
		solo.finishOpenedActivities();
	}

	public void test01_baidudenglu() {
	
		//assertTrue(solo.getView("login_btn") != null);
		if (solo.waitForView(solo.getView(Constants.LOGIN_BUTTON))) {
			solo.clickOnView(solo.getView(Constants.LOGIN_BUTTON));
			solo.sleep(1000);
			if (solo.searchText(Constants.TEXT_BAIDU_LOGIN)) {
				solo.clearEditText(0);
				solo.clearEditText(1);
				solo.clickOnView(solo.getView(Constants.EDIT_ACCOUNT));
				solo.clearEditText((EditText) solo
						.getView(Constants.EDIT_ACCOUNT));
				// 点击输入用户账号
				solo.enterText((EditText) solo.getView(Constants.EDIT_ACCOUNT),
						Constants.USER_BAIDU);
				// 点击密码输入框，输入密码
				solo.clickOnView(solo.getView(Constants.EDIT_PASS));
				// 清空密码输入�?
				solo.clearEditText((EditText) solo.getView(Constants.EDIT_PASS));

				solo.enterText((EditText) solo.getView(Constants.EDIT_PASS),
						Constants.PASS_BAIDU);
				solo.sleep(500);
				solo.clickOnButton(Constants.BUTTON_LOGIN);

				//assertTrue("登录失败", solo.waitForText("登录成功"));
			}

			 if(solo.waitForText("登录成功")){
			 Constants.Test_Result =DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H1", 1);
			 sendmsg.send();
			 }else{
			 Constants.Test_Result =DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(),"H1",0);
			 sendmsg.send();
			 }

		}

	}
	

	public void test02_baidudenglu() {
	
		//assertTrue(solo.getView("login_btn") != null);
		if (solo.waitForView(solo.getView(Constants.LOGIN_BUTTON))) {
			solo.clickOnView(solo.getView(Constants.LOGIN_BUTTON));
			solo.sleep(1000);
			if (solo.searchText(Constants.TEXT_BAIDU_LOGIN)) {
				solo.clearEditText(0);
				solo.clearEditText(1);
				solo.clickOnView(solo.getView(Constants.EDIT_ACCOUNT));
				solo.clearEditText((EditText) solo
						.getView(Constants.EDIT_ACCOUNT));
				// 点击输入用户账号
				solo.enterText((EditText) solo.getView(Constants.EDIT_ACCOUNT),
						Constants.USER_BAIDU1);
				// 点击密码输入框，输入密码
				solo.clickOnView(solo.getView(Constants.EDIT_PASS));
				// 清空密码输入�?
				solo.clearEditText((EditText) solo.getView(Constants.EDIT_PASS));

				solo.enterText((EditText) solo.getView(Constants.EDIT_PASS),
						Constants.PASS_BAIDU1);
				solo.sleep(500);
				solo.clickOnButton(Constants.BUTTON_LOGIN);

				//assertTrue("登录失败", solo.waitForText("登录成功"));
			}

			 if(solo.waitForText("登录成功")){
			 Constants.Test_Result =DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H1", 1);
			 sendmsg.send();
			 }else{
			 Constants.Test_Result =DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(),"H1",0);
			 sendmsg.send();
			 }

		}

	}

	public void test03_baidudenglu() {
	
		//assertTrue(solo.getView("login_btn") != null);
		if (solo.waitForView(solo.getView(Constants.LOGIN_BUTTON))) {
			solo.clickOnView(solo.getView(Constants.LOGIN_BUTTON));
			solo.sleep(1000);
			if (solo.searchText(Constants.TEXT_BAIDU_LOGIN)) {
				solo.clearEditText(0);
				solo.clearEditText(1);
				solo.clickOnView(solo.getView(Constants.EDIT_ACCOUNT));
				solo.clearEditText((EditText) solo
						.getView(Constants.EDIT_ACCOUNT));
				// 点击输入用户账号
				solo.enterText((EditText) solo.getView(Constants.EDIT_ACCOUNT),
						Constants.USER_BAIDU2);
				// 点击密码输入框，输入密码
				solo.clickOnView(solo.getView(Constants.EDIT_PASS));
				// 清空密码输入�?
				solo.clearEditText((EditText) solo.getView(Constants.EDIT_PASS));

				solo.enterText((EditText) solo.getView(Constants.EDIT_PASS),
						Constants.PASS_BAIDU2);
				solo.sleep(500);
				solo.clickOnButton(Constants.BUTTON_LOGIN);

				//assertTrue("登录失败", solo.waitForText("登录成功"));
			}

			 if(solo.waitForText("登录成功")){
			 Constants.Test_Result =DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(), "H1", 1);
			 sendmsg.send();
			 }else{
			 Constants.Test_Result =DeviceUtil.getAssetStatus(solo.getCurrentActivity().getApplicationContext(),"H1",0);
			 sendmsg.send();
			 }

		}

	}

}
