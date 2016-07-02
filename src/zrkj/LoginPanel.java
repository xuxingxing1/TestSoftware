package zrkj;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class LoginPanel extends JPanel{
	private JPasswordField password = new JPasswordField(8);
	private JComboBox<String> loginname = new JComboBox();
	private String AdminLoginName;
	public LoginPanel() {
		AdminLoginName = new DataFile().AdminLoginName;
		loginname.addItem(AdminLoginName);
		setLayout(new GridLayout(2, 2, 5, 10));
		add(new JLabel("用户名 name："));
		add(loginname);
		add(new JLabel("密码 password："));
		add(password);
	}
}
