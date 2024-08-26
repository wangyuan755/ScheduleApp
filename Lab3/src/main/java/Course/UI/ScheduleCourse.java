package Course.UI;
import Course.Course;
import Course.CourseIntervalSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScheduleCourse {
    ScheduleCourse(CourseIntervalSet set , int courseID , int remainTime)
        {
        JFrame frame = new JFrame("选择界面");
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 1)); // 3行1列的布局

        // 第一行：选择周一到周日
        String[] daysOfWeek = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        JComboBox<String> dayComboBox = new JComboBox<>(daysOfWeek);
        frame.add(dayComboBox);
        //1-(8:00-10:00),2-(10:00-12:00),3-(13:00-15:00),4-(15:00-17:00),5-(19:00-21:00)"
        // 第二行：选择一到五
        String[] numbers = {"8:00-10:00", "10:00-12:00", "13:00-15:00", "15:00-17:00", "19:00-21:00"};
        JComboBox<String> numberComboBox = new JComboBox<>(numbers);
        frame.add(numberComboBox);
        // 第三行：确认按钮
        JButton confirmButton = new JButton("确认");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDay = (String) dayComboBox.getSelectedItem();
                String selectedNumber = (String) numberComboBox.getSelectedItem();
                try {
                    int day = convertDayToNumber(selectedDay);
                    int time = convertTimePeriodToNumber(selectedNumber);
                    Course resCourse  = new Course();
                    boolean flag=false;
                    for(Course c:set.getCourseList().keySet())
                    {
                        if(c.getCourseID()==courseID)
                        {
                            resCourse = c;
                            flag = true;
                        }
                    }
                    if(flag)
                    {
                        try {
                            set.addSchedule(resCourse, day, time);
                            JOptionPane.showMessageDialog(null, "添加课程:"+resCourse.toString()+"到课程表中"+
                                    "选择的日期是: " + selectedDay + ", 选择的数字是: " + selectedNumber);
                        } catch (RuntimeException error)
                        {
                            JOptionPane.showMessageDialog(null,"该课程学时已分配完");
                        }
                    }
                }
                catch (IllegalArgumentException error)
                {
                    JOptionPane.showMessageDialog(null, "请选择日期和时间段 ");
                }
            }
        });
        frame.add(confirmButton);

        // 显示窗口
        frame.setVisible(true);
    }
    public static int convertDayToNumber(String day) {
        switch (day) {
            case "周一":
                return 1;
            case "周二":
                return 2;
            case "周三":
                return 3;
            case "周四":
                return 4;
            case "周五":
                return 5;
            case "周六":
                return 6;
            case "周日":
                return 7;
            default:
                throw new IllegalArgumentException("无效的日期字符串: " + day);
        }
    }
    public static int convertTimePeriodToNumber(String timePeriod) {
        switch (timePeriod) {
            case "8:00-10:00":
                return 1;
            case "10:00-12:00":
                return 2;
            case "13:00-15:00":
                return 3;
            case "15:00-17:00":
                return 4;
            case "19:00-21:00":
                return 5;
            default:
                throw new IllegalArgumentException("无效的时间段字符串: " + timePeriod);
        }
    }
}
