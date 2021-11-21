/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.fatec.ProjetoInter2019_web.filho;

import br.com.fatec.projetointer2019_lib.dao.FilhoDAO;
import br.com.fatec.projetointer2019_lib.dao.GenericDAO;
import br.com.fatec.projetointer2019_lib.dao.ProgramasDAO;
import br.com.fatec.projetointer2019_lib.dao.RelatorioDAO;
import br.com.fatec.projetointer2019_lib.model.Filho;
import br.com.fatec.projetointer2019_lib.model.Programas;
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
@WebServlet(name = "FilhoCadastrar", urlPatterns = {"/FilhoCadastrar"})
public class FilhoCadastrar extends HttpServlet {

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
        int idFilho = Integer.parseInt(request.getParameter("idfilho"));
        int idLogin = Integer.parseInt(request.getParameter("idlogin"));
        String nome = request.getParameter("nome");
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        Date dataNascimento = Conversao.converterData(request.getParameter("datanascimento"));
        int idRelatorio = Integer.parseInt(request.getParameter("idrelatorio"));
        String descricao = request.getParameter("descricao");
        

            
        String mensagem = null;
  
        try{
            //busca objeto de cidade
            GenericDAO oRelatorioDAO = new RelatorioDAO();
            Relatorio oRelatorio = (Relatorio) oRelatorioDAO.carregar(idRelatorio);

            //gera objeto de cliente
            Filho oFilho = new Filho (idFilho,
                                       descricao,
                                       "A",
                                       idLogin,
                                       nome,
                                       usuario,
                                       senha,
                                       dataNascimento,
                                       oRelatorio);

            GenericDAO dao = new FilhoDAO();
            if (dao.cadastrar(oFilho)){
                mensagem = "Filho cadastrada com sucesso!";                
            } else {
                mensagem = "Problemas ao cadastrar Filho. "
                    + "Verifique os dados informados "
                        + "e tente novamente!";
            }

            request.setAttribute("mensagem", mensagem);
            response.sendRedirect("FilhoListar");

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
            Logger.getLogger(FilhoCadastrar.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(FilhoCadastrar.class.getName()).log(Level.SEVERE, null, ex);
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
