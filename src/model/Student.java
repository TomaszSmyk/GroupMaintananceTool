package model;

public class Student implements Person {
    private String firstName;
    private String secondName;
    private int index;
    private String email;
    private int groupID;

    public Student(String firstName, String secondName, int index, String email, int groupID) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.index = index;
        this.email = email;
        this.groupID = groupID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getIndex() {
        return index;
    }

    public String getEmail() {
        return email;
    }

    public int getGroupID() {
        return groupID;
    }
}
