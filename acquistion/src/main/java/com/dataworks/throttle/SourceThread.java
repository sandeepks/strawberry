package com.dataworks.throttle;

import com.dataworks.connections.SqlMgr;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Sandeep on 6/2/17.
 */
public class SourceThread extends Thread {
    private Context context;
    private Throttler throttler;
    private BlockingQueue<ImportContext> queue;
    private SqlMgr sourceManager;
    private ThreadLocal<Map<Integer, ImportContext>> cxn2Context = null;
    private CountDownLatch latch;

    public SourceThread(String name, Throttler throttler, BlockingQueue<ImportContext> queue, Context context) {
        super(name);
        this.throttler = throttler;
        this.queue = queue;
        this.context = context;
        latch = new CountDownLatch(context.getTblConfig().size());
    }

    @Override
    public void run(){
        cxn2Context = createContext();
        boolean continueFlag = true;
        while(continueFlag){

            if(throttler.getConnectionCounter() > 0) {
                try {
                    getConnectionObj(throttler.getConnectionCounter());
                    release2Queue();
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }


    }

    private Integer getConnectionObj(int counter) {
        Integer returnValue = null;

        for (int i = 0; i < counter; i++) {

            final boolean b = cxn2Context.get().containsKey(i);
            if(b){
                returnValue = i;
            }
        }

        return returnValue;
    }

    private ThreadLocal<Map<Integer,ImportContext>> createContext() {
        Integer i = guessConnection();
        //create Map of number of connections needed to ImportContext
        return null;

    }

    private Integer guessConnection() {
        Integer returnValue = null;
        //Get Row Size For 10 Rows
        //Pick Max out of 10 Rows Size
        //Get the HDFS Block Size
        //Find the Incremental Data volumn or number of Records
        // (number of Records * max Row Size)/hdfs block size gives the number of connections needed

        return returnValue;
    }

    private void release2Queue(){

    }


}
