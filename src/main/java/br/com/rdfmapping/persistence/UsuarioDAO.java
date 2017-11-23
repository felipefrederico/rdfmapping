package br.com.rdfmapping.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.rdfmapping.model.Usuario;

public class UsuarioDAO{
	
	public static void salvarUsuario(Usuario usuario){
		String SQL = "INSERT INTO usuario (nome,senha) VALUES("+
					 "'"+usuario.getNome()+"',"+
					 "'"+usuario.getSenha()+"');";
		try{
			PreparedStatement st = PSQLConnection.connect().prepareStatement(SQL);
			st.executeQuery();
			PSQLConnection.connect().close();
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public static boolean Login(Usuario usuario){
		String SQL = "SELECT id,nome FROM usuario WHERE nome='"+usuario.getNome()+
					 "' AND senha=MD5('"+usuario.getSenha()+"');";
		try{
			PreparedStatement st = PSQLConnection.connect().prepareStatement(SQL);
			ResultSet rs = st.executeQuery();
			PSQLConnection.connect().close();
			if(rs.next()){
				return true;
			}else{
				return false;
			}			
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
}
