import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Simple Audit class which takes a string, time stamps it and appends it to a
 * file. Which file is specified in OPTIONS static variables.
 * 
 * @author Ludde
 * @see Options
 */
public class Audit {
	private static PrintWriter pw = null;

	/**
	 * Appends a string to the file specified by the static variable in OPTIONS class.
	 * @param toLog the string to be appended to the file.
	 */
	public static void log(String toLog) {
		if (Options.AUDIT_LOGGING) {
			if (pw == null) {
				startPrintWriter();
			}
			Date date = Calendar.getInstance().getTime();

			DateFormat df = new SimpleDateFormat("yyyy-MMM-dd  hh:mm:ss - ");
			String time = df.format(date);
			pw.println(time + toLog);
			System.out.println(toLog);
			pw.flush();
		}
	}

	/**
	 * Initates the PrintWriter.
	 */
	private static void startPrintWriter() {
		try {
			File file = new File(Options.AUDIT_FILE_NAME);
			System.out.println(Options.AUDIT_FILE_NAME);
			FileWriter fw = new FileWriter(file, true);
			pw = new PrintWriter(new BufferedWriter(fw));
			pw.println();
			pw.println();
			pw.println("__________NEW SESSION__________");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			pw.close();
		}

	}
}
