package Data;

public class EducationalAdmin extends SystemUser{
    private String id;

    public EducationalAdmin() {
        this.id = "thisisadmin1234";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
