package service;

import models.Employees;
import repositories.EmployeesRepository;
import repositories.FileUserRepository;

import java.util.Optional;

public class LoginService {
    private FileUserRepository userRepository;
    private Employees User;
    private String Type;
    private LoginService() {
        userRepository = new FileUserRepository();
    }

    public boolean login(String username, String password) {
        //FileUserRepository userRepository = FileUserRepository.getInstance();
        Optional<Employees> u = userRepository.findUserByUsername(username);

        if(u.isPresent()) {
            Employees usr = u.get();
            System.out.println(usr.getPassword());
            if (usr.getPassword().equals(password)) {
                //setting current user Id
                this.User = usr;
                return true;
            }
        }

        return false;
    }

    public static LoginService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Employees getUser() {
        return User;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    private static class SingletonHolder {
        private static LoginService INSTANCE = new LoginService();
    }
}