package com.testing.login;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UseMysql {

	private Connection ct;

	public UseMysql(Connection cnn) {
		ct=cnn;
	}
	

	public Map<String, String> getUserInfo(String name) {
		String sql = "SELECT * FROM userinfo where username='" + name + "';";
		System.out.println(sql);
	
		ResultSet rs = null;
	
		Statement sm;
		try {
		
			sm = ct.createStatement();
	
			rs = sm.executeQuery(sql);

			Map<String, String> map = new HashMap<String, String>();
		
			if (rs != null && rs.next()) {
			
				ResultSetMetaData rsmd = rs.getMetaData();
				
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				
					if (!(rsmd.getColumnName(i).equals("password") || rsmd.getColumnName(i).equals("id")))
						
						map.put(rsmd.getColumnName(i), rs.getString(i));
				}
				System.out.println(map.toString());
			}
			
			sm.close();
			rs.close();
			return map;
		} catch (SQLException e) {
		}
		return null;
	}
	
	
	public boolean Login(String name,String pwd) {

		String sql="select * from userinfo where username='"+name+"' and pwd='"+pwd+"'";
		System.out.println(sql);
		
		ResultSet rs=null;
		
		Statement sm;
		 try {
		
			sm=ct.createStatement();
	
			rs=sm.executeQuery(sql);
			List<HashMap<String,String>> dl=new ArrayList<HashMap<String,String>>();
			HashMap<String,String> map=null;
	
		while(rs!=null && rs.next()) {
			
			ResultSetMetaData rsmd=rs.getMetaData();
			map=new HashMap<String,String>();
			
			for(int i=1;i<=rsmd.getColumnCount();i++) {
//				System.out.println("rs.getString(i):"+rs.getString(i));
		
				map.put(rsmd.getColumnName(i),rs.getString(i) );
				
			}
			System.out.println(map.toString());
			dl.add(map);

		}
		System.out.println(dl);
		sm.close();
		rs.close();
		if(map.get("id")!=null) {
			return true;
		}
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		 return false;
	}

	public boolean Plogin(String name,String pwd) {
			try {
			
				CallableStatement cm=ct.prepareCall("call login(?,?)");
				cm.setString(1, name);
				cm.setString(2, pwd);
			
				ResultSet rs=cm.executeQuery();
				if(rs!=null && rs.next()) {
					ResultSetMetaData rsmd=rs.getMetaData();
					HashMap<String,String> map=new HashMap<String,String>();
					for(int i=1;i<=rsmd.getColumnCount();i++) {
						map.put(rsmd.getColumnName(i),rs.getString(i));
						
					}
					System.out.println(map.toString());
					cm.close();
					rs.close();
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return false;
	}

}
