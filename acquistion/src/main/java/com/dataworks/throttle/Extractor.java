package com.dataworks.throttle;


import com.dataworks.error.ThrowIt;
import com.google.common.util.concurrent.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Sandeep on 6/1/17.
 */
public class Extractor {
    private Context context;
    private final ListeningExecutorService executorService;
    private final ExecutorService callBackExecutorService;
    private CountDownLatch latch;

    public Extractor(Context context) throws ThrowIt {
        this.context = context;
        this.executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(context.getNumberOfThreads()));
        callBackExecutorService = Executors.newCachedThreadPool();
        latch = new CountDownLatch(3);
    }


    public boolean extract(){
        boolean returnValue = false;
        List<ExtractorCallBack> callBackList = new LinkedList<ExtractorCallBack>();
        for(int i = 0; i < context.getNumberOfThreads(); i++) {
            ExtractTask task = new ExtractTask(context);
            ListenableFuture<?> future = executorService.submit(task);
            ExtractorCallBack<Object> extractorCallBack = new ExtractorCallBack<Object>(latch);
            addCallBack(extractorCallBack, future);
            callBackList.add(extractorCallBack);

        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return returnValue;

    }

    public <V> void addCallBack(final FutureCallback<? super V> callback, ListenableFuture<V> future){
        Futures.addCallback(future, callback, callBackExecutorService);
    }



}
