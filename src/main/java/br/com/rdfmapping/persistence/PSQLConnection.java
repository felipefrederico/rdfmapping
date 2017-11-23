package br.com.rdfmapping.persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum PSQLConnection {
	
		psqlConnection;
	
    	public static Connection connect(){
    		Connection con = null;
    		
	        try{
	          Class.forName("org.postgresql.Driver");	
	          con = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/rdf_mapping", "felipe", "1234");
	        }catch(ClassNotFoundException cnfx){
	        	System.out.println(cnfx.getMessage());
	        }	                   
	        catch (SQLException e){
	            System.err.print(e.getMessage());
	        }
	        
	        return con;
    }
}