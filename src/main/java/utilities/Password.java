package utilities;

public class Password {

    private String login;
    private String password;
    private String site;
    private String user;

    public Password(String login, String password, String site, String user) {
        this.login = login;
        this.password = password;
        this.site = site;
        this.user = user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
