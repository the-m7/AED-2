public class Estudiante {
    String Name, Phone, Email, PostalZip, County;

    public Estudiante(String name, String phone, String email, String county, String postalZip) {
        this.Name = name;
        this.Phone = phone;
        this.Email = email;
        this.County = county;
        this.PostalZip = postalZip;
    }

    @Override
    public String toString() {
        return "Estudiante [Name=" + Name + ", Phone=" + Phone + ", Email=" + Email + ", PostalZip=" + PostalZip
                + ", County=" + County + "]";
    }

    public String getEmail() {
        return Email;
    }

}
