package Course.UI;
import Course.Course;
import Course.CourseIntervalSet;
import Course.CourseScheduleApp;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Objects;

public class CourseInsert extends JFrame{
    public CourseInsert(CourseIntervalSet set) throws HeadlessException
    {
        JFrame frame = new JFrame("添加课程");
        frame.setSize(400, 300);

        // 创建一个 JPanel 对象
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // 使用边界布局

        // 创建一个 JPanel 用于放置标签和输入框
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10)); // 使用网格布局，每行两个组件，间距为10

        // 创建一个 "课程ID" 对象并设置其文本和字体
        JLabel label1 = new JLabel("课程ID");
        label1.setFont(new Font("楷体", Font.BOLD, 20));
        JTextField courseIdTextField = new JTextField(20); // 创建输入框

        // 创建名为“课程名称”的 JLabel
        JLabel courseNameLabel = new JLabel("课程名称");
        courseNameLabel.setFont(new Font("楷体", Font.BOLD, 20));
        JTextField courseNameTextField = new JTextField(20); // 创建输入框

        // 创建名为“教师名称”的 JLabel
        JLabel teacherLabel = new JLabel("教师名称");
        teacherLabel.setFont(new Font("楷体", Font.BOLD, 20));
        JTextField teacherTextField = new JTextField(20); // 创建输入框

        // 创建名为“上课地点”的 JLabel
        JLabel locationLabel = new JLabel("上课地点");
        locationLabel.setFont(new Font("楷体", Font.BOLD, 20));
        JTextField locationTextField = new JTextField(20); // 创建输入框

        // 创建名为“学时数”的 JLabel
        JLabel hoursLabel = new JLabel("学时数");
        hoursLabel.setFont(new Font("楷体", Font.BOLD, 20));
        JTextField hoursTextField = new JTextField(20); // 创建输入框

        // 将 JLabel 和输入框添加到 inputPanel 中
        inputPanel.add(label1);
        inputPanel.add(courseIdTextField);
        inputPanel.add(courseNameLabel);
        inputPanel.add(courseNameTextField);
        inputPanel.add(teacherLabel);
        inputPanel.add(teacherTextField);
        inputPanel.add(locationLabel);
        inputPanel.add(locationTextField);
        inputPanel.add(hoursLabel);
        inputPanel.add(hoursTextField);

        // 创建确认按钮
        JButton confirmButton = new JButton("确认");
        confirmButton.setFont(new Font("楷体", Font.BOLD, 20));
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //insertCourse(long ID,String CourseName , String Teacher,String Location,int creditHour)
                long ID = -1;
                String CourseName = null;
                String Teacher = null;
                String Location = null;
                int creditHour = -1;
                if(courseIdTextField.getText().matches("\\d+"))
                {
                    ID = Long.parseLong(courseIdTextField.getText());
                }
                else {
                    JOptionPane.showMessageDialog(null,"课程id应是整数");
                    return;
                }
                CourseName = courseNameTextField.getText();
                Teacher = teacherTextField.getText();
                Location = locationTextField.getText();
                if(hoursTextField.getText().matches("\\d+")) {
                    creditHour = Integer.parseInt(hoursTextField.getText());
                }
                else {
                    JOptionPane.showMessageDialog(null,"学时数应是整数");
                    return;
                }
                try {
                    set.insertCourse(ID,CourseName,Teacher,Location,creditHour);
                }
                catch (NumberFormatException error)
                {
                    JOptionPane.showMessageDialog(null,error.getMessage());
                    return;
                }
                JOptionPane.showMessageDialog(null,"课程添加成功");
                frame.dispose();
            }
        });

        // 将 inputPanel 添加到 panel 的中心位置
        panel.add(inputPanel, BorderLayout.CENTER);

        // 将确认按钮添加到 panel 的南边位置
        panel.add(confirmButton, BorderLayout.SOUTH);

        // 将 JPanel 添加到 JFrame 中
        frame.add(panel);

        // 设置 JFrame 可见
        frame.setVisible(true);
    }


}
