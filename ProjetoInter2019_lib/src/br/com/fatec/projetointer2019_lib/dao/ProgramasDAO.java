/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.projetointer2019_lib.dao;

import br.com.fatec.projetointer2019_lib.model.Programas;
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
public class ProgramasDAO implements GenericDAO {
    
    private Connection conexao;
    
    public ProgramasDAO() throws Exception{
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
        Programas oProgramas = (Programas) objeto;
        Boolean retorno=false;
        if (oProgramas.getIdPrograma()== 0) {
            retorno = this.inserir(oProgramas);
        }else{
            retorno = this.alterar(oProgramas);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Programas oProgramas = (Programas) objeto;
        PreparedStatement stmt = null;
        
        String sql = "insert into programas (nomeprograma, dataacesso, horaacesso) values (?,?,?)";  
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oProgramas.getNomePrograma());        
            stmt.setString(2, oProgramas.getDataAcesso());
            stmt.setString(3, oProgramas.getHoraAcesso());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar o Programa! Erro: "+ex.getMessage());
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
        Programas oProgramas = (Programas) objeto;
        PreparedStatement stmt = null;
        String sql= "update programas set nomeprograma=?, dataacesso=?, horaacesso=? where idprograma=?"; 
        try {
            stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, oProgramas.getNomePrograma());
            stmt.setString(2, oProgramas.getDataAcesso());
            stmt.setString(3, oProgramas.getHoraAcesso());
            stmt.setInt(4, oProgramas.getIdPrograma());                       
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar o Programa! Erro: "
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
        int idPrograma = numero;
        PreparedStatement stmt= null;

        String sql = "delete from programas where idPrograma=?";
        try {
            stmt = conexao.prepareStatement(sql);         
            stmt.setInt(1, idPrograma);            
            stmt.execute();
            return true;         
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir o Programa! Erro: "
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
        int idPrograma = numero;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        Programas oProgramas = null;
        String sql="select * from programas where idprograma=?";
        
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idPrograma);
            rs=stmt.executeQuery();          
            while (rs.next()) {                
                oProgramas = new Programas();
                oProgramas.setIdPrograma(rs.getInt("idPrograma"));
                oProgramas.setNomePrograma(rs.getString("nomePrograma"));
                oProgramas.setDataAcesso(rs.getString("dataAcesso"));
                oProgramas.setHoraAcesso(rs.getString("horaAcesso"));

            }
            return oProgramas;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Programa! Erro: "
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
        String sql = "Select * from programas";               
        try {
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();           
            while (rs.next()) {                
                Programas oProgramas = new Programas();
                oProgramas.setIdPrograma(rs.getInt("idPrograma"));
                oProgramas.setNomePrograma(rs.getString("nomePrograma"));
                oProgramas.setDataAcesso(rs.getString("dataAcesso"));
                oProgramas.setHoraAcesso(rs.getString("horaAcesso"));
                resultado.add(oProgramas);
            }
        
        }catch (SQLException ex) {
            System.out.println("Problemas ao listar Programas! Erro: " +ex.getMessage());
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
