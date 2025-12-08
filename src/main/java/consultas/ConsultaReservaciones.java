package consultas;

import dominio.Reservacion;
import bd.BD;

import java.util.List;

public class ConsultaReservaciones {

	public static void main( String args[] ) {

		BD bd = new BD();
		bd.crearConexion();

		List<Reservacion> lista = bd.getConexion().query(Reservacion.class);

		System.out.println("==================== RESERVACIONES ====================");
		System.out.printf("%-5s %-10s %-10s %-10s %-15s %-10s%n",
				"ID", "ID Cliente", "Cama", "ID Clase", "Fecha Clase", "Instructor");

		for (Reservacion r : lista) {
			System.out.printf("%-5d %-10d %-10d %-10d %-15s %-10s%n",
					r.getIdReservacion(),
					r.getCliente().getIdCliente(),
					r.getIdCamaAsignada(),
					r.getClase().getIdClase(),
					r.getClase().getHorario().mostrarFecha(), 
					r.getClase().getInstructor().getNombre() 
					);
		}

		bd.cerrarConexion();
	}

}