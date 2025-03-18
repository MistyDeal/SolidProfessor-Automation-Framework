package com.solidprofessor.qa.utils;

 

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Utility class for logging messages using the Apache Log4j 2 library.
 */
public class LoggerUtil {

	private Logger logger;

	/**
	 * Constructor that initializes the logger with the class name of the LoggerUtil
	 * class.
	 */
	public LoggerUtil() {
		logger = LogManager.getLogger(LoggerUtil.class);
	}

	/**
	 * Logs an info level message.
	 * 
	 * @param message The message to be logged.
	 */
	public void logInfo(String message) {
		logger.info(message);
	}

	/**
	 * Logs a debug level message.
	 * 
	 * @param message The message to be logged.
	 */
	public void logDebug(String message) {
		logger.debug(message);
	}

	/**
	 * Logs a warning level message.
	 * 
	 * @param message The message to be logged.
	 */
	public void logWarn(String message) {
		logger.warn(message);
	}

	/**
	 * Logs an error level message.
	 *
	 * @param message The message to be logged.
	 */
	public void logError(String message) {
		logger.error(message);
	}

	/**
	 * Logs a fatal level message.
	 *
	 * @param message The message to be logged.
	 */
	public void logFatal(String message) {
		logger.fatal(message);
	}

}
