package sample.TDAs;

public class City {
    int idCity;
    String name;
    State idState;
    Country idCountry;

    public City(int idCity, String name, Country idCountry, State idState) {
        this.idCity = idCity;
        this.name = name;
        this.idCountry = idCountry;
        this.idState = idState;
    }

    public City() {
    }

    @Override
    public String toString() {
        return name;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}