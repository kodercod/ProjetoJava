/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.ProjetoInter2019_web.relatorio;

import br.com.fatec.projetointer2019_lib.dao.GenericDAO;
import br.com.fatec.projetointer2019_lib.dao.InternetDAO;
import br.com.fatec.projetointer2019_lib.dao.ProgramasDAO;
import br.com.fatec.projetointer2019_lib.model.Relatorio;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "RelatorioNovo", urlPatterns = {"/RelatorioNovo"})
public class RelatorioNovo extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        String mensagem = null;
        try{
            //cria cidade vazia
            Relatorio oRelatorio = new Relatorio();
            //Gera lista de estado
            GenericDAO oProgramasDAO = new ProgramasDAO();
            request.setAttribute("programas", oProgramasDAO.listar());
            GenericDAO oInternetDAO = new InternetDAO();
            request.setAttribute("sites", oInternetDAO.listar());
            //cria variavel no servidor para armazenar objeto de lombada
            request.setAttribute("relatorio", oRelatorio);
            
            //dispacha objeto de lombada para a pagina jsp
            request.getRequestDispatcher("/cadastros/Relatorio/relatorioCadastrar.jsp")
                    .forward(request, response);
        }
        catch(Exception ex){
            System.out.println("Problemas no Servlet ao Novo Relatorio! "
                    + "Erro: " + ex.getMessage());
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
        processRequest(request, response);
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
        processRequest(request, response);
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
