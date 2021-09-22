package com.qzj;

import com.qzj.utils.ThreadUtils;
import org.apache.commons.lang3.RandomUtils;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * DESCRIPTION:TODO <br/>
 *
 * @author qizhongju
 * @Date: 2021/8/17 11:52  <br/>
 */
public class SemaphoreTest {

    static Semaphore semaphore = new Semaphore(1);

    static class Player implements Runnable {
        private String nickName;
        private Semaphore semaphore;

        public Player(String nickName, Semaphore semaphore) {
            this.nickName = nickName;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {

                if (semaphore.tryAcquire())

                    semaphore.acquire();
                System.out.println(ThreadUtils.getThreadName() + nickName + "准备打疫苗---");
                TimeUnit.SECONDS.sleep(RandomUtils.nextInt(1, 4));
                System.out.println(ThreadUtils.getThreadName() + nickName + "疫苗打完，释放资源---");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        List<String> playerList = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            playerList.add("同学" + i);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (String player : playerList) {
            executorService.submit(new Player(player, semaphore));
        }
        executorService.shutdown();
    }

}
