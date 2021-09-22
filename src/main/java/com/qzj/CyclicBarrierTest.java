package com.qzj;

import com.qzj.utils.ThreadUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.*;

/**
 * DESCRIPTION:TODO <br/>
 *
 * @author qizhongju
 * @Date: 2021/8/17 11:54  <br/>
 */
public class CyclicBarrierTest {

    static class Player implements Runnable {
        private String hero;
        private CyclicBarrier cyclicbarrier = null;

        Player(String hero, CyclicBarrier cyclicBarrier) {
            this.hero = hero;
            this.cyclicbarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(ThreadUtils.getThreadName() + hero + "开始加载---");
                //模拟加载过程
                TimeUnit.SECONDS.sleep(RandomUtils.nextInt(2, 8));
                System.out.println(ThreadUtils.getThreadName() + hero + "加载成功，等待其他玩家加载成功---");
                cyclicbarrier.await();
                System.out.println(ThreadUtils.getThreadName() + hero + "：看见所有玩家加载成功，游戏开始---");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

        String[] heros = new String[]{"鲁班", "昭君", "悟空", "八戒", "项羽"};
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (String hero : heros) {
            executorService.submit(new Player(hero, cyclicBarrier));
        }
        executorService.shutdown();
    }

}
