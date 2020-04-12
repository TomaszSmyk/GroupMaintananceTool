package controller;

import model.InsertApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PresenceController {
    private boolean isPresent;
    private int groupID;
    private int studentID;
    public PresenceController(boolean isPresent, int groupID, int studentID) {
        this.isPresent = isPresent;
        this.groupID = groupID;
        this.studentID = studentID;
        update();
    }
    public void update() {
        InsertApp app = new InsertApp();
        app.insertPresence(isPresent, groupID, studentID);
    }
}
