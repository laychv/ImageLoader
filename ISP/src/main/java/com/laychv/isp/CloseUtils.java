package com.laychv.isp;

import java.io.Closeable;

/**
 * @author: LayChv
 * @date: 2022/6/10
 * @des:
 */
public final class CloseUtils {
    public static void closeQuietly(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
