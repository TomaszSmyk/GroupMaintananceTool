package model;

import view.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Model - used to manipulate data, pass it to the database, update View
 */
public class Model {
    //this collection is main storage - data gethered from user is stored here and later this data is
    // inserted into database
    private static List<Person> students;

    //group and lesson number - important to guarantee stability of this model
    public static int lessonNumber = 1;
    public static int activeGroup = 1;

    /**
     * Constructor with no parameters that is mainly used to refresh data, invokes other constructor with
     * basic data
     */
    public Model() {
        this(1, 1);
    }

    /**
     * Upadates static fields and also refreshes data
     * @param group - number of student's group
     * @param lesson - number of student's current lesson
     */
    public Model(int group, int lesson) {
        activeGroup = group;
        lessonNumber = lesson;
        updateStudents();
        updateStudentTable(group);
    }

    /**
     * Check if student is present at given group and lesson
     * @param groupNumber - number of student's group
     * @param lessonNumber - number of student's current lesson
     * @return presence - 1 or 0 - yes or no
     */
    public int getPresence(int groupNumber, int lessonNumber) {
        Database database = new Database();
        int attended = database.getPresence(groupNumber, lessonNumber);
        return attended;
    }

    /**
     * updates student data, gets from database and writes into that collection field
     */
    private void updateStudents() {
        Database database = new Database();
        students = database.getAllStudents();
        for (Person student : students) {
            System.out.println(student);
        }
    }

    /**
     * Insert data into table, but only students from given group
     * @param group - number of student's group
     */
    public void updateStudentTable(int group) {
        Database database = new Database();
        activeGroup = group;
        JTable table = View.getPresenceTable();
        DefaultTableModel model = new DefaultTableModel(new Object[]{"LP", "First Name", "Second Name", "Group", "Index", "Email", "Presence"}, 0);

        for(Person student: students) {
            if (database.isStudentPresenceInDatabaseOnLessonNumber(Integer.parseInt(student.getID()), lessonNumber)) {
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

    /**
     * Gets groupp numbers from database and writes this data into set to make sure there will not be any duplicates
     * @return - numbers of groups
     */
    public SortedSet<Integer> updateGroupNumbers() {
        this.updateStudents();
        SortedSet<Integer> groups = new TreeSet<>();
        for(Person student: students) {
            groups.add(student.getGroupNumber());
        }
        return groups;
    }

    /**
     * Controller invokes this method to update Presence table, cause table row was changed
     */
    public static void fireTabChanged() {
        Database database = new Database();
        for (Person student : students) {
            if (student.getGroupNumber() == activeGroup) {
                if (database.isStudentPresenceInDatabaseOnLessonNumber(Integer.parseInt(student.getID()), lessonNumber)) {
                    //here is code to update database, cause such row already exists
                    System.out.println(student.getPresence() + " " + Model.lessonNumber + " " + Integer.parseInt(student.getID()));
                    database.updatePresence(student.getPresence(), Integer.parseInt(student.getID()), Model.lessonNumber);
                }
                else {
                    //and here is just insert into database, cause such record is not present in db
                    database.insertPresence(Integer.parseInt(student.getID()), student.getPresence(), Model.lessonNumber);
                }
            }
        }

    }

    /**
     * updates collection by setting presence into each object in it
     * @param id - student's id
     * @param isPresent - is student present
     */
    public static void changeStudentPresence(int id, boolean isPresent) {
        for(Person student: students) {
            if (Integer.parseInt(student.getID()) == id) {
                student.setPresence(isPresent);
            }
        }
    }

    /**
     * @return current date
     */
    public static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime now = LocalDateTime.now();
        return (String) dtf.format(now);
    }
}
