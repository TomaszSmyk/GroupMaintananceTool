package model;

/**
 * Its builder with getters, used to store date that later on will be inserted into the database
 */
public class Student implements Person {
    private final String ID;
    private final String firstName;
    private final String lastName;
    private final int groupNumber;
    private final int index;
    private final String email;
    private boolean isPresent;

    public static class Builder {
        private String ID = "";
        private String firstName = "";
        private String lastName = "";
        private int groupNumber = 0;
        private int index = 0;
        private String email = "";
        private boolean isPresent = true;

        public Builder ID(String ID) {
            this.ID = ID;
            return this;
        }
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder groupNumber(int groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }
        public Builder index(int index) {
            this.index = index;
            return this;
        }
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        public Builder isPresent(boolean isPresent) {
            this.isPresent = isPresent;
            return this;
        }
        public Student build() {
            return new Student(this);
        }
    }
    private Student(Builder builder) {
        ID = builder.ID;
        firstName = builder.firstName;
        lastName = builder.lastName;
        groupNumber = builder.groupNumber;
        index = builder.index;
        email = builder.email;
        isPresent = builder.isPresent;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public int getGroupNumber() {
        return groupNumber;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public boolean getPresence() {
        return isPresent;
    }

    @Override
    public void setPresence(boolean isPresent) {
        this.isPresent = isPresent;
    }

    @Override
    public Object[] getData() {
        return new Object[]{ID, firstName, lastName, groupNumber, index, email, isPresent};
    }
}
