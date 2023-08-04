package Librarian;

public class LIBRARIAN_ID {
    private String name;
    private String ID;
    private String pwd;

    public String getName() {
        return name;
    }

    public LIBRARIAN_ID(String ID, String name) {
        this.ID = ID;
        this.pwd = ID + "@" + name;
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public String getPwd() {
        return pwd;
    }
}
