package sample.TDAs;

public class State {
    int idState;
    String name;
    Country idCountry;

    public State(int idState, String name, Country idCountry) {
        this.idState = idState;
        this.name = name;
        this.idCountry = idCountry;
    }

    @Override
    public String toString() {
        return name;
    }

    public State() {
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Country idCountry) {
        this.idCountry = idCountry;
    }
}
