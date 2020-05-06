package model;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class Database {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:src/resources/College.db";
        Connection conn = null;//todo make it singleton
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
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
        System.out.println(firstName + lastName + index + group + email);
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

//    public void insertPresence(boolean isPresent, int groupId, int studentId) {
//        String sql = "INSERT INTO Presence (IsPresent, GroupID, StudentID) VALUES (?,?,?)";
//        try (Connection conn = this.connect();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setBoolean(1, isPresent);
//            pstmt.setInt(2, groupId);
//            pstmt.setInt(3, studentId);
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

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

    public DefaultTableModel selectAllStudentData(DefaultTableModel model){
        String sql = "SELECT * FROM Student";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {

                String id = rs.getString("StudentID");
                String fName = rs.getString("StudentFirstName");
                String sName = rs.getString("StudentSecondName");
                int index = rs.getInt("StudentIndex");
                String email = rs.getString("StudentEmail");
                int groupID = rs.getInt("GroupID");
                System.out.println(id + " " + fName + " " + sName + index  + " " + email + " " + groupID);
                model.addRow(new Object[]{id, fName, sName, index, email, groupID});
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return model;
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

    public DefaultTableModel selectOneGroupStudentData(DefaultTableModel model, int groupNumber){
        String sql = "SELECT * FROM Student WHERE 'group' = " + groupNumber;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {

                String id = rs.getString("StudentID");
                String fName = rs.getString("StudentFirstName");
                String sName = rs.getString("StudentSecondName");
                int index = rs.getInt("StudentIndex");
                String email = rs.getString("StudentEmail");
                int groupID = rs.getInt("GroupID");
                boolean isPresent = false;
                System.out.println(id + " " + fName + " " + sName + index  + " " + email + " " + groupID);
                model.addRow(new Object[]{id, fName, sName, index, email, groupID, isPresent});
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return model;
    }

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

        app.selectAllStudentData();
    }
}