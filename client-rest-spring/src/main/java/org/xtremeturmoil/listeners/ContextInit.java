package org.xtremeturmoil.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class ContextInit implements ServletContextListener{

	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("Application Shutting Down.");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("Application Staring...");
	}

}
