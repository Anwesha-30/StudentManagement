import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {
    int roll;
    String name;
    int age;
    String course;

    Student(int roll, String name, int age, String course) {
        this.roll = roll;
        this.name = name;
        this.age = age;
        this.course = course;
    }
}

public class StudentManagementGUI extends JFrame implements ActionListener {

    JTextField tfRoll, tfName, tfAge, tfCourse;
    JTextArea area;
    JButton addBtn, viewBtn, searchBtn, deleteBtn, clearBtn;

    ArrayList<Student> students = new ArrayList<>();

    StudentManagementGUI() {
        setTitle("Student Management System");
        setSize(550, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // -------- Top Panel (Form) --------
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Roll No:"));
        tfRoll = new JTextField();
        panel.add(tfRoll);

        panel.add(new JLabel("Name:"));
        tfName = new JTextField();
        panel.add(tfName);

        panel.add(new JLabel("Age:"));
        tfAge = new JTextField();
        panel.add(tfAge);

        panel.add(new JLabel("Course:"));
        tfCourse = new JTextField();
        panel.add(tfCourse);

        add(panel, BorderLayout.NORTH);

        // -------- Center (Text Area) --------
        area = new JTextArea();
        area.setEditable(false);
        JScrollPane sp = new JScrollPane(area);
        add(sp, BorderLayout.CENTER);

        // -------- Bottom Panel (Buttons) --------
        JPanel btnPanel = new JPanel(new GridLayout(1, 5, 10, 10));

        addBtn = new JButton("Add");
        viewBtn = new JButton("View");
        searchBtn = new JButton("Search");
        deleteBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");

        btnPanel.add(addBtn);
        btnPanel.add(viewBtn);
        btnPanel.add(searchBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(clearBtn);

        add(btnPanel, BorderLayout.SOUTH);

        // Button Listeners
        addBtn.addActionListener(this);
        viewBtn.addActionListener(this);
        searchBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addBtn) {
            int roll = Integer.parseInt(tfRoll.getText());
            String name = tfName.getText();
            int age = Integer.parseInt(tfAge.getText());
            String course = tfCourse.getText();

            students.add(new Student(roll, name, age, course));
            area.setText("Student Added Successfully!\n");

        } else if (e.getSource() == viewBtn) {
            area.setText("");
            for (Student s : students) {
                area.append("Roll: " + s.roll +
                        ", Name: " + s.name +
                        ", Age: " + s.age +
                        ", Course: " + s.course + "\n");
            }

        } else if (e.getSource() == searchBtn) {
            int roll = Integer.parseInt(tfRoll.getText());
            area.setText("");

            for (Student s : students) {
                if (s.roll == roll) {
                    area.setText("Found:\nRoll: " + s.roll +
                            "\nName: " + s.name +
                            "\nAge: " + s.age +
                            "\nCourse: " + s.course);
                    return;
                }
            }
            area.setText("Student not found!");

        } else if (e.getSource() == deleteBtn) {
            int roll = Integer.parseInt(tfRoll.getText());

            for (Student s : students) {
                if (s.roll == roll) {
                    students.remove(s);
                    area.setText("Student Deleted!");
                    return;
                }
            }
            area.setText("Student not found!");

        } else if (e.getSource() == clearBtn) {
            tfRoll.setText("");
            tfName.setText("");
            tfAge.setText("");
            tfCourse.setText("");
            area.setText("");
        }
    }

    public static void main(String[] args) {
        new StudentManagementGUI();
    }
}
