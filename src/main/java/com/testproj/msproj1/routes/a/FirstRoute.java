	package com.testproj.msproj1.routes.a;
	
	import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
	
	//@Component
	public class FirstRoute extends RouteBuilder{
	
		@Autowired
		private SampleBean sampleBean;
		
		@Autowired
		private LogMeBean logMeBean;
		
		@Autowired
		private FirstProcessor firstProcessor;
		
		@Override
		public void configure() throws Exception {
			from("timer:first-timer")
			.log("${body}")
			//.transform().constant("Hi Sasi")
			//.transform().constant("Sasi"+LocalDateTime.now())
			.bean("sampleBean","getTimeNow")
			.log("${body}")
			.bean("logMeBean")
			.log("${body}")
			.process(firstProcessor)
			.to("log:first-timer");
		}
	}
	
	@Component
	class SampleBean{
		public String getTimeNow() {
			return "Time Now - "+LocalDateTime.now();
		}
	}
	
	@Component
	class LogMeBean{
		private Logger logger = LoggerFactory.getLogger(LogMeBean.class);
		public void logThis(String message) {
			logger.info("LogMeBean Component {}", message);
		}
	}
	
	@Component
	class FirstProcessor implements Processor{

		private Logger logger = LoggerFactory.getLogger(FirstProcessor.class);
		
		@Override
		public void process(Exchange exchange) throws Exception {
			logger.info("Logging from processer class {}", exchange.getMessage().getBody());
		}
		
	}
