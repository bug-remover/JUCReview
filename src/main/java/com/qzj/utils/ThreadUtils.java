package com.qzj.utils;

/**
 * DESCRIPTION:TODO <br/>
 *
 * @author qizhongju
 * @Date: 2021/8/17 14:08  <br/>
 */
public class ThreadUtils {

    public static String getThreadName() {
        return String.format("[%s]：", Thread.currentThread().getName());
    }

}
