package ru.netology.testHW;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class TestMassAttack {
    Basket basket1;

    @BeforeEach
    void prepareBasket() {
        String[] products = {"bread", "salt"};
        int[] price = {20, 10};
        int[] amount = {3, 0};
        basket1 = new Basket(products, price, amount);
    }

    @Test
    public void testConstructor() {
        Assertions.assertNotNull(basket1);
    }

    @Test
    public void testGetAmount() {
        Assertions.assertEquals(3, basket1.getAmount(3));
    }

    @Test
    public void testGetAmountLessZero() {
        Assertions.assertEquals(0, basket1.getAmount(-2));
    }

    @Test
    public void testGetProductBeyondLimits() {
        Assertions.assertEquals(-1, basket1.getProductNum(-1));
        Assertions.assertEquals(-1, basket1.getProductNum(basket1.products.length + 1));
    }

    @Test
    public void testPrintCart() {
        String consoleOutput = null;
        PrintStream originalOutput = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            basket1.printCart();
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (basket1.amount[0] > 0) {
            Assertions.assertEquals("Ваша корзина:\r\n" +
                    basket1.products[0] + ": " + basket1.amount[0] + " шт на сумму: " + (basket1.amount[0]) * (basket1.price[0]) + " руб\r\n"
                    + "Итого: " + (basket1.amount[0]) * (basket1.price[0]) + "\r\n", consoleOutput);
        } else {
            System.out.println("Корзина пуста");
        }
    }
}
