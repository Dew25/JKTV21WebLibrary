/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Author;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.AuthorFacade;
import tools.BirthdayConverter;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "AuthorServlet", urlPatterns = {
    "/newAuthor",
    "/createAuthor",
    "/listAuthors",
    
})
public class AuthorServlet extends HttpServlet {

    @EJB private AuthorFacade authorFacade;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/newAuthor":
                request.getRequestDispatcher("/WEB-INF/author/createAuthor.jsp").forward(request, response);
                break;
            case "/createAuthor":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String year = request.getParameter("year");
                String month = request.getParameter("month");
                String day = request.getParameter("day");
                Author author = new Author();
                author.setFirstname(firstname);
                author.setLastname(lastname);
                author.setBirthday(new BirthdayConverter().getDate(
                                            Integer.parseInt(day),
                                            Integer.parseInt(month), 
                                            Integer.parseInt(year)
                                    ));
                authorFacade.create(author);
                request.setAttribute("info", "Автор добавлен");
                request.getRequestDispatcher("/index").forward(request, response);
                break;
            case "/listAuthors":
                request.setAttribute("listAuthors", authorFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/author/listAuthors.jsp").forward(request, response);
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
