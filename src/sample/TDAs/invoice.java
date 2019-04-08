package sample.TDAs;

public class invoice {
     int idInvoice;
    Sale idSale;
    String dateInvoice;
    Users user;

    public invoice(int idInvoice, Sale idSale, String dateInvoice, Users user) {
        this.idInvoice = idInvoice;
        this.idSale = idSale;
        this.dateInvoice = dateInvoice;
        this.user = user;
    }

    public invoice() {
    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Sale getIdSale() {
        return idSale;
    }

    public void setIdSale(Sale idSale) {
        this.idSale = idSale;
    }

    public String getDateInvoice() {
        return dateInvoice;
    }

    public void setDateInvoice(String dateInvoice) {
        this.dateInvoice = dateInvoice;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
