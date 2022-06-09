package com.laychv.ocp.after;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: LayChv
 * @date: 2022/6/9
 * @des:
 */
public class ImageLoader {

    private ImageCache cache = new MemoryCache();

    private final ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void displayImage(String url, ImageView imageView) {
        Bitmap bitmap = cache.get(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        // 没有缓存，网络下载
        request(url, imageView);
    }

    public Bitmap downloadImage(String imgUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imgUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(con.getInputStream());
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public void setImageCache(ImageCache cache) {
        this.cache = cache;
    }

    private void request(String imgUrl, ImageView imageView) {
        imageView.setTag(imgUrl);
        service.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(imgUrl);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(imgUrl)) {
                    imageView.setImageBitmap(bitmap);
                }
                cache.put(imgUrl, bitmap);
            }
        });
    }

}
