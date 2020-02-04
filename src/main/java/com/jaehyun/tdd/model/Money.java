package com.jaehyun.tdd.model;

public class Money implements Expression{


    protected int amount;
    protected String currency;


    public Money(int amount, String currency){
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    @Override
    public boolean equals(Object obj) {
        Money money = (Money)obj;
        return amount == money.amount
                && currency().equals(money.currency());
    }

    public Expression times(int multiplier){
        return new Money(amount * multiplier, this.currency);
    }

    public String currency() {
        return this.currency;
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    public Expression plus(Expression addend){
        return new Sum(this, addend);
    }

    public Money reduce(Bank bank, String to){
        int rate = bank.rate(currency, to);
        return new Money(amount / rate, to);
    }
}
