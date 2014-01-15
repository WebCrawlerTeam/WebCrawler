package com.yuli.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yuli.netspider.torrentLink;
import com.yuli.netspider.utils;


/*
 * 作者：于立
 * 这是一个数据库的连接以及以及创建类
 */
public class DBconnection {
	Connection conn = null;
	Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList<resultBean> arr = new ArrayList<resultBean>();
    resultToList resulttolist = null;
	//连接数据库
	@SuppressWarnings("finally")
	public List<String> DBconnec(String s) throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.jdbc.Driver");
			String dburl = "jdbc:mysql://localhost:3306?useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection(dburl, utils.USER, utils.PASSWORD);
			
			if(!conn.isClosed()) {
//				System.out.println("数据库连接成功！");
			}
			else {
				System.out.println("数据库连接失败！");
			}
			DBcreate();
			return dataSelect(s);
	}
	//如果数据库不存在，则创建他
	public void DBcreate() {
		//创建数据库，完成初始化
				String sql = null;
				String frontpage = utils.URLS;
		        String url = frontpage;
//				String url = torrentLink.getTorrent();
		        
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
						
						sql = "INSERT INTO record (URL, crawled) VALUES ('" + frontpage + "',0)";
		    			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    			pstmt.execute();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
	}
	//数据选择操作
	public List<String> dataSelect(String searchContent) throws SQLException{
			String sql = null;
			List<String> ls = new ArrayList<String>();
//			sql = "SELECT URL2, webTitle FROM webcontent";
			sql = "SELECT URL2, webTitle FROM webcontent";
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
//				System.out.println("非空！");
			}
			arr = (ArrayList<resultBean>) resultToList.rtl(rs);
			for(int i=0;i<arr.size();i++) {
				String temp = arr.get(i).getTitle();
				boolean flag = temp.contains(searchContent);
				if(flag) {
					ls.add(arr.get(i).getUrl());
				}
			}
			return ls;
	}
}
