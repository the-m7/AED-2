/**
 * 
 */
package checkpoint;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author MAAG
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
		User loggedUser = null;
		
		Login myLogin;
		myLogin = new Login();
		
		Scanner in = new Scanner(System.in);
		
		while(true) {
		
		System.out.println("Bienvenido al portal electronico");
		System.out.println("Ingrese su usuario: ");
		String username = in.next();
		System.out.println("Ingrese su contrasenia: ");
		String password = in.next();
		
		loggedUser = myLogin.hasAccess(username, password);
		
		if (loggedUser != null) {
			System.out.println("login exitoso");
			
			boolean bmenu = true;
			while(bmenu) {
				loggedUser.printMenu();
				System.out.print("Que desea hacer: ");
				bmenu = loggedUser.doAction(in.next());
				System.out.println();
			}
		} else {
			System.out.println("Usuario o Password incorrectos");
		}
		
	}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}