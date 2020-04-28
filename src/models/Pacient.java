package models;

import java.util.Objects;
import java.util.Optional;

public class Pacient {
    private Integer id;
    private String name;
    private String Type;
    public Pacient(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.Type = type;
    }
    public Pacient(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Pacient(String type) {
        this.Type = type;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pacient pacient = (Pacient) o;
        return name.equals(pacient.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
    public Optional<Integer> getDoctorId() {return Optional.empty(); }
}
