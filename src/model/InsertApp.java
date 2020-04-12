package model;

import javax.swing.table.DefaultTableModel;
import java.sql.*;


public class InsertApp {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:src/resources/College.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(int groupNumber, String groupLeader, String groupSubject) {
        String sql = "INSERT INTO 'Group'(GroupNumber, GroupLeader, GroupSubject) VALUES (?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, groupNumber);
            pstmt.setString(2, groupLeader);
            pstmt.setString(3, groupSubject);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertStudent(String StudentFirstName, String StudentSecondName, int StudentIndex, String StudentEmail, int GroupID) {
        String sql = "INSERT INTO Student(StudentFirstName, StudentSecondName, StudentIndex, StudentEmail, GroupID) VALUES (?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, StudentFirstName);
            pstmt.setString(2, StudentSecondName);
            pstmt.setInt(3, StudentIndex);
            pstmt.setString(4, StudentEmail);
            pstmt.setInt(5, GroupID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertPresence(boolean isPresent, int groupId, int studentId) {
        String sql = "INSERT INTO Presence (IsPresent, GroupID, StudentID) VALUES (?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, isPresent);
            pstmt.setInt(2, groupId);
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

    public void selectAllStudentData(){
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

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public DefaultTableModel selectOneGroupStudentData(DefaultTableModel model, int groupNumber){
        String sql = "SELECT * FROM Student WHERE GroupID = " + groupNumber;

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
        InsertApp app = new InsertApp();
        // insert three new rows
//        app.insert(1, "Piotr Kopniak", "Java");
//        app.insert(4, "Piotr Kopniak", "Java");
//        app.insert(5, "Piotr Kopniak", "Java");
//        app.insert(6, "Piotr Kopniak", "Java");
//        app.insert(7, "Piotr Kopniak", "Java");
//        app.insert(8, "Piotr Kopniak", "Java");
//        app.insert(10, "Piotr Kopniak", "Java");
//        app.insert(11, "Piotr Kopniak", "Java");

//        app.insertStudent("A" , "A", 1, "A@A", 1);
//        app.insertStudent("B" , "B", 2, "B@B", 1);
//        app.insertStudent("C" , "C", 3, "C@C", 1);
//        app.insertStudent("D" , "D", 4, "D@D", 1);
//        app.insertStudent("E" , "E", 5, "E@E", 1);
//        app.insertStudent("F" , "F", 6, "F@F", 1);
//        app.insertStudent("G" , "G", 7, "G@G", 1);
//        app.insertStudent("H" , "H", 8, "H@H", 1);
//        app.insertStudent("I" , "I", 9, "I@I", 1);
//        app.insertStudent("J" , "J", 10, "J@J", 1);
//        app.insertStudent("K" , "K", 11, "K@K", 1);
//        app.insertStudent("L" , "L", 12, "L@L", 1);
//        app.insertStudent("M" , "M", 13, "M@M", 1);
//        app.insertStudent("N" , "N", 14, "N@N", 1);
//        app.insertStudent("O" , "O", 15, "O@O", 1);
//        app.insertStudent("P" , "P", 16, "P@P", 1);

        app.selectAllStudentData();
    }
}