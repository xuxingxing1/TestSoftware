package zrkj;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
public class DevicePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int ZR321 = 0;
	public static final int ZR621 = 1;
	public static final int ZR921 = 2;
	public static final int CP200N = 3;
	private int type;
	private JTabbedPane tabp = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
	private DevicePanelCP200N devicePanelCP200N = new DevicePanelCP200N();
	private DevicePanelCP200N devicePanelCP200N2 = new DevicePanelCP200N();
	private DevicePanelCP200N devicePanelCP200N3 = new DevicePanelCP200N();
	private DevicePanelCP200N devicePanelCP200N4 = new DevicePanelCP200N();
	
	public DevicePanel() {
		//setSize(800, 600);
		tabp.add(devicePanelCP200N,"321控制板测试软件");
		tabp.add(devicePanelCP200N2,"621控制板测试软件");
		tabp.add(devicePanelCP200N3,"921控制板测试软件");
		tabp.add(devicePanelCP200N4,"CP200N控制板测试软件");
		setLayout(new BorderLayout());
		add(tabp, BorderLayout.CENTER);
		
		switch(type) {
			case ZR321:tabp.setSelectedIndex(ZR321);
				break;
			case ZR621:tabp.setSelectedIndex(ZR621);
				break;
			case ZR921:tabp.setSelectedIndex(ZR921);
				break;
			case CP200N:tabp.setSelectedIndex(CP200N);
				break;
			default:tabp.setSelectedIndex(CP200N);
		}
	}
	
	public void show(int type) {
		this.type = type;
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	/*public Dimension getPreferredSize() {
		//Rectangle rc=getContentPane().getBounds();
		return new Dimension(rc.width, rc.height);
	}*/
}
