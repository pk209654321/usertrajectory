package cn.sscf.usertrajectory.receiver;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;


@Component
public class LoginReceive {
	private static final Logger logger = LoggerFactory.getLogger(LoginReceive.class);

	//@RabbitListener(queues = "online-user-trace") // 监听器监听指定的Queue
	public void processC(byte[] data, @Header(AmqpHeaders.REPLY_TO) String replyTo, @Header(AmqpHeaders.CORRELATION_ID) byte[] correlationId) {
	}

	private void reply(String replyTo, byte[] correlationId) {

	}
}
