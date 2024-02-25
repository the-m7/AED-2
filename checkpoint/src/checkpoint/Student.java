/**
 * 
 */
package checkpoint;

/**
 * @author MAAG
 *
 */
public class Student extends User {

	public Student(String user, String pass) {
		super(user, pass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printMenu() {
		System.out.println("Bienvenido Alumno");
		System.out.println("Seleccione su opcion");
		System.out.println("1. Consultar nota de una clase especifica");
		System.out.println("2. Realizar Pago");
		System.out.println("3. Consultar Pago");
		System.out.println("4. Cerrar Sesion");
	}

	@Override
	public boolean doAction(String sel) {
		switch(sel) {
		case "1":
			System.out.println("Consultando Nota...");
			break;
		case "2":
			System.out.println("Realizando Pago...");
			break;
		case "3":
			System.out.println("Consultando Pago...");
			break;
		case "4":
			System.out.println("Cerrando sesión");
			return false;
		}
		return true;
	}

	
}