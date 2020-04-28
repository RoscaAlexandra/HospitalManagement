package models;

import java.util.Optional;

import static java.util.stream.IntStream.range;

public class ChronicPacient extends Pacient {
   // private Appointment[] appointments;
    private Integer doctorId;

    public ChronicPacient(Integer id, String name, Integer doctorId) {
        super(id,name, "Chronic");
       // this.appointments = appointments;
        this.doctorId = doctorId;
    }

    //public Appointment[] getAppointments() {
    //    return this.appointments;
   // }

//    public void addAppointment(Appointment appointment) {
//        int n = this.appointments.length;
//        // create a new array of size n+1
//        Appointment[] V = new Appointment[n + 1];
//
//        range(0, n).forEach(i -> V[i] = this.appointments[i]);
//
//        V[n] = appointment;
//
//        this.appointments = V;
//    }

    public Optional<Integer> getDoctorId() {
        return Optional.ofNullable(this.doctorId);
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }
}
