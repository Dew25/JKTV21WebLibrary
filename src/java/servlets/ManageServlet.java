/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Author;
import entity.Book;
import entity.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.AuthorFacade;
import session.BookFacade;
import session.HistoryFacade;
import tools.PropertyLoader;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "ManageServlet", urlPatterns = {
    
    "/newBook",
    "/createBook",
    "/listTakedBooks",
    
})
public class ManageServlet extends HttpServlet {

    @EJB private AuthorFacade authorFacade;
    @EJB private BookFacade bookFacade;
    @EJB private HistoryFacade historyFacade;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "У вас нет прав. Авторизуйтесь");
            request.getRequestDispatcher(PropertyLoader.getPath("loginForm")).forward(request, response);
            return;
        }
        User authUser = (User) session.getAttribute("authUser");
        if(authUser == null){
            request.setAttribute("info", "У вас нет прав. Авторизуйтесь");
            request.getRequestDispatcher(PropertyLoader.getPath("loginForm")).forward(request, response);
            return;
        }
        if(!authUser.getRoles().contains(LoginServlet.Roles.MANAGER.toString())){
            request.setAttribute("info", "У вас нет прав. Авторизуйтесь");
            request.getRequestDispatcher(PropertyLoader.getPath("loginForm")).forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            
            case "/newBook":
                request.setAttribute("listAuthors",authorFacade.findAll());
                request.getRequestDispatcher(PropertyLoader.getPath("createBook")).forward(request, response);
                break;
            case "/createBook":
                 String title = request.getParameter("title");
                 String[] authors = request.getParameterValues("authors");
                 List<Author> listBookAuthors = new ArrayList<>();
                 for (int i = 0; i < authors.length; i++) {
                    listBookAuthors.add(authorFacade.find(Long.parseLong(authors[i])));
                }
                Book book = new Book();
                book.setTitle(title);
                book.setAuthors(listBookAuthors);
                bookFacade.create(book);
                for (int i = 0; i < listBookAuthors.size(); i++) {
                    Author a = listBookAuthors.get(i);
                    a.getBooks().add(book);
                    authorFacade.edit(a);
                }
                request.setAttribute("listBooks", bookFacade.findAll());
                request.getRequestDispatcher(PropertyLoader.getPath("listBooks")).forward(request, response);
                break;
            case "/listTakedBooks":
                request.setAttribute("listTakedBooks", historyFacade.getListTakedBooks());
                request.getRequestDispatcher(PropertyLoader.getPath("listTakedBooks")).forward(request, response);
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
