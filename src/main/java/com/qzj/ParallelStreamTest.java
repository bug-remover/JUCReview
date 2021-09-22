package com.qzj;

import com.qzj.utils.ThreadUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * DESCRIPTION:TODO <br/>
 *
 * @author qizhongju
 * @Date: 2021/9/8 17:33  <br/>
 */
public class ParallelStreamTest {

    public static void test1() {
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        //普通stream
        Arrays.asList(arr).stream().forEach(num -> {
            try {
                TimeUnit.SECONDS.sleep(RandomUtils.nextInt(1, 2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(ThreadUtils.getThreadName() + ":" + num);
        });

        //并行stream
        Arrays.asList(arr).parallelStream().forEach(num -> {
            try {
                TimeUnit.SECONDS.sleep(RandomUtils.nextInt(1, 2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(ThreadUtils.getThreadName() + ":" + num);
        });
    }

    public static void main(String[] args) {
        test1();
    }


}
