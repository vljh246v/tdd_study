package com.jaehyun.tdd.Service;

import com.jaehyun.tdd.model.Bank;
import com.jaehyun.tdd.model.Expression;
import com.jaehyun.tdd.model.Money;
import com.jaehyun.tdd.model.Sum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CalculatorServiceTest {

    private CalculatorService calculatorServiceUnderTest;

    @BeforeEach
    void setUp() {
        calculatorServiceUnderTest = new CalculatorService();
    }


    @Test
    public void testMultiplication(){
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    public void testEquality(){
        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertFalse(Money.dollar(5).equals(Money.dollar(6)));
        assertTrue(Money.franc(5).equals(Money.franc(5)));
        assertFalse(Money.franc(5).equals(Money.franc(6)));
        assertFalse(Money.franc(5).equals(Money.dollar(5)));
    }

    @Test
    public void testFrancMultiplication(){
        Money five = Money.franc(5);
        assertEquals(Money.franc(10), five.times(2));
        assertEquals(Money.franc(15), five.times(3));
    }

    @Test
    public void testCurrency(){
        assertEquals("USD", Money.dollar(1).currency());
    }

    @Test
    public void testSimpleAddition(){

        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    public void testPlusReturnsSum(){
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.augend);
        assertEquals(five, sum.addend);
    }

    @Test
    public void testReduceSum(){
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        final Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7), result);

    }

    @Test
    public void testReduceMoney(){
        Bank bank = new Bank();
        final Money result = bank.reduce(Money.dollar(1), "USD");
        assertEquals(result, Money.dollar(1));

    }

    @Test
    public void testReduceMoneyDifferentCurrency(){
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        final Money result = bank.reduce(Money.franc(2), "USD");
        assertEquals(result, Money.dollar(1));

    }
}
