/**
 * Options which can be modified in order to change the game play. Currently
 * only changeable by editing the code, future implementations could include
 * changing these variables within the GUI.
 * 
 * @author Ludde
 *
 */
public class Options {
	public static final int WIN_CONDITION = 4;
	public static final String TITLE = "Four In a Row - Lunicore";
	public static final boolean AUDIT_LOGGING = true;
	public static final String WORKING_DIR = System.getProperty("user.home") + System.getProperty("file.separator");
	public static final String AUDIT_FILE_NAME = WORKING_DIR + "fourinarow.audit.txt";
}
