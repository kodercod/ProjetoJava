/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.projetointer2019_lib.dao;


import br.com.fatec.projetointer2019_lib.model.Internet;
import br.com.fatec.projetointer2019_lib.model.Programas;
import br.com.fatec.projetointer2019_lib.model.Relatorio;
import br.com.fatec.projetointer2019_lib.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeffersonpasserini
 */
public class RelatorioDAO implements GenericDAO {
    
    private Connection conexao;
    
    public RelatorioDAO() throws Exception{
        try {
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com Sucesso"); 
       } catch (Exception ex) {
            System.out.println("Problemas ao conectar no BD! Erro: "+ex.getMessage());
        }
    }
    
    @Override
    public Boolean cadastrar(Object object) {
        Relatorio oRelatorio = (Relatorio) object;
        Boolean retorno=false;
        if (oRelatorio.getIdRelatorio()== 0) {
            retorno = this.inserir(oRelatorio);
        }else{
            retorno = this.alterar(oRelatorio);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object object) {
        Relatorio oRelatorio = (Relatorio) object;
        PreparedStatement stmt = null;
        
        String sql = "insert into relatorio (datarelatorio, horarelatorio, idprograma, idinternet) values (?,?,?,?)";  
        try {
            stmt = conexao.prepareStatement(sql);      
            stmt.setString(1, oRelatorio.getDataRelatorio());
            stmt.setString(2, oRelatorio.getHoraRelatorio());
            stmt.setInt(3, oRelatorio.getProgramas().getIdPrograma());
            stmt.setInt(4, oRelatorio.getInternet().getIdInternet());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar o Relatorio! Erro: "+ex.getMessage());
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
    public Boolean alterar(Object object) {
        Relatorio oRelatorio = (Relatorio) object;
        PreparedStatement stmt = null;
        String sql= "update relatorio set datarelatorio=?, horarelatorio=?, idprograma=?, idinternet=? where idrelatorio=?"; 
        try {
            stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, oRelatorio.getDataRelatorio());
            stmt.setString(2, oRelatorio.getHoraRelatorio());
            stmt.setInt(3, oRelatorio.getProgramas().getIdPrograma());
            stmt.setInt(4, oRelatorio.getInternet().getIdInternet());
            stmt.setInt(5, oRelatorio.getIdRelatorio());                       
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar o Relatorio! Erro: "
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
        int idRelatorio = numero;
        PreparedStatement stmt= null;

        String sql = "delete from relatorio where idRelatorio=?";
        try {
            stmt = conexao.prepareStatement(sql);         
            stmt.setInt(1, idRelatorio);            
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
        int idRelatorio = numero;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        Relatorio oRelatorio = null;
        String sql="select * from relatorio where idRelatorio=?";
        
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idRelatorio);
            rs=stmt.executeQuery();          
            while (rs.next()) {                
                oRelatorio = new Relatorio();
                oRelatorio.setIdRelatorio(rs.getInt("idrelatorio"));
                oRelatorio.setDataRelatorio(rs.getString("dataRelatorio"));
                oRelatorio.setHoraRelatorio(rs.getString("horaRelatorio"));

                ProgramasDAO oProgramasDAO = new ProgramasDAO();
                oRelatorio.setProgramas((Programas) oProgramasDAO.carregar(rs.getInt("idPrograma")));
                InternetDAO oInternetDAO = new InternetDAO();
                oRelatorio.setInternet((Internet) oInternetDAO.carregar(rs.getInt("idInternet")));
            }
            return oRelatorio;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Relatorio! Erro: "
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
        String sql = "Select * from relatorio order by idrelatorio";               
        try {
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();           
            while (rs.next()) {                
                Relatorio oRelatorio = new Relatorio();
                oRelatorio.setIdRelatorio(rs.getInt("idRelatorio"));
                oRelatorio.setDataRelatorio(rs.getString("dataRelatorio"));
                oRelatorio.setHoraRelatorio(rs.getString("horaRelatorio"));

                try{
                    ProgramasDAO oProgramasDAO = new ProgramasDAO();
                    oRelatorio.setProgramas((Programas) oProgramasDAO.carregar(rs.getInt("idprograma")));
                    InternetDAO oInternetDAO = new InternetDAO();
                    oRelatorio.setInternet((Internet) oInternetDAO.carregar(rs.getInt("idinternet")));
                } catch (Exception ex) {
                    System.out.println("Problemas ao listar Relatorio! Erro: "+ex.getMessage());
                }
                
                resultado.add(oRelatorio);
            }
        
        }catch (SQLException ex) {
            System.out.println("Problemas ao listar Relatorio! Erro: "+ex.getMessage());
        }
        finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parametros de conexão! Erro: "+ex.getMessage());
            }
        }
        return resultado;
    }

    public List<Relatorio> listar(int idPrograma, int idInternet) {
        List<Relatorio> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from relatorio where idprograma = ?, idinternet = ? order by idrelatorio";               
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idPrograma);
            stmt.setInt(2, idInternet);
            rs=stmt.executeQuery();           
            while (rs.next()) {                
                 Relatorio oRelatorio = new Relatorio();
                oRelatorio.setIdRelatorio(rs.getInt("idRelatorio"));
                oRelatorio.setDataRelatorio(rs.getString("dataRelatorio"));
                oRelatorio.setHoraRelatorio(rs.getString("horaRelatorio"));

                try{
                    ProgramasDAO oProgramasDAO = new ProgramasDAO();
                    oRelatorio.setProgramas((Programas)oProgramasDAO.carregar(rs.getInt("idprograma")));
                    InternetDAO oInternetDAO = new InternetDAO();
                    oRelatorio.setInternet((Internet)oInternetDAO.carregar(rs.getInt("idinternet")));
                } catch (Exception ex) {
                    System.out.println("Problemas ao listar relatorio! Erro: "
                            +ex.getMessage());
                }
                
                resultado.add(oRelatorio);
            }
        
        }catch (SQLException ex) {
            System.out.println("Problemas ao listar Relatorio! Erro: "
                    +ex.getMessage());
        }
        finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parametros de conexão! "
                        + "Erro: "+ex.getMessage());
            }
        }
        return resultado;
    }    
}
