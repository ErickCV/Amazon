package sample.TDAs;

public class Users {

    String user, userName , password;
    Role idRole;
    int log;

    public Users() {
    }

    public Users(int log) {
        this.log = log;
    }


    public Users(String user, String userName, String password, Role idRole) {
        this.user = user;
        this.userName = userName;
        this.password = password;
        this.idRole = idRole;
    }

    public String getUser() {
        return user;
    }

    public int getLog() {
        return log;
    }

    public void setLog(int log) {
        this.log = log;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getIdRole() {
        return idRole;
    }

    public void setIdRole(Role idRole) {
        this.idRole = idRole;
    }
}
