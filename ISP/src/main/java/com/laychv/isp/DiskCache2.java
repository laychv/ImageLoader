package com.laychv.isp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.OutputStream;

/**
 * @author: LayChv
 * @date: 2022/6/1
 * @des: 将图片缓存到SD卡中
 */
public class DiskCache2 {

    DiskLruCache diskLruCache;
    static String cacheDir = "sdcard/cache/";

    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(url + cacheDir);
    }

    public void put(String url, Bitmap bitmap) {
        // https://github.com/JakeWharton/DiskLruCache
        DiskLruCache.Editor editor = null;
        try {
            editor = diskLruCache.edit(url);
            if (editor != null) {
                OutputStream os = editor.newOutputStream(0);
                if (writeBitmapToDisk(bitmap, os)) {
                    editor.commit();
                } else {
                    editor.abort();
                }
                CloseUtils.closeQuietly(os);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean writeBitmapToDisk(Bitmap bitmap, OutputStream os) {
        return false;
    }

}
