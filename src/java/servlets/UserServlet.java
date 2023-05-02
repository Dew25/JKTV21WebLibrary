/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BookFacade;
import session.HistoryFacade;
import session.ReaderFacade;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "UserServlet", urlPatterns = {
    "/takeOnBook",
    "/createHistory",
    "/formReturnBook",
    "/returnBook",
    "/logout",
})
public class UserServlet extends HttpServlet {
    @EJB private BookFacade bookFacade;
    @EJB private ReaderFacade readerFacade;
    @EJB private HistoryFacade historyFacade;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "У вас нет прав. Авторизуйтесь");
            request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
            return;
        }
        User authUser = (User) session.getAttribute("authUser");
        if(authUser == null){
            request.setAttribute("info", "У вас нет прав. Авторизуйтесь");
            request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
            return;
        }
        if(!authUser.getRoles().contains(LoginServlet.Roles.USER.toString())){
            request.setAttribute("info", "У вас нет прав. Авторизуйтесь");
            request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            case "/takeOnBook":
                request.setAttribute("listReaders", readerFacade.findAll());
                request.setAttribute("listBooks", bookFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/history/takeOnBook.jsp").forward(request, response);
                break;
            case "/createHistory":
                String readerId = request.getParameter("readerId");
                String bookId = request.getParameter("bookId");
                Reader reader = readerFacade.find(Long.parseLong(readerId));
                Book book = bookFacade.find(Long.parseLong(bookId));
                History history = new History();
                history.setReader(reader);
                history.setBook(book);
                history.setTakeOnBook(new GregorianCalendar().getTime());
                historyFacade.create(history);
                request.setAttribute("info", "Книга выдана");
                request.getRequestDispatcher("/index").forward(request, response);
                break;
           
            case "/formReturnBook":
                request.setAttribute("listTakedBooks", historyFacade.getListTakedBooks());
                request.getRequestDispatcher("/WEB-INF/history/returnBook.jsp").forward(request, response);
                break;
            case "/returnBook":
                String historyId = request.getParameter("historyId");
                history = historyFacade.find(Long.parseLong(historyId));
                history.setReturnBook(new GregorianCalendar().getTime());
                historyFacade.edit(history);
                request.getRequestDispatcher("/formReturnBook").forward(request, response);
                break;
            case "/logout":
                session = request.getSession(false);
                if(session != null){
                    session.invalidate();
                    request.setAttribute("info", "Вы вышли");
                }
                response.sendRedirect(request.getContextPath()+"/index");
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
