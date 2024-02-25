/**
 * 
 */
package checkpoint;

/**
 * @author MAAG
 *
 */
public class Employee extends User {

	public Employee(String user, String pass) {
		super(user, pass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printMenu() {
		System.out.println("Bienvenido");
		System.out.println("Seleccione su opcion");
		System.out.println("1. Crear Cursos");
		System.out.println("2. Asignar estudiante");
		System.out.println("3. Asignar Profesor");
		System.out.println("4. Asignar Pago a profesor");
		System.out.println("5. Cerrar Sesion");
	}

	@Override
	public boolean doAction(String sel) {
		switch(sel) {
		case "1":
			System.out.println("Crear Cursos...");
			break;
		case "2":
			System.out.println("Asignar estudiante...");
			break;
		case "3":
			System.out.println("Asignar Profesor...");
			break;
		case "4":
			System.out.println("Asignar pago a Profesor...");
			break;
		case "5":
			System.out.println("Cerrando sesion");
			return false;
		}
		return true;
	}
}