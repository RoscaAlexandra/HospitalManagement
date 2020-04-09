package models;

import java.util.Date;

public class MonthlyAppointment extends Appointment{
    private ChronicPacient chronicPacient;

    public MonthlyAppointment(Date date, Doctor doctor, ChronicPacient chronicPacient) {
        super(date, doctor);
        this.chronicPacient = chronicPacient;
    }
}
