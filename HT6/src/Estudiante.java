public class Estudiante {
    String Name, Phone, Email, PostalZip, County;

    public Estudiante(String name, String phone, String email, String county, String postalZip) {
        Name = name;
        Phone = phone;
        Email = email;
        County = county;
        PostalZip = postalZip;
    }

    @Override
    public String toString() {
        return "Estudiante [Name=" + Name + ", Phone=" + Phone + ", Email=" + Email + ", PostalZip=" + PostalZip
                + ", County=" + County + "]";
    }

    public String getEmail() {
        return Email;
    }

    public String getCounty() {
        return County;
    }

    public void put(String calcularHash, Estudiante estudiante) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'put'");
    }
}
