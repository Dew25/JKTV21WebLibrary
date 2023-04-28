
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Reader;
import entity.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ReaderFacade;
import session.UserFacade;
import tools.EncryptPassword;

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
    @EJB private UserFacade userFacade;
    
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/newReader":
                request.getRequestDispatcher("/WEB-INF/reader/createReader.jsp").forward(request, response);
                break;
            case "/createReader":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                if(firstname == null || firstname.isEmpty() || lastname == null || lastname.isEmpty()
                        || phone == null || phone.isEmpty() || login == null || login.isEmpty()
                        || password == null || password.isEmpty()){
                    request.setAttribute("info", "Не все поля заполнены");
                    request.getRequestDispatcher("/newReader").forward(request, response);
                    break;
                }
                Reader reader = new Reader();
                reader.setFirstname(firstname);
                reader.setLastname(lastname);
                reader.setPhone(phone);
                readerFacade.create(reader);
                User user = new User();
                user.setLogin(login);
                EncryptPassword ep = new EncryptPassword();
                user.setSalt(ep.getSalt());
                user.setPassword(ep.getProtectedPassword(password, user.getSalt()));
                user.setReader(reader);
                user.getRoles().add(LoginServlet.Roles.USER.toString());
                try {
                    userFacade.create(user);
                } catch (Exception e) {
                    request.setAttribute("info", "Такой пользователь уже существует");
                    request.getRequestDispatcher("/newReader").forward(request, response);
                    break;
                }
                request.setAttribute("info", "Читатель зарегистрирован");
                request.getRequestDispatcher("/index").forward(request, response);
                break;
            case "/listReaders":
                HttpSession session = request.getSession(false);
                if(session == null){
                    request.setAttribute("info", "У вас нет прав. Авторизуйтесь");
                    request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
                    break;
                }
                User authUser = (User) session.getAttribute("authUser");
                if(authUser == null){
                    request.setAttribute("info", "У вас нет прав. Авторизуйтесь");
                    request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
                    break;
                }
                if(!authUser.getRoles().contains(LoginServlet.Roles.ADMINISTRATOR.toString())){
                    request.setAttribute("info", "У вас нет прав. Авторизуйтесь");
                    request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
                    break;
                }
                request.setAttribute("listReaders", readerFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/reader/listReaders.jsp").forward(request, response);
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
