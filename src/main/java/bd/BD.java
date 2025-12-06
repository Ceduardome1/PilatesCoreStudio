package bd;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class BD {

    private static final String ARCHIVO_BD = "pilates.db4o";

    private ObjectContainer conexion = null;

    public synchronized ObjectContainer crearConexion() {
        try {
            if (conexion == null || conexion.ext().isClosed()) {
                conexion = Db4oEmbedded.openFile(ARCHIVO_BD);
            }
            return conexion;

        } catch (Exception e) {
            throw new RuntimeException("No se pudo abrir la base DB4O", e);
        }
    }

    public synchronized void cerrarConexion() {
        try {
            if (conexion != null && !conexion.ext().isClosed()) {
                conexion.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("No se pudo cerrar la base DB4O", e);
        }
    }

    public ObjectContainer getConexion() {
        return conexion;
    }

    public synchronized void confirmarTransaccion() {
        if (conexion != null && !conexion.ext().isClosed()) {
            conexion.commit();
        }
    }

    public synchronized void deshacerTransaccion() {
        if (conexion != null && !conexion.ext().isClosed()) {
            conexion.rollback();
        }
    }
    
}