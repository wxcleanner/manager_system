package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.logging.Logger;

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

public class RegFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 3518738865467346092L;
    private JPanel contentPane;
    // 进一步优化变量名，使其表意更清晰准确
    private JPasswordField passwordField;
    private JTextField userNameTextField;
    private JPasswordField confirmPasswordField;
    private dbutil dbUtil = new dbutil();
    private UserDao userDao = new UserDao();
    private static final Logger LOGGER = Logger.getLogger(RegFrame.class.getName());

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegFrame frame = new RegFrame();
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
    public RegFrame() {
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

        JButton btnRegister = new JButton("注册"); // 将按钮名称修改为更符合功能的"注册"
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                regActionPerformed(e);
            }
        });
        btnRegister.setBounds(381, 368, 97, 23);
        contentPane.add(btnRegister);

        userNameTextField = new JTextField();
        userNameTextField.setBounds(390, 156, 139, 21);
        contentPane.add(userNameTextField);
        userNameTextField.setColumns(10);

        JLabel lblNewLabel = new JLabel("注册账号");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
        lblNewLabel.setBounds(391, 85, 276, 37);
        contentPane.add(lblNewLabel);

        JLabel lblConfirmPassword = new JLabel("确认密码");
        lblConfirmPassword.setFont(new Font("宋体", Font.PLAIN, 12));
        lblConfirmPassword.setBounds(322, 267, 58, 54);
        contentPane.add(lblConfirmPassword);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(391, 284, 138, 21);
        contentPane.add(confirmPasswordField);
    }

    // 提取获取数据库连接的方法，统一处理异常
    private Connection getDatabaseConnection() {
        Connection con = null;
        try {
            con = dbUtil.getCon();
        } catch (Exception e) {
            LOGGER.severe("获取数据库连接失败: " + e.getMessage());
            showErrorMessage("注册失败，无法连接到数据库，请联系管理员");
        }
        return con;
    }

    // 提取关闭数据库连接的方法，统一处理异常
    private void closeDatabaseConnection(Connection con) {
        try {
            dbUtil.closeCon(con);
        } catch (Exception e) {
            LOGGER.severe("关闭数据库连接失败: " + e.getMessage());
            // 可以选择在这里也给用户一个轻量级提示，或者仅记录日志，视具体需求而定
        }
    }

    // 验证输入信息的合法性，返回验证结果和提示信息（若有）
    private boolean validateInput(String userName, String password, String confirmPassword) {
        if (StringUtil.isEmpty(userName)) {
            showErrorMessage("请输入用户名");
            userNameTextField.requestFocus(); // 让用户名输入框获得焦点
            return false;
        }
        if (StringUtil.isEmpty(password)) {
            showErrorMessage("请输入密码");
            passwordField.requestFocus(); // 让密码输入框获得焦点
            return false;
        }
        if (StringUtil.isEmpty(confirmPassword)) {
            showErrorMessage("请输入确认密码");
            confirmPasswordField.requestFocus(); // 让确认密码输入框获得焦点
            return false;
        }
        if (!password.equals(confirmPassword)) {
            showErrorMessage("用户两次密码不一致");
            passwordField.requestFocus(); // 让密码输入框获得焦点，方便用户修改
            return false;
        }
        return true;
    }

    // 执行注册的核心业务逻辑
    private void performRegistration(Connection con, User user) {
        try {
            int addnum = userDao.addUser(con, user);
            if (addnum == 1) {
                showSuccessMessage("注册成功");
            } else {
                showErrorMessage("注册失败，请检查输入信息是否符合要求或联系管理员");
            }
        } catch (Exception e) {
            LOGGER.severe("注册过程出现异常: " + e.getMessage());
            showErrorMessage("注册失败，出现未知异常，请联系管理员");
        }
    }

    // 注册操作的事件处理方法，整合调用其他方法来完成注册流程
    private void regActionPerformed(ActionEvent e) {
        String userName = userNameTextField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (validateInput(userName, password, confirmPassword)) {
            Connection con = getDatabaseConnection();
            if (con!= null) {
                User user = new User(userName, password);
                performRegistration(con, user);
                closeDatabaseConnection(con);
            }
        }
    }

    // 封装一个用于显示成功信息的方法，方便统一处理提示风格
    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "成功", JOptionPane.INFORMATION_MESSAGE);
    }

    // 封装一个用于显示错误信息的方法，方便统一处理提示风格
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "错误", JOptionPane.ERROR_MESSAGE);
    }
}