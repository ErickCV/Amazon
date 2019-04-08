package sample.TDAs;

public class TypeDecoration {
    int idTypeDecoration;
    String name,
    description,
    image;

    public TypeDecoration(int idTypeDecoration, String name, String description, String image) {
        this.idTypeDecoration = idTypeDecoration;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    @Override
    public String toString() {
        return "TypeDecoration{" +
                "idTypeDecoration=" + idTypeDecoration +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public TypeDecoration(String name) {
        this.name = name;
    }

    public TypeDecoration() {
    }

    public int getIdTypeDecoration() {
        return idTypeDecoration;
    }

    public void setIdTypeDecoration(int idTypeDecoration) {
        this.idTypeDecoration = idTypeDecoration;
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
}
