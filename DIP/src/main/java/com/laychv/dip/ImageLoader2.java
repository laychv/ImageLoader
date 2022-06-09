package com.laychv.dip;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * @author: LayChv
 * @date: 2022/6/9
 * @des:
 */
public class ImageLoader2 {

    // 具体实现
    DoubleCache cache = new DoubleCache();

    public void displayImage(String url, ImageView imageView) {
        Bitmap bitmap = cache.get(url);
        if (bitmap != null) {
//            download(url,imageView);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    public void setImageCache(DoubleCache cache) {
        this.cache = cache;
    }
}
