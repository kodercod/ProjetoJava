/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.projetointer2019_lib.dao;

import br.com.fatec.projetointer2019_lib.model.Programas;
import br.com.fatec.projetointer2019_lib.model.Relatorio;
import br.com.fatec.projetointer2019_lib.model.login;
import br.com.fatec.projetointer2019_lib.utils.ConnectionFactory;
import br.com.fatec.projetointer2019_lib.utils.Conversao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author Luis Silva
 */
public class loginDAO {
    
    private Connection conexao;
    
    public loginDAO() throws Exception{
        try{
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    public int cadastrar(Object objeto) throws ParseException {
        login ologin = (login) objeto;
        int retorno = 0;
        
        if (ologin.getIdLogin()==0)
        {
            login objlogin = this.carregarUsuario(ologin.getUsuario());
            if (objlogin.getIdLogin()==0)
                retorno = this.inserir(ologin);
            else
                retorno = objlogin.getIdLogin();
        }
        else {
            retorno = this.alterar(ologin);
        }
        
        return retorno;
    }

    public int inserir(Object objeto) {
        login ologin = (login) objeto;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        Integer idLogin=null;
        String sql = "insert into login (nome,usuario,senha, datanascimento, idrelatorio) "
                + "values (?, ?, ?, ?, ?) returning idLogin;";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, ologin.getNome());
            stmt.setString(2, ologin.getUsuario());
            stmt.setString(3, ologin.getSenha());
            stmt.setDate(4, new java.sql.Date(ologin.getDataNascimento().getTime()));
            stmt.setInt(5, ologin.getRelatorio().getIdRelatorio());

            rs=stmt.executeQuery();
            while (rs.next()){
                idLogin = rs.getInt("idLogin");
            }
        }
        catch (SQLException ex){
            System.out.println("Problemas ao cadastrar login! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt,rs);
            }
            catch (Exception ex){
                System.out.println("Problemas ao fechar os parâmetos de conexão! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return idLogin;
    }

    public int alterar(Object objeto) {
        login ologin = (login) objeto;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        Integer idLogin=null;
        String sql = "update login set usuario=?, datanascimento=?, idrelatorio=? "
                + "where idlogin=? returning idLogin;";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, ologin.getUsuario());
            stmt.setDate(2, new java.sql.Date(ologin.getDataNascimento().getTime()));
            stmt.setInt(3, ologin.getRelatorio().getIdRelatorio());
            stmt.setInt(4, ologin.getIdLogin());
            rs=stmt.executeQuery();
            while (rs.next()){
                idLogin = rs.getInt("idLogin");
            }
        }
        catch (SQLException ex){
            System.out.println("Problemas ao cadastrar login! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt,rs);
            }
            catch (Exception ex){
                System.out.println("Problemas ao fechar os parâmetos de conexão! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return idLogin;

    }
    
    public login carregarUsuario(String usuario) throws ParseException {
       PreparedStatement stmt = null;
       ResultSet rs = null;
       login ologin = null;
       String sql = "Select * from login where usuario=?;";

       try{
            stmt=conexao.prepareStatement(sql);
            stmt.setString(1, usuario);
            rs=stmt.executeQuery();
           
            while (rs.next()){
               
               ologin = this.carregar(rs.getInt("idlogin"));
            }
            
            if (ologin == null)
            {
                Date novaData = Conversao.dataAtual();
                Relatorio oRelatorio = new Relatorio();
                ologin = new login(0,"","","",novaData,oRelatorio);
            }
            
       }
       catch(SQLException ex){
           System.out.println("Problemas ao carregar pessoa!"
                   + "Erro:"+ex.getMessage());
       }
      
       return ologin;
    }
    
    public login carregar(int id){
        int idLogin = id;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        login ologin = null;
        String sql = "Select * from login where idlogin=?";
        
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, idLogin);
            rs=stmt.executeQuery();            

            while(rs.next()){
                
                Relatorio oRelatorio = null;
                
                try{
                   RelatorioDAO oRelatorioDAO = new RelatorioDAO();
                                      
                   int idRelatorio = rs.getInt("idrelatorio");
                   
                   oRelatorio = (Relatorio) oRelatorioDAO.carregar(idRelatorio);
                  
                }catch(Exception ex){
                   System.out.println("Problemas ao carregar usuario!"
                       + "Erro:"+ex.getMessage());
                }

                
                ologin = new login(rs.getInt("idlogin"),
                                        rs.getString("nome"),
                                       rs.getString("usuario"),
                                       rs.getString("senha"),
                                       rs.getDate("datanascimento"),
                                       oRelatorio);
            }
            return ologin;
        }catch(SQLException ex){
            System.out.println("Problemas ao carregar comprador! Erro "+ex.getMessage());
            return null;
        }finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro "+ex.getMessage());
            }
        }           
    }
    
}
