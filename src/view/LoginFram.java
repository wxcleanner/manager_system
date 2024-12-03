package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import model.User;
import util.StringUtil;
import util.dbutil;

public class LoginFram extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 3518738865467346092L;
    private JPanel contentPane;
    // 进一步优化变量名，使其表意更准确
    private JPasswordField passwordField;
    private JTextField userNameTextField;
    private JComboBox<String> roleComboBox;
    private dbutil dbUtil = new dbutil();
    private UserDao userDao = new UserDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginFram frame = new LoginFram();
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
    public LoginFram() {
        setTitle("员工信息管理系统1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1366, 768);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(192, 192, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // 修改标签变量名为更清晰的名称
        JLabel lblUserName = new JLabel("账号");
        lblUserName.setFont(new Font("宋体", Font.PLAIN, 18));
        lblUserName.setBounds(322, 129, 58, 73);
        contentPane.add(lblUserName);

        JLabel lblPassword = new JLabel("密码");
        lblPassword.setFont(new Font("宋体", Font.PLAIN, 18));
        lblPassword.setBounds(320, 212, 58, 54);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(391, 230, 138, 21);
        contentPane.add(passwordField);

        roleComboBox = new JComboBox<>();
        roleComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"员工", "管理员"}));
        roleComboBox.setBounds(391, 292, 119, 23);
        contentPane.add(roleComboBox);

        JButton btnLogin = new JButton("登录");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginActionPerformed(e);
            }
        });
        btnLogin.setBounds(370, 403, 97, 23);
        contentPane.add(btnLogin);

        userNameTextField = new JTextField();
        userNameTextField.setBounds(390, 156, 139, 21);
        contentPane.add(userNameTextField);
        userNameTextField.setColumns(10);

        JLabel lblRole = new JLabel("权限");
        lblRole.setFont(new Font("新宋体", Font.PLAIN, 18));
        lblRole.setBounds(322, 285, 58, 35);
        contentPane.add(lblRole);
        
        JButton btnNewButton = new JButton("注册");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegFrame().setVisible(true);
            }
        });
        btnNewButton.setBounds(632, 451, 97, 23);
        contentPane.add(btnNewButton);
    }

    private void loginActionPerformed(ActionEvent e) {
        String userName = userNameTextField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        // 统一验证提示格式，更友好规范
        if (StringUtil.isEmpty(userName)) {
            JOptionPane.showMessageDialog(null, "请输入用户名");
            return;
        }
        if (StringUtil.isEmpty(password)) {
            JOptionPane.showMessageDialog(null, "请输入密码");
            return;
        }

        String role = null;
        String selectedRole = (String) roleComboBox.getSelectedItem();
        User user = new User(userName, password, role);
        Connection con = null;
        // 修正权限设置逻辑的语法错误（去掉多余分号），并增加空行增强可读性
        if (selectedRole.equals("管理员")) {
            user.setPower("管理员");
            try {
                con = dbUtil.getCon();
                User currentUser = userDao.login(con, user);
                if (currentUser!= null) {
                    dispose();
                    new AdminMainFrame().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "用户名或者密码错误，请重新输入");
                }
            } catch (Exception ex) {
                // 优化异常提示信息，更准确反映问题
                JOptionPane.showMessageDialog(null, "登录时出现异常，请联系管理员", "登录异常", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    dbUtil.closeCon(con);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else if (selectedRole.equals("员工")) {
            user.setPower("员工");
            try {
                con = dbUtil.getCon();
                User currentUser = userDao.login(con, user);
                if (currentUser!= null) {
                    dispose();
                    new UserMainFrame().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "用户名或者密码错误，请重新输入");
                }
            } catch (Exception ex) {
                // 优化异常提示信息，更准确反映问题
                JOptionPane.showMessageDialog(null, "登录时出现异常，请联系管理员", "登录异常", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    dbUtil.closeCon(con);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}