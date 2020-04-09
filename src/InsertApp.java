import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;


public class InsertApp {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:src/College.db";
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

    public static void main(String[] args) {
//        try {
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        InsertApp app = new InsertApp();
        // insert three new rows
        app.insert(1, "Piotr Kopniak", "Java");
        app.insert(4, "Piotr Kopniak", "Java");
        app.insert(5, "Piotr Kopniak", "Java");
        app.insert(6, "Piotr Kopniak", "Java");
        app.insert(7, "Piotr Kopniak", "Java");
        app.insert(8, "Piotr Kopniak", "Java");
        app.insert(10, "Piotr Kopniak", "Java");
        app.insert(11, "Piotr Kopniak", "Java");
    }
}