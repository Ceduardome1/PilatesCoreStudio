package inserciones;

import bd.BD;
import dominio.*;
import utilerias.Rutinas;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import actores.Cliente;
import actores.Recepcionista;

public class InsercionesAleatoriasReservaciones {
	
	private static final int nReservaciones = 5;
	private static final int nClases = 10;
	private static final int diasLimite = 30;
	
	public static void main(String[] args) {

		BD bd = new BD();
		try {
			System.out.println("Insertando Reservaciones...");

			List<Clase> clases = bd.getConexion().query(Clase.class);
			LinkedList< Sala > salasDisponibles = new LinkedList< Sala >(bd.getConexion().query(Sala.class) );
				if ( clases.isEmpty() ) {
					System.out.println("No hay clientes o clases, no se pueden crear reservaciones.");
					return;
				}

			Recepcionista recep = new Recepcionista(
				900,
				"Recepcionista",
				"General",
				"Central",
				clases.get(0).getSala().getSucursal()
			);

			for (int i = 1; i <= nReservaciones; i++) {

					if( salasDisponibles.isEmpty() ) {
						bd.deshacerTransaccion();
						return;
					}
				
					String nombre = Rutinas.NomAleatorio( 
						Rutinas.NAleatorio(1, 3), 2, Rutinas.Volado() 
					);
					
					
					SaldoClases saldo = new SaldoClases(
							Rutinas.NAleatorio( 0, nClases + 1 ),
		
							LocalDate.now().plusDays(
									Rutinas.NAleatorio(1, diasLimite+1 )
							)
					);
					
					String cadtel = "667";
						for( int itel=0; itel<7; itel++ ) {
							cadtel += ""+Rutinas.NAleatorio(0, 10);
						}
					long tel = Long.parseLong( cadtel );
					
					Cliente cliente = new Cliente(
						i,//Rutinas.NAleatorio(100, 1000),
						tel,
						nombre.split(" ")[0],
						nombre.split(" ")[1],
						nombre.split(" ")[2],
						nombre.split(" ")[0]+"@mail.com",
						saldo
					);
					
				Clase cl = Rutinas.DatoAleatorio( clases.toArray(new Clase[0] ));
				int aleatorio = Rutinas.NAleatorio( 0, salasDisponibles.size() );
				Sala sala = salasDisponibles.remove(aleatorio);
				Integer idCama = sala.asignarCama();
				
					if( sala.estaDisponible() )
						salasDisponibles.add( aleatorio, sala );
				
				Reservacion r = new Reservacion( i, idCama, cl, cliente, recep );
					
				bd.getConexion().store(r);
				System.out.println("Reservación insertada ID: " + i);
			}

			bd.confirmarTransaccion();
			System.out.println("RESERVACIONES INSERTADAS.\n");

		} catch (Exception e) {
			bd.deshacerTransaccion();
			e.printStackTrace();
		} finally {
			bd.cerrarConexion();
		}
	}
}
