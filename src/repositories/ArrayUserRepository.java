package repositories;

import models.Employees;

import java.util.Optional;

public class ArrayUserRepository implements EmployeesRepository {
    @Override
    public void addUser(Employees employees, String type) {

    }

    @Override
    public Optional<Employees> findUserByUsername(String username) {
        return Optional.empty();
    }
}
