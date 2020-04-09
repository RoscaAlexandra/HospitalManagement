package models;

public class Prescription {
    private String [] Drugs;
    private String type;

    public Prescription(String[] drugs, String type) {
        Drugs = drugs;
        this.type = type;
    }
}
