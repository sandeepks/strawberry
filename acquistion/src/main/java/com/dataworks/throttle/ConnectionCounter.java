package com.dataworks.throttle;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Sandeep on 5/22/17.
 */
public class ConnectionCounter {
    private AtomicInteger c = null;

    public ConnectionCounter(int initialValue) {
        this.c = new AtomicInteger(initialValue);
    }

    public void increment() {
        c.getAndIncrement();
    }

    public void decrement() {
        c.getAndDecrement();
    }

    public int getCounter() {
        return c.get();
    }
}
