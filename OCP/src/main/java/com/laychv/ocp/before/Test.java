package com.laychv.ocp.before;

/**
 * @author: LayChv
 * @date: 2022/6/9
 * @des:
 */
public class Test {
    public static void main(String[] args) {
        ImageLoader loader = new ImageLoader();
        loader.useDiskCache(true);
        loader.useDiskCache(false);
    }
}
