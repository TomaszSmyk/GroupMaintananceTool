package model;

import javafx.scene.chart.PieChart;
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
    public static int activeGroup = 1;
    public Model() {
        this(1, 1);
    }

    public Model(int group, int lesson) {
        activeGroup = group;
        lessonNumber = lesson;
        updateStudents(); // todo make it update students in given group
        updateStudentTable(group);
    }

    private void updateStudents() {
        Database database = new Database();//todo make it Singleton!
        students = database.getAllStudents();
        //students = database.getStudentsFromGroup(Model.activeGroup);
        for (Person student : students) {
            System.out.println(student);
        }
    }

    public void updateStudentTable(int group) {
        Database database = new Database();
        activeGroup = group;
        JTable table = View.getPresenceTable();
        DefaultTableModel model = new DefaultTableModel(new Object[]{"LP", "First Name", "Second Name", "Group", "Index", "Email", "Presence"}, 0);

        for(Person student: students) {
            //todo make it check in Presence DB if student is already in there and update student presence if so.
            if (database.isStudentPresenceInDatabaseOnLessonNumber(Integer.parseInt(student.getID()), lessonNumber)) {
                System.out.println("List student: " + database.getStudentPresenceOnLessonNumber(Integer.parseInt(student.getID()), lessonNumber));
                student.setPresence(database.getStudentPresenceOnLessonNumber(Integer.parseInt(student.getID()), lessonNumber));
            } else {
                student.setPresence(true);
            }
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
                System.out.println("Inserting to presence tab:  ID: " + Integer.parseInt(student.getID()) + " Present: " + student.getPresence());
//                database.insertPresence(Integer.parseInt(student.getID()), student.getPresence());
//                database.updatePresence(student.getPresence(), Integer.parseInt(student.getID()));
//                System.out.println("def: " + database.isStudentIDInDatabase(7));
//                System.out.println("Data: " + Model.getCurrentDate());
                System.out.println("Is student in DB: " + database.isStudentIDInDatabase(Integer.parseInt(student.getID())));
//                System.out.println("Is current date in DB: " + database.isDateInDatabase(Model.getCurrentDate()));
                System.out.println("Is lesson " + lessonNumber + " in DB: " + database.isLessonNumberInDatabase(lessonNumber));
                boolean abc = database.isStudentPresenceInDatabaseOnLessonNumber(Integer.parseInt(student.getID()), lessonNumber);
                System.out.println("If: " + abc);
                if (database.isStudentPresenceInDatabaseOnLessonNumber(Integer.parseInt(student.getID()), lessonNumber)) {
                    //todo here will be code to update database, cause such row already exists
                    System.out.println(student.getPresence() + " " + Model.lessonNumber + " " + Integer.parseInt(student.getID()));
                    database.updatePresence(student.getPresence(), Integer.parseInt(student.getID()), Model.lessonNumber);
                }
                else {
                    //todo and here will be just insert into database, cause such record is not present in db
                    database.insertPresence(Integer.parseInt(student.getID()), student.getPresence(), Model.lessonNumber);
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
