package com.bjsxt.test;

import java.sql.Connection;
import java.sql.DriverManager;

import com.bjsxt.util.ConnectPoolFactory;

public class TestConnectPool {

	public static void main(String[] args) throws Exception {
		//使用连接池
		long start = System.currentTimeMillis();
		for(int i=0; i< 1000; i++){
			Connection conn = ConnectPoolFactory.getInstance().getConnect();
			conn.close();
		}
		long end = System.currentTimeMillis();
		
		System.out.println(end-start);

		//使用普通方式
		start = System.currentTimeMillis();
		for(int i=0; i<=1000; i++){
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn1 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott", "1234");
			conn1.close();
		}
		end = System.currentTimeMillis();
		
		System.out.println(end-start);
	}

}
