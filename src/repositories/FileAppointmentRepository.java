package repositories;

import exceptions.InexistentUserFileException;
import models.Appointment;
import models.Employees;
import service.LoginService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class FileAppointmentRepository implements AppointmentRepository {
    private final String file = "APPOINTMENTS";
    private File log = new File("APPOINTMENTS");

    @Override
    public void addAppointment(Appointment appointment, String type) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        try{
            if(!log.exists()){
                System.out.println("We had to make a new file.");
                log.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(log, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(appointment.getId()+","+formatter.format(appointment.getDate())+","+appointment.getDoctor().getId()+","+type+"\n");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Optional<ArrayList<Appointment>> getAppointmentsByDate(Date date, Date endDate) {
        Path path = Paths.get(file);
        Appointment app = null;
        ArrayList<Appointment> aApp = new ArrayList<Appointment>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        LoginService L = LoginService.getInstance();
        Integer UserId = L.getUser().getId();

        try {
            if (!Files.exists(path)) {
                throw new InexistentUserFileException();
            }
            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(","); // id,date,doctorId

                if ((formatter.parse(attr[1]).compareTo(date ) >= 0 ) && (formatter.parse(attr[1]).compareTo(endDate ) <= 0 ) && (Integer.parseInt(attr[2]) == UserId)) {
                    app = new Appointment();
                    app.setId(Integer.parseInt(attr[0]));
                    app.setDate(formatter.parse(attr[1]));
                  //  app.setPassword(attr[2]);
                    //set doctor also - add name and salary for doctor -  find by id
                    aApp.add(app);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(aApp);
    }
}
