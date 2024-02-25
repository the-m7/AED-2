/**
 * 
 */
package checkpoint;

/**
 * @author MAAG
 *
 */
public abstract class User {

	private String User;
	private String Pass;
	
	public User(String user, String pass) {
		User=user;
		Pass=pass;
	}
	
	public String getUsername() {
		return User;
	}

	public void setUsername(String username) {
		this.User = username;
	}

	public String getPassword() {
		return Pass;
	}

	public void setPassword(String password) {
		this.Pass = password;
	}

	public abstract void printMenu();

	public abstract boolean doAction(String string);
		
}