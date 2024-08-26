package Course.UI;
import Course.CourseIntervalSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame{
    public Home(CourseIntervalSet set) throws HeadlessException{
        this.setTitle("课程管理系统主页");
        this.setSize(400,400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel home = new JPanel();
        JButton SemesterSet = new JButton("学期属性");
        JButton CourseInsert = new JButton("添加课程");
        JButton PrintCourse = new JButton("已有课程/安排课程");
        JButton PrintSchedule = new JButton("打印课程表");
        home.add(SemesterSet);
        home.add(CourseInsert);
        home.add(PrintCourse);
        home.add(PrintSchedule);
        this.add(home);
        this.setVisible(true);
        SemesterSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SemesterSet(set);
            }
        });
        CourseInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CourseInsert(set);
            }
        });
        PrintCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(set.getCourseList().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"未加入任何课程");
                    return;
                }
                new CourseList(set);
            }
        });
        PrintSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PrintSchedule(set);
            }
        });

    }
    public static void main(String[] Args)
    {
        CourseIntervalSet set = new CourseIntervalSet();
        new Home(set);
    }

}
