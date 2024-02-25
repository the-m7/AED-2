/**
 * 
 */
package checkpoint;

import java.util.ArrayList;
//import java.util.HashMap;

/**
 * @author MAAG
 *
 */
public class Login {

	//HashMap<String, String> users;
	ArrayList<User> Users = new ArrayList<User>();
	
	public Login() throws Exception {
		ArrayList<String> UsersSL = new ArrayList<String>();
		UsersSL = FileHelper.readFile("datos.txt");
		
		
		for(int i=0;i<UsersSL.size();i++) {
			String[] row = UsersSL.get(i).split(";");
			for(int j=0;j<row.length;j++) {
				row[j]=row[j].strip();
			}
			User facUser = UserInstanceCreator.getInstance(row);
			if(facUser!=null)
				Users.add(facUser);
			else
				throw new Exception("Formato Incorrecto");
		}
//		printUsers();
		
//		users = new HashMap<String, String>();
//		users.put("alo171164@uvg.edu.gt", "password1");
//		users.put("maalonso@uvg.edu.gt", "password2");
//		users.put("maalonso_adm@uvg.edu.gt", "password3");
	}
	
	public User hasAccess(String username, String password) {
		for(int i=0;i<Users.size();i++) {
			if(Users.get(i).getUsername().equals(username)) {
				if(Users.get(i).getPassword().equals(password))
					return Users.get(i);
			}
		}
		return null;
	}
	
	public void printUsers() {
		for(int i=0;i<Users.size();i++) {
			User user = Users.get(i);
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			System.out.println(user.getClass());
		}
	}
}