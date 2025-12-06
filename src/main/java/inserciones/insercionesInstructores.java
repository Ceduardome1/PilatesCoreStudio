package inserciones;

import actores.Instructor;
import dominio.Direccion;
import dominio.Sucursal;
import bd.BD;

public class insercionesInstructores{

    public static void main(String[] args) {

        BD bd = new BD();
        bd.crearConexion();
        
        try {

            Sucursal suc = new Sucursal(
                    1,
                    new Direccion(6700,(short) 25, "Roma Norte", "Puebla")
            );

            Instructor i1 = new Instructor(1, "Ana", "Morales", "Cruz", suc);
            Instructor i2 = new Instructor(2, "Pedro", "Santos", "Luna", suc);

            bd.getConexion().store(i1);
            bd.getConexion().store(i2);
            bd.confirmarTransaccion();

            System.out.println("INSTRUCTORES INSERTADOS");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.cerrarConexion();
        }
    }
}
