package model;

import view.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Model {
    private static List<Person> students;
    public static int lessonNumber = 1;
    private static int activeGroup = 1;
    public Model() {
        this(1);
    }

    public Model(int group) {
        updateStudents();
        updateStudentTable(group);
    }

    private void updateStudents() {
        Database database = new Database();//todo make it Singleton!
        students = database.getAllStudents();
        for (Person student : students) {
            System.out.println(student);
        }
    }

    public void updateStudentTable(int group) {
        activeGroup = group;
        JTable table = View.getPresenceTable();
        DefaultTableModel model = new DefaultTableModel(new Object[]{"LP", "First Name", "Second Name", "Group", "Index", "Email", "Presence"}, 0);

        for(Person student: students) {
            if (student.getGroupNumber() == group) {
                model.addRow(student.getData());
            }
        }
        table.setModel(model);
    }

    public SortedSet<Integer> updateGroupNumbers() {
        SortedSet<Integer> groups = new TreeSet<>();
        for(Person student: students) {
            groups.add(student.getGroupNumber());
        }
        System.out.println(groups);
        return groups;
    }

    public static void fireTabChanged() {
        Database database = new Database();
        for (Person student : students) {
            if (student.getGroupNumber() == activeGroup) {
                System.out.println("Inserting to presence tab:  " + Integer.parseInt(student.getID()) + " " + student.getPresence());
//                database.insertPresence(Integer.parseInt(student.getID()), student.getPresence());
//                database.updatePresence(student.getPresence(), Integer.parseInt(student.getID()));
//                System.out.println("def: " + database.isStudentIDInDatabase(7));
                System.out.println(Model.getCurrentDate());
                System.out.println(database.isStudentIDInDatabase(1));
                System.out.println(database.isDateInDatabase(Model.getCurrentDate()));
                System.out.println(database.isLessonNumberInDatabase(lessonNumber));
                if (database.isStudentIDInDatabase(1) && database.isDateInDatabase(Model.getCurrentDate()) && database.isLessonNumberInDatabase(lessonNumber)) {
                    //todo here will be code to update database, cause such row already exists
                }
                else {
                    //todo and here will be just insert into database, cause such record is not present in db
                }
            }
        }
    }

    public static void changeStudentPresence(int id, boolean isPresent) {
        for(Person student: students) {
            if (Integer.parseInt(student.getID()) == id) {
                student.setPresence(isPresent);
            }
        }
    }

    public static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime now = LocalDateTime.now();
        return (String) dtf.format(now);
    }
}
