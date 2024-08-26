package Course.UI;

import Course.Course;
import Course.CourseIntervalSet;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseList {
    public CourseList(CourseIntervalSet set) {
        JFrame frame = new JFrame("Button Table Example");
        frame.setSize(800, 300);

        // 创建表格模型
        MyTableModel model = new MyTableModel(set);
        JTable table = new JTable(model);
        // 设置按钮列
        TableColumn column = table.getColumnModel().getColumn(6); // 假设按钮在第三列
        column.setCellRenderer(new ButtonRenderer());
        column.setCellEditor(new ButtonEditor(new JCheckBox(), table, model ,set));

        // 添加表格到滚动面板
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}

// 自定义表格模型
class MyTableModel extends AbstractTableModel {
    private final String[] columnNames = {"课程ID", "课程名称", "教师名称", "上课地点", "学时数","未分配学时数","开始分配"};
    private Object[][] data = new Object[15][7];

    MyTableModel(CourseIntervalSet set)
    {
        int flag = 0;
        for(Course i :set.getCourseList().keySet())
        {
            data[flag][0] = String.valueOf(i.getCourseID());
            data[flag][1] = i.getCourseName();
            data[flag][2] = i.getCourseTeacher();
            data[flag][3] = i.getLocation();
            data[flag][4] = String.valueOf(i.getCreditHour());
            data[flag][5] = set.getCourseList().get(i).toString();
            data[flag][6] = "点击开始分配";
            flag ++;
        }
    }


    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        return col == 6; // 只有按钮列可编辑
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}

// 按钮渲染器
class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

// 按钮编辑器
class ButtonEditor extends javax.swing.DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;
    private MyTableModel model;

    public ButtonEditor(JCheckBox checkBox, JTable table, MyTableModel model , CourseIntervalSet set) {
        super(checkBox);
        this.table = table;
        this.model = model;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.convertRowIndexToModel(table.getEditingRow());
                /*
                System.out.println("Row data: ");
                for (int col = 0; col < model.getColumnCount(); col++) {
                    System.out.println(model.getColumnName(col) + ": " + model.getValueAt(row, col));
                }
                */
                int remainTime = Integer.parseInt(model.getValueAt(row,5).toString());
                if(remainTime<=0 )
                 {
                     JOptionPane.showMessageDialog(null,"该课程学时已分配完");
                     return;
                 }
                 else {
                     int ID = Integer.parseInt(model.getValueAt(row,0).toString());
                     new ScheduleCourse(set ,ID,remainTime);
                 }

                fireEditingStopped();
            }
        });
    }
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        isPushed = false;
        return label;
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}

