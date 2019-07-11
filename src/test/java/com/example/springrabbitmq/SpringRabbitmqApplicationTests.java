package com.example.springrabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRabbitmqApplicationTests {
	@Autowired
	private RabbitAdmin rabbitAdmin;

	@Test
	public void contextLoads() {
		rabbitAdmin.declareExchange(new DirectExchange("test.direct.spring",false,true));
	}


}
