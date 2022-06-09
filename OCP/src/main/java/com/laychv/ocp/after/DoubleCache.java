package com.laychv.ocp.after;

import android.graphics.Bitmap;

/**
 * @author: LayChv
 * @date: 2022/6/9
 * @des:
 */
public class DoubleCache implements ImageCache {

    MemoryCache memoryCache = new MemoryCache();
    DiskCache diskCache = new DiskCache();

    @Override
    public void put(String url, Bitmap bmp) {
        memoryCache.put(url, bmp);
        diskCache.put(url, bmp);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap == null) {
            bitmap = diskCache.get(url);
        }
        return bitmap;
    }
}
