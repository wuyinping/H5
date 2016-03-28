package com.duoku.platform.demo.test.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by wuyinping on 2016/3/28.
 */

public class log {
/**
 * 保持文件内容到sd
 *
 * @param dir
 * @param filename
 * @param obj
 */
public synchronized static void savefile(String dir, String filename, Object obj)
        {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
        File dirPath = new File(dir);
        if (!dirPath.exists())
        {
        dirPath.mkdirs();
        }

        // 打开文件
        File file = new File(dirPath, filename);

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try
        {
        if (!file.exists())
        {
        file.createNewFile();
        }

        // 保存文件
        fos = new FileOutputStream(file,true);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        oos.close();
        fos.close();
        } catch (Exception e)
        {
        e.printStackTrace();
        } finally
        {
        if (fos != null)
        {
        try
        {
        oos.close();
        fos.close();
        oos = null;
        fos = null;
        } catch (IOException e)
        {
        e.printStackTrace();
        }
        }
        }

        }
        }
        }


