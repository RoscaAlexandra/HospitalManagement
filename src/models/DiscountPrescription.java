package models;

public class DiscountPrescription extends Prescription{
    private Integer discount;

    public DiscountPrescription(String[] drugs, String type, Integer discount) {
        super(drugs, type);
        this.discount = discount;
    }
}
