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
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class UserMainFrame extends JFrame {
    private JDesktopPane desktopPane; // 优化变量名，使其表意更清晰，代表桌面面板用于承载内部窗口

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserMainFrame frame = new UserMainFrame();
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
    public UserMainFrame() {
        setTitle("员工信息管理系统1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 954, 738);

        initMenuBar(); // 调用方法初始化菜单栏

        desktopPane = new JDesktopPane();
        getContentPane().add(desktopPane, BorderLayout.CENTER); // 调整布局位置，可根据实际需求修改布局方式，这里以居中为例
    }

    // 初始化菜单栏的方法
    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // 设置菜单
        JMenu settingMenu = new JMenu("设置");
        menuBar.add(settingMenu);

        // 修改密码菜单项
        JMenuItem changePasswordMenuItem = new JMenuItem("修改密码");
        changePasswordMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // 这里添加修改密码的具体业务逻辑代码，比如弹出密码修改界面等
                    // 示例：假设存在一个ChangePasswordDialog用于密码修改的对话框类
                    ChangePasswordDialog dialog = new ChangePasswordDialog();
                    dialog.setVisible(true);
                } catch (Exception ex) {
                    showErrorMessage("修改密码功能出现异常，请联系管理员");
                }
            }
        });
        settingMenu.add(changePasswordMenuItem);

        // 退出菜单项
        JMenuItem exitMenuItem = new JMenuItem("退出");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        settingMenu.add(exitMenuItem);

        // 个人信息管理菜单
        JMenu personalInfoMenu = new JMenu("个人信息管理");
        menuBar.add(personalInfoMenu);

        // 基本信息维护菜单项，后续需补充具体业务逻辑
        JMenuItem maintainPersonalInfoMenuItem = new JMenuItem("基本信息维护");
        maintainPersonalInfoMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // 这里添加基本信息维护的具体业务逻辑代码，例如调用相应的信息维护方法等
                    // 示例代码省略，需根据实际业务完善
                } catch (Exception ex) {
                    showErrorMessage("基本信息维护功能出现异常，请联系管理员");
                }
            }
        });
        personalInfoMenu.add(maintainPersonalInfoMenuItem);
    }

    // 封装一个用于显示错误信息的方法，方便统一处理异常提示
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "错误", JOptionPane.ERROR_MESSAGE);
    }

    // 假设这里的ChangePasswordDialog是密码修改对话框类，目前代码中未定义完整，需根据实际需求创建具体的类实现其功能等
    class ChangePasswordDialog extends javax.swing.JDialog {
        // 对话框相关的组件定义、布局设置、业务逻辑等代码，此处暂省略，需进一步完善
    }
}