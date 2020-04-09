package service;

import models.Employees;
import repositories.EmployeesRepository;

public class MonthlySalary {
    private Integer monthlySalary;
    public void calculateMonthlySalary() {
        EmployeesRepository empRep = EmployeesRepository.getInstance();

        int n = empRep.getEmployees().size();
        int s = empRep.getEmployees().stream().mapToInt(Employees::getSalary).sum();
        this.monthlySalary = s;
    }

    public Integer getMonthlySalary() {
        this.calculateMonthlySalary();
        return monthlySalary;
    }
}
