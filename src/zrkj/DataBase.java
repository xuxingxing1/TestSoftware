package zrkj;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	String mysqlDriver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CarPark";
	String newUrl = "jdbc:mysql://localhost:3306";
	String username = "root";
	String password = "root";
	//Connection conn = null;
	Connection conn;
	//Connection newConn = null;
	Connection newConn;
	public static void main(String[] args) {
		String database = "test2";
		System.out.println("haha");
		new DataBase().getConn(database);
		System.out.println("haha");
	}
	
	public Connection getConn(String database) {
		System.out.println("haha");
		try{
			Class.forName(mysqlDriver);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try{
			String tableSql = "create table t_user (username varchar(50) not null primary key,"
					+ "password varchar(20) not null);";
			String databaseSql = "create database " + database;
			conn = DriverManager.getConnection(url, username, password);
			Statement smt = conn.createStatement();
			System.out.println("haha");
			if(conn != null) {
				System.out.println("数据库连接成功!");
				smt.executeUpdate(databaseSql);
				newConn = DriverManager.getConnection(newUrl + database, username, password);
				if(newConn != null) {
					System.out.println("已经连接到新创建的数据库：" + database);
					Statement newSmt = newConn.createStatement();
					int i = newSmt.executeUpdate(tableSql);
					if(i == 0) {
						System.out.println(tableSql + "表已经创建成功");
					}
				}
			} else {
				System.out.println("haha");
			}
		} catch(SQLException e1) {
			e1.printStackTrace();
		}
		return conn;
	}
	
}
