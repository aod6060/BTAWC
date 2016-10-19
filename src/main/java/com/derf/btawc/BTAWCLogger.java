package com.derf.btawc;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public final class BTAWCLogger {
	
	private static Logger logger;
	
	public static void init() {
		logger = LogManager.getLogger(Loader.MODID);
	}
	
	public static Logger getLogger() {
		return logger;
	}
}
