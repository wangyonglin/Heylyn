package com.heylyn.os;




import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by honjane on 2017/3/12.
 */

public class MessageQueue {
    private static final String TAG = MessageQueue.class.getName();
    Message[] mItems;
    int mPutIndex;
    private int mCount;
    private int mTakeIndex;
    Lock mLock;
    Condition mNotEmpty;
    Condition mNotFull;

    public MessageQueue() {
        mItems = new Message[50];
        mLock = new ReentrantLock();
        mNotEmpty = mLock.newCondition();
        mNotFull = mLock.newCondition();
    }


    Message next() {
        Message msg = null;
        try {
            mLock.lock();

            while (mCount <= 0) {

                mNotEmpty.await();
              
            }
            msg = mItems[mTakeIndex];

            mItems[mTakeIndex] = null;
            mTakeIndex = (++mTakeIndex >= mItems.length) ? 0 : mTakeIndex;
            mCount--;

            mNotFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }

        return msg;
    }


    public void enqueueMessage(Message message) {

        try {
            mLock.lock();

            while (mCount >= mItems.length) {

                mNotFull.await();

            }

            mItems[mPutIndex] = message;
            mPutIndex = (++mPutIndex >= mItems.length) ? 0 : mPutIndex;
            mCount++;
            mNotEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }


    }
}
