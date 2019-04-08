package sample.TDAs;

public class Promotion {
     int idPromo;
    String name,
    datePromo;

    public Promotion(int idPromo, String name, String datePromo) {
        this.idPromo = idPromo;
        this.name = name;
        this.datePromo = datePromo;
    }

    public Promotion() {
    }

    @Override
    public String toString() {
        return  name ;
    }

    public int getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatePromo() {
        return datePromo;
    }

    public void setDatePromo(String datePromo) {
        this.datePromo = datePromo;
    }
}
