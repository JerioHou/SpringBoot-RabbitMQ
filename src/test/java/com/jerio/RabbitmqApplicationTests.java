package com.jerio;

import com.jerio.mq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {
	@Autowired
	Sender sender;

	@Test
	public void sendByDirect() {
		sender.sendByDirect();
	}

	@Test
	public void sendByTopic1(){
		sender.sendByTopic1();
	}

	@Test
	public void sendByTopic2(){
		sender.sendByTopic2();
	}
	@Test
	public void sendByFanout(){
		sender.sendByFanout();
	}

	@Test
    public void testAck(){
        sender.sendDirectAck();
    }
}
