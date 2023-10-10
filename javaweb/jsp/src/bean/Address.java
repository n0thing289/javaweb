package bean;

public class Address {
    private String city;
    private String street;
    private String email;

    public Address() {
    }

    public Address(String city, String street, String email) {
        this.city = city;
        this.street = street;
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
