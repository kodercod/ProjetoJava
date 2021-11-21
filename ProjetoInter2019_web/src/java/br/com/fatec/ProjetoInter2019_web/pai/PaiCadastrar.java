/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.ProjetoInter2019_web.pai;

import br.com.fatec.projetointer2019_lib.dao.GenericDAO;
import br.com.fatec.projetointer2019_lib.dao.PaiDAO;
import br.com.fatec.projetointer2019_lib.dao.RelatorioDAO;
import br.com.fatec.projetointer2019_lib.model.Pai;
import br.com.fatec.projetointer2019_lib.model.Relatorio;
import br.com.fatec.projetointer2019_lib.utils.Conversao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luis Silva
 */
@WebServlet(name = "PaiCadastrar", urlPatterns = {"/PaiCadastrar"})
public class PaiCadastrar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=iso-8859-1");
        //pega dados do formulario
        int idPai = Integer.parseInt(request.getParameter("idpai"));
        int idLogin = Integer.parseInt(request.getParameter("idlogin"));
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        Date dataNascimento = Conversao.converterData(request.getParameter("datanascimento"));
        int idRelatorio = Integer.parseInt(request.getParameter("idrelatorio"));
        String observacao = request.getParameter("observacao");
        

            
        String mensagem = null;
  
        try{
            //busca objeto de cidade
            GenericDAO oRelatorioDAO = new RelatorioDAO();
            Relatorio oRelatorio = (Relatorio) oRelatorioDAO.carregar(idRelatorio);
            //gera objeto de cliente
            Pai oPai = new Pai (idPai,
                                       cpf,
                                       observacao,
                                       "A",
                                       idLogin,
                                       nome,
                                       usuario,
                                       senha,
                                       dataNascimento,
                                       oRelatorio);

            GenericDAO dao = new PaiDAO();
            if (dao.cadastrar(oPai)){
                mensagem = "Pai cadastrada com sucesso!";                
            } else {
                mensagem = "Problemas ao cadastrar Pai. "
                    + "Verifique os dados informados "
                        + "e tente novamente!";
            }

            request.setAttribute("mensagem", mensagem);
            response.sendRedirect("PaiListar");

        } catch (Exception ex){
             System.out.println("Problemas no Servlet ao cadastrar"
                     + " Usuário! Erro: " + ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(PaiCadastrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(PaiCadastrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
