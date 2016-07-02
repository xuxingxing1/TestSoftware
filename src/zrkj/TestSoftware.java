package zrkj;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TestSoftware extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame deviceframe = new JFrame();
	private DevicePanel devicepanel = new DevicePanel();
	private JFrame loginframe = new JFrame();
	private LoginPanel loginpanel = new LoginPanel();
	private JMenuBar jmenubar = new JMenuBar();
	private JMenu menu1 = new JMenu("使用向导 Guide");
	private JMenu menu2 = new JMenu("登录系统 login");
	private JMenu menu3 = new JMenu("参数设置 Set");
	private JMenu menu4 = new JMenu("车辆档案 Car list");
	private JMenu menu5 = new JMenu("实时监控 Monitor");
	private JMenu menu6 = new JMenu("统计报表 Count & Report");
	private JMenu menu7 = new JMenu("关于 About");
	private JMenuItem menu1_1 = new JMenuItem("初次使用向导 First user guide");
	private JMenuItem menu1_2 = new JMenuItem("机器人帮手 Robot help you");
	private JMenuItem menu2_1 = new JMenuItem("登录 log in");
	private JMenuItem menu2_2 = new JMenuItem("交班 Exchange");
	private JMenuItem menu2_3 = new JMenuItem("添加用户 Add user");
	private JMenuItem menu2_4 = new JMenuItem("角色管理 Role manage");
	private JMenuItem menu3_1 = new JMenuItem("设备 Device");
	private JMenuItem menu3_2 = new JMenuItem("功能 Function");
	private JMenuItem menu3_3 = new JMenuItem("费率 Fee");
	private JMenuItem menu3_4 = new JMenuItem("数据库 Database");
	private JMenuItem menu3_5 = new JMenuItem("导入导出设置信息 Import & Export");
	private JMenuItem menu3_6 = new JMenuItem("喜好 Hobby");
	private JMenuItem menu4_1 = new JMenuItem("车辆档案 Car list");
	private JMenuItem menu4_2 = new JMenuItem("导入车辆档案 Import car list");
	private JMenuItem menu4_3 = new JMenuItem("清空车辆档案 Empty car list");
	private JMenuItem menu5_1 = new JMenuItem("实时监控 Monitor");
	private JMenuItem menu5_2 = new JMenuItem("上传下载 Up & Down load");
	private JMenuItem menu5_3 = new JMenuItem("测试软件 Test software");
	private JMenuItem menu6_1 = new JMenuItem("报表样式 Report style");
	private JMenuItem menu6_2 = new JMenuItem("收费报表 Fee report ");
	private JMenuItem menu6_3 = new JMenuItem("出入记录 Record report");
	private JMenuItem menu6_4 = new JMenuItem("系统日志 System log report");
	private JMenuItem menu7_1 = new JMenuItem("版本说明 Instruction");
	private JMenuItem menu7_2 = new JMenuItem("联系我们 Contact us");
	private JMenuItem menu7_3 = new JMenuItem("注册 Register");
	private String path = "/img/1.png";
	private String CompanyName = "";
	public TestSoftware() {
		CompanyName = new DataFile().CompanyName;
		
		setUI();//设定UI[软件主界面]
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dimension scrSize=Toolkit.getDefaultToolkit().getScreenSize();
		TestSoftware mframe = new TestSoftware();
		mframe.setTitle("停车场管理软件.版本" +  + serialVersionUID + 
				"(Parking manage software . Version:" + serialVersionUID + ")" + mframe.CompanyName);
		int h = scrSize.width - 500;
		mframe.setSize(h, 120);
		int w = (scrSize.width / 2) - (h / 2);
		mframe.setLocation(w, 0);//窗体居中
		mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mframe.setVisible(true);
		
		
	}
	
	private void setUI() {	//设定UI[软件主界面]
		try {//添加一个窗体图标
			Image img = ImageIO.read(this.getClass().getResource(path));
			this.setIconImage(img);
		} catch(IOException e) {
			System.out.println("获取软件主窗体图标失败");
			javax.swing.JOptionPane.showMessageDialog(null, "获取软件主窗体图标失败");
			e.printStackTrace();
		}
		setJMenuBar(jmenubar);
		jmenubar.add(menu1);
		jmenubar.add(menu2);
		jmenubar.add(menu3);
		jmenubar.add(menu4);
		jmenubar.add(menu5);
		jmenubar.add(menu6);
		jmenubar.add(menu7);
		menu1.add(menu1_1);
		menu1.add(menu1_2);
		menu2.add(menu2_1);
		menu2.add(menu2_2);
		menu2.add(menu2_3);
		menu2.add(menu2_4);
		menu3.add(menu3_1);
		menu3.add(menu3_2);
		menu3.add(menu3_3);
		menu3.add(menu3_4);
		menu3.add(menu3_5);
		menu3.add(menu3_6);
		menu4.add(menu4_1);
		menu4.add(menu4_2);
		menu4.add(menu4_3);
		menu5.add(menu5_1);
		menu5.add(menu5_2);
		menu5.add(menu5_3);
		menu6.add(menu6_1);
		menu6.add(menu6_2);
		menu6.add(menu6_3);
		menu6.add(menu6_4);
		menu7.add(menu7_1);
		menu7.add(menu7_2);
		menu7.add(menu7_3);
		menu2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginframe.add(loginpanel);
				loginframe.setVisible(true);
				loginframe.setSize(250, 100);
				loginframe.setLocationRelativeTo(null);
			}
		});
		menu3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				devicepanel.show(DevicePanel.ZR621);
				deviceframe.setVisible(true);
				deviceframe.setSize(800, 600);
				
			}
		});
		deviceframe.getContentPane().add(devicepanel);
		deviceframe.pack();
		deviceframe.setTitle("设备 Device");
	}
}

