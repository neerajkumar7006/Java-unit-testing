package com.demo.service;

import com.demo.domain.Order;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    Order order=mock(Order.class);
    OrderService orderService=spy(OrderService.class);
    EmailService instance=spy(EmailService.class);
    String cc=null;

    @Test(expected = RuntimeException.class)
    public void testPlaceOrder_OrderIsNotNull()
    {
        orderService.placeOrder(order);
    }

    @Test(expected = NullPointerException.class)
    public void testPlaceOrder_OrderIsNull()
    {
        order=null;
        orderService.placeOrder(order);
    }
    @Test(expected = NullPointerException.class)
    public void testPlaceOrder_OrderIsNullAndEmailIsNotNull()
    {
        order=null;
        orderService.placeOrder(order,"ashish@gmail.com");
    }
    @Test(expected =RuntimeException.class)
    public void testPlaceOrder_OrderIsNotNullAndEmailIsNull()
    {
        if(cc==null)
            throw new RuntimeException();

        orderService.placeOrder(order,cc);
    }
    @Test(expected = NullPointerException.class)
    public void testPlaceOrder_OrderIsNullAndEmailIsNull()
    {
        order=null;
        orderService.placeOrder(order,cc);
    }
    @Test
    public void testPlaceOrder_OrderIsNotNullAndEmailIsNotNull()
    {
       assertTrue(orderService.placeOrder(order,"ashish@gmail.com"));
    }
    @Test
    public void testPlaceOrder_VerifyingCalls()
    {
        double price=0.0;
        orderService.placeOrder(order,cc);
        instance.sendEmail(order,cc);

        verify(orderService,times(1)).placeOrder(order,cc);
        verify(order,times(1)).setPriceWithTax(price);
        verify(instance,times(1)).sendEmail(order,cc);
        verify(order,times(3)).setCustomerNotified(true);
    }
}
