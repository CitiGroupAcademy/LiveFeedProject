	package project.logging;
	
	import java.io.BufferedWriter;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.time.LocalDateTime;
	
	/**
	 * Log manager class prints to the log.txt file use statement
	 * project.logging.LogManager.criticalLog("Test");
	 * 
	 * @author Citi 2015
	 *
	 */
	public class LogManager {
	
		/**
		 * Method prints a debug passed as a parameter to the log file
		 * 
		 * @param text
		 */
		public static void debugLog(String text) {
	
			try (PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("log", true)))) {
	
				out.println("Debug Log, " + LocalDateTime.now() + ", "  +text);
	
			} catch (IOException e) {
			}
	
		}
	
		/**
		 * Method prints an info passed as a parameter to the log file
		 * 
		 * @param text
		 */
		public static void infoLog(String text) {
	
			try (PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("log", true)))) {
	
				out.println("Info Log, "+  LocalDateTime.now()+  ", " + text);
	
			} catch (IOException e) {
			}
	
		}
	
		/**
		 * Method prints a warning passed as a parameter to the log file
		 * 
		 * @param text
		 */
		public static void warningLog(String text) {
	
			try (PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("log", true)))) {
	
				out.println("Warning Log, "+  LocalDateTime.now()  +", " + text);
	
			} catch (IOException e) {
			}
	
		}
	
		/**
		 * Method prints an error passed as a parameter to the log file
		 * 
		 * @param text
		 */
		public static void errorLog(String text) {
	
			try (PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("log", true)))) {
	
				out.println("Error Log, " +  LocalDateTime.now() + ", " + text);
	
			} catch (IOException e) {
			}
	
		}
	
		/**
		 * Method prints a critical error passed as a parameter to the log file
		 * 
		 * @param text
		 */
		public static void criticalLog(String text) {
	
			try (PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("log", true)))) {
	
				out.println("Critical Log, "+  LocalDateTime.now() + ", " + text);
	
			} catch (IOException e) {
			}
	
		}
	}
