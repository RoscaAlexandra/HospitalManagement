package models;

import java.util.Date;

public class Appointment {
    private Integer id;
    private Date date;
    private Employees doctor;

    public Appointment() {

    }
    public Appointment(Integer id, Date date, Employees doctor) {
        this.id = id;
        this.date = date;
        this.doctor = doctor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setDate(Date d) {
        this.date = d;
    }
    public Date getDate() {
        return date;
    }

    public Employees getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
