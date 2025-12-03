package dominio;
import vistas.*;

public enum Operaciones {
	
    Clases( VistaClases.class, VistaSelectorClases.class ),
    Clientes( null, VistaSelectorClientes.class ),
    Reservaciones( VistaReservaciones.class, null );

    private final Class<?> interfazGestion;
    private final Class<?> interfazConsulta;

    Operaciones( Class<?> gestion, Class<?> consulta ) {
        this.interfazGestion = gestion;
        this.interfazConsulta = consulta;
    }

	public Class<?> getUIGestion() {
		return interfazGestion;
	}

	public Class<?> getUIConsulta() {
		return interfazConsulta;
	}
    
}