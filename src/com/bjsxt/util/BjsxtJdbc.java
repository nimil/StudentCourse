package com.bjsxt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//封装jdbc
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;
public class BjsxtJdbc {

	private static Logger logger = Logger.getLogger(BjsxtJdbc.class.getName());
	//声明参数信息
	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String userpwd = null;
	
	//静态代码块，当类被加载自动执行，而且只执行一次
	static{
		try {
			//创建properties对象
			Properties properties = new Properties();
			properties.load(BjsxtJdbc.class.getClassLoader().getResourceAsStream("bjsxJdbc.properties"));
			String drivername = properties.getProperty("driver");
			driver = properties.getProperty(drivername+"driver");
			url = properties.getProperty(drivername+"url");
			username = properties.getProperty(drivername+"username");
			userpwd = properties.getProperty(drivername+"userpwd");
		} catch (Exception e) {
			System.out.println("BjsxtJdbc.enclosing_method(读取配置文件发出现错误)");
			e.printStackTrace();
		}
		
	}
	//创建连接
	public static Connection getConnect(){
		//声明连接
		Connection connection = null;
		try {
			//1：加载一个Driver驱动
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, userpwd);
		} catch (ClassNotFoundException e) {
			logger.error("创建连接出错1");
			System.out.println("BjsxtJdbc.getConnect(检查是否添加驱动包)");
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error("创建连接出错2");
			System.out.println("BjsxtJdbc.getConnect(请检查连接参数)："+"[url]:"+url+"[username]:"+username+"[userpwd]:"+userpwd);
			e.printStackTrace();
		}
		return connection;
	}
	
	//使用连接池创建连接
/*	public static Connection getConnect(){
		//1:通过配置文件获取连接
		//Connection connection = ConnectPoolFactory.getInstance().getConnect();
		
		//2:通过web.xml获取连接
		Connection connection = null;
		try {
			System.out.println("BjsxtJdbc.getConnect(创建连接)");
			logger.info("info创建连接");
			logger.debug("debug创建连接");
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			connection = DriverManager.getConnection("proxool.test");
		} catch (Exception e) {
			logger.error("创建连接出错");
			e.printStackTrace();
		}
		return connection;
	}*/
	
	//创建sql命令发送器--preparedStatement
	public static PreparedStatement getPreparedStatement(Connection connection,CharSequence sql){
		//声明sql命令发送器
		PreparedStatement preparedStatement = null;
		try {
			logger.info("创建sql命令发送器");
			preparedStatement = connection.prepareStatement(sql.toString());
		} catch (SQLException e) {
			logger.error("创建sql命令发送器出错");
			e.printStackTrace();
		}
		return preparedStatement;
	}
	
	//创建sql命令发送器--Statement
	public static Statement getStatement(Connection connection){
		//声明sql命令发送器
		Statement statement = null;
		try {
			logger.info("创建sql命令发送器");
			statement = connection.createStatement();
		} catch (SQLException e) {
			logger.error("创建sql命令发送器出错");
			e.printStackTrace();
		}
		return statement;
	}
	
	//关闭资源连接
	public static void closeAll(ResultSet resultSet,Statement statement,Connection connection){
		logger.info("关闭连接");
		//后打开的先关闭
		if(resultSet!=null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error("关闭连接失败");
				e.printStackTrace();
			}
		}
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error("关闭连接失败");
				e.printStackTrace();
			}
		}
		
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("关闭连接失败");
				e.printStackTrace();
			}
		}
		
	}
	
	
	//封装DML方法
	public static int excuteDML(CharSequence sql,Object...obj){
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		//声明一个变量
		int n = 0;
		try {
			//创建连接
			connection  = getConnect();
			ps = getPreparedStatement(connection, sql.toString());
			//设置值
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i+1, obj[i]);
			}
			n = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll(null, ps, connection);
		}
		return n;
	}
	
}
