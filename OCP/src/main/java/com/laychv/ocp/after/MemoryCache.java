package com.laychv.ocp.after;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * @author: LayChv
 * @date: 2022/6/9
 * @des:
 */
public class MemoryCache implements ImageCache {

    private LruCache<String, Bitmap> cache;

    @Override
    public void put(String url, Bitmap bmp) {
        cache.put(url, bmp);
    }

    @Override
    public Bitmap get(String url) {
        return cache.get(url);
    }
}
