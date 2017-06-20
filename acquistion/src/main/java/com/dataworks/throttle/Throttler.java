package com.dataworks.throttle;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Sandeep on 5/23/17.
 */
public class Throttler {
    private ConnectionCounter counter = null;

    private final ListeningExecutorService executorService;

    private final ExecutorService callBackExecutorService;


    public Throttler(ConnectionCounter counter) {
        this.counter = counter;
        this.executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        callBackExecutorService = Executors.newCachedThreadPool();
    }

    public  ListenableFuture<ImportStatus> submit(Callable<ImportStatus> c){
        return executorService.submit(c);
    }

    public <V> void addCallBack(final FutureCallback<? super ImportStatus> callback, ListenableFuture<ImportStatus> future){
        Futures.addCallback(future, callback, callBackExecutorService);
    }

    public int getConnectionCounter(){
        return counter.getCounter();
    }

    public ConnectionCounter getCounter() {
        return counter;
    }
}
