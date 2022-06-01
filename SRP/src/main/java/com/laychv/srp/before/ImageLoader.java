package com.laychv.srp.before;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单一职责原则
 * 图片加载类
 * 这个类包含图片加载，图片缓存功能，这也是可以优化的地方
 */
public class ImageLoader {
    // 图片缓存
    LruCache<String, Bitmap> mCache;
    // 线程池，线程数量为CPU数量
    ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public ImageLoader() {
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

    public void displayImage(String url, ImageView imageView) {
        imageView.setTag(url);
        service.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                }
                mCache.put(url, bitmap);
            }
        });
    }

    public Bitmap downloadImage(String imgUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imgUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}