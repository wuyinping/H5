package com.duoku.platform.demo.test.utils;

import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class sendmsg {

	public static String smsHost = "http://monitor.client.duoku.com:8080/unionsdkmonitor/unionsdk_monitor.php?";

	public static void send() {
		if (!Constants.Test_Result.equals("")) {
			new Thread() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					super.run();

					try {
						HttpGet hpUpload = new HttpGet(smsHost + Constants.Test_Result);
						hpUpload.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.104 Safari/537.36");

						DefaultHttpClient dhc = new DefaultHttpClient();
						HttpResponse hr = dhc.execute(hpUpload);

						Log.e("TAG", "upload status : " + hr.getEntity().toString());

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}.start();
		}
	}

}
