package Course.UI;
import Course.CourseIntervalSet;
import Course.CourseScheduleApp;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class SemesterSet extends JFrame {
    public SemesterSet(CourseIntervalSet set) throws HeadlessException
    {
        JFrame frame = new JFrame("学期属性");
        frame.setSize(400,400);
        JPanel panel = new JPanel();
        JPanel part1 = new JPanel();
        JPanel part2 = new JPanel();
        JPanel part3 = new JPanel();
        JLabel startTime = new JLabel("学期开始时间");
        startTime.setFont(new Font("楷体",Font.BOLD,20));
        JTextField field1 = new JTextField("YYYY-MM-DD",15);
        JLabel Length = new JLabel("学期长度(周)");
        Length.setFont(new Font("楷体",Font.BOLD,20));
        JTextField field2 = new JTextField(15);
        JButton button = getjButton(set, field1, field2, frame);
        part1.add(startTime);
        part1.add(field1);
        part2.add(Length);
        part2.add(field2);
        part3.add(button);
        panel.add(part1);
        panel.add(part2);
        panel.add(part3);
        frame.add(panel);
        frame.setVisible(true);
    }

    @NotNull
    private static JButton getjButton(CourseIntervalSet set, JTextField field1, JTextField field2 , JFrame frame) {
        JButton button = new JButton("确认");
        button.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String inputDate = field1.getText();
               try {
                   LocalDate startDate = CourseScheduleApp.DateSplitter(inputDate);
                   set.setStartTime(startDate);
               }
               catch (NumberFormatException | IndexOutOfBoundsException error) {
                   JOptionPane.showMessageDialog(null,"输入格式错误(YYYY-MM-DD)");
               }
               String inputLength = field2.getText();
               try {
                   int length = Integer.parseInt(inputLength);
                   if(length<=0)
                   {
                       throw new NumberFormatException("error");
                   }
                   set.setSemesterLength(length);
               }
               catch (NumberFormatException error2)
               {
                   JOptionPane.showMessageDialog(null,"学期长度必须是整数");
               }
               JOptionPane.showMessageDialog(null,"设置成功");
                frame.dispose();
           }
       });
        return button;
    }

}
