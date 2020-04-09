package repositories;

import models.Doctor;
import models.Employees;
import models.Manager;

import java.util.ArrayList;
import java.util.Optional;

public class EmployeesRepository {
    private ArrayList<Employees> employees = new ArrayList<>();
    public ArrayList<Employees> getEmployees() {
        return employees;
    }
    public EmployeesRepository()
    {
        Manager manager = Manager.getInstance();
        manager.setName("Manager name");
        manager.setSalary(10000);
        manager.setPassword("Pass1");
        manager.setUsername("UsernameManager");


        Doctor a1 = new Doctor("Doctor1",2000, "UserName1", "Pass1");
        Doctor a2 = new Doctor("Doctor2",3000, "UserName2", "Pass1");

        employees.add(a1);
        employees.add(a2);
        employees.add(manager);

    }
    public static EmployeesRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static EmployeesRepository INSTANCE = new EmployeesRepository();
    }
    public Optional<Employees> findUserByUsername(String username) {
        for (Employees u : employees) {
            if (u != null) {
                if (username.equals(u.getUsername())) {
                    return Optional.of(u);
                }
            }
        }

        return Optional.empty();
    }
}
