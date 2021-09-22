package com.qzj;

import com.qzj.utils.ThreadUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 核心方法
 * countDown
 * await
 * <p>
 * <br/>
 *
 * @author qizhongju
 * @Date: 2021/8/17 11:50  <br/>
 */
public class CountdownLatchTest {

    static class DownloadTask extends Thread {
        private String fileName;
        private CountDownLatch countDownLatch;

        public DownloadTask(String fileName, CountDownLatch countDownLatch) {
            this.fileName = fileName;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(ThreadUtils.getThreadName() + fileName + "开始下载--");
            try {
                TimeUnit.SECONDS.sleep(RandomUtils.nextInt(2, 8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(ThreadUtils.getThreadName() + fileName + "下载完成--");
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) {
        /**
         * 模拟生成五个文件，需要打包下载
         */
        List<String> fileList = new ArrayList<String>();
        for (int i = 1; i <= 5; i++) {
            fileList.add("文件" + i);
        }

        //创建任务去单独生成文件
        CountDownLatch countDownLatch = new CountDownLatch(fileList.size());
        for (String fileName : fileList) {
            DownloadTask downloadTask = new DownloadTask(fileName, countDownLatch);
            downloadTask.start();
        }
        try {
            //阻塞，等待其他线程完成
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始打包");
        System.out.println("批量下载任务完成，通知用户");
    }

}
