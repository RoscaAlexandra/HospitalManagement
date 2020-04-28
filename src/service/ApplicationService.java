package service;

import models.Appointment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class ApplicationService {
    private LoginService service = LoginService.getInstance();
    private DoctorsService doctorsService = DoctorsService.getInstance();
    private ManagerService managerService = ManagerService.getInstance();
    private AuditService auditService = AuditService.getInstance();

    public void RunApp() throws IOException, ParseException {
        boolean loginStatus = this.login();
        if(loginStatus == false) {
            System.out.println("You can not use the app without logging in!");
        } else {
            System.out.println("You logged in as a " + service.getUser().getType());
            if(service.getUser().getType().equals( "Manager")) {
                this.openManagerMenu();
            } else if(service.getUser().getType().equals( "Doctor" )) {
                this.openDoctorMenu();
            }
        }
    }
    /***
     LOGIN
     ***/
    private boolean login() throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));

        boolean result = false;
        while(result == false) {
            System.out.println("Username:");
            String username = obj.readLine();
            System.out.println("Password:");
            String password = obj.readLine();
            result = this.service.login(username, password); //try login
            if (result == true) {
                System.out.println("Login Successful! You can use the app now!");
            } else {
                System.out.println("Login Filed! Retry?Y/N");
                String confirm = obj.readLine();
                if(confirm.equals("N")) {
                    break;
                } else if(confirm.equals("Y")) {
                    continue;
                }
            }
        }
        return result;
    }
    /************ Manager specific stuff *****/
    private void openManagerMenu() throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        String option = "0";
        while(!Objects.equals(option, "29")) {
            System.out.println("Here are your options:");
            System.out.println("1.Check list of doctors");
            System.out.println("2.Check monthly expenses on salaries");
            System.out.println("3.Check doctors and corresponding pacients");
            System.out.println("29.Exit");

            option = obj.readLine();
            if (option.equals("1")) {
                this.auditService.addAction("Get list of doctors - by "+ service.getUser().getId());
                this.managerService.getListOfDoctors();
            }
            else if(option.equals("2")){
                this.auditService.addAction("Get monthly salary - by " + service.getUser().getId());
                this.managerService.getMonthlySalary();
            }
            else if(option.equals("3")){
                this.auditService.addAction("Get list of doctors and their pacients - by " + service.getUser().getId());
                this.managerService.getDoctorsAndPacients();
            }
            else if(!Objects.equals(option, "29")){
                System.out.println("No such option!");
            }
        }
    }
    /************ Doctor specific stuff *****/
    private void openDoctorMenu() throws IOException, ParseException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        String option = "0";
        while(!Objects.equals(option, "29")) {
            System.out.println("Here are your options:");
            System.out.println("1.Check appointments made for today");
            System.out.println("2.Check appointments made for next month");
            System.out.println("3.Check my chronic pacients");
            System.out.println("4.Add new appointment");
            System.out.println("29.Exit");

            option = obj.readLine();
            if (option.equals("1")) {
                this.auditService.addAction("Get today appointments - by " + service.getUser().getId());
                this.doctorsService.getTodayAppointments();
            }
            else if(option.equals("2")){
                this.auditService.addAction("Get number of next month appointments - by " + service.getUser().getId());
                this.doctorsService.getNumberOfAppointmentsNextMonth();
            }
            else if(option.equals("3")){
                this.auditService.addAction("Get list of chronic pacients - by " + service.getUser().getId());
                this.doctorsService.getMyPacients();
            }
            else if(option.equals("4")){
                this.auditService.addAction("Added new appointment - by " + service.getUser().getId());
                this.doctorsService.addNewAppointment();
            }
            else if(!Objects.equals(option, "29")){
                System.out.println("No such option!");
            }
        }
    }
}
