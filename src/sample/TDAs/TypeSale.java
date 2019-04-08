package sample.TDAs;

public class TypeSale {
     int idTypeSale ;
    String name,
    description;

    public TypeSale() {
    }

    @Override
    public String toString() {
        return name;
    }

    public int getIdTypeSale() {
        return idTypeSale;
    }

    public void setIdTypeSale(int idTypeSale) {
        this.idTypeSale = idTypeSale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public TypeSale(int idTypeSale, String name, String description) {
        this.idTypeSale = idTypeSale;
        this.name = name;
        this.description = description;
    }
}
