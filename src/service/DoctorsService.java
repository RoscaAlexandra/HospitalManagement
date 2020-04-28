package service;

import models.Appointment;
import models.Employees;
import models.Pacient;
import repositories.FileAppointmentRepository;
import repositories.FilePacientRepository;
import repositories.FileUserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class DoctorsService {
    private FileUserRepository employeesRepository;
    private FileAppointmentRepository appointmentRepository;
    private FilePacientRepository pacientRepository;
    private LoginService service;

    private DoctorsService() {
        employeesRepository = new FileUserRepository();
        appointmentRepository = new FileAppointmentRepository();
        pacientRepository = new FilePacientRepository();
        service = LoginService.getInstance();
    }
    public void getTodayAppointments() {
        Optional<ArrayList<Appointment>> aApp = appointmentRepository.getAppointmentsByDate(Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
        if(aApp.isPresent()) {
            aApp.get().forEach(a -> System.out.println(String.valueOf(a.getId()) + " " + a.getDate()));
            if(aApp.get().size() == 0) System.out.println("No appointments for today!");
        }
    }
    public void getNumberOfAppointmentsNextMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        Date endDate = cal.getTime();
        Optional<ArrayList<Appointment>> aApp = appointmentRepository.getAppointmentsByDate(Calendar.getInstance().getTime(), endDate);

        if(aApp.isPresent()) {
          //  aApp.get().forEach(a -> System.out.println(String.valueOf(a.getId()) + " " + a.getDate()));
            System.out.println("Next month you have " + aApp.get().size() + " appointments");
        }

    }
    public void getMyPacients() {
        Optional<ArrayList<Pacient>> pacients = pacientRepository.findPacientsByDoctorId(null);
        if(pacients.isPresent()) pacients.get().forEach(p -> System.out.println("id: " + p.getId() + ", name: " + p.getName()));
    }
    public void addNewAppointment() throws IOException, ParseException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));

        Employees user = service.getUser();
        //TODO verify inputs

        System.out.println("Appointment id: ");
        String id = obj.readLine();

        System.out.println("Appointment date(dd-MM-yyyy): ");
        String date = obj.readLine();

        System.out.println("Appointment type(OneTime/Monthly): ");
        String type = obj.readLine();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date goodDate = formatter.parse(date);

        Appointment appointment = new Appointment(Integer.parseInt(id), goodDate, user);
        appointmentRepository.addAppointment(appointment, type);
    }
    public static DoctorsService getInstance() {
        return DoctorsService.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static DoctorsService INSTANCE = new DoctorsService();
    }
}
