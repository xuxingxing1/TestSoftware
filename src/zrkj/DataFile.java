package zrkj;

import java.io.IOException;

public class DataFile {
	protected String CompanyName;
	protected String AdminLoginName;
	private String defineSetDat = "#【此行为注释】参数配置文件，特别重要！请勿擅自删改！！！\n" + 
			"#【此行为注释】下面是软件的设计开发公司名称及电话\n" + 
			"@设计：深圳市智锐科技有限公司 电话：13691645742 (Design:ZRKJ Call:13691645742)\n" + 
			"#【此行为注释】下面是\n   ";
	public DataFile() {	//获取配置文件中的信息
		java.io.File file = new java.io.File("set.dat");
		if(file.exists() == false) {
			try {
				java.io.PrintWriter output = new java.io.PrintWriter(file);
				output.print(defineSetDat);
				output.close();
			} catch(IOException e) {
				System.out.println("创建文件 “ set.dat ” 失败");
				javax.swing.JOptionPane.showMessageDialog(null, "创建文件 “ set.dat ” 失败");
				e.printStackTrace();
			}
		}
		try {
			boolean getDataFlag = false;
			java.util.Scanner input = new java.util.Scanner(file);
			while(input.hasNextLine() && !getDataFlag) {
				String i = input.nextLine();
				if(i.length() != 0) {
					if(((String) i.subSequence(0, 1)).equals("@")) {
						CompanyName = (String) i.subSequence(1, i.length());
						getDataFlag = true;
					}
				}
			}
			getDataFlag = false;
			while(input.hasNextLine() && !getDataFlag) {
				String i = input.nextLine();
				if(i.length() != 0) {
					if(((String) i.subSequence(0, 1)).equals("@")) {
						AdminLoginName = (String) i.subSequence(1, i.length());
						getDataFlag = true;
					}
				}
			}
			input.close();
		} catch(IOException e) {
			System.out.println("读取文件 “ set.dat ” 失败");
			javax.swing.JOptionPane.showMessageDialog(null, "读取文件 “ set.dat ” 失败");
			e.printStackTrace();
		}
	}
}
