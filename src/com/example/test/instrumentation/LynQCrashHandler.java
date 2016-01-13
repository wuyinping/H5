

package com.example.test.instrumentation;
import android.content.Context; 
import android.app.Instrumentation;
import android.os.Debug;
import android.os.Environment;
import android.util.Log;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class LynQCrashHandler
    implements Thread.UncaughtExceptionHandler
{

    private LynQCrashHandler()
    {
        defaultHandler = null;
        mCrashFilePath = null;
        
    }

    public static LynQCrashHandler getInstance()
    {
        if(mInstance == null)
            mInstance = new LynQCrashHandler();
        return mInstance;
    }

    public void init()
    {
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void release()
    {
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(defaultHandler);
    }

   
  
    public void uncaughtException(Thread arg0, Throwable arg1)
    {
        try
        {
            Writer result = new StringWriter();
            PrintWriter printWriter = new PrintWriter(result);
            printWriter.append(arg1.getMessage());
            arg1.printStackTrace(printWriter);
            Log.getStackTraceString(arg1);
            for(Throwable cause = arg1.getCause(); cause != null; cause = cause.getCause())
                cause.printStackTrace(printWriter);

            String msg = result.toString();
            printWriter.close();
            saveCrashReportFile(msg);
            if(arg1.getClass().equals(java.lang.OutOfMemoryError.class)){
                try
                {
                    dumpHeap();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        defaultHandler.uncaughtException(arg0, arg1);
    }
    
    private void dumpHeap()throws IOException
    {
        long timestamp = System.currentTimeMillis();
        String fileName = createSaveFilePath();
        String dumpPath = (new StringBuilder(String.valueOf(fileName))).append("OOM-").append(timestamp).append(".hprof").toString();
        Log.i(TAG, (new StringBuilder("Dumping hprof data to: ")).append(dumpPath).toString());
        try
        {
            Debug.dumpHprofData(dumpPath);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Log.i(TAG, (new StringBuilder("Dumping hprof data to: ")).append(dumpPath).toString());
    }
    

    private String saveCrashReportFile(String result) {
			try {
				Log.d(TAG, "Writing crash report file.");
				 String fileName;
			        Log.d(TAG, "Writing crash report file.");
			        long timestamp = System.currentTimeMillis();
			        Date d = new Date(timestamp);
			        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd_HH-mm-ss");
    		        fileName = createSaveFilePath();
    		        
//			        fileName=getTestResultDir(getContext());
			        fileName = (new StringBuilder(String.valueOf(fileName))).append("stack-").append(sdf.format(d)).append(".txt").toString();
			        File file = new File(fileName);
			        FileOutputStream trace = new FileOutputStream(file, true);
				   Log.d(TAG, fileName);

				String lineSeparator = System.getProperty("line.separator");
				if (lineSeparator == null) {
					lineSeparator = "\n";
				}
				OutputStreamWriter writer = new OutputStreamWriter(trace,
						"ISO8859_1"); 

				writer.write("#"); 
				writer.write(new Date().toString());
				writer.write(lineSeparator);
				writer.write(result);
				writer.write(lineSeparator);
				writer.flush();

				trace.flush();
				trace.close();
				return fileName;
			} catch (Exception e) {
				Log.d(TAG, "An error occured while writing the report file..."
						+ e);
			}
		
		return null;
	}
    private boolean isSDCardAvaliable(){  
        return Environment.getExternalStorageState()  
                    .equals(Environment.MEDIA_MOUNTED);   
    } 
    private String createSaveFilePath() {
		
			//mCrashFilePath = (new StringBuilder()).append("/sdcard").append(CRASH_FILE_PATH).toString(); 
	            //String mCrashFilePath = context.getCacheDir().getPath() + CRASH_FILE_PATH;  
	           if(isSDCardAvaliable()){  
	        	   //mCrashFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ CRASH_FILE_PATH;  
	        	   mCrashFilePath = "/mnt/sdcard"+ CRASH_FILE_PATH;  
	           }
			File destDir = new File(mCrashFilePath);
			if (!destDir.exists()) {
				destDir.mkdirs();
			}
		

		return mCrashFilePath;
	}

    
    public void setCrashFilePath(String path)
    {
        mCrashFilePath = path;
    }

   private static final String TAG = "dumperror";
    private static LynQCrashHandler mInstance = null;
    private Thread.UncaughtExceptionHandler defaultHandler;
    private String mCrashFilePath;
    private static String CRASH_FILE_PATH = "/" + "robotium"+"/"+"testcrash"+"/";

}
