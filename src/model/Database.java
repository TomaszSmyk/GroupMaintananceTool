package model;


import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that contains various methods used to generate queries
 */
public class Database {

    private Connection connect() {

        String url = "jdbc:sqlite::resource:resources/College.db";
//        String url = "jdbc:sqlite:src/resources/College.db"; // this version is preferred when working in IDE
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

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

    protected void insertPresence(int studentId, boolean isPresent, int lessonNumber) {
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

    protected void updatePresence(boolean isPresent, int studentId, int lessonNumber) {


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

    protected boolean getStudentPresenceOnLessonNumber(int ID, int lessonNumber) {
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

    protected boolean isStudentPresenceInDatabaseOnLessonNumber(int ID, int lessonNumber) {
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


    protected boolean isStudentIDInDatabase(int studentID) {
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

    protected boolean isLessonNumberInDatabase(int lessonNumber) {
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

}