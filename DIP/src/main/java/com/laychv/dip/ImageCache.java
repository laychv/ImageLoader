package com.laychv.dip;

import android.graphics.Bitmap;

/**
 * @author: LayChv
 * @date: 2022/6/9
 * @des:
 */
public interface ImageCache {
    public void put(String url, Bitmap bmp);

    public Bitmap get(String url);
}
