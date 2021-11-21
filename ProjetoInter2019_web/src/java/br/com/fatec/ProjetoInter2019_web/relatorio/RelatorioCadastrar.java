/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.ProjetoInter2019_web.relatorio;

import br.com.fatec.projetointer2019_lib.dao.GenericDAO;
import br.com.fatec.projetointer2019_lib.dao.InternetDAO;
import br.com.fatec.projetointer2019_lib.dao.ProgramasDAO;
import br.com.fatec.projetointer2019_lib.dao.RelatorioDAO;
import br.com.fatec.projetointer2019_lib.model.Internet;
import br.com.fatec.projetointer2019_lib.model.Programas;
import br.com.fatec.projetointer2019_lib.model.Relatorio;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jeffersonpasserini
 */
@WebServlet(name = "RelatorioCadastrar", urlPatterns = {"/RelatorioCadastrar"})
public class RelatorioCadastrar extends HttpServlet {

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
        int idrelatorio = Integer.parseInt(request.getParameter("idrelatorio"));
        int idPrograma = Integer.parseInt(request.getParameter("idprograma"));
        int idInternet = Integer.parseInt(request.getParameter("idinternet"));
        String dataRelatorio = request.getParameter("datarelatorio");
        String horaRelatorio = request.getParameter("horarelatorio");
        String mensagem = null;
        
        try{
            GenericDAO oProgramasDAO = new ProgramasDAO();
            Programas oProgramas = (Programas) oProgramasDAO.carregar(idPrograma);
            GenericDAO oInternetDAO = new InternetDAO();
            Internet oInternet = (Internet) oInternetDAO.carregar(idInternet);
            
            Relatorio oRelatorio = new Relatorio();
            oRelatorio.setIdRelatorio(idrelatorio);
            oRelatorio.setDataRelatorio(dataRelatorio);
            oRelatorio.setHoraRelatorio(horaRelatorio);
            oRelatorio.setProgramas(oProgramas);
            oRelatorio.setInternet(oInternet);
               
            GenericDAO dao = new RelatorioDAO();
            if (dao.cadastrar(oRelatorio)){
                mensagem = "Relatorio cadastrado com sucesso!";                
            } else {
                mensagem = "Problemas ao cadastrar Relatorio. "
                    + "Verifique os dados informados "
                        + "e tente novamente!";
            }

            request.setAttribute("mensagem", mensagem);
            response.sendRedirect("RelatorioListar");

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
