/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.projetointer2019_lib.dao;

import br.com.fatec.projetointer2019_lib.model.Filho;
import br.com.fatec.projetointer2019_lib.model.Programas;
import br.com.fatec.projetointer2019_lib.model.Relatorio;
import br.com.fatec.projetointer2019_lib.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis Silva
 */
public class FilhoDAO implements GenericDAO{
        private Connection conexao;
        
    public FilhoDAO() throws Exception{
        try{
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso");
        }catch(Exception ex){
            throw new Exception("Problemas ao conectar no BD! Erro: "+ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Boolean retorno = false;
        try {
            Filho oFilho = (Filho) objeto;
            
            if (oFilho.getIdFilho()==0) {
                //verifica se já existe pessoa com este CPF cadastrada.
                int idFilho = this.verificarUsuario(oFilho.getUsuario());
                if (idFilho==0) {
                    //se não encontrou insere
                    retorno = this.inserir(oFilho);
                }else{
                    //se encontrou cliente com o cpf altera
                    oFilho.setIdFilho(idFilho);
                    retorno = this.alterar(oFilho);
                }
            } else {
              retorno = this.alterar(oFilho);
            }
            
        } catch (Exception ex){
            System.out.println("Problemas ao incluir fornecedor! Erro "+ex.getMessage());            
        }
        return retorno;

    }

    @Override
    public Boolean inserir(Object objeto) {
        Filho oFilho = (Filho) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into filho (idlogin, descricao, situacao) values (?, ?, ?)";
        
        try{
            loginDAO ologinDAO = new loginDAO();
            int idlogin = ologinDAO.cadastrar(oFilho);
            
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idlogin);
            stmt.setString(2, oFilho.getDescricao());
            stmt.setString(3, "A");
            stmt.execute();
            
            return true;
        }catch(Exception ex){
            System.out.println("Problemas ao incluir filho! Erro "+ex.getMessage());
            return false;
        }finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro: "+ex.getMessage());
            }
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Filho oFilho = (Filho) objeto;
        PreparedStatement stmt = null;
        String sql = "update filho set descricao=? "
                + "where idFilho=?";
        
        try{
            loginDAO ologinDAO = new loginDAO();
            ologinDAO.cadastrar(oFilho);
            
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oFilho.getDescricao());
            stmt.setInt(2, oFilho.getIdFilho());
            stmt.execute();
            
            return true;
        }catch(Exception ex){
            System.out.println("Problemas ao alterar filho! Erro: "+ex.getMessage());
            return false;
        }finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro "+ex.getMessage());
            }
        }
    }

    @Override
    public Boolean excluir(int id) {
        
        PreparedStatement stmt = null;
                
        try{
            //carrega dados de fornecedor
            FilhoDAO oFilhoDAO = new FilhoDAO();
            Filho oFilho = (Filho) oFilhoDAO.carregar(id);
            //verifica a situação do fornecedor
            String situacao="A";
            if(oFilho.getSituacao().equals(situacao)){
                situacao = "I";
            }else{
                situacao = "A";
            }
            //prepara comando sql
            String sql = "update filho set situacao=? where idFilho=?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, oFilho.getIdFilho());
            stmt.execute();
            return true;
        }catch (Exception ex){
            System.out.println("Problemas ao ativar/desativar filho! Erro: "+ex.getMessage());
            return false;
        }finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parâmetros de conexão! Erro: "+ex.getMessage());
            }
        }
    }

    @Override
    public Object carregar(int numero) {
        int idFilho = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Filho oFilho = null;
        String sql = "Select p.*, c.idfilho, c.descricao, c.situacao"
                + " from filho c, login p "
                + "where c.idlogin = p.idlogin and c.idfilho=?";
        
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, idFilho);
            rs=stmt.executeQuery();
            
            while(rs.next()){
                //Busca a cidade
                Relatorio oRelatorio = null;
                try{
                   RelatorioDAO oRelatorioDAO = new RelatorioDAO();
                   oRelatorio = (Relatorio) oRelatorioDAO.carregar(rs.getInt("idfilho"));                   
                }catch(Exception ex){
                   System.out.println("Problemas ao carregar filho!"
                       + "Erro:"+ex.getMessage());
                }
                
                oFilho = new Filho(rs.getInt("idfilho"),
                                       rs.getString("descricao"),
                                       rs.getString("situacao"),                                       
                                       rs.getInt("idlogin"),
                                       rs.getString("nome"),
                                       rs.getString("usuario"),
                                       rs.getString("senha"),
                                       rs.getDate("datanascimento"),
                                       oRelatorio);
            }
            return oFilho;
        }catch(SQLException ex){
            System.out.println("Problemas ao carregar filho! Erro "+ex.getMessage());
            return null;
        }finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro "+ex.getMessage());
            }
        }   
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql= "Select p.*, c.idfilho, c.descricao, c.situacao"
                + " from filho c, login p "
                + "where c.idlogin = p.idlogin order by idlogin";
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                
                //busca cidade
                Relatorio oRelatorio = null;
                try{
                   RelatorioDAO oRelatorioDAO = new RelatorioDAO();
                   oRelatorio = (Relatorio) oRelatorioDAO.carregar(rs.getInt("idfilho"));
                }catch(Exception ex){
                   System.out.println("Problemas ao carregar filho!"
                       + "Erro:"+ex.getMessage());
                }
                
                Filho  oFilho = new Filho(rs.getInt("idfilho"),
                                       rs.getString("descricao"),
                                       rs.getString("situacao"),                                       
                                       rs.getInt("idlogin"),
                                       rs.getString("nome"),
                                       rs.getString("usuario"),
                                       rs.getString("senha"),
                                       rs.getDate("datanascimento"),
                                       oRelatorio);
            

                resultado.add(oFilho);
            }
        }catch(SQLException ex){
            System.out.println("Problemas ao listar filho! Erro "+ex.getMessage());
        }finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parâmetros de conexão! Erro: "+ex.getMessage());
            }
        }
        return resultado;
    }
    
    public int verificarUsuario(String usuario){
        PreparedStatement stmt = null;
        ResultSet rs= null;
        int idFilho = 0;
        String sql = "Select f.* from filho f, login p "
                + "where f.idlogin = p.idlogin and p.usuario=?;";
        
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setString(1, usuario);
            rs=stmt.executeQuery();
            
            while(rs.next()){
                idFilho = rs.getInt("idfilho");
            }
            return idFilho;
        }catch(SQLException ex){
            System.out.println("Problemas ai carregar filho! Erro: "+ex.getMessage());
            return idFilho;
        }
    }
}
