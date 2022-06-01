package com.laychv.srp.after;

import android.graphics.Bitmap;
import android.util.LruCache;

public class ImageCache {

    LruCache<String, Bitmap> mCache;

    public ImageCache() {
        initImageCache();
    }

    public void initImageCache() {
        // 计算可使用的最大内存
        int max = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // 取1/4可用内存做缓存
        int size = max / 4;
        mCache = new LruCache<String, Bitmap>(size) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    public void put(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }

    public Bitmap get(String url) {
        return mCache.get(url);
    }
}
