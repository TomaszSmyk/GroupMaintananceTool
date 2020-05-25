package view;

import controller.Controller;
import controller.TabController;
import controller.TableController;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.SortedSet;

public class View {
    private JFrame frame = new JFrame("APP");

    private JPanel mainPanel = new CustomJPanel();
    private JTabbedPane tabbedPane = new JTabbedPane();

    private JPanel homePanel = new CustomJPanel();
    private JLabel groupNumberLabel = new JLabel("Group number: ");
    private JComboBox<Integer> groupNumbers;
    private SortedSet<Integer> groups;
    private JLabel lessonNumberLabel = new JLabel("Lesson number");
    private Integer[] lesson = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    private JComboBox<Integer> lessonNumbers = new JComboBox<>(lesson);


    /**
     * This is what I want to implement in the home section
     * -week number (1-15)
     * -day of the week
     * -hour that classes take place
     * -who is the lecturer - further implementation to the login/register screen
     *
     * The date that I want to write into the database need to be in DD:MM:YYYY HH format. No need to add hours.
     * Year will be extracted from DateTimeFormatter
     *         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
     *         LocalDateTime now = LocalDateTime.now();
     *         System.out.println(dtf.format(now));
     */

    private JPanel presencePanel = new CustomJPanel();
    private static JTable presenceTable= new CustomJTable();


    private JPanel addPanel = new CustomJPanel();
    private JLabel addFirstNameLabel = new JLabel("First name:");
    public static JTextField addFirstNameTextField = new JTextField();
    private JLabel addLastNameLabel = new JLabel("Last name:");
    public static JTextField addLastNameTextField = new JTextField();
    private JLabel addIndexLabel = new JLabel("Index:");
    public static JTextField addIndexTextField = new JTextField();
    private JLabel addGroupLabel = new JLabel("Group:");
    public static JTextField addGroupTextField = new JTextField();
    private JLabel addEmailLabel = new JLabel("Email:");
    public static JTextField addEmailTextField = new JTextField();

    private JButton addButton = new JButton("ADD");

    private JPanel deletePanel = new CustomJPanel();

    private JPanel chartsPanel = new CustomJPanel();

    protected static final Dimension widowSize = new Dimension(1000, 700);


    public View() {
        Model model = new Model();
        groups = model.updateGroupNumbers();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(widowSize);
        frame.setResizable(false);

        mainPanel.setLayout(new BorderLayout());

        tabbedPane.setUI(new CustomTabbedPaneUI());
        tabbedPane.addMouseListener(new TabController());

        setupHomeTab();
        setupPresenceTab();
        setupAddTab();
        setupDeleteTab();
        setupChartsTab();

        tabbedPane.setTabPlacement(JTabbedPane.LEFT);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        setupListeners();

        frame.getContentPane().add(mainPanel);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setupHomeTab() {
        homePanel.setLayout(new FlowLayout());

        homePanel.add(groupNumberLabel);
        groupNumbers = new JComboBox<Integer>(new DefaultComboBoxModel<Integer>(groups.toArray(new Integer[0])));
        groupNumbers.setActionCommand(Command.GROUP_NUMBER_CHANGED.toString());
        groupNumbers.addActionListener(new Controller());
        homePanel.add(groupNumbers);

        homePanel.add(lessonNumberLabel);
        lessonNumbers.setActionCommand(Command.WEEK_NUMBER_CHANGED.toString());
        lessonNumbers.addActionListener(new Controller());
        homePanel.add(lessonNumbers);

        tabbedPane.add(Command.HOME.toString(), homePanel);
    }

    private void setupPresenceTab() {


        presencePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        //todo set background of table to gradient
        //todo add table to scrollable pane to prevent window from cutting of bottom of the table
        presenceTable.addMouseListener(new TableController());
        presencePanel.add(presenceTable, gbc);

        tabbedPane.add(Command.PRESENCE.toString(), presencePanel);
    }

    private void setupAddTab() {
        addPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        gbc.weightx = 1;

        gbc.gridx = 1;
        gbc.gridy = 0;
        addFirstNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        addPanel.add(addFirstNameLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        addPanel.add(addFirstNameTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        addLastNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        addPanel.add(addLastNameLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        addPanel.add(addLastNameTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        addIndexLabel.setHorizontalAlignment(JLabel.RIGHT);
        addPanel.add(addIndexLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        addPanel.add(addIndexTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        addGroupLabel.setHorizontalAlignment(JLabel.RIGHT);
        addPanel.add(addGroupLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        addPanel.add(addGroupTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        addEmailLabel.setHorizontalAlignment(JLabel.RIGHT);
        addPanel.add(addEmailLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        addPanel.add(addEmailTextField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 5;
        addPanel.add(addButton, gbc);

        tabbedPane.add(Command.ADD.toString(), addPanel);
    }
    private void setupDeleteTab() {
        tabbedPane.add(Command.DELETE.toString(), deletePanel);
    }

    private void setupChartsTab() {
        tabbedPane.add(Command.CHARTS.toString(), chartsPanel);
    }

    private void setupListeners() {
        Controller controller = new Controller();
        this.addButton.setActionCommand(Command.ADD.toString());
        this.addButton.addActionListener(controller);
    }

    public static void createAndShowUI() {
        new View();
    }

    public static JTable getPresenceTable() {
        return presenceTable;
    }

}