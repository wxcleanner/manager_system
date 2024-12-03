package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class AdminMainFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // 优化变量名，使其表意更清晰，代表桌面面板用于承载内部窗口
    private JDesktopPane desktopPane; 

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminMainFrame frame = new AdminMainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AdminMainFrame() {
        setTitle("员工信息管理系统1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 954, 738);

        // 创建菜单栏并添加到窗口
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // 部门管理菜单
        JMenu departmentMenu = new JMenu("部门管理");
        menuBar.add(departmentMenu);

        // 部门添加菜单项
        JMenuItem addDepartmentMenuItem = new JMenuItem("部门添加");
        addDepartmentMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    departmentaddinterframe departmentAddInternalFrame1 = new departmentaddinterframe();
                    departmentAddInternalFrame1.setVisible(true);
                    desktopPane.add(departmentAddInternalFrame1);
                    // 可以考虑进一步设置内部窗口的大小、位置等布局相关属性，示例如下：
                    // departmentAddInternalFrame.setSize(400, 300);
                    // departmentAddInternalFrame.setLocation(50, 50);
                } catch (Exception ex) {
                    // 优化异常处理，弹出提示框告知用户出现问题
                    showErrorMessage("部门添加功能出现异常，请联系管理员");
                }
            }
        });
        departmentMenu.add(addDepartmentMenuItem);

        // 部门维护菜单项，此处可后续补充具体的点击事件处理逻辑
        JMenuItem maintainDepartmentMenuItem = new JMenuItem("部门维护");
        maintainDepartmentMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Departmentmanager departmentAddInternalFrame2 = new Departmentmanager();
                departmentAddInternalFrame2.setVisible(true);
                desktopPane.add(departmentAddInternalFrame2);
        	}
        });
        departmentMenu.add(maintainDepartmentMenuItem);

        // 员工信息管理菜单
        JMenu employeeInfoMenu = new JMenu("员工信息管理");
        menuBar.add(employeeInfoMenu);

        // 基本信息录入菜单项，可完善具体业务逻辑
        JMenuItem inputBasicInfoMenuItem = new JMenuItem("基本信息录入");
        inputBasicInfoMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		StaffAddInterFrame StaffAddInterFrame = new StaffAddInterFrame();
        		StaffAddInterFrame.setVisible(true);
        		desktopPane.add(StaffAddInterFrame);
        	}
        });
        employeeInfoMenu.add(inputBasicInfoMenuItem);

        // 基本信息维护菜单项，后续添加对应功能代码
        JMenuItem maintainBasicInfoMenuItem = new JMenuItem("基本信息维护");
        employeeInfoMenu.add(maintainBasicInfoMenuItem);

        // 员工职务调整菜单项，功能待完善
        JMenuItem adjustEmployeePositionMenuItem = new JMenuItem("员工职务调整");
        employeeInfoMenu.add(adjustEmployeePositionMenuItem);

        // 职务调整记录菜单项，按需补充具体操作代码
        JMenuItem positionAdjustRecordMenuItem = new JMenuItem("职务调整记录");
        employeeInfoMenu.add(positionAdjustRecordMenuItem);

        // 用户管理菜单
        JMenu userManagementMenu = new JMenu("用户管理");
        menuBar.add(userManagementMenu);

        // 用户添加菜单项，功能实现部分可进一步细化
        JMenuItem addUserMenuItem = new JMenuItem("用户添加");
        addUserMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        userManagementMenu.add(addUserMenuItem);

        // 用户维护菜单项，添加相应业务逻辑代码
        JMenuItem maintainUserMenuItem = new JMenuItem("用户维护");
        userManagementMenu.add(maintainUserMenuItem);

        // 设置菜单
        JMenu settingMenu = new JMenu("设置");
        menuBar.add(settingMenu);

        // 修改菜单项，可根据实际修改需求完善功能
        JMenuItem modifyMenuItem = new JMenuItem("修改");
        settingMenu.add(modifyMenuItem);

        // 退出菜单项，添加退出程序的逻辑
        JMenuItem exitMenuItem = new JMenuItem("退出");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        settingMenu.add(exitMenuItem);

        desktopPane = new JDesktopPane();
        getContentPane().add(desktopPane, BorderLayout.CENTER); // 调整布局位置，可根据实际需求修改布局方式
    }

    // 封装一个用于显示错误信息的方法，方便统一处理异常提示
    private void showErrorMessage(String message) {
        javax.swing.JOptionPane.showMessageDialog(this, message, "错误", javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    // 假设这里的DepartmentAddInternalFrame是内部窗口类，目前代码中未定义完整，需根据实际需求创建具体的类实现其功能等
    class DepartmentAddInternalFrame extends javax.swing.JInternalFrame {
        // 内部窗口相关的组件定义、布局设置、业务逻辑等代码，此处暂省略，需进一步完善
    }
}