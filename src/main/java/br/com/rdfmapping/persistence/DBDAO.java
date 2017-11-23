package br.com.rdfmapping.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBDAO {
	public static ArrayList<String> GetTables(){
		ArrayList<String> tables = new ArrayList<String>();
		
		String SQL = "SELECT table_name FROM information_schema.tables"+
                      " WHERE table_schema='open_data'";
		try{
			PreparedStatement st = PSQLConnection.connect().prepareStatement(SQL);
			ResultSet rs = st.executeQuery();
			while (rs.next()){
				tables.add(rs.getString("table_name") );
			}
			PSQLConnection.connect().close();
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		
		return tables;
	}
	
	public static ArrayList<String> GetColumns(String table){
		ArrayList<String> columns = new ArrayList<String>();
		
		String SQL = "SELECT column_name FROM information_schema.columns WHERE "
				+ "table_name = '"+table+"';";
		try{
			PreparedStatement st = PSQLConnection.connect().prepareStatement(SQL);
			ResultSet rs = st.executeQuery();
			while (rs.next()){
				columns.add(rs.getString("column_name") );
			}
			PSQLConnection.connect().close();
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		
		return columns;
	}
	
	public static ResultSet SelectAllFrom(String table){
		ResultSet data = null;
		
		String SQL = "SELECT * FROM open_data."+table+";";
		try{
			PreparedStatement st = PSQLConnection.connect().prepareStatement(SQL);
			data = st.executeQuery();
			PSQLConnection.connect().close();
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		
		return data;
	}

}
