package com.duoku.platform.demo.test;

import android.test.ActivityInstrumentationTestCase2;
import com.duoku.platform.demo.test.utils.Constants;
import com.duoku.platform.demo.test.utils.log;
import com.robotium.solo.Solo;

import java.io.FileNotFoundException;
import java.util.Date;

/**
 * Created by wuyinping on 2016/3/28.
 */
public class test extends ActivityInstrumentationTestCase2{
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.baidu.bdgamesdk.demo.activity.WelcomeActivity";
    private Solo solo;
    private static Class<?> launcherActivityClass;
    static {
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public test()throws ClassNotFoundException  {
        super(LAUNCHER_ACTIVITY_FULL_CLASSNAME,launcherActivityClass);
    }

    public void setUp() throws Exception{
        solo=new Solo(getInstrumentation());
        getActivity();
    }
    public void tearDown() throws Exception{

        solo.finishOpenedActivities();
    }
    public void test_home() throws FileNotFoundException {


         try{
             solo.clickOnView(solo.getView("oif"));
         }catch (Exception e){
             log.savefile(Constants.CACH_DIR,Constants.logFilename,new Date().toString()+"zhaobudao ");
         }








    }
}
