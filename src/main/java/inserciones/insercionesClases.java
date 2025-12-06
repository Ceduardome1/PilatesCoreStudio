package inserciones;

import actores.Administrador;
import actores.Instructor;
import dominio.Clase;
import dominio.Horario;
import dominio.Sala;
import dominio.Direccion;
import dominio.Sucursal;
import bd.BD;

import java.time.LocalDate;
import java.time.LocalTime;

public class insercionesClases {

    public static void main(String[] args) {

        BD bd = new BD();
        bd.crearConexion();
        
        try {

            Sucursal suc = new Sucursal(
                    1,
                    new Direccion(6700, (short) 25, "Roma Norte", "Puebla")
            );

            Sala sala1 = new Sala(1, suc, 10);
            Sala sala2 = new Sala(2, suc, 12);

            Instructor ins = new Instructor(
                    1, "Ana", "Morales", "Cruz", suc
            );

            Administrador admin = new Administrador(
                    100, "Admin", "General", "Sistema", suc
            );

            LocalDate fecha1 = LocalDate.now().plusDays(1);
            LocalDate fecha2 = LocalDate.now().plusDays(1);

            Horario h1 = new Horario(fecha1, LocalTime.of(9, 0));
            Horario h2 = new Horario(fecha2, LocalTime.of(10, 0));

            Clase clase1 = new Clase(1, sala1, ins, h1, admin);
            Clase clase2 = new Clase(2, sala2, ins, h2, admin);

            bd.getConexion().store(clase1);
            bd.getConexion().store(clase2);
            bd.confirmarTransaccion();

            System.out.println("CLASES INSERTADAS");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.cerrarConexion();
        }
    }
    
}
