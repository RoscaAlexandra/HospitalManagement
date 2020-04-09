package com.proiect.proiect;

import models.Doctor;
import service.MonthlySalary;

public class Main {
    public static void main(String[] args) {
        MonthlySalary m = new MonthlySalary();
        Integer monthlyExpense = m.getMonthlySalary();
        System.out.println(monthlyExpense);
    }
}
