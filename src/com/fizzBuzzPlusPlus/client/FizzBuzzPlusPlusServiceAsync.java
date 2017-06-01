package com.fizzBuzzPlusPlus.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface FizzBuzzPlusPlusServiceAsync {
    void getFizzBuzz(int limit, int first, int second, AsyncCallback<List<String>> async);
}
