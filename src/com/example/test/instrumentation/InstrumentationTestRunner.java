package com.example.test.instrumentation;

/**
 * Created by wuyinping on 2016/3/28.
 */

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import com.duoku.platform.demo.test.utils.Constants;
import com.duoku.platform.demo.test.utils.DeviceUtil;
import com.duoku.platform.demo.test.utils.log;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This test_chouti runner creates a TEST-all.xml in the files directory of the
 * application under test_chouti. The output is compatible with that of the junitreport
 * ant task, the format that is understood by Hudson. Currently this
 * implementation does not implement the all aspects of the junitreport format,
 * but enough for Hudson to parse the test_chouti results.
 */
public class InstrumentationTestRunner extends android.test.InstrumentationTestRunner {
    private long mTestStarted;
    @Override
    public void onCreate(Bundle arguments) {
        LynQCrashHandler.getInstance().init();
        super.onCreate(arguments);
    }

    @Override
    public void onStart() {

//        try {
//            AssetManager am = getContext().getAssets();
//            InputStream is = am.open("phone.txt");
//            int length = is.available();
//            byte[] content = new byte[length];
//            is.read(content);
//            String phoneString = EncodingUtils.getString(content, "UTF-8");
//            phones = phoneString.split(",");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            File fileRobo = new File(getTestResultDir(getTargetContext()));
//
//            if (!fileRobo.exists()) {
//                fileRobo.mkdir();
//            }
//            if (isSDCardAvaliable()) {
//                File resultFile = new File(
//                        getTestResultDir(getTargetContext()), JUNIT_XML_FILE);
//                startJUnitOutput(new FileWriter(resultFile));
//            } else {
//                startJUnitOutput(new FileWriter(new File(getTargetContext()
//                        .getFilesDir(), JUNIT_XML_FILE)));
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        super.onStart();
    }



    /**
     * �ж�SD���Ƿ����
     *
     * @return
     */
    private boolean isSDCardAvaliable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * ��ȡ���Խ����ļ����ڵ�·��
     *
     * @param context
     *            ���⹤�̵�context
     * @return ���ز��Խ����ļ����ڵ�·��
     */
    // private String getTestResultDir(Context context){
    // String packageName = "/" + "robotium"+"/"+"junit";
    // String filepath = context.getCacheDir().getPath() + packageName;
    // if(isSDCardAvaliable()){
    // filepath = Environment.getExternalStorageDirectory().getAbsolutePath()+
    // packageName;
    // }
    //
    // return filepath;
    // }
    private String getTestResultDir(Context context) {
        String packageName = "/mnt/sdcard" + "/" + "robotium" + "/" + "junit";
        String filepath = context.getCacheDir().getPath() + packageName;
        filepath = "/mnt/sdcard" + "/" + "robotium" + "/" + "junit";
        // if(android.os.Build.VERSION.SDK_INT < 8){
        // if(isSDCardAvaliable()){
        // filepath =
        // Environment.getExternalStorageDirectory().getAbsolutePath()+
        // packageName;}
        // }else{
        // if(isSDCardAvaliable()){
        // filepath =
        // Environment.getExternalStorageDirectory().getAbsolutePath()+
        // packageName;}
        // }

        return filepath;
    }

    private XmlSerializer newSerializer(Writer writer) {
        try {
            XmlPullParserFactory pf = XmlPullParserFactory.newInstance();
            XmlSerializer serializer = pf.newSerializer();
            serializer.setOutput(writer);
            return serializer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //String smsHost = "http://211.152.103.231:8080/unionsdkmonitor/unionsdk_monitor.php?";
    //http://monitor.client.duoku.com:8080/unionsdkmonitor/unionsdk_monitor.php?
    String smsHost ="http://monitor.client.duoku.com:8080/unionsdkmonitor/unionsdk_monitor.php?";
    @Override
    public void sendStatus(int resultCode, Bundle results) {
        super.sendStatus(resultCode, results);
        switch (resultCode) {
            case REPORT_VALUE_RESULT_OK:
            case REPORT_VALUE_RESULT_ERROR:
            case REPORT_VALUE_RESULT_FAILURE:
                try {
                    recordTestResult(resultCode, results);

                    //Log.e("TAG", "test_chouti result : " + Constants.Test_Result);

//                    if (!Constants.Test_Result.equals("")) {
//                        new Thread() {
//
//                            @Override
//                            public void run() {
//                                // TODO Auto-generated method stub
//                                super.run();
//
//                                try {
//                                    HttpGet hpUpload = new HttpGet(smsHost
//                                            + Constants.Test_Result);
//                                    hpUpload.addHeader(
//                                            "User-Agent",
//                                            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.104 Safari/537.36");
//
//                                    DefaultHttpClient dhc = new DefaultHttpClient();
//                                    HttpResponse hr = dhc.execute(hpUpload);
//
//                                    Log.e("TAG", "upload status : "
//                                            + hr.getEntity().toString());
//
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//
//                            }
//
//                        }.start();
//                    }

                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                break;
            case REPORT_VALUE_RESULT_START:
                recordTestStart(results);
            default:
                break;
        }
    }

    void recordTestStart(Bundle results) {
        mTestStarted = System.currentTimeMillis();
    }

    String recordTestResult(int resultCode, Bundle results) throws IOException {
        float time = (System.currentTimeMillis() - mTestStarted) / 1000.0f;
        String className = results.getString(REPORT_KEY_NAME_CLASS);
        String testMethod = results.getString(REPORT_KEY_NAME_TEST);
        String stack = results.getString(REPORT_KEY_STACK);
        int current = results.getInt(REPORT_KEY_NUM_CURRENT);
        int total = results.getInt(REPORT_KEY_NUM_TOTAL);


        String message = "";
//
        if (resultCode != REPORT_VALUE_RESULT_OK) {

            if (stack != null) {
                String reason = stack.substring(0, stack.indexOf('\n'));

                int index = reason.indexOf(':');
                if (index > -1) {
                    message = reason.substring(index + 1);
                    reason = reason.substring(0, index);
                }
                String sep="-------------------------------------------------------------------------------------------";
                StringBuilder sb= new StringBuilder();
                String IMEI=DeviceUtil.getmtype(this.getContext());
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                String now= df.format(new Date()).toString();
                sb.append(sep);
                sb.append("IMEI:").append(IMEI).append("\n");
                sb.append("time:").append(now).append("\n");
                sb.append("message:").append(message).append("\n");
                sb.append("type:").append(reason).append("\n");
                sb.append("stack:").append(stack).append("\n").append(sep);
                log.savefile(Constants.CACH_DIR,Constants.logFilename,sb.toString());
            }
        }

        return message;
    }

    @Override
    public void finish(int resultCode, Bundle results) {

        super.finish(resultCode, results);
    }

    void endTestSuites() {

    }
}