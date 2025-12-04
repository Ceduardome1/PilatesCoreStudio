package interfaces;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.TimeVetoPolicy;

import dominio.HorarioLaboral;
import dominio.ReglasNegocio;

public class PoliticaHoras implements TimeVetoPolicy {

    private DatePicker datePicker;

    public PoliticaHoras(DatePicker datePicker, TimePicker tp ) {
        this.datePicker = datePicker;
        
        datePicker.addDateChangeListener((dce) -> {
        	tp.setTime(null);
        });
        
        int inc = ReglasNegocio.getMinDuracionClase();
        ArrayList<LocalTime> lista = new ArrayList<>();
        LocalTime t = LocalTime.MIDNIGHT;
	        while ( !t.equals(LocalTime.MIDNIGHT.minusMinutes(inc) ) ) {
	            lista.add(t);
	            t = t.plusMinutes(inc);
	        }
        TimePickerSettings s = tp.getSettings();
        s.generatePotentialMenuTimes(lista);

    }

    @Override
    public boolean isTimeAllowed( LocalTime time ) {
        LocalDate fecha = datePicker.getDate();
	        if (fecha == null) 
	            return false;
	        
	        if(time.getMinute() != 0) 
	        	return false;
	        
        DayOfWeek dia = fecha.getDayOfWeek();
        HorarioLaboral horarioLaboral = HorarioLaboral.getHorarioLaboral( dia );
        	if( horarioLaboral== null ) 
        		return false;
        
        LocalDate hoy = LocalDate.now();
        	if( hoy.isEqual( fecha )
        	&& !time.isAfter( LocalTime.now() ) )
	        			return false;
        	
        return 
                !time.isBefore( 
                	horarioLaboral.getInicio()
                )
                &&
                !time.isAfter( 
                	horarioLaboral.getFin().minusMinutes(
                			ReglasNegocio.getMinDuracionClase()
                	) 
                );
    }

}