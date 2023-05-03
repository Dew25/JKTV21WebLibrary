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
import session.AuthorFacade;
import session.BookFacade;
import session.ReaderFacade;
import session.UserFacade;
import tools.EncryptPassword;
import tools.PropertyLoader;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "LoginServlet", loadOnStartup = 1, urlPatterns = {
    "/about",
    "/index",
    "/loginForm",
    "/login",
    "/listBooks",
     "/listAuthors",
     "/newReader", 
    "/createReader",
   
    
})
public class LoginServlet extends HttpServlet {

    @EJB private UserFacade userFacade;
    @EJB private ReaderFacade readerFacade;
    @EJB private BookFacade bookFacade;
    @EJB private AuthorFacade authorFacade;
    
    static enum Roles {ADMINISTRATOR,MANAGER,USER};

    @Override
    public void init() throws ServletException {
        super.init(); 
        if(userFacade.count()>0) return;
        Reader reader = new Reader();
        reader.setFirstname("Juri");
        reader.setLastname("Melnikov");
        reader.setPhone("545454545");
        readerFacade.create(reader);
        User user = new User();
        user.setLogin("Administrator");
        EncryptPassword ep = new EncryptPassword();
        user.setSalt(ep.getSalt());
        user.setPassword(ep.getProtectedPassword("12345", user.getSalt()));
        user.setReader(reader);
        user.getRoles().add(LoginServlet.Roles.ADMINISTRATOR.toString());
        user.getRoles().add(LoginServlet.Roles.MANAGER.toString());
        user.getRoles().add(LoginServlet.Roles.USER.toString());
        userFacade.create(user);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/about":
                request.getRequestDispatcher(PropertyLoader.getPath("about")).forward(request, response);
                break;
            case "/index":
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/loginForm":
                request.getRequestDispatcher(PropertyLoader.getPath("loginForm")).forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                User user = userFacade.findByLogin(login);
                if(user == null){
                    request.setAttribute("info", "Нет такого пользователя или неправильный пароль.");
                    request.getRequestDispatcher(PropertyLoader.getPath("loginForm")).forward(request, response);
                    break;
                }
                EncryptPassword ep = new EncryptPassword();
                password = ep.getProtectedPassword(password, user.getSalt());
                if(!password.equals(user.getPassword())){
                    request.setAttribute("info", "Нет такого пользователя или неправильный пароль.");
                    request.getRequestDispatcher(PropertyLoader.getPath("loginForm")).forward(request, response);
                    break;
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("authUser", user);
                request.setAttribute("info", "Привет, "+user.getLogin()+"!");
                request.getRequestDispatcher(PropertyLoader.getPath("index")).forward(request, response);
                break;
            
            case "/listBooks":
                request.setAttribute("listBooks", bookFacade.findAll());
                request.getRequestDispatcher(PropertyLoader.getPath("listBooks")).forward(request, response);
                break;    
            case "/listAuthors":
                request.setAttribute("listAuthors", authorFacade.findAll());
                request.getRequestDispatcher(PropertyLoader.getPath("listAuthors")).forward(request, response);
                break;
            case "/newReader":
                request.getRequestDispatcher(PropertyLoader.getPath("createReader")).forward(request, response);
                break;
            case "/createReader":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                login = request.getParameter("login");
                password = request.getParameter("password");
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
                user = new User();
                user.setLogin(login);
                ep = new EncryptPassword();
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
                request.getRequestDispatcher(PropertyLoader.getPath("index")).forward(request, response);
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
