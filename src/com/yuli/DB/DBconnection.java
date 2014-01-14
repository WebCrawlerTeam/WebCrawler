package com.yuli.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.yuli.netspider.torrentLink;
import com.yuli.netspider.utils;

/*
 * 作者：于立
 * 这是一个数据库的连接以及以及创建类
 */
public class DBconnection {
	Connection conn = null;
	//连接数据库
	public void DBconnec() {
		String frontpage = utils.URLS;
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dburl = "jdbc:mysql://localhost:3306?useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection(dburl, utils.USER, utils.PASSWORD);
			
			if(!conn.isClosed()) {
				System.out.println("数据库连接成功！");
			}
			else {
				System.out.println("数据库连接失败！");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//如果数据库不存在，则创建他
	public void DBcreate() {
		//创建数据库，完成初始化
				String sql = null;
//		        String url = frontpage;
				String url = torrentLink.getTorrent();
		        Statement stmt = null;
		        PreparedStatement pstmt = null;
		        ResultSet rs = null;
		        int count = 0;
				if(conn != null) {
					try{
						sql = "CREATE DATABASE IF NOT EXISTS crawler DEFAULT CHARSET=utf8;";
						stmt = conn.createStatement();
						stmt.executeUpdate(sql);
						
						sql = "USE crawler";
						stmt = conn.createStatement();
						stmt.executeUpdate(sql);
						
						sql = "create table if not exists record (recordID int(5) not null auto_increment, URL text not null, crawled tinyint(1) not null, primary key (recordID)) engine=InnoDB DEFAULT CHARSET=utf8";
						stmt = conn.createStatement();
						stmt.executeUpdate(sql);
						
						sql = "create table if not exists webcontent (contentID int(5) not null auto_increment, URL2 text not null, webTitle text, webContent text, primary key (contentID)) engine=InnoDB DEFAULT CHARSET=utf8";
						stmt = conn.createStatement();
						stmt.executeUpdate(sql);
						
						sql = "create table if not exists tags (tagnum int(4) not null auto_increment, tagname text not null, primary key (tagnum)) engine=InnoDB DEFAULT CHARSET=utf8";
						stmt = conn.createStatement();
						stmt.executeUpdate(sql);
						
						sql = "INSERT INTO record (URL, crawled) VALUES ('" + url + "',0)";
		    			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    			pstmt.execute();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
	}
}
