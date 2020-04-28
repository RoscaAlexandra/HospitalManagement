package models;

public class Employees {
    private Integer id;
    private String name;
    private Integer salary;
    private String Type;

    private String username;
    private String password;

    public Employees(Integer id, String name, Integer salary, String username, String password, String type) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.username = username;
        this.password = password;
        this.Type = type;
    }

    public Employees(String type) {
        this.Type = type;
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
}
