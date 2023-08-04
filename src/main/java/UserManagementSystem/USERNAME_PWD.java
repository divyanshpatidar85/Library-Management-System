package UserManagementSystem;

public class USERNAME_PWD {
    private String name;
    private String password;

    public USERNAME_PWD(String name, String password) {
        this.password = password;
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
