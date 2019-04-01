package com.example.demo.core;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponses {

    private List<String> messages = new ArrayList<>();

    private ErrorResponses() {
    }

    private ErrorResponses(List<String> messages) {
        this.messages = messages;
    }

    public static ErrorResponses getInstance(List<String> messages) {
        return new ErrorResponses(messages);
    }
}
