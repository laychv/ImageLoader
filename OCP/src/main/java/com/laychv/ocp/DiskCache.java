package com.laychv.ocp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileOutputStream;

/**
 * @author: LayChv
 * @date: 2022/6/1
 * @des: 将图片缓存到SD卡中
 */
public class DiskCache {

    static String cacheDir = "sdcard/cache/";

    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(url + cacheDir);
    }

    public void put(String url, Bitmap bitmap) {
        FileOutputStream fos = null;

    }

}
