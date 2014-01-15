package com.yuli.netspider;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.yuli.DB.DBconnection;
import com.yuli.DB.resultBean;
import com.yuli.tools.Analyz;

public class UserSearch extends DBconnection{
	DBconnection dbc = null;
//	List<resultBean> list = new ArrayList<resultBean>();
	List<String> list = new ArrayList<String>();
	List<String> list2 = new ArrayList<String>();
	private Scanner sca;
	public UserSearch() throws ClassNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
		boolean flag = true;
		System.out.println("欢迎使用WebSpider搜索");
		System.out.println("by:网络爬虫小组");
		while(flag){
			System.out.println("请输入您要搜索的内容？");
			sca = new Scanner(System.in);
			String str = sca.nextLine();
	//		String str = "百度";
			list2 = Analyz.fenci(str);
	//		System.out.println(str);
			try {
				for(int i=0;i<list2.size();i++) {
					String temp = list2.get(i);
					System.out.println("--------------------------------------------------");
					System.out.println("这是("+temp+")的搜索结果：");
//					list = (List<resultBean>) this.DBconnec(temp);
					list = (List<String>) this.DBconnec(temp);
					if(list.size() <=1) {
						System.out.println("抱歉，没有您想要的结果。。。o(╯□╰)o");
					}else {
//						for(resultBean s:list) {
//							System.out.println(s.getTitle());
//							System.out.println(s.getUrl());
//						}
						for(int j=0;j<list.size();j++){
//							System.out.println(j+1+"、"+list.get(j).getTitle());
//							System.out.println(list.get(j).getUrl());
							System.out.println(list.get(j));
						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("继续输入请输入1,退出输入0？");
//			int s = sca.nextInt();
			if(sca.nextInt() == 1) {
				flag = true;
			}else{
				flag = false;
			}
		}
		System.out.println("服务已经结束！谢谢使用");
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
//		UserSearch us = new UserSearch();
		mainPro mp = new mainPro();
		mp.start();
//		UserSearch us = new UserSearch();
		
	}
}
