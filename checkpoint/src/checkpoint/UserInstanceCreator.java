/**
 * 
 */
package checkpoint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author MAAG
 *
 */
public class UserInstanceCreator {
	
	public static User getInstance(String[] row) {
		User myUser;
		String user = row[0];
		String pass = row[1];
		String type = row[2];
		
		switch(type) {
		case "Student":{
			myUser = new Student(user,pass);
		}break;
		
		case "Professor":{
			myUser = new Professor(user, pass);
		}break;
		
		case "Employee":{
			myUser = new Employee(user, pass);
		}break;
		
		case "Audit":{
			myUser = new Audit(user, pass);
		}break;
		
		default:{
			myUser = null;
		}break;
		}
		
		return myUser;
	}
}