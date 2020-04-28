package service;

import models.Employees;
import models.Pacient;
import repositories.EmployeesRepository;
import repositories.FilePacientRepository;
import repositories.FileUserRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;

public class ManagerService {
    private FileUserRepository empRep = new FileUserRepository();
    private FilePacientRepository pacientRepository = new FilePacientRepository();

    public Integer calculateMonthlySalary() {
        int n = empRep.getEmployees().size();
        int s = empRep.getEmployees().stream().mapToInt(Employees::getSalary).sum();
        return s;
    }

    public void getMonthlySalary() {
        Integer sum = this.calculateMonthlySalary();
        System.out.println("Monthly expenses on employees: " + sum);
    }
    public void getListOfDoctors() {
        ArrayList<Employees> empList =  empRep.getEmployees();
        empList.forEach(emp -> System.out.println("Username: " + emp.getUsername() + " ,salary: " + emp.getSalary() + " ,type: " + emp.getType()));
    }
    public Integer[] addToArray(Integer [] v, Integer newInt, Integer n) {
       // Integer n = v.length;
        // create a new array of size n+1
        Integer[] V = new Integer[n + 1];

        range(0, n).forEach(i -> V[i] = v[i]);

        V[n] = newInt;

        return V;
    }
    public Integer[] getDoctorsIds() {
        Integer ids [] ={};
        Integer[] finalId;
        Integer n = 0;
        ArrayList<Employees> empList =  empRep.getEmployees();

       for(Integer i = 0; i< empList.size();i++) {
           if(empList.get(i).getType().equals("Doctor")) {
               ids = addToArray(ids, empList.get(i).getId(), n);
               n++;
           }
       }
        return  ids;
    }
    public void getDoctorsAndPacients() {
        Integer[] doctorsIds = getDoctorsIds();

        for(Integer i = 0;i < doctorsIds.length; i ++) {
            Integer id = doctorsIds[i];
            System.out.println("\n");
            System.out.println("Doctor " + id + "\n");
            Optional<ArrayList<Pacient>> pacients  = pacientRepository.findPacientsByDoctorId(id);
            if(pacients.isPresent()) {
                pacients.get().forEach(p -> System.out.println("id: " + p.getId() + ", name: " + p.getName()));
            }
        }
    }
    public static ManagerService getInstance() {
        return ManagerService.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static ManagerService INSTANCE = new ManagerService();
    }
}
