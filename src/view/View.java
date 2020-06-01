package view;

import controller.Controller;
import controller.TabController;
import controller.TableController;
import model.Model;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.SortedSet;

public class View {
    //Main frame
    private JFrame frame = new JFrame("APP");

    //main panel, used to set all components into it, starting with tabbed pane
    private JPanel mainPanel = new CustomJPanel();
    public static JTabbedPane tabbedPane = new JTabbedPane();

    //home tab components
    private JPanel homePanel = new CustomJPanel();
    private JLabel groupNumberLabel = new JLabel("Group number: ");
    public static JComboBox<Integer> groupNumbers;
    private static SortedSet<Integer> groups;
    private JLabel lessonNumberLabel = new JLabel("Lesson number");
    private Integer[] lesson = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    private JComboBox<Integer> lessonNumbers = new JComboBox<>(lesson);

    //presence tab components
    private JPanel presencePanel = new CustomJPanel();
    private static JTable presenceTable= new CustomJTable();


    //add tab components
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

    //remove tab components
    private JPanel removePanel = new CustomJPanel();
    private JLabel removeFirstNameLabel = new JLabel("First Name: ");
    public static JTextField removeFirstNameTextField = new JTextField();
    private JLabel removeLastNameLabel = new JLabel("Last name:");
    public static JTextField removeLastNameTextField = new JTextField();
    private JLabel removeIndexLabel = new JLabel("Index:");
    public static JTextField removeIndexTextField = new JTextField();

    private JButton removeButton = new JButton("REMOVE");

    //chart tab components
    private static ChartPanel chartsPanel;

    //fixed window size, otherwise, left menu won't align nicely with window size.
    protected static final Dimension widowSize = new Dimension(1000, 700);


    public View() {
        Model model = new Model();
        groups = model.updateGroupNumbers();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(widowSize);
        frame.setResizable(false);

        mainPanel.setLayout(new BorderLayout());

        //setting customized UI - mainly because of color changing and resizing capabilities
        tabbedPane.setUI(new CustomTabbedPaneUI());
        //mouse listener will refresh tabs after clicking certain ones
        tabbedPane.addMouseListener(new TabController());

        //setting up all tabs
        setupHomeTab();
        setupPresenceTab();
        setupAddTab();
        setupRemoveTab();
        setupChartsTab();

        //fixing menu on the left side of the window
        tabbedPane.setTabPlacement(JTabbedPane.LEFT);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        setupListeners();

        frame.getContentPane().add(mainPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Setts up HOME tab,
     * Uses no layout to align components nicely, also takes care of listeners of JCompoBox components
     *
     */
    private void setupHomeTab() {

        groupNumberLabel.setHorizontalAlignment(JLabel.RIGHT);
        homePanel.add(groupNumberLabel);

        groupNumbers = new JComboBox<Integer>(new DefaultComboBoxModel<Integer>(groups.toArray(new Integer[0])));
        groupNumbers.setActionCommand(Command.GROUP_NUMBER_CHANGED.toString());
        groupNumbers.addActionListener(new Controller());
        homePanel.add(new JPanel().add(groupNumbers));


        lessonNumberLabel.setHorizontalAlignment(JLabel.RIGHT);
        homePanel.add(lessonNumberLabel);
        lessonNumbers.setActionCommand(Command.WEEK_NUMBER_CHANGED.toString());
        lessonNumbers.addActionListener(new Controller());
        homePanel.add(lessonNumbers);

        tabbedPane.add(Command.HOME.toString(), homePanel);
    }

    /**
     * Creates table and places it into the presence tab, also adding listeners
     */
    private void setupPresenceTab() {

        //todo change gbl to sth else
        presencePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        //todo add table to scrollable pane to prevent window from cutting of bottom of the table
        presenceTable.addMouseListener(new TableController());
        presencePanel.add(presenceTable, gbc);


        tabbedPane.add(Command.PRESENCE.toString(), presencePanel);
    }

    /**
     * Sets up ADD tab
     * Aligne component with Grid Bag Layout
     */
    private void setupAddTab() {
        addPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        gbc.weightx = 1;

            //1st row
        gbc.gridx = 1;
        gbc.gridy = 0;
        addFirstNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        addPanel.add(addFirstNameLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        addPanel.add(addFirstNameTextField, gbc);

            //2nd row
        gbc.gridx = 1;
        gbc.gridy = 1;
        addLastNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        addPanel.add(addLastNameLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        addPanel.add(addLastNameTextField, gbc);

            //3rd row
        gbc.gridx = 1;
        gbc.gridy = 2;
        addIndexLabel.setHorizontalAlignment(JLabel.RIGHT);
        addPanel.add(addIndexLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        addPanel.add(addIndexTextField, gbc);

            //4th row
        gbc.gridx = 1;
        gbc.gridy = 3;
        addGroupLabel.setHorizontalAlignment(JLabel.RIGHT);
        addPanel.add(addGroupLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        addPanel.add(addGroupTextField, gbc);

            //5th row
        gbc.gridx = 1;
        gbc.gridy = 4;
        addEmailLabel.setHorizontalAlignment(JLabel.RIGHT);
        addPanel.add(addEmailLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        addPanel.add(addEmailTextField, gbc);

            //6th row
        gbc.gridx = 3;
        gbc.gridy = 5;
        addPanel.add(addButton, gbc);

        tabbedPane.add(Command.ADD.toString(), addPanel);
    }

    /**
     * Sets up REMOVE tab, aligns components with Grid bag layout
     */
    private void setupRemoveTab() {
        removePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        gbc.weightx = 1;

            //1st row
        gbc.gridx = 1;
        gbc.gridy = 0;
        removeFirstNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        removePanel.add(removeFirstNameLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        removePanel.add(removeFirstNameTextField, gbc);

            //2nd row
        gbc.gridx = 1;
        gbc.gridy = 1;
        removeLastNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        removePanel.add(removeLastNameLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        removePanel.add(removeLastNameTextField, gbc);

            //3rd row
        gbc.gridx = 1;
        gbc.gridy = 2;
        removeIndexLabel.setHorizontalAlignment(JLabel.RIGHT);
        removePanel.add(removeIndexLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        removePanel.add(removeIndexTextField, gbc);

            //4th row
        gbc.gridx = 3;
        gbc.gridy = 5;//places button on the bottom of the window
        removePanel.add(removeButton, gbc);

        tabbedPane.add(Command.DELETE.toString(), removePanel);
    }

    /**
     * Creates new object - chart - and places it into Chart tab
     */
    public static void setupChartsTab() {
        chartsPanel = new ChartPanel(LineChart.updateChart());
        tabbedPane.add(Command.CHARTS.toString(), chartsPanel);
    }


    /**
     * Sets up all listeners that will serve to control buttons
     */
    private void setupListeners() {
        Controller controller = new Controller();
        this.addButton.setActionCommand(Command.ADD.toString());
        this.addButton.addActionListener(controller);

        this.removeButton.setActionCommand(Command.DELETE.toString());
        this.removeButton.addActionListener(controller);
    }


    public static void createAndShowUI() {
        new View();
    }

    public static JTable getPresenceTable() {
        return presenceTable;
    }

}