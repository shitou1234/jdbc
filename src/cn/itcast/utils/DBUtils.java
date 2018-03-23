package cn.itcast.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DBUtils {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	static{
		//读取配置文件
		ResourceBundle rb = ResourceBundle.getBundle("db");
		
		driver = rb.getString("driver");
		url = rb.getString("url");
		username = rb.getString("username");
		password = rb.getString("password");
	}
	
	
	public static Connection getConnection(){
		
		//注册驱动
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("注册驱动失败!别玩了!");
			}
		//获得连接
			try {
				return DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("获得连接失败!请检查你的用户名密码url!");
			}
	}
	
	
	public static void close(ResultSet rs,Statement st,Connection conn){

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						if (conn != null) {
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}

			}
		}
		
	}

}
