package models;

import java.util.Date;

public class Appointment {
    private Date date;
    private Doctor doctor;


    public Appointment(Date date, Doctor doctor) {
        this.date = date;
        this.doctor = doctor;
    }
}
