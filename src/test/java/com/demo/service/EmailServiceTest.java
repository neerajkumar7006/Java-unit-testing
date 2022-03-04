package com.demo.service;

import com.demo.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {
    EmailService instance=spy(EmailService.class);
    @Mock
    Order order;
    @Test
    public void testSendEmail_WithCredential()
    {

        assertTrue(instance.sendEmail(order, "ashish@gmail.com"));
    }
    @Test(expected = RuntimeException.class)
    public void testSendEmail_WithOrder()
    {

        instance.sendEmail(order);

    }
    @Test(expected =NullPointerException.class)
    public void testSendEmail_WhenOrderIsNull()
    {
        order=null;
        instance.sendEmail(order);
    }
    @Test(expected =NullPointerException.class)
    public void testSendEmail_OrderIsNullAndEmailIsNotNull()
    {
        order=null;
        instance.sendEmail(order,"ashish@gmail.com");
    }
    @Test(expected =RuntimeException.class)
    public void testSendEmail_OrderIsNotNullAndEmailIsNull()
    {

       String cc=null;
       if(cc==null)
           throw new RuntimeException();
        instance.sendEmail(order,cc);
    }
    @Test(expected = NullPointerException.class)
    public void testSendEmail_OrderIsNullAndEmailIsNull()
    {
        order=null;
        String cc=null;
        instance.sendEmail(order,cc);
    }


}