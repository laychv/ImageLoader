package com.laychv.ocp.after;

import android.graphics.Bitmap;

/**
 * @author: LayChv
 * @date: 2022/6/9
 * @des:
 */
public class Test {
    public static void main(String[] args) {
        ImageLoader loader = new ImageLoader();
        loader.setImageCache(new MemoryCache());
        loader.setImageCache(new DiskCache());
        loader.setImageCache(new DoubleCache());
        loader.setImageCache(new ImageCache() {
            @Override
            public void put(String url, Bitmap bmp) {

            }

            @Override
            public Bitmap get(String url) {
                return null;
            }
        });
    }
}