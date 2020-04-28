package models;


public  class  Manager extends Employees{

    private Manager() {
        super("Manager");
        // super(name, salary);
    }

    public static Manager getInstance() {
        return SingletonHolder1.INSTANCE;
    }

     private static class SingletonHolder1{
        private static Manager INSTANCE = new Manager();
    }
}