package com.testproj.msproj1.routes.c;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AMQMsgSenderRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("timer:active-mq-timer?period=10000")
		.transform().constant("Hey its me, giving you a ping at "+LocalDateTime.now())
		.log("${body}")
		.to("activemq:my-activemq-queue1");
	}
	
}
