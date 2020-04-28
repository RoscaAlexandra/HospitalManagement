package com.proiect.proiect;
import models.ChronicPacient;
import models.Pacient;
import repositories.FilePacientRepository;
import service.ApplicationService;

import java.io.IOException;
import java.text.ParseException;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        ApplicationService applicationService = new ApplicationService();
        applicationService.RunApp();
//        FilePacientRepository p = new FilePacientRepository();
//        p.addPacient(new Pacient(1,"Pacient1","OneTime"));
//        p.addPacient(new Pacient(2,"Pacient2","OneTime"));
//        p.addPacient(new ChronicPacient(3,"Pacient3", 2));
//        p.addPacient(new ChronicPacient(4,"Pacient4", 2));
//        p.addPacient(new ChronicPacient(5,"Pacient5", 1));
//        p.addPacient(new ChronicPacient(6,"Pacient6", 1));
    }

}
