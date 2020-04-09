package models;

public class Employees {
    private String name;
    private Integer salary;

    private String username;
    private String password;

    public Employees(String name, Integer salary, String username, String password) {
        this.name = name;
        this.salary = salary;
        this.username = username;
        this.password = password;
    }

    public Employees() {

    }


    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
