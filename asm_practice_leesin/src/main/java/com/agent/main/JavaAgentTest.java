package com.agent.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.MessageFormat;


public class JavaAgentTest {
	private static final Logger logger = LoggerFactory.getLogger(JavaAgentTest.class);
	public static void main(String[] args) {
		logger.info("my name is : {}", "Leesin");
	}

}
