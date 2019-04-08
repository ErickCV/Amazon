package sample.TDAs;

public class Country {
     int idCountry;
    String name ;

    public Country(int idCountry, String name) {
        this.idCountry = idCountry;
        this.name = name;
    }

    public Country() {
    }

    @Override
    public String toString() {
        return  name ;
    }

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
