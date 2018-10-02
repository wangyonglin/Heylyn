package com.heylyn.os;

/**
 * Created by honjane on 2017/3/12.
 */

public final class Looper {


    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();
    public MessageQueue mQueue;

    public Looper() {
        mQueue = new MessageQueue();
    }


    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public static void loop() {
        Looper me = myLooper();
        MessageQueue queue = me.mQueue;
        //轮询
        Message msg;
        for (; ; ) {
            msg = queue.next();
            if (msg == null || msg.target == null) {
                continue;
            }

            msg.target.dispatchMessage(msg);

        }
    }
}
