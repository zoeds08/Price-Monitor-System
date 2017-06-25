package crawler;


import crawler.LevelToCrawl.LevelThread;
import crawler.LevelToCrawl.LevelThread2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class CrawlerMain {

    public static void main(String[] args) throws Exception {

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        LevelThread levelThread = new LevelThread();
        LevelThread2 levelThread2 = new LevelThread2();
        scheduledThreadPool.scheduleAtFixedRate(levelThread,0,100, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleAtFixedRate(levelThread2,0,100,TimeUnit.SECONDS);
    }
}
