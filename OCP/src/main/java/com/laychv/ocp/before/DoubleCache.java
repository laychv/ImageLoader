package com.laychv.ocp.before;

import android.graphics.Bitmap;

/**
 * @author: LayChv
 * @date: 2022/6/9
 * @des:
 */
public class DoubleCache {
    ImageCache iCache = new ImageCache();
    DiskCache dCache = new DiskCache();

    public Bitmap get(String url) {
        Bitmap bitmap = iCache.get(url);
        if (bitmap == null) {
            bitmap = dCache.get(url);
        }
        return bitmap;
    }

    public void put(String url, Bitmap bitmap) {
        iCache.put(url, bitmap);
        dCache.put(url, bitmap);
    }
}
