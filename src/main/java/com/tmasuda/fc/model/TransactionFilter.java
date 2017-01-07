package com.tmasuda.fc.model;

public class TransactionFilter {
    public Integer page;
    public Integer size;

    @Override
    public String toString() {
        return "TransactionFilter{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}