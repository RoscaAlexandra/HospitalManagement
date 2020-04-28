package models;

import java.util.stream.IntStream;

import static java.util.stream.IntStream.*;

public class Doctor extends Employees {
    private Pacient[] aPacient;

    public Doctor(Integer id, String name, Integer salary, String username, String password) {
        super(id, name, salary, username, password, "Doctor");
      //  this.aPacient = aPacient;
    }
    public void addPacient(Pacient pacient) {
        int n = this.aPacient.length;
        // create a new array of size n+1
        Pacient[] V = new Pacient[n + 1];

        range(0, n).forEach(i -> V[i] = this.aPacient[i]);

        V[n] = pacient;

        this.aPacient = V;
    }
    public Pacient[] listOfPacients() {
        return this.aPacient;
    }
}
