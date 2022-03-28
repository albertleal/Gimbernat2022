package com.albertleal.gimbernat.helpers;

public interface Callback {
    void onSuccess(Object responseObject);
    void onError(String error);
}
