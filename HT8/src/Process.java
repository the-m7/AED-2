public class Process {
    String Name;
    String User;
    Integer Ni;
    Integer Pr;

    public Process(String name, String user, Integer ni) {
        Name = name;
        User = user;
        Ni = ni;
        Pr = 120 + ni;
    }

    public String getName() {
        return Name;
    }

    public String getUser() {
        return User;
    }

    public Integer getNi() {
        return Ni;
    }

    public Integer getPr() {
        return Pr;
    }

    @Override
    public String toString() {
        return "Process [Name=" + Name + ", User=" + User + ", Ni=" + Ni + ", Pr=" + Pr + "]";
    }

    
}
