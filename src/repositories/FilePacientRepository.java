package repositories;
import exceptions.InexistentUserFileException;
import models.Pacient;
import service.LoginService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

public class FilePacientRepository implements PacientRepository {
    private final String file = "PACIENTS";
    private File log = new File("PACIENTS");
    private LoginService service = LoginService.getInstance();

    @Override
    public void addPacient(Pacient pacient) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        try{
            if(!log.exists()){
                System.out.println("We had to make a new file.");
                log.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(log, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if(pacient.getType() == "OneTime")
                 bufferedWriter.write(pacient.getId()+"," + pacient.getName() + "," + pacient.getType() + "\n");
            else {//chronic
                if(pacient.getDoctorId().isPresent()) {
                    Integer id = pacient.getDoctorId().get();
                    bufferedWriter.write(pacient.getId() + "," + pacient.getName() + "," + pacient.getType() + "," + id + "\n");
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Optional<ArrayList<Pacient>> findPacientsByDoctorId(Integer id) {
        Integer doctorId;
        if(id == null) {
            doctorId = service.getUser().getId();
        } else {
            doctorId = id;
        }

        Path path = Paths.get(file);
        Pacient pacient = null;
        ArrayList<Pacient> aPacient = new ArrayList<Pacient>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentUserFileException();
            }
            var list = Files.readAllLines(path);
            for (String u : list) {
                String[] attr = u.split(","); // id,name,type[,doctorId]
                if(attr[2].equals( "Chronic")) {
                    if (attr[3].equals(String.valueOf(doctorId))) {
                        pacient = new Pacient(attr[2]);
                        pacient.setId(Integer.parseInt(attr[0]));
                        pacient.setName(attr[1]);
                        aPacient.add(pacient);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(aPacient);
    }

}
