package inserciones;

import java.time.LocalDate;

import actores.Cliente;
import dominio.SaldoClases;
import bd.BD;

public class insercionesClientes {

    public static void main(String[] args) {

        BD bd = new BD();
        bd.crearConexion();
        
        try {

        	LocalDate vigencia1 = java.time.LocalDate.now().plusDays(30);
        	LocalDate vigencia2 = java.time.LocalDate.now().plusDays(20);

            Cliente c1 = new Cliente(
                    1,
                    5522334455L,
                    "Linda",
                    "Garcia",
                    "Flores",
                    "linda@gmail.com",
                    new SaldoClases(10, vigencia1)
            );

            Cliente c2 = new Cliente(
                    2,
                    5511223344L,
                    "Marco",
                    "Lopez",
                    "Ramirez",
                    "marco@gmail.com",
                    new SaldoClases(5, vigencia2)
            );

            bd.getConexion().store(c1);
            bd.getConexion().store(c2);
            bd.confirmarTransaccion();

            System.out.println("CLIENTES INSERTADOS");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.cerrarConexion();
        }
    }
}
