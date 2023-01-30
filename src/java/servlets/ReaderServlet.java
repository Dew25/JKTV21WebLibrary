
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Reader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.ReaderFacade;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "ReaderServlet", urlPatterns = {
    "/newReader", 
    "/createReader",
    "/listReaders",
    
})
public class ReaderServlet extends HttpServlet {

    @EJB private ReaderFacade readerFacade;
    
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/newReader":
                request.getRequestDispatcher("/WEB-INF/createReader.jsp").forward(request, response);
                break;
            case "/createReader":
                Reader reader = new Reader();
                reader.setFirstname(request.getParameter("firstname"));
                reader.setLastname(request.getParameter("lastname"));
                reader.setPhone(request.getParameter("phone"));
                readerFacade.create(reader);
                request.getRequestDispatcher("/listReaders").forward(request, response);
                break;
            case "/listReaders":
                request.setAttribute("listReaders", readerFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/listReaders.jsp").forward(request, response);
                break;
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
