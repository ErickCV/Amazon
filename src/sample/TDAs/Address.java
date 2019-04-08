package sample.TDAs;

public class Address {
    int idAddress;
    String street,
    email,
    phone,
    nameCommerce;
    City idCity;
    State idState;
    Country idCountry;
    int cp;

    public Address() {
    }

    @Override
    public String toString() {
        return street;
    }

    public Address(int idAddress, String street, String email, String phone, String nameCommerce, City idCity, State idState, Country idCountry, int cp) {
        this.idAddress = idAddress;
        this.street = street;
        this.email = email;
        this.phone = phone;
        this.nameCommerce = nameCommerce;
        this.idCity = idCity;
        this.idState = idState;
        this.idCountry = idCountry;
        this.cp = cp;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNameCommerce() {
        return nameCommerce;
    }

    public void setNameCommerce(String nameCommerce) {
        this.nameCommerce = nameCommerce;
    }

    public City getIdCity() {
        return idCity;
    }

    public void setIdCity(City idCity) {
        this.idCity = idCity;
    }

    public State getIdState() {
        return idState;
    }

    public void setIdState(State idState) {
        this.idState = idState;
    }

    public Country getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Country idCountry) {
        this.idCountry = idCountry;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }
}
