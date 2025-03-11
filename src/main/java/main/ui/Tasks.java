package main.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Tasks extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    public Tasks() {
        setTitle("Task List");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ALEXIA\\Downloads\\5042264.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 735);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(183, 211, 217));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        String[] columnNames = { "ID", "Fed", "Is_Clean" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 540, 550);
        contentPane.add(scrollPane);

        loadTasks();

        JButton btnNewButton_1_1 = new JButton("Back");
        btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton_1_1.setBounds(50, 617, 122, 21);
        contentPane.add(btnNewButton_1_1);
        btnNewButton_1_1.addActionListener(e -> {
            MainMenu menuFrame = new MainMenu();
            menuFrame.setVisible(true);
            dispose();  
        });
    }

    private void loadTasks() {

        //List<Task> tasks = getTasks(); ]

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
    /*    for (Task task : tasks) {
            model.addRow(new Object[] { task.getId(), task.isFed(), task.isClean() });
        }
    }

    //private List<Task> getTasks() { }
     * */
     */

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Tasks frame = new Tasks();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

class Task {
    private int id;
    private boolean fed;
    private boolean isClean;

    public Task(int id, boolean fed, boolean isClean) {
        this.id = id;
        this.fed = fed;
        this.isClean = isClean;
    }

    public int getId() {
        return id;
    }

    public boolean isFed() {
        return fed;
    }

    public boolean isClean() {
        return isClean;
    }
}
