package Data;

public class Student {
    private String firstName;
    private String lastName;
    private int chairNumber;
    private int id;

    public Student(String firstName, String lastName, int id, int chairNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.chairNumber = chairNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public int getChairNumber() {
        return chairNumber;
    }

    public void setChairNumber(int chairNumber) {
        this.chairNumber = chairNumber;
    }
}
