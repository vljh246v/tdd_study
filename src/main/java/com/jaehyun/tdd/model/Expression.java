package com.jaehyun.tdd.model;

public interface Expression {
    Money reduce(Bank bank, String to);
}
