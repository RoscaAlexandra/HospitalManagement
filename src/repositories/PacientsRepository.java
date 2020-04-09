package repositories;

import models.Appointment;
import models.ChronicPacient;
import models.Pacient;

import java.util.ArrayList;

public class PacientsRepository {
    private ArrayList<Pacient> pacients = new ArrayList<>();
    public PacientsRepository()
    {
        Pacient a1 = new Pacient("Pacient1");
        Pacient a2 = new Pacient("Pacient2");
        ChronicPacient a3 = new ChronicPacient("Chronic pacient", new Appointment[0]);

        pacients.add(a1);
        pacients.add(a2);
        pacients.add(a3);
    }
}
