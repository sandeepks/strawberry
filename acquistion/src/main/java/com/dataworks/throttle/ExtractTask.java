package com.dataworks.throttle;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Sandeep on 6/2/17.
 */
public class ExtractTask<V> implements Callable<V> {
    private Context context;
    private Thread producerThread = null;
    private Thread consumerThread = null;
    private BlockingQueue<ImportContext> queue = null;
    private Throttler throttler = null;

    public ExtractTask(Context context) {
        throttler = new Throttler(new ConnectionCounter(context.getConnections()));
        queue = new LinkedBlockingQueue<ImportContext>(getDepthOfQueue());
        this.producerThread = new SourceThread("Source Extraction Thread", throttler, queue, context);
        this.consumerThread = new SyncThread("Task Submitter Thread", throttler, queue, context);
    }

    private int getDepthOfQueue() {
        //from context get the number of Unique Src Entities from AppTable Config.
        //Multiply it with the factor number of (cores * 2 - 8) *
        return 0;
    }

    public V call() throws Exception {
        V returnValue = null;
        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        return returnValue;
    }
}
