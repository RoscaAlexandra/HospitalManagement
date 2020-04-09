package service;

import models.Employees;
import repositories.EmployeesRepository;

import java.util.Optional;

public class LoginService {

    private LoginService() {

    }

    public boolean login(Employees employee) {
        EmployeesRepository userRepository = EmployeesRepository.getInstance();
        Optional<Employees> u = userRepository.findUserByUsername(employee.getUsername());

        if(u.isPresent()) {
            Employees usr = u.get();
            if (usr.getPassword().equals(employee.getPassword())) {
                return true;
            }
        }

        return false;
    }

    public static LoginService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static LoginService INSTANCE = new LoginService();
    }
}