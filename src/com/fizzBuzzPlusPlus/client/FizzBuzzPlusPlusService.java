package com.fizzBuzzPlusPlus.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("FizzBuzzPlusPlusService")
public interface FizzBuzzPlusPlusService extends RemoteService {
    List<String> getFizzBuzz(int limit, int first, int second);
}
