package com.dataworks.throttle;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Sandeep on 6/2/17.
 */
public class SyncThread extends Thread {
    private Context context;
    private Throttler throttler;
    private BlockingQueue<ImportContext> queue;
    private CountDownLatch latch;

    public SyncThread(String name, Throttler throttler, BlockingQueue queue, Context context) {
        super(name);
        this.throttler = throttler;
        this.queue = queue;
        this.context = context;
        latch = new CountDownLatch(context.getTblConfig().size());
    }

    @Override
    public void run() {
        boolean continueFlag = true;
        while(continueFlag){

            if(throttler.getConnectionCounter() > 0) {
                try {
                    ImportContext context = queue.take();
                    IngestTask ingestTask = new IngestTask(throttler.getCounter(), context);
                    ListenableFuture<ImportStatus> submittedTask = throttler.submit(ingestTask);
                    throttler.addCallBack(new ThrottlerCallback<ImportStatus>(latch), submittedTask);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
