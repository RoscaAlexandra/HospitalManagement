package models;

import java.util.Objects;

public class Pacient {
    private String name;

    public Pacient(String name) {
        this.name = name;
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
}
