package repositories;

import exceptions.InexistentUserFileException;
import models.Employees;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

public class FileUserRepository implements EmployeesRepository {

    private final String file = "USERS";
    private File log = new File("USERS");

    public void addUser(Employees user, String type) {
//        try (PrintStream out = new PrintStream(file)) {
//            out.println(user.getId()+","+user.getUsername()+","+user.getPassword());
        try {
            if (!log.exists()) {
                System.out.println("We had to make a new file.");
                log.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(log, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(user.getId() + "," + user.getUsername() + "," + user.getPassword() + "," + type + user.getSalary() + "\n");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Employees> findUserByUsername(String username) {
        Path path = Paths.get(file);
        Employees user = null;

        try {
            if (!Files.exists(path)) {
                throw new InexistentUserFileException();
            }
            var list = Files.readAllLines(path);
            for (String u : list) {
                String[] attr = u.split(","); // 1,john,12345
                if (attr[1].equals(username)) {
                    user = new Employees(attr[3]);
                    user.setId(Integer.parseInt(attr[0]));
                    user.setUsername(attr[1]);
                    user.setPassword(attr[2]);
                    //user.setType(attr[3]);
                    user.setSalary(Integer.parseInt(attr[4]));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }

    public ArrayList<Employees> getEmployees() {
        Path path = Paths.get(file);
        Employees user = null;
        ArrayList<Employees> aEmp = new ArrayList<>();
        try {
            if (!Files.exists(path)) {
                throw new InexistentUserFileException();
            }
            var list = Files.readAllLines(path);
            for (String u : list) {
                String[] attr = u.split(","); // 1,john,1234,Manager,27283
                user = new Employees(attr[3]);
                user.setId(Integer.parseInt(attr[0]));
                user.setUsername(attr[1]);
                user.setPassword(attr[2]);
                user.setSalary(Integer.parseInt(attr[4]));
                aEmp.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aEmp;
    }
}