/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.projetointer2019_lib.dao;

import br.com.fatec.projetointer2019_lib.model.Pai;
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
public class PaiDAO implements GenericDAO{
    private Connection conexao;
        
    public PaiDAO() throws Exception{
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
            Pai oPai = (Pai) objeto;
            
            if (oPai.getIdPai()==0) {
                //verifica se já existe pessoa com este CPF cadastrada.
                int idPai = this.verificarUsuario(oPai.getUsuario());
                if (idPai==0) {
                    //se não encontrou insere
                    retorno = this.inserir(oPai);
                }else{
                    //se encontrou cliente com o cpf altera
                    oPai.setIdPai(idPai);
                    retorno = this.alterar(oPai);
                }
            } else {
              retorno = this.alterar(oPai);
            }
            
        } catch (Exception ex){
            System.out.println("Problemas ao incluir pai! Erro "+ex.getMessage());            
        }
        return retorno;

    }

    @Override
    public Boolean inserir(Object objeto) {
        Pai oPai = (Pai) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into pai (idLogin, cpf, observacao, situacao) values (?, ?, ?, ?)";
        
        try{
            loginDAO ologinDAO = new loginDAO();
            int idLogin = ologinDAO.cadastrar(oPai);
            
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idLogin);
            stmt.setString(2, oPai.getCpf());
            stmt.setString(3, oPai.getObservacao());
            stmt.setString(4, "A");
            stmt.execute();
            
            return true;
        }catch(Exception ex){
            System.out.println("Problemas ao incluir Pai! Erro "+ex.getMessage());
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
        Pai oPai = (Pai) objeto;
        PreparedStatement stmt = null;
        String sql = "update pai set cpf=?, observacao=?"
                + "where idPai=?";
        
        try{
            loginDAO ologinDAO = new loginDAO();
            ologinDAO.cadastrar(oPai);
            
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oPai.getCpf());
            stmt.setString(2, oPai.getObservacao());
            stmt.setInt(3, oPai.getIdPai());
            stmt.execute();
            
            return true;
        }catch(Exception ex){
            System.out.println("Problemas ao alterar pai! Erro: "+ex.getMessage());
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
            PaiDAO oPaiDAO = new PaiDAO();
            Pai oPai = (Pai) oPaiDAO.carregar(id);
            //verifica a situação do fornecedor
            String situacao="A";
            if(oPai.getSituacao().equals(situacao)){
                situacao = "I";
            }else{
                situacao = "A";
            }
            //prepara comando sql
            String sql = "update pai set situacao=? where idPai=?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, oPai.getIdPai());
            stmt.execute();
            return true;
        }catch (Exception ex){
            System.out.println("Problemas ao ativar/desativar pai! Erro: "+ex.getMessage());
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
        int idPai = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pai oPai = null;
        String sql = "Select p.*, c.idpai, c.cpf, c.observacao, c.situacao"
                + " from pai c, login p "
                + "where c.idlogin = p.idlogin and c.idpai=?";
        
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, idPai);
            rs=stmt.executeQuery();
            
            while(rs.next()){
                //Busca a cidade
                Relatorio oRelatorio = null;
                try{
                   RelatorioDAO oRelatorioDAO = new RelatorioDAO();; 
                   oRelatorio = (Relatorio) oRelatorioDAO.carregar(rs.getInt("idrelatorio"));                  
                }catch(Exception ex){
                   System.out.println("Problemas ao carregar relatorio!"
                       + "Erro:"+ex.getMessage());
                }
                
                oPai = new Pai(rs.getInt("idpai"),
                                       rs.getString("cpf"),
                                       rs.getString("observacao"),
                                       rs.getString("situacao"),
                                       rs.getInt("idlogin"),
                                       rs.getString("nome"),
                                       rs.getString("usuario"),
                                       rs.getString("senha"),
                                       rs.getDate("datanascimento"),
                                       oRelatorio);
            }
            return oPai;
        }catch(SQLException ex){
            System.out.println("Problemas ao carregar pai! Erro "+ex.getMessage());
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
        
        String sql= "Select p.*, c.idpai, c.cpf, c.observacao, c.situacao"
                + " from pai c, login p "
                + "where c.idlogin = p.idlogin order by idlogin";
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                
                //busca cidade
                Relatorio oRelatorio = null;
                try{
                   RelatorioDAO oRelatorioDAO = new RelatorioDAO();
                   oRelatorio = (Relatorio) oRelatorioDAO.carregar(rs.getInt("idrelatorio"));
                }catch(Exception ex){
                   System.out.println("Problemas ao carregar pai!"
                       + "Erro:"+ex.getMessage());
                }
                
                Pai  oPai = new Pai(rs.getInt("idpai"),
                                       rs.getString("cpf"),
                                       rs.getString("observacao"),
                                       rs.getString("situacao"),
                                       rs.getInt("idlogin"),
                                       rs.getString("nome"),
                                       rs.getString("usuario"),
                                       rs.getString("senha"),
                                       rs.getDate("datanascimento"),
                                       oRelatorio);

                resultado.add(oPai);
            }
        }catch(SQLException ex){
            System.out.println("Problemas ao listar pai! Erro "+ex.getMessage());
        }finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parâmetros d econexão! Erro: "+ex.getMessage());
            }
        }
        return resultado;
    }
    
    public int verificarUsuario(String usuario){
        PreparedStatement stmt = null;
        ResultSet rs= null;
        int idPai = 0;
        String sql = "Select f.* from pai f, login p "
                + "where f.idlogin = p.idlogin and p.usuario=?;";
        
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setString(1, usuario);
            rs=stmt.executeQuery();
            
            while(rs.next()){
                idPai = rs.getInt("idpai");
            }
            return idPai;
        }catch(SQLException ex){
            System.out.println("Problemas ai carregar pessoa! Erro: "+ex.getMessage());
            return idPai;
        }
    }   
}
