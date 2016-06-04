package com.nrp.android.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileManager {

    private static String homeDir;
    private static String logDir;
    private static String imgDownloadDir;

    /**
     * get app's home directory
     *
     * @return
     */
    public static String getHomeDir() {
        if (homeDir == null) {
            if (SysUtil.isSdExist()) {
                homeDir = Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/MaJia";
            } else {
                homeDir = Environment.getRootDirectory()
                        .getAbsolutePath() + "/MaJia";
            }
        } else {
            File file = new File(homeDir);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return homeDir;
    }

    /**
     * get log directory
     *
     * @return
     */
    public static String getLogDir() {
        logDir = getHomeDir() + "/log";
        File file = new File(logDir);
        file.mkdirs();
        return logDir;
    }

    /**
     * get downloaded images' directory
     * @return
     */
    public static String getImgDownloadDir() {
        imgDownloadDir = getHomeDir() + "/download";
        File file = new File(imgDownloadDir);
        file.mkdirs();
        return imgDownloadDir;
    }


    public static void makeDir(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
    /**
     * 将sample工程需要的资源文件拷贝到SD卡中使用（授权文件为临时授权文件，请注册正式授权）
     *
     * @param isCover 是否覆盖已存在的目标文件
     * @param source
     * @param dest
     */
    public static void copyFromAssetsToSdcard(Context context , boolean isCover, String source, String dest) {
        File file = new File(dest);
        if (isCover || (!isCover && !file.exists())) {
            InputStream is = null;
            FileOutputStream fos = null;
            try {
                is = context.getResources().getAssets().open(source);
                String path = dest;
                fos = new FileOutputStream(path);
                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = is.read(buffer, 0, 1024)) >= 0) {
                    fos.write(buffer, 0, size);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
