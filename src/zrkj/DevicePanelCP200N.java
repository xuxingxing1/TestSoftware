package zrkj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DevicePanelCP200N extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String carNo = "贵A456HY挂";
	private JButton jb_1_01 = new JButton("连接设备");
	private JButton jb_1_02 = new JButton("断开设备");
	private JButton jb_3_01 = new JButton("祝你一路平安");
	private JLabel jl_1_01 = new JLabel("设备未连接");
	private JTextField jtf_3_01 = new JTextField(carNo);
	private String[] defaultCarNoChinese = {//车牌号码首字符
			"京A", "京B", "京C", "京E", "京F", "京G", "京O",
			"津A", "津B", "津C", "津E",
			"冀A", "冀B", "冀C", "冀D", "冀E", "冀F", "冀G", "冀H", "冀J", "冀R", "冀T",  
			"晋A", "晋B", "晋C", "晋D", "晋E", "晋F", "晋H", "晋J", "晋K", "晋L", "晋M", 
			"蒙A", "蒙B", "蒙C", "蒙D", "蒙E", "蒙F", "蒙G", "蒙H", "蒙J", "蒙K", "蒙L", 
			"辽A", "辽B", "辽C", "辽D", "辽E", "辽F", "辽G", "辽H", "辽J", "辽M", "辽N", "辽P", "辽V",  
			"吉A", "吉B", "吉C", "吉D", "吉E", "吉F", "吉G", "吉H", 
			"黑A", "黑B", "黑C", "黑D", "黑E", "黑F", "黑G", "黑H", "黑J", "黑K", "黑L", "黑M", "黑N", "黑P", 
			"沪A", "沪B", "沪C", "沪D",
			"苏A", "苏B", "苏C", "苏D", "苏E", "苏F", "苏G", "苏H", "苏J", "苏K", "苏L", "苏M", "苏N",  
			"浙A", "浙B", "浙C", "浙D", "浙E", "浙F", "浙G", "浙H", "浙J", "浙K", "浙L",  
			"皖A", "皖B", "皖C", "皖D", "皖E", "皖F", "皖G", "皖H", "皖J", "皖K", "皖L", "皖M", "皖N", "皖P", "皖Q", "皖R", 
			"闽A", "闽B", "闽C", "闽D", "闽E", "闽F", "闽G", "闽H", "闽J", "闽K", 
			"赣A", "赣B", "赣C", "赣D", "赣E", "赣F", "赣G", "赣H", "赣J", "赣K", "赣L", 
			"鲁A", "鲁B", "鲁C", "鲁D", "鲁E", "鲁F", "鲁G", "鲁H", "鲁J", "鲁K", "鲁L", "鲁M", "鲁N", "鲁P", "鲁Q", "鲁R", "鲁U", 
			"豫A", "豫B", "豫C", "豫D", "豫E", "豫F", "豫G", "豫H", "豫J", "豫K", "豫L", "豫M", "豫N", "豫P", "豫Q", "豫R", "豫S", "豫U", 
			"鄂A", "鄂B", "鄂C", "鄂D", "鄂E", "鄂F", "鄂G", "鄂H", "鄂J", "鄂K", "鄂L", "鄂M", "鄂N", "鄂P", "鄂Q", 
			"湘A", "湘B", "湘C", "湘D", "湘E", "湘F", "湘G", "湘H", "湘J", "湘K", "湘L", "湘M", "湘N", "湘P", 
			"粤A", "粤B", "粤C", "粤D", "粤E", "粤F", "粤G", "粤H", "粤J", "粤K", "粤L", "粤M", "粤N", "粤P", "粤Q", "粤R", "粤S", "粤T", "粤U", "粤V", "粤W", "粤X", "粤Y", "粤Z",  
			"桂A", "桂B", "桂C", "桂D", "桂E", "桂F", "桂G", "桂H", "桂J", "桂K", "桂L", "桂M", "桂N", "桂P", 
			"琼A", "琼B", "琼C", 
			"渝A", "渝B", "渝C", "渝F", "渝G", "渝H", 
			"川A", "川B", "川C", "川D", "川E", "川F", "川H", "川J", "川K", "川L", "川Q", "川R", "川S", "川T", "川U", "川V", "川W", "川Z", 
			"贵A", "贵B", "贵C", "贵D", "贵E", "贵F", "贵G", "贵H", "贵J",  
			"云A", "云B", "云C", "云D", "云E", "云F", "云G", "云H", "云J", "云L", "云K", "云M", "云N", "云P", "云Q", "云R", "云S", 
			"藏A", "藏B", "藏C", "藏D", "藏E", "藏F", "藏G", 
			"陕A", "陕B", "陕C", "陕D", "陕E", "陕F", "陕G", "陕H", "陕J", "陕K", "陕U", 
			"甘A", "甘B", "甘C", "甘D", "甘E", "甘F", "甘G", "甘H", "甘J", "甘K", "甘L", "甘M", "甘N", "甘P", 
			"青A", "青B", "青C", "青D", "青E", "青F", "青G", "青H", 
			"宁A", "宁B", "宁C", "宁D", 
			"新A", "新B", "新C", "新D", "新E", "新F", "新G", "新H", "新J", "新K", "新L", "新M", "新N", "新P", "新Q", "新R", 
			"WJ0", "WJ1", "WJ2", "WJ3"};//消，边，通，森，金，警，电
	private char[] defaultCarNoEnglish = {	//车牌号码英文和数字字符
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
			'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9'};
	private String[] defaultCarNoCode = {	//车牌号码末尾字符
			"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", 
			"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", 
			"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ",
			"  ", "港", "澳", "  ", "  ", "  ", "警", "  ", "  ", "  ", 
			"  ", "  ", "  ", "  ", "使", "学", "挂", "  "};

	public DevicePanelCP200N() {
		add(jb_1_01);
		add(jb_1_02);
		add(jl_1_01);
		add(jtf_3_01);
		add(jb_3_01);
		jb_3_01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				byte [] byteCarNo = {0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20};
				byteCarNo = getCarNo();
				for(int i = 0; i < byteCarNo.length; i++) {
					System.out.print(i + ":0x" + Integer.toHexString(byteCarNo[i]));
					System.out.print("         ");
				}
				System.out.println("         ");
				setCarNo();
			}
		});
	}
	
	private void setCarNo() {	//随机摇号一个车牌号码，并填写入车牌号码文本框
		if(jtf_3_01.getText().length() != 0) {
			carNo = defaultCarNoChinese[(int)(Math.random() * defaultCarNoChinese.length)];	//获取一个车牌汉字
			carNo += defaultCarNoEnglish[(int)(Math.random() * defaultCarNoEnglish.length)];	//获取一个车牌字母数字
			carNo += defaultCarNoEnglish[(int)(Math.random() * defaultCarNoEnglish.length)];	//获取一个车牌字母数字
			carNo += defaultCarNoEnglish[(int)(Math.random() * defaultCarNoEnglish.length)];	//获取一个车牌字母数字
			carNo += defaultCarNoEnglish[(int)(Math.random() * defaultCarNoEnglish.length)];	//获取一个车牌字母数字
			carNo += defaultCarNoEnglish[(int)(Math.random() * defaultCarNoEnglish.length)];	//获取一个车牌字母数字
			carNo += defaultCarNoCode[(int)(Math.random() * defaultCarNoCode.length)];	//获取一个车牌末尾字符
		} else {
			carNo = "";
		}
		jtf_3_01.setText(carNo);
	}
	
	private byte [] getCarNo() {	//获取文本框中填写的车牌号码
		String temp = jtf_3_01.getText();
		char [] chartemp = temp.toCharArray();
		byte [] byteCarNo = {0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20};
		int index = 0;
		for(int i = 0; i < temp.length(); i++) {
			if((char)(byte)chartemp[i] != chartemp[i]) {	//是汉字字符
				if(index < byteCarNo.length - 1) {
					byteCarNo[index++] = (byte)(chartemp[i] >> 8);
					byteCarNo[index++] = (byte)chartemp[i];
				} else {
					return byteCarNo;
				}
			} else {	//是英文或数字字符
				if(index < byteCarNo.length) {
					byteCarNo[index++] = (byte)chartemp[i];
				} else {
					return byteCarNo;
				}
			}
		}
		return byteCarNo;
	}
}
