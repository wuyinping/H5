package com.duoku.platform.demo.test.utils;

import android.widget.EditText;
import com.robotium.solo.Solo;

/**
 * Created by wuyinping on 16/2/16.
 */
public class DKlogin {
    public static void login(Solo solo, String username, String passwd){

        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.clickOnView(solo.getView(Constants.EDIT_ACCOUNT));
        solo.clearEditText((EditText) solo
                .getView(Constants.EDIT_ACCOUNT));
        // 点击输入用户账号
        solo.enterText((EditText) solo.getView(Constants.EDIT_ACCOUNT),
                username);
        // 点击密码输入框，输入密码
        solo.clickOnView(solo.getView(Constants.EDIT_PASS));
        // 清空密码输入框
        solo.clearEditText((EditText) solo.getView(Constants.EDIT_PASS));

        solo.enterText((EditText) solo.getView(Constants.EDIT_PASS),
                passwd);
        solo.sleep(500);
        solo.clickOnButton(Constants.BUTTON_LOGIN);


    }
}
