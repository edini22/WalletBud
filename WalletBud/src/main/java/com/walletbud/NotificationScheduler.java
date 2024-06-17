package com.walletbud;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotificationScheduler {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void start() {
        Runnable task = new NotificationTask();
        // Schedule the task to run once a day
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.DAYS);
    }

    public void stop() {
        scheduler.shutdown();
    }
}