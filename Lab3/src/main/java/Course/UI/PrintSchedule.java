package Course.UI;

import Course.Course;
import Course.CourseIntervalSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class PrintSchedule {
    public PrintSchedule(CourseIntervalSet set) {
        // 定义课程表的行和列
        String[] days = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        String[] periods = {"第一节(8:00-10:00)", "第二节(10:00-12:00)", "第三节(13:00-15:00)",
                "第四节(15:00-17:00)", "第五节(19:00-21:00)"};

        // 创建一个二维数组来表示课程表
        String[][] schedule = new String[days.length][periods.length];

        // 初始化课程表，这里可以填入具体的课程名称
        // 例如：schedule[0][0] = "数学";
        // 这里我们暂时用 "空" 来表示没有课程
        for(Course i:set.getCourseList().keySet())
        {
            ArrayList<Integer> time =  set.getCourseTime(i.getCourseID());
            if(time.isEmpty())
            {
                continue;
            }
            else
            {
                for (int j:time)
                {
                    schedule[j/5][j%5] = i.getCourseName();
                }
            }
        }

        // 创建一个表格模型
        DefaultTableModel model = new DefaultTableModel(schedule, periods);
        model.addColumn("星期",days);


        // 创建一个JTable
        JTable table = new JTable(model);

        // 设置表格的列头
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        // 创建一个JFrame
        JFrame frame = new JFrame("课程表");
        frame.add(new JScrollPane(table));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

