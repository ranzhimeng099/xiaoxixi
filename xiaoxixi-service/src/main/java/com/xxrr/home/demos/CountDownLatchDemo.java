package com.xxrr.home.demos;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class CountDownLatchDemo {

    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    private static final Logger LOGGER = LoggerFactory.getLogger(CountDownLatchDemo.class);

    private static class WorkerThread implements Runnable {
        private Long deliveryId;
        private CountDownLatch latch;
        private Map<Long, String> resultMap;

        public WorkerThread(Long deliveryId, CountDownLatch latch, Map<Long, String> resultMap) {
            this.deliveryId = deliveryId;
            this.latch = latch;
            this.resultMap = resultMap;
        }

        @Override
        public void run() {
            try {
                resultMap.put(deliveryId, "yeah!");
                System.out.println(deliveryId);
            } catch (Exception e) {
                LOGGER.error("error1", e);
            } finally {
                latch.countDown();
            }
        }
    }

    public static void main(String[] args) {
        Map<Long, String> resultMap = new ConcurrentHashMap<>();

        List<Long> deliveryIds = new ArrayList<>();
        deliveryIds.add(1L);
        deliveryIds.add(2L);
        deliveryIds.add(3L);
        deliveryIds.add(4L);

        try {
            CountDownLatch latch = new CountDownLatch(deliveryIds.size());
            for (Long deliveryId : deliveryIds) {
                WorkerThread worker = new WorkerThread(deliveryId, latch, resultMap);
                executorService.execute(worker);
            }
            latch.await(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            LOGGER.error("error2", e);
        } finally {
            executorService.shutdown();
        }
        System.out.println(JSON.toJSONString(resultMap));
    }
}