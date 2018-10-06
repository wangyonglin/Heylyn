package com.heylyn.os;

public interface TakeClassCallback<T> {
    public void loader(Class<T> cls);
}
