/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.projetointer2019_lib.dao;

import br.com.fatec.projetointer2019_lib.model.Internet;
import br.com.fatec.projetointer2019_lib.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeffersonpasserini
 */
public class InternetDAO implements GenericDAO {
    
    private Connection conexao;
    
    public InternetDAO() throws Exception{
        try {
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com Sucesso"); 
       } catch (Exception ex) {
            System.out.println("Problemas ao conectar no BD! Erro: "
                    +ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Internet oInternet = (Internet) objeto;
        Boolean retorno=false;
        if (oInternet.getIdInternet()== 0) {
            retorno = this.inserir(oInternet);
        }else{
            retorno = this.alterar(oInternet);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Internet oInternet = (Internet) objeto;
        PreparedStatement stmt = null;
        
        String sql = "insert into internet (nomepagina, enderecopagina, dataacesso, horaacesso) values (?,?,?,?)";  
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oInternet.getNomePagina()); 
            stmt.setString(2, oInternet.getEnderecoPagina());
            stmt.setString(3, oInternet.getDataAcesso());
            stmt.setString(4, oInternet.getHoraAcesso());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar o Site! Erro: "+ex.getMessage());
            return false;
        }
        finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parametros de conexão! "
                        + "Erro: "+ex.getMessage());
            }
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Internet oInternet = (Internet) objeto;
        PreparedStatement stmt = null;
        String sql= "update internet set nomepagina=?, enderecopagina=?, dataacesso=?, horaacesso=? where idinternet=?"; 
        try {
            stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, oInternet.getNomePagina());
            stmt.setString(2, oInternet.getEnderecoPagina());
            stmt.setString(3, oInternet.getDataAcesso());
            stmt.setString(4, oInternet.getHoraAcesso());
            stmt.setInt(5, oInternet.getIdInternet());                       
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar o Site! Erro: "
                    +ex.getMessage());
            return false;
        }
        finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parametros de conexão! "
                        + "Erro: "+ex.getMessage());
            }
        }
        
    }

    @Override
    public Boolean excluir(int numero) {
        int idInternet = numero;
        PreparedStatement stmt= null;

        String sql = "delete from internet where idInternet=?";
        try {
            stmt = conexao.prepareStatement(sql);         
            stmt.setInt(1, idInternet);            
            stmt.execute();
            return true;         
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir o Site! Erro: "
                    +ex.getMessage());
            return false;           
        }
        finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parametros de conexão! "
                        + "Erro: "+ex.getMessage());
            }
        }
    }

    @Override
    public Object carregar(int numero) {
        int idInternet = numero;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        Internet oInternet = null;
        String sql="select * from internet where idInternet=?";
        
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idInternet);
            rs=stmt.executeQuery();          
            while (rs.next()) {                
                oInternet = new Internet();
                oInternet.setIdInternet(rs.getInt("idInternet"));
                oInternet.setNomePagina(rs.getString("nomePagina"));
                oInternet.setEnderecoPagina(rs.getString("enderecoPagina"));
                oInternet.setDataAcesso(rs.getString("dataAcesso"));
                oInternet.setHoraAcesso(rs.getString("horaAcesso"));

            }
            return oInternet;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Site! Erro: "
                    +ex.getMessage());
            return false;   
        }
        finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parametros de conexão! "
                        + "Erro: "+ex.getMessage());
            }
        }
        
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from internet";               
        try {
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();           
            while (rs.next()) {                
                Internet oInternet = new Internet();
                oInternet.setIdInternet(rs.getInt("idInternet"));
                oInternet.setNomePagina(rs.getString("nomePagina"));
                oInternet.setEnderecoPagina(rs.getString("enderecoPagina"));
                oInternet.setDataAcesso(rs.getString("dataAcesso"));
                oInternet.setHoraAcesso(rs.getString("horaAcesso"));
                resultado.add(oInternet);
            }
        
        }catch (SQLException ex) {
            System.out.println("Problemas ao listar Site! Erro: " +ex.getMessage());
        }
        finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt,rs);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parametros de conexão! "
                        + "Erro: "+ex.getMessage());
            }
        }
        return resultado;
        
    }    
}
