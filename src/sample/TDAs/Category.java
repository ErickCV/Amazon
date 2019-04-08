package sample.TDAs;

public class Category {
     int idCategory;
    String name,
    description,
    image;

    public Category(int idCategory, String name, String description, String image) {
        this.idCategory = idCategory;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Category(int idCategory, String name){
        this.idCategory = idCategory;
        this.name = name;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return name;
    }
}
