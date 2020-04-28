package repositories;
import models.Pacient;

import java.util.Optional;

public interface PacientRepository {

    void addPacient(Pacient pacient);

    static PacientRepository build(Type type) {
        switch (type) {
            case FILE: return new FilePacientRepository();
            case ARRAY:return new ArrayPacientRepository();
        }

        throw new RuntimeException("No such type");
    }

    enum Type {
        FILE, ARRAY
    }
}