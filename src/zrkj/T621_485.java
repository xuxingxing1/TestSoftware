package zrkj;
/**
 * @name T621_485.java
 * @use 按照621的485协议，生成发送的数据、解析接收的数据
 * @author xuxingxing
 *
 */
public class T621_485 {
	final byte RECEIVE_HEAD = 0x03;
	final byte RECEIVE_ETX = 0x0e;
	final byte RECEIVE_END = 0x0d;
	final byte SEND_HEAD = 0x01;
	final byte SEND_ETX = 0x0e;
	final byte SEND_END = 0x0d;
	static final int BUFFER_LENGTH = 256;
	static byte[] ReceiveDataBuf = new byte[BUFFER_LENGTH];
	static byte[] SendDataBuf = new byte[BUFFER_LENGTH];
	static int ReceiveIndex;
	static int ReceiveProtocolHeadIndex;
	static int ReceiveProtocolEndIndex;
	static int SendIndex;
	static int SendProtocolHeadIndex;
	static int SendProtocolEndIndex;
	static String TipsText = new String();
	/* 方法------------------------------------------------------------------------------------------------------------- */
	boolean ProcessReceiveData() {//处理接收到的数据
		boolean returnflag = false;
		TipsText = "未接收到有效的协议";
		if(FindReceiveHead()) {
			if(FindReceiveEnd()) {
				if(FindReceiveCrc()) {
					TipsText = "收到：";
					FindReceiveID();
					FindReceiveFunctionCode();
					returnflag = true;
				}
			}
		}
		return returnflag;
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private boolean FindReceiveHead() {//查找接收协议头
		while(ReceiveIndex != ReceiveProtocolHeadIndex) {
			ReceiveProtocolEndIndex = ReceiveProtocolHeadIndex;
			if(ReceiveDataBuf[ReceiveProtocolHeadIndex] != RECEIVE_HEAD) {
				ReceiveProtocolHeadIndex = IndexInc(ReceiveProtocolHeadIndex);
				return false;
			}
			else {
				return true;
			}
		}
		return false;
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private boolean FindReceiveEnd() {//查找接收协议尾
		boolean findetxflag = false;
		ReceiveProtocolEndIndex = IndexInc(ReceiveProtocolEndIndex);
		while(ReceiveIndex != ReceiveProtocolEndIndex) {
			if(ReceiveDataBuf[ReceiveProtocolEndIndex] == RECEIVE_HEAD) {
				ReceiveProtocolHeadIndex = ReceiveProtocolEndIndex; 
			}
			if(findetxflag) {
				if(ReceiveDataBuf[ReceiveProtocolEndIndex] == RECEIVE_END) {
					return true;
				}
			}
			else {
				if(ReceiveDataBuf[ReceiveProtocolEndIndex] == RECEIVE_ETX) {
					findetxflag = true;
				}
			}
			ReceiveProtocolEndIndex = IndexInc(ReceiveProtocolEndIndex);
		}
		return false;
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private boolean FindReceiveCrc() {//查找接收协议校验码并验证校验码
		int tempheadindex = ReceiveProtocolHeadIndex;
		byte crc = 0;
		byte temp, temp1, temp2;		
		while(ReceiveDataBuf[tempheadindex] != RECEIVE_ETX) {
			crc = (byte) (crc ^ ReceiveDataBuf[tempheadindex]);
			tempheadindex = IndexInc(tempheadindex);
		}
		crc = (byte) (crc ^ ReceiveDataBuf[tempheadindex]);
		temp1 = ReceiveDataBuf[tempheadindex + 1];
		temp2 = ReceiveDataBuf[tempheadindex + 2];
		temp = (byte)(((temp1 & 0x0f) << 4) | (temp2 & 0x0f));
		if(crc == temp) {
			return true;
		}
		else {
			return false;
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void FindReceiveID() {//查找ID号
		TipsText += (GetReceiveData2to1Byte() + "#控制板返回");
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void FindReceiveFunctionCode() {//查找功能码
		byte temp = GetReceiveData2to1Byte();
		switch(temp) {
			case 0x11:GetReceiveDataCmd11();break; //获取“校对时钟”命令的接收数据
			case 0x12:GetReceiveDataCmd12();break; //获取“读取时钟”命令的接收数据
			case 0x13:GetReceiveDataCmd13();break; //获取“读取控制板的序列号”命令的接收数据
			case 0x14:GetReceiveDataCmd14();break; //获取“读取控制板的版本号”命令的接收数据
			case 0x15:GetReceiveDataCmd15();break; //获取“初始化控制板”命令的接收数据
			case 0x16:GetReceiveDataCmd16();break; //获取“软件复位控制板”命令的接收数据
			case 0x17:GetReceiveDataCmd17();break; //获取“根据序列号设置控制板号”命令的接收数据
			case 0x18:GetReceiveDataCmd18();break; //获取“设置同卡时间”命令的接收数据 
			case 0x19:GetReceiveDataCmd19();break; //获取“读取同卡时间”命令的接收数据
			case 0x1A:GetReceiveDataCmd1A();break; //获取“设置开门时间”命令的接收数据
			case 0x1B:GetReceiveDataCmd1B();break; //获取“读取开门时间”命令的接收数据
			case 0x1C:GetReceiveDataCmd1C();break; //获取“设置带地感自动开存记录标志位”命令的接收数据
			case 0x1D:GetReceiveDataCmd1D();break; //获取“读取带地感自动开存记录标志位”命令的接收数据
			case 0x1E:GetReceiveDataCmd1E();break; //获取“设置控制板类型”命令的接收数据
			case 0x1F:GetReceiveDataCmd1F();break; //获取“读取控制板类型”命令的接收数据
			case 0x21:GetReceiveDataCmd21();break; //获取“增加/修改一条用户资料”命令的接收数据
			case 0x22:GetReceiveDataCmd22();break; //获取“读取一条用户资料”命令的接收数据
			case 0x23:GetReceiveDataCmd23();break; //获取“删除一条用户资料”命令的接收数据
			case 0x24:GetReceiveDataCmd24();break; //获取“清除所有用户资料”命令的接收数据
			case 0x25:GetReceiveDataCmd25();break; //获取“读取用户资料的数量”命令的接收数据
			case 0x31:GetReceiveDataCmd31();break; //获取“读取一条记录”命令的接收数据
			case 0x32:GetReceiveDataCmd32();break; //获取“删除一条记录”命令的接收数据
			case 0x33:GetReceiveDataCmd33();break; //获取“清除所有记录”命令的接收数据
			case 0x41:GetReceiveDataCmd41();break; //获取“软件开闸”命令的接收数据
			case 0x42:GetReceiveDataCmd42();break; //获取“软件锁定开闸”命令的接收数据
			case 0x43:GetReceiveDataCmd43();break; //获取“解除锁定开闸”命令的接收数据
			case 0x44:GetReceiveDataCmd44();break; //获取“控制蜂鸣”命令的接收数据
			case 0x45:GetReceiveDataCmd45();break; //获取“切换开闸方式”命令的接收数据
			case 0x46:GetReceiveDataCmd46();break; //获取“确认开闸”命令的接收数据
			case 0x51:GetReceiveDataCmd51();break; //获取“转发显示”命令的接收数据
			default:TipsText += "错误的功能码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd11() {//获取“校对时钟”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“校对时钟”成功";
		} else if(temp0 == 0x31){
			TipsText += "“校对时钟”失败";
		} else{
			TipsText += "“校对时钟”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd12() {//获取“读取时钟”命令的接收数据
		byte temp1 = GetReceiveData1Byte();
		byte temp2 = GetReceiveData1Byte();
		if(temp2 == RECEIVE_ETX) {
			if(temp2 == 0x30) {
				TipsText += "“读取时钟”成功但未返回时间数据";
			} else if(temp2 == 0x31){
				TipsText += "“读取时钟”失败";
			} else{
				TipsText += "“读取时钟”乱码";
			}
		}else {
			byte temp = (byte)(((temp1 & 0x0f) << 4) | (temp2 & 0x0f));
			TipsText += ("“读取时钟”成功，当前时间：20" + temp + "年");
			temp = GetReceiveData2to1Byte();
			TipsText += (temp + "月");
			temp = GetReceiveData2to1Byte();
			TipsText += (temp + "日");
			temp = GetReceiveData2to1Byte();
			TipsText += (temp + "时");
			temp = GetReceiveData2to1Byte();
			TipsText += (temp + "分");
			temp = GetReceiveData2to1Byte();
			TipsText += (temp + "秒");
			temp = GetReceiveData1Byte();
			switch(temp) {
				case 0x31:TipsText += "星期一";break;
				case 0x32:TipsText += "星期二";break;
				case 0x33:TipsText += "星期三";break;
				case 0x34:TipsText += "星期四";break;
				case 0x35:TipsText += "星期五";break;
				case 0x36:TipsText += "星期六";break;
				case 0x37:TipsText += "星期日";break;
				default:TipsText += "星期一";
			}
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd13() {//获取“读取控制板的序列号”命令的接收数据
		TipsText += ("“读取控制板的序列号”成功，" +  GetReceiveData2to1Byte());
		TipsText += GetReceiveData2to1Byte();
		TipsText += GetReceiveData2to1Byte();
		TipsText += GetReceiveData2to1Byte();
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd14() {//获取“读取控制板的版本号”命令的接收数据
		TipsText += ("“读取控制板的版本号”成功，");
		byte temp = GetReceiveData1Byte();
		while(temp != RECEIVE_ETX) {
			TipsText += temp; 
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd15() {//获取“初始化控制板”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“初始化控制板”成功";
		} else if(temp0 == 0x31){
			TipsText += "“初始化控制板”失败";
		} else{
			TipsText += "“初始化控制板”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd16() {//获取“软件复位控制板”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“软件复位控制板”成功";
		} else if(temp0 == 0x31){
			TipsText += "“软件复位控制板”失败";
		} else{
			TipsText += "“软件复位控制板”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd17() {//获取“根据序列号设置控制板号”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“根据序列号设置控制板号”成功，复位后生效";
		} else if(temp0 == 0x31){
			TipsText += "“根据序列号设置控制板号”失败";
		} else{
			TipsText += "“根据序列号设置控制板号”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd18() {//获取“设置同卡时间”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“设置同卡时间”成功，复位后生效";
		} else if(temp0 == 0x31){
			TipsText += "“设置同卡时间”失败";
		} else{
			TipsText += "“设置同卡时间”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd19() {//获取“读取同卡时间”命令的接收数据
		TipsText += ("“读取同卡时间”成功，单位0.1秒，" +  GetReceiveData2to1Byte());
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd1A() {//获取“设置开门时间”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“设置开门时间”成功";
		} else if(temp0 == 0x31){
			TipsText += "“设置开门时间”失败";
		} else{
			TipsText += "“设置开门时间”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd1B() {//获取“读取开门时间”命令的接收数据
		TipsText += ("“读取开门时间”成功，单位0.1秒，" +  GetReceiveData2to1Byte());
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd1C() {//获取“设置带地感自动开存记录标志位”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“设置带地感自动开存记录标志位”成功";
		} else if(temp0 == 0x31){
			TipsText += "“设置带地感自动开存记录标志位”失败";
		} else{
			TipsText += "“设置带地感自动开存记录标志位”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd1D() {//获取“读取带地感自动开存记录标志位”命令的接收数据
		TipsText += ("“读取开门时间”成功，" +  GetReceiveData1Byte());
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd1E() {//获取“设置控制板类型”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“设置控制板类型”成功";
		} else if(temp0 == 0x31){
			TipsText += "“设置控制板类型”失败";
		} else{
			TipsText += "“设置控制板类型”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd1F() {//获取“读取控制板类型”命令的接收数据
		byte tmp = GetReceiveData1Byte();
		switch(tmp) {
			case 0x31:TipsText += "“读取控制板类型”成功，控制板类型：" + "单进";break;
			case 0x32:TipsText += "“读取控制板类型”成功，控制板类型：" + "单出";break;
			case 0x33:TipsText += "“读取控制板类型”成功，控制板类型：" + "一进一出";break;
			default:TipsText += "“读取控制板类型”成功，但是数据错误";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd21() {//获取“增加/修改一条用户资料”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“增加/修改一条用户资料”成功";
		} else if(temp0 == 0x31){
			TipsText += "“增加/修改一条用户资料”失败";
		} else{
			TipsText += "“增加/修改一条用户资料”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd22() {//获取“读取一条用户资料”命令的接收数据
		
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd23() {//获取“删除一条用户资料”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“删除一条用户资料”成功";
		} else if(temp0 == 0x31){
			TipsText += "“删除一条用户资料”失败";
		} else{
			TipsText += "“删除一条用户资料”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd24() {//获取“清除所有用户资料”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“清除所有用户资料”成功";
		} else if(temp0 == 0x31){
			TipsText += "“清除所有用户资料”失败";
		} else{
			TipsText += "“清除所有用户资料”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd25() {//获取“读取用户资料的数量”命令的接收数据
		short tmp = (short)(GetReceiveData2to1Byte());
		tmp = (short)((tmp << 16) & 0xff00);
		tmp += (short)(GetReceiveData2to1Byte());
		TipsText += "“读取用户资料的数量”成功，用户数量：" + tmp;
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd31() {//获取“读取一条记录”命令的接收数据
		
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd32() {//获取“删除一条记录”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“删除一条记录”成功";
		} else if(temp0 == 0x31){
			TipsText += "“删除一条记录”失败";
		} else{
			TipsText += "“删除一条记录”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd33() {//获取“清除所有记录”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“清除所有记录”成功";
		} else if(temp0 == 0x31){
			TipsText += "“清除所有记录”失败";
		} else{
			TipsText += "“清除所有记录”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd41() {//获取“软件开闸”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“软件开闸”成功";
		} else if(temp0 == 0x31){
			TipsText += "“软件开闸”失败";
		} else{
			TipsText += "“软件开闸”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd42() {//获取“软件锁定开闸”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“软件锁定开闸”成功";
		} else if(temp0 == 0x31){
			TipsText += "“软件锁定开闸”失败";
		} else{
			TipsText += "“软件锁定开闸”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd43() {//获取“解除锁定开闸”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“解除锁定开闸”成功";
		} else if(temp0 == 0x31){
			TipsText += "“解除锁定开闸”失败";
		} else{
			TipsText += "“解除锁定开闸”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd44() {//获取“控制蜂鸣”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“控制蜂鸣”成功";
		} else if(temp0 == 0x31){
			TipsText += "“控制蜂鸣”失败";
		} else{
			TipsText += "“控制蜂鸣”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd45() {//获取“切换开闸方式”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“切换开闸方式”成功";
		} else if(temp0 == 0x31){
			TipsText += "“切换开闸方式”失败";
		} else{
			TipsText += "“切换开闸方式”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd46() {//获取“确认开闸”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“确认开闸”成功";
		} else if(temp0 == 0x31){
			TipsText += "“确认开闸”失败";
		} else{
			TipsText += "“确认开闸”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private void GetReceiveDataCmd51() {//获取“转发显示”命令的接收数据
		byte temp0 = GetReceiveData1Byte();
		if(temp0 == 0x30) {
			TipsText += "“转发显示”成功";
		} else if(temp0 == 0x31){
			TipsText += "“转发显示”失败";
		} else{
			TipsText += "“转发显示”乱码";
		}
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private int IndexInc(int index) {//指针加一
		index++;
		if(index >= BUFFER_LENGTH) index = 0;
		return index;
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private byte GetReceiveData2to1Byte() {//获取接收数据的2个字节并且合并成为1个字节
		byte temp1 = GetReceiveData1Byte();
		byte temp2 = GetReceiveData1Byte();
		return (byte)(((temp1 & 0x0f) << 4) | (temp2 & 0x0f));
	}
	/* 方法------------------------------------------------------------------------------------------------------------- */
	private byte GetReceiveData1Byte() {//获取接收数据的1个字节
		ReceiveProtocolHeadIndex = IndexInc(ReceiveProtocolHeadIndex);
		return ReceiveDataBuf[ReceiveProtocolHeadIndex] ;
	}
}
