/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.fatec.ProjetoInter2019_web.programa;

import br.com.fatec.projetointer2019_lib.dao.GenericDAO;
import br.com.fatec.projetointer2019_lib.dao.ProgramasDAO;
import br.com.fatec.projetointer2019_lib.model.Programas;
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
@WebServlet(name = "ProgramaCadastrar", urlPatterns = {"/ProgramaCadastrar"})
public class ProgramaCadastrar extends HttpServlet {

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
        int idPrograma = Integer.parseInt(request.getParameter("idprograma"));
        String nomeProgramas = request.getParameter("nomeprograma");
        String dataAcesso = request.getParameter("dataacesso");
        String horaAcesso = request.getParameter("horaacesso");
        String mensagem = null;

        Programas oProgramas = new Programas();
        oProgramas.setIdPrograma(idPrograma);
        oProgramas.setNomePrograma(nomeProgramas);
        oProgramas.setDataAcesso(dataAcesso);
        oProgramas.setHoraAcesso(horaAcesso);
        
        try{

            GenericDAO dao = new ProgramasDAO();
            if (dao.cadastrar(oProgramas)){
                mensagem = "Programa cadastrado com sucesso!";                
            } else {
                mensagem = "Problemas ao cadastrar Programa. "
                    + "Verifique os dados informados e tente novamente!";
            }

            request.setAttribute("mensagem", mensagem);
            response.sendRedirect("ProgramaListar");

        } catch (Exception ex){
             System.out.println("Problemas no Servlet ao cadastrar"
                     + " Programa! Erro: " + ex.getMessage());
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
