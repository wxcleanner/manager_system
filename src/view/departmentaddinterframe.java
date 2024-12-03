package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.Department;
import util.StringUtil;
import util.dbutil;
import dao.DepartmentDao;

@SuppressWarnings("serial")
public class departmentaddinterframe extends JInternalFrame {
    private JLabel lblDepartmentName;
    private JTextField departNameTxt;
    private JLabel lblDepartmentDescription;
    private JTextArea departDescriptionTxt;
    private dbutil dbutill = new dbutil();
    private DepartmentDao departmentDao = new DepartmentDao();

    // 启动应用程序，在事件分发线程中创建并显示部门添加内部窗口
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                departmentaddinterframe frame = new departmentaddinterframe();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // 创建部门添加内部窗口的构造函数
    public departmentaddinterframe() {
        setIconifiable(true);
        setClosable(true);
        setTitle("部门添加");
        setBounds(100, 100, 626, 548);

        lblDepartmentName = new JLabel("部门名称");
        lblDepartmentName.setFont(new Font("宋体", Font.PLAIN, 18));

        lblDepartmentDescription = new JLabel("部门描述");
        lblDepartmentDescription.setFont(new Font("宋体", Font.PLAIN, 18));

        departNameTxt = new JTextField();
        departNameTxt.setColumns(10);

        departDescriptionTxt = new JTextArea();
        departDescriptionTxt.setFont(new Font("宋体", Font.PLAIN, 18));

        JButton btnAdd = new JButton("添加");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    Department department = buildDepartmentObject();
                    if (department!= null) {
                        int result = addDepartmentToDatabase(department);
                        handleAddResult(result);
                    }
                }
            }
        });

        JButton btnReset = new JButton("重置");
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetInputFields();
            }
        });

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                       .addGroup(groupLayout.createSequentialGroup()
                               .addGap(115)
                               .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                       .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                       .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                               .addComponent(lblDepartmentName, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                                               .addComponent(lblDepartmentDescription, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)))
                               .addGap(28)
                               .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                       .addComponent(departNameTxt, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                                       .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                               .addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                               .addComponent(departDescriptionTxt, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)))
                               .addContainerGap(183, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                       .addGroup(groupLayout.createSequentialGroup()
                               .addGap(144)
                               .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                       .addComponent(departNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                       .addComponent(lblDepartmentName))
                               .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                       .addGroup(groupLayout.createSequentialGroup()
                                               .addGap(52)
                                               .addComponent(lblDepartmentDescription))
                                       .addGroup(groupLayout.createSequentialGroup()
                                               .addGap(42)
                                               .addComponent(departDescriptionTxt, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
                               .addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                               .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                       .addComponent(btnAdd)
                                       .addComponent(btnReset))
                               .addGap(86))
        );
        getContentPane().setLayout(groupLayout);
    }

    // 验证输入是否合法
    private boolean validateInput() {
        String departName = departNameTxt.getText().trim();
        String departDescription = departDescriptionTxt.getText().trim();
        if (StringUtil.isEmpty(departName)) {
            JOptionPane.showMessageDialog(null, "部门名称不能为空");
            return false;
        }
        if (StringUtil.isEmpty(departDescription)) {
            JOptionPane.showMessageDialog(null, "部门描述不能为空");
            return false;
        }
        // 这里可以添加更多的验证逻辑，比如检查部门名称长度是否符合数据库要求等
        return true;
    }

    // 构建Department对象
    private Department buildDepartmentObject() {
        String departName = departNameTxt.getText().trim();
        String departDescription = departDescriptionTxt.getText().trim();
        try {
            return new Department(departName, departDescription);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "构建部门对象出现错误：" + e.getMessage());
            return null;
        }
    }

    // 将部门信息添加到数据库并返回影响行数
    private int addDepartmentToDatabase(Department department) {
        Connection con = null;
        try {
            con = dbutill.getCon();
            return departmentDao.add(con, department);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "数据库操作出现错误：" + e.getMessage());
            return -1;
        } finally {
            try {
                dbutill.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 根据添加结果进行相应提示和操作
    private void handleAddResult(int result) {
        if (result == 1) {
            JOptionPane.showMessageDialog(null, "添加成功");
            resetInputFields();
        } else {
            JOptionPane.showMessageDialog(null, "添加失败");
        }
    }

    // 重置输入框内容
    private void resetInputFields() {
        departNameTxt.setText("");
        departDescriptionTxt.setText("");
    }
}