/**
 * 
 */
package checkpoint;

/**
 * @author MAAG
 *
 */
public class Professor extends User {

	public Professor(String user, String pass) {
		super(user, pass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printMenu() {
		System.out.println("Bienvenido Profesor");
		System.out.println("Seleccione su opcion");
		System.out.println("1. Ingresar notas");
		System.out.println("2. Cobrar Pago");
		System.out.println("3. Historial de Pagos");
		System.out.println("4. Cerrar Sesion");
	}

	@Override
	public boolean doAction(String sel) {
		switch(sel) {
		case "1":
			System.out.println("Ingresando Notas...");
			break;
		case "2":
			System.out.println("Cobrando Pago...");
			break;
		case "3":
			System.out.println("Revisando historial de Pago...");
			break;
		case "4":
			System.out.println("Cerrando sesion");
			return false;
		}
		return true;
	}

}