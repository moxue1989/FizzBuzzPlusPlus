package com.fizzBuzzPlusPlus.server;

import com.fizzBuzzPlusPlus.client.FizzBuzzPlusPlusService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzzPlusPlusServiceImpl extends RemoteServiceServlet implements FizzBuzzPlusPlusService {

    private String stringFizz = "Fizz";
    private String stringBuzz = "Buzz";
    private String stringFizzBuzz = "FizzBuzz";


    @Override
    public List<String> getFizzBuzz(int limit, int fizz, int buzz) {

        List<String> result = new ArrayList<>();

        result.add("0");

        for (int i = 1; i <= limit; i++) {
            boolean isFizz = (i % fizz == 0);
            boolean isBuzz = (i % buzz == 0);

            if (isFizz && isBuzz) {
                result.add(stringFizzBuzz);
            } else if (isFizz) {
                result.add(stringFizz);
            } else if (isBuzz) {
                result.add(stringBuzz);
            } else {
                result.add(i + "");
            }
        }
        return result;

    }
}