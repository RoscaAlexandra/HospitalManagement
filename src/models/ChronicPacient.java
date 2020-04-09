package models;

import static java.util.stream.IntStream.range;

public class ChronicPacient extends Pacient {
    private Appointment[] appointments;

    public ChronicPacient(String name, Appointment[] appointments) {
        super(name);
        this.appointments = appointments;
    }

    public Appointment[] getAppointments() {
        return this.appointments;
    }

    public void addAppointment(Appointment appointment) {
        int n = this.appointments.length;
        // create a new array of size n+1
        Appointment[] V = new Appointment[n + 1];

        range(0, n).forEach(i -> V[i] = this.appointments[i]);

        V[n] = appointment;

        this.appointments = V;
    }
}
