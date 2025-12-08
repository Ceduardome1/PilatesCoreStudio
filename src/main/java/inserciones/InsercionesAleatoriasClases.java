package inserciones;

import actores.Administrador;
import actores.Instructor;
import dominio.Clase;
import dominio.Horario;
import dominio.ReglasNegocio;
import dominio.Sala;
import dominio.Direccion;
import dominio.Sucursal;
import bd.BD;
import utilerias.Rutinas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public class InsercionesAleatoriasClases {

	private static final int nSalas = 5, totalClases = 10, diasMaxClase = 30;
	
	
	public static void main(String[] args) {

		BD bd = new BD();

		try {
			System.out.println("\n===============================");
			System.out.println("   INSERTANDO CLASES ALEATORIAS");
			System.out.println("===============================\n");

			Sucursal suc = new Sucursal(
				1,
				new Direccion(6700, (short)25, "Roma Norte", "CDMX")
			);

			LinkedList<Sala> salas = new LinkedList<Sala>();
				for( int i=0; i<nSalas; i++ ) {
					int camas = ReglasNegocio.getCapacidadCamasSala();//Rutinas.NAleatorio(0, ReglasNegocio.getCapacidadCamasSala() );
					salas.add( new Sala( i, suc, camas) );
				}

			Administrador admin = new Administrador(
				1, "Admin", "Central", "Sistema", suc
			);

			for (int i = 0; i < totalClases; i++) {

				String nombreInstructor = Rutinas.NomAleatorio( 
					Rutinas.NAleatorio(1, 3), 2, Rutinas.Volado() 
				);

				Instructor instructor = new Instructor(
					i,//Rutinas.NAleatorio(100, 1000),
					nombreInstructor.split(" ")[0],
					nombreInstructor.split(" ")[1],
					nombreInstructor.split(" ")[2],
					suc
				);

				Sala sala = Rutinas.DatoAleatorio(salas.toArray(new Sala[0]));

				LocalDate fecha = LocalDate.now().plusDays(
					Rutinas.NAleatorio(1, diasMaxClase+1 )
				);

				int hora = Rutinas.NAleatorio(7, 18);
				LocalTime time = LocalTime.of(hora, 0);

				Horario horario = new Horario(
					fecha,
					time
				);

				Clase clase = new Clase(
						i,
						sala,
						instructor,
						horario,
						admin
						);

				bd.getConexion().store(clase);
				System.out.println("Clase aleatoria insertada ID: " + i);
			}

			bd.confirmarTransaccion();

			System.out.println("\nTODAS LAS CLASES ALEATORIAS FUERON INSERTADAS\n");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bd.cerrarConexion();
		}
	}
}
