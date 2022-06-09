package com.laychv.ocp.before;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {
    // 图片缓存
    ImageCache iCache = new ImageCache();
    // sd cache
    DiskCache dCache = new DiskCache();
    //
    DoubleCache douCache = new DoubleCache();
    //
    boolean isUseDiskCache = false;
    //
    boolean isUseDoubleCache = false;
    // 线程池，线程数量为CPU数量
    ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void displayImage(String url, ImageView imageView) {
        Bitmap bitmap = null;
        if (isUseDiskCache) {
            bitmap = dCache.get(url);
        } else if (isUseDoubleCache) {
            bitmap = douCache.get(url);
        } else {
            bitmap = iCache.get(url);
        }
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public void useDiskCache(boolean dCache) {
        isUseDiskCache = dCache;
    }

    public void useDoubleCache(boolean doubleCache) {
        isUseDoubleCache = doubleCache;
    }
}
