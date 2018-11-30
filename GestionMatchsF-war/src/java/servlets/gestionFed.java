/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.gestionFederationLocal;

/**
 *
 * @author katia
 */
@WebServlet(name = "gestionFed", urlPatterns = {"/gestionFed"})
public class gestionFed extends HttpServlet {

    @EJB
    private gestionFederationLocal gestionFederation;

     protected void creerEq(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException           
    {
        String nomEquipe = request.getParameter("nomEquipe");
        
        System.out.println("1");
        
        String message = "";
        if (nomEquipe.trim().isEmpty())
        {
            message = "Erreur, vous n'avez pas rempli tous les champs pour accéder au formulaire de création d'un arbitre";
        System.out.println("2");
        }
        else {
            System.out.println("3");
            gestionFederation.CreerEquipe(nomEquipe);
            message = "Equipe créée";          
            
        }
        request.setAttribute("message", message);
        
    }
     
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
        response.setContentType("text/html;charset=UTF-8");
        String message="";
        response.setContentType("text/html;charset=UTF-8");
        String jspClient = null;
        RequestDispatcher Rd;
        
        String act = request.getParameter("action");
        act =null;
        if(act==null)
        {
             jspClient = "/Auth.jsp";
            request.setAttribute("message", "pas d'infos");
        }
        else if (act.equals("CreerEquipe") ) /* auth de fédé*/
        {
            jspClient = "/Federation/MenuFederation.jsp";
            creerEq(request,response);
            
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet gestionFed</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet gestionFed at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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