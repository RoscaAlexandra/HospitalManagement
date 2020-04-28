package repositories;

import models.Appointment;

public interface AppointmentRepository {
    void addAppointment(Appointment appointment, String type);

    static AppointmentRepository build(PacientRepository.Type type) {
        switch (type) {
            case FILE: return new FileAppointmentRepository();
            case ARRAY:return new ArrayAppointmentRepository();
        }

        throw new RuntimeException("No such type");
    }

    enum Type {
        FILE, ARRAY
    }
}
