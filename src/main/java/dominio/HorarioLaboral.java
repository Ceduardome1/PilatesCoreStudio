package dominio;

import java.time.DayOfWeek;
import java.time.LocalTime;

public enum HorarioLaboral {

    LUNES( LocalTime.of(9, 0), LocalTime.of(19, 0) ), 
    MARTES( LocalTime.of(9, 0), LocalTime.of(19, 0) ),
    MIERCOLES( LocalTime.of(9, 0), LocalTime.of(19, 0) ),
    JUEVES( LocalTime.of(9, 0), LocalTime.of(19, 0) ),
    VIERNES( LocalTime.of(9, 0), LocalTime.of(19, 0) ),
    SABADO( LocalTime.of(10, 0), LocalTime.of(15, 0) ),
	DOMINGO( null, null );
	
    private final LocalTime inicio;
    private final LocalTime fin;

    HorarioLaboral(LocalTime inicio, LocalTime fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public LocalTime getInicio() { return inicio; }
    public LocalTime getFin()    { return fin; }

    public static boolean esLaboral( DayOfWeek dia ) {
        return dia != DayOfWeek.SUNDAY;
    }

    public static HorarioLaboral getHorarioLaboral( DayOfWeek dia ) {
    	
    	switch (dia) {
			case MONDAY:
				return LUNES;
			case TUESDAY:
				return MARTES;
			case WEDNESDAY:
				return MIERCOLES;
			case THURSDAY:
				return JUEVES;
			case FRIDAY:
				return VIERNES;
			case SATURDAY:
				return SABADO;
			default:
				return null;
    	}
    	
    }
    
}
