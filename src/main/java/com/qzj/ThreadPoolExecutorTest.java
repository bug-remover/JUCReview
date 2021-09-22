package com.qzj;

import com.qzj.utils.ThreadUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.*;

/**
 * DESCRIPTION:TODO <br/>
 *
 * @author qizhongju
 * @Date: 2021/8/17 11:58  <br/>
 */
public class ThreadPoolExecutorTest {

    static class Task implements Runnable {
        private int num;

        public Task(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println(ThreadUtils.getThreadName() + "执行任务" + num);
            //模拟任务执行过程
            try {
                TimeUnit.SECONDS.sleep(RandomUtils.nextInt(1, 3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        int coreThreadNum = 5;
        int maxThreadNum = 10;
        long keepAliveTime = 5;
        ArrayBlockingQueue queue = new ArrayBlockingQueue(10);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(coreThreadNum, maxThreadNum, keepAliveTime, TimeUnit.SECONDS, queue);
        for (int i = 0; i < 20; i++) {
            executor.submit(new Task(i));
        }
        executor.shutdown();
    }

}
