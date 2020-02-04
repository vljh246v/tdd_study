package com.jaehyun.tdd.model;

public interface Expression {
    Money reduce(Bank bank, String to);

    Expression plus(Expression tenFrances);

    Expression times(int multiplier);
}

