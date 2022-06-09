package com.laychv.dip;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * @author: LayChv
 * @date: 2022/6/9
 * @des:
 */
public class ImageLoader3 {

    // 依赖于抽象，默认一个具体实现
    ImageCache cache = new MemoryCache();

    public void displayImage(String url, ImageView imageView) {
        Bitmap bitmap = cache.get(url);
        if (bitmap != null) {
//            download(url,imageView);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    public void setImageCache(ImageCache cache) {
        this.cache = cache;
    }
}
