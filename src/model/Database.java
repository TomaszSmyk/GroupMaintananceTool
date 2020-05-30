package model;



import javax.print.attribute.standard.PresentationDirection;
import javax.swing.*;
import javax.xml.crypto.dsig.spec.XPathFilterParameterSpec;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Database {

    private Connection connect() {
        // SQLite connection string

        String url = "jdbc:sqlite::resource:resources/College.db";
        Connection conn = null;//todo make it singleton
        try {
            Class.forName("org.sqlite.JDBC");
//            Class.forName("org.sqlite.JDBC").newInstance();
            conn = DriverManager.getConnection(url);
//            this.createStudent();
//            this.createPresence();
        } catch (SQLException e) {
            //todo create tables if missing and maybe fill them with data
            System.out.println(e.getMessage());
//            this.createStudent();
//            this.createPresence();
//            SwingUtilities.invokeLater(() -> {
//                try {
//                    view.View.createAndShowUI();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    private void createStudent() {
        String sql = "CREATE TABLE IF NOT EXISTS Student(\n"
                + "	id integer PRIMARY KEY,\n"
                + "	firstName text NOT NULL,\n"
                + "	'index' integer NOT NULL,\n"
                + "	'group' integer NOT NULL,\n"
                + "	email text NOT NULL\n"
                + ");";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void createPresence() {
        String sql = "CREATE TABLE IF NOT EXISTS Presence(\n"
                + "	id integer PRIMARY KEY,\n"
                + "	StudentID integer NOT NULL,\n"
                + "	Date text NOT NULL,\n"
                + "	LessonNumber integer NOT NULL,\n"
                + "	IsPresent interger CHECK (IsPresent IN (0, 1)) NOT NULL\n"
                + ");";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    public void insert(int groupNumber, String groupLeader, String groupSubject) {
//        String sql = "INSERT INTO 'Group'(GroupNumber, GroupLeader, GroupSubject) VALUES (?,?,?)";
//
//        try (Connection conn = this.connect();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, groupNumber);
//            pstmt.setString(2, groupLeader);
//            pstmt.setString(3, groupSubject);
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public void insertStudent(String firstName, String lastName, int index, int group, String email) {
        String sql = "INSERT INTO Student(firstName, lastName, 'index', 'group', email) VALUES (?,?,?,?,?)";
        System.out.println("ADDING STUDENT TO THE DATABASE: " + firstName + lastName + index + group + email);
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, index);
            pstmt.setInt(4, group);
            pstmt.setString(5, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertPresence(int studentId, boolean isPresent, int lessonNumber) {
        String sql = "INSERT INTO Presence (StudentID, Date, LessonNumber, IsPresent) VALUES (?, ?, ?, ?)";
        System.out.println("Insert presence: present: " + isPresent + " id: " + studentId + " ln: " + lessonNumber);
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setString(2, Model.getCurrentDate());
            pstmt.setInt(3, lessonNumber);
            pstmt.setBoolean(4, isPresent);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeStudentFromDatabase(String fName, String lName, int index) {
        String sql = "DELETE FROM Student WHERE (firstName = ? AND lastName = ? AND \"index\" = ?)";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, fName);
            pstmt.setString(2, lName);
            pstmt.setInt(3, index);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePresence(boolean isPresent, int studentId, int lessonNumber) {


        String sql = "UPDATE Presence SET IsPresent = ? WHERE (LessonNumber = ? AND StudentID = ?)";
        System.out.println("Update presence: " + isPresent + studentId + " ln: " + lessonNumber);
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, isPresent);
            pstmt.setInt(2, lessonNumber);
            pstmt.setInt(3, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


//    private boolean wasStudentAlreadyInsertedToPresenceDatabaseToday() {//todo make it insert value only if it was not already inserted
////        String sql = "SELECT Date, StudentID FROM Presence WHERE Date "
//        try (Connection conn = this.connect();
//             Statement stmt  = conn.createStatement();
//             ResultSet rs    = stmt.executeQuery(sql)){
//
//            // loop through the result set
//            while (rs.next()) {
//
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return false;
//    }
//
//    public DefaultTableModel selectAllStudentData(DefaultTableModel model){
//        String sql = "SELECT * FROM Student";
//
//        try (Connection conn = this.connect();
//             Statement stmt  = conn.createStatement();
//             ResultSet rs    = stmt.executeQuery(sql)){
//
//            // loop through the result set
//            while (rs.next()) {
//
//                String id = rs.getString("StudentID");
//                String fName = rs.getString("StudentFirstName");
//                String sName = rs.getString("StudentSecondName");
//                int index = rs.getInt("StudentIndex");
//                String email = rs.getString("StudentEmail");
//                int groupID = rs.getInt("GroupID");
//                System.out.println(id + " " + fName + " " + sName + index  + " " + email + " " + groupID);
//                model.addRow(new Object[]{id, fName, sName, index, email, groupID});
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return model;
//    }

    protected List<Person> getAllStudents() {
        List<Person> students = new LinkedList<>();

        String sql = "SELECT * FROM Student";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                String id = rs.getString("StudentID");
                String fName = rs.getString("firstName");
                String lName = rs.getString("lastName");
                int index = rs.getInt("index");
                int groupNumber = rs.getInt("group");
                String email = rs.getString("email");
                System.out.println(id + " " + fName + " " + lName + index + groupNumber + " " + email);
                Student student = new Student.Builder().ID(id).firstName(fName).lastName(lName).index(index).groupNumber(groupNumber).email(email).build();
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    protected List<Person> getStudentsFromGroup(int groupNumber) {
        List<Person> students = new LinkedList<>();

        String sql = "SELECT * FROM Student WHERE ('group' = ?)";

        try (Connection conn = this.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setInt(1, groupNumber);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("StudentID");
                String fName = rs.getString("firstName");
                String lName = rs.getString("lastName");
                int index = rs.getInt("index");
                int group = rs.getInt("group");
                String email = rs.getString("email");
                System.out.println(id + " " + fName + " " + lName + index + group + " " + email);
                Student student = new Student.Builder().ID(id).firstName(fName).lastName(lName).index(index).groupNumber(group).email(email).build();
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return students;
    }

    public void selectAllStudentData(){
        String sql = "SELECT * FROM Student";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {

                String id = rs.getString("StudentID");
                String fName = rs.getString("firstName");
                String sName = rs.getString("lastName");
                int index = rs.getInt("index");
                int group = rs.getInt("group");
                String email = rs.getString("email");
                System.out.println(id + " " + fName + " " + sName + " " + index  + " " + group + " " + email);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private ArrayList<Integer> getPresenceData(int studentId) {
        ArrayList<Integer> ids = new ArrayList<>();
        String d = Model.getCurrentDate();
        String sql = "SELECT * FROM Presence WHERE Date =" + d;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {

                int id = rs.getInt("PresenceID");
                String sId = rs.getString("StudentID");
                String date = rs.getString("Date");
                boolean isPresent = rs.getBoolean("IsPresent");
                System.out.println(id + " " + sId + " " + date + " " + isPresent);

                ids.add(id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ids;
    }

    protected int getPresence(int groupNumber, int lessonNumber) {

        String sql = "SELECT COUNT(Presence.IsPresent) AS Attended " +
                "FROM Presence JOIN Student on Presence.StudentID = Student.StudentID " +
                "WHERE (Presence.IsPresent=true AND Student.\"group\" = ? AND Presence.LessonNumber = ?) " +
                "GROUP BY Presence.LessonNumber";

        int attended = 0;

        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setInt(1, groupNumber);
            stmt.setInt(2, lessonNumber);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                attended = rs.getInt("Attended");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Attended: " + attended + " in group: " + groupNumber + " lesson: " + lessonNumber);
        return attended;
    }

    public boolean getStudentPresenceOnLessonNumber(int ID, int lessonNumber) {
        String sql = "SELECT IsPresent FROM Presence WHERE (StudentID = ? AND LessonNumber = ?)";

        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ID);
            pstmt.setInt(2, lessonNumber);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                boolean p = rs.getBoolean("IsPresent");
                System.out.println("ID: " + ID + " LN: " + lessonNumber + " is: " + p);
                return p; //returns true when executed statement selected student
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true; //returns false when there was no content in query
    }

    public boolean isStudentPresenceInDatabaseOnLessonNumber(int ID, int lessonNumber) {
        String sql = "SELECT * FROM Presence WHERE (StudentID = ? AND LessonNumber = ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, ID);
            pstmt.setInt(2, lessonNumber);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean isStudentIDInDatabase(int studentID) {
        String sql = "SELECT StudentID FROM Presence WHERE StudentID=" + studentID;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean isDateInDatabase(String date) {
        String sql = "SELECT Date FROM Presence WHERE (Date=" + date + ")";
        System.out.println("sql: " + sql);

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean isLessonNumberInDatabase(int lessonNumber) {
        String sql = "SELECT LessonNumber FROM Presence WHERE LessonNumber=" + lessonNumber;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

//
//    public DefaultTableModel selectOneGroupStudentData(DefaultTableModel model, int groupNumber){
//        String sql = "SELECT * FROM Student WHERE 'group' = " + groupNumber;
//
//        try (Connection conn = this.connect();
//             Statement stmt  = conn.createStatement();
//             ResultSet rs    = stmt.executeQuery(sql)){
//
//            // loop through the result set
//            while (rs.next()) {
//
//                String id = rs.getString("StudentID");
//                String fName = rs.getString("StudentFirstName");
//                String sName = rs.getString("StudentSecondName");
//                int index = rs.getInt("StudentIndex");
//                String email = rs.getString("StudentEmail");
//                int groupID = rs.getInt("GroupID");
//                boolean isPresent = false;
//                System.out.println(id + " " + fName + " " + sName + index  + " " + email + " " + groupID);
//                model.addRow(new Object[]{id, fName, sName, index, email, groupID, isPresent});
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return model;
//    }

//    public void updateStudent(int id, String name, double capacity) {
//        String sql = "UPDATE Student SET name = ? , "
//                + "capacity = ? "
//                + "WHERE StudentID = ?";
//
//        try (Connection conn = this.connect();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            // set the corresponding param
//            pstmt.setString(1, name);
//            pstmt.setDouble(2, capacity);
//            pstmt.setInt(3, id);
//            // update
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public static void main(String[] args) {
        Database app = new Database();
        // insert three new rows
//        app.insert(1, "Piotr Kopniak", "Java");
//        app.insert(4, "Piotr Kopniak", "Java");
//        app.insert(5, "Piotr Kopniak", "Java");
//        app.insert(6, "Piotr Kopniak", "Java");
//        app.insert(7, "Piotr Kopniak", "Java");
//        app.insert(8, "Piotr Kopniak", "Java");
//        app.insert(10, "Piotr Kopniak", "Java");
//        app.insert(11, "Piotr Kopniak", "Java");

//        app.insertStudent("A" , "A", 1, 1,"A@A");
//        app.insertStudent("B" , "B", 2, 1,"B@B");
//        app.insertStudent("C" , "C", 3, 1,"C@C");
//        app.insertStudent("D" , "D", 4, 1,"D@D");
//        app.insertStudent("E" , "E", 5, 1,"E@E");
//        app.insertStudent("F" , "F", 6, 2, "F@F");
//        app.insertStudent("G" , "G", 7, 3,"G@G");
//        app.insertStudent("H" , "H", 8, 4,"H@H");
//        app.insertStudent("I" , "I", 9, 4,"I@I");
//        app.insertStudent("J" , "J", 10, 4,"J@J");
//        app.insertStudent("K" , "K", 11, 4,"K@K");
//        app.insertStudent("L" , "L", 12, 4,"L@L");
//        app.insertStudent("M" , "M", 13, 5,"M@M");
//        app.insertStudent("N" , "N", 14, 5,"N@N");
//        app.insertStudent("O" , "O", 15, 6,"O@O");
//        app.insertStudent("P" , "P", 16, 6,"P@P");

//        app.selectAllStudentData();
//        app.getPresenceData( 6);
    }
}