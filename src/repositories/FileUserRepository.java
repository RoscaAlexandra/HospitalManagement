package repositories;

import exceptions.InexistentUserFileException;
import models.Employees;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FileUserRepository extends EmployeesRepository {

    private final String file = "USERS";

    public void addUser(Employees user) {
        try (PrintStream out = new PrintStream(file)) {
            out.println(user.getId()+","+user.getUsername()+","+user.getPassword());
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
                String [] attr = u.split(","); // 1,john,12345
                if (attr[1].equals(username)) {
                    user = new Employees();
                    user.setId(Integer.parseInt(attr[0]));
                    user.setUsername(attr[1]);
                    user.setPassword(attr[2]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }
}