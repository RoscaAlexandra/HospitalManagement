package models;

import java.util.Date;
import java.util.GregorianCalendar;

public class MonthlyAppointment extends Appointment{
    private ChronicPacient chronicPacient;

    public MonthlyAppointment(Integer id, Date date, Doctor doctor, ChronicPacient chronicPacient) {
        super(id, date, doctor);
        this.chronicPacient = chronicPacient;
    }
}
