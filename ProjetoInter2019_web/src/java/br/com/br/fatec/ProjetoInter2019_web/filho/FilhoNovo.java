/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.fatec.ProjetoInter2019_web.filho;

import br.com.fatec.projetointer2019_lib.dao.GenericDAO;
import br.com.fatec.projetointer2019_lib.dao.InternetDAO;
import br.com.fatec.projetointer2019_lib.dao.ProgramasDAO;
import br.com.fatec.projetointer2019_lib.dao.RelatorioDAO;
import br.com.fatec.projetointer2019_lib.model.Filho;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luis Silva
 */
@WebServlet(name = "FilhoNovo", urlPatterns = {"/FilhoNovo"})
public class FilhoNovo extends HttpServlet {

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
            Filho oFilho = Filho.filhoVazio();
            //Gera lista de estado
            GenericDAO oRelatorioDAO = new RelatorioDAO();
            request.setAttribute("relatorios", oRelatorioDAO.listar());
            //cria variavel no servidor para armazenar fornecedor
            request.setAttribute("filho", oFilho);
            
            //dispacha objeto de lombada para a pagina jsp
            request.getRequestDispatcher("/cadastros/Filho/filhoCadastrar.jsp")
                    .forward(request, response);
        }
        catch(Exception ex){
            System.out.println("Problemas no Servlet ao Novo filho! "
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
