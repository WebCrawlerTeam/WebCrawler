package com.yuli.netspider;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.yuli.DB.DBconnection;

public class UserSearch extends DBconnection{
	DBconnection dbc = null;
	List<String> list = new ArrayList<String>();
	private Scanner sca;
	public UserSearch() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("请输入您要搜索的内容？");
//		sca = new Scanner(System.in);
//		String str = sca.nextLine();
		String str = "百度";
		System.out.println(str);
		try {
			list = (List<String>) this.DBconnec(str);
			for(String s:list) {
				System.out.println(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		UserSearch us = new UserSearch();
	}
}
