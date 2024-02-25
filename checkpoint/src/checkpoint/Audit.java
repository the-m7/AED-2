package checkpoint;

/**
 * 
 */
public class Audit extends User {

	public Audit(String user, String pass) {
		super(user, pass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printMenu() {
		System.out.println("Bienvenido");
		System.out.println("Seleccione su opcion");
		System.out.println("1. Revisar Notas");
		System.out.println("2. Revisar Cuotas pagadas");
		System.out.println("3. Revisar pagos a docentes");
		System.out.println("4. Cerrar Sesion");
	}
	
	@Override
	public boolean doAction(String sel) {
		switch(sel) {
		case "1":
			System.out.println("Revisar Notas...");
			break;
		case "2":
			System.out.println("Revisar Cuotas pagadas...");
			break;
		case "3":
			System.out.println("Revisar pagos a docentes...");
			break;
		case "4":
			System.out.println("Cerrando sesion");
			return false;
		}
		return true;
	}
}
