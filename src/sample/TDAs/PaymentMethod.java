package sample.TDAs;

public class PaymentMethod {
     int idPayment;
    String typePayment,
    decription;

    @Override
    public String toString() {
        return typePayment ;
    }

    public PaymentMethod() {
    }

    public PaymentMethod(int idPayment, String typePayment, String decription) {
        this.idPayment = idPayment;
        this.typePayment = typePayment;
        this.decription = decription;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
