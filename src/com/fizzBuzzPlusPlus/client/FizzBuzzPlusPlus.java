package com.fizzBuzzPlusPlus.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import java.util.List;

public class FizzBuzzPlusPlus implements EntryPoint {

    private Panel inputPanel = RootPanel.get("slotInput"),
            outputPanel = RootPanel.get("slotOutput"),
            calcPanel = RootPanel.get("slotCalc");

    private Label lblFizz = new Label(),
            lblBuzz = new Label(),
            lblLimit = new Label();

    private IntegerBox intFizz = new IntegerBox(),
            intBuzz = new IntegerBox(),
            intLimit = new IntegerBox();

    private Button btnCalculate = new Button("FizzBuzz");

    private FizzBuzzPlusPlusServiceAsync fizzBuzzPlusPlusServiceAsync = GWT.create(FizzBuzzPlusPlusService.class);

    private ListBox lbFizzBuzz = new ListBox();

    public void onModuleLoad() {
        lblFizz.setText("Pick a number for Fizz:");
        lblBuzz.setText("Pick a number for Buzz:");
        lblLimit.setText("Pick a limit N:");

        intFizz.setTitle("Fizz");
        intBuzz.setTitle("Buzz");
        intLimit.setTitle("Limit");

        lbFizzBuzz.setWidth("400px");
        lbFizzBuzz.setVisible(false);

        btnCalculate.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (validate(intFizz) && validate(intBuzz) && validate(intLimit)) {
                    doFizzBuzz();
                }
            }
        });

        inputPanel.add(lblFizz);
        inputPanel.add(intFizz);

        inputPanel.add(lblBuzz);
        inputPanel.add(intBuzz);

        inputPanel.add(lblLimit);
        inputPanel.add(intLimit);

        outputPanel.add(lbFizzBuzz);
        calcPanel.add(btnCalculate);
    }

    private boolean validate(IntegerBox input) {

        if (input.getText().matches("^[1-9]\\d*$")) {
            if (input.getValue() > 10000) {
                Window.alert(input.getTitle() + " is too big! (10000 max)");
                return false;
            } else {
                return true;
            }
        } else {
            Window.alert(input.getTitle() + " has to be a positive Interger!!");
            return false;
        }
    }

    private void doFizzBuzz() {
        int fizz = intFizz.getValue();
        int buzz = intBuzz.getValue();
        int limit = intLimit.getValue();

        if (fizzBuzzPlusPlusServiceAsync == null) {
            fizzBuzzPlusPlusServiceAsync = GWT.create(FizzBuzzPlusPlusService.class);
        }

        AsyncCallback<List<String>> callback = new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

            @Override
            public void onSuccess(List<String> result) {
                showFizzBuzz(result);
            }
        };

        fizzBuzzPlusPlusServiceAsync.getFizzBuzz(limit, fizz, buzz, callback);
    }

    private void showFizzBuzz(List<String> result) {
        lbFizzBuzz.clear();
        lbFizzBuzz.setVisibleItemCount(Math.min(20, result.size()));
        for (String item : result) {
            lbFizzBuzz.addItem(item + "");
        }
        lbFizzBuzz.setVisible(true);
    }
}
