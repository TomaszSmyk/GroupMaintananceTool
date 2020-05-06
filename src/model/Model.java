package model;

import view.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class Model {
    private static List<Person> students;
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

}
