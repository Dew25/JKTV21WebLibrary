 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.User;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.HistoryFacade;
import session.ReaderFacade;
import session.UserFacade;
import tools.PropertyLoader;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "AdminServlet", urlPatterns = {
    
    "/listReaders",
    "/changeRole",
    "/editUserRole",
    "/statisticForm",
    "/calcStatistic"
    
})
public class AdminServlet extends HttpServlet {

    @EJB private ReaderFacade readerFacade;
    @EJB private UserFacade userFacade;
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
        if(!authUser.getRoles().contains(LoginServlet.Roles.ADMINISTRATOR.toString())){
            request.setAttribute("info", "У вас нет прав. Авторизуйтесь");
            request.getRequestDispatcher(PropertyLoader.getPath("loginForm")).forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            case "/listReaders":
                request.setAttribute("listReaders", readerFacade.findAll());
                request.getRequestDispatcher(PropertyLoader.getPath("listReaders")).forward(request, response);
                break;
            case "/changeRole":
                request.setAttribute("listRoles", LoginServlet.Roles.values());
                request.setAttribute("listUsers", userFacade.findAll());
                request.getRequestDispatcher(PropertyLoader.getPath("changeRole")).forward(request, response);
                break;
            case "/editUserRole":
                String btnDelete = request.getParameter("btnDelete");
                String btnAdd = request.getParameter("btnAdd");
                String userId = request.getParameter("userId");
                String roleName = request.getParameter("roleName");
                if(userId == null || userId.isEmpty() || roleName == null || roleName.isEmpty()){
                    request.setAttribute("info", "Не выбран пользователь или роль из списка");
                    request.getRequestDispatcher("/changeRole").forward(request, response);
                    break;
                }
                User user = userFacade.find(Long.parseLong(userId));
                if(user.getLogin().equals("Administrator")){
                    request.setAttribute("info", "Этому пользователю изменить роль нет возможности");
                    request.getRequestDispatcher("/changeRole").forward(request, response);
                    break;
                }
                if(btnDelete == null || btnDelete.isEmpty()){
                    if(!user.getRoles().contains(roleName)){
                        user.getRoles().add(roleName);
                    }
                }else if(btnAdd == null || btnAdd.isEmpty()){
                    if(user.getRoles().contains(roleName)){
                        user.getRoles().remove(roleName);
                    }
                }
                userFacade.edit(user);
                if(!Objects.equals(user.getId(), authUser.getId())){
                    request.getRequestDispatcher("/changeRole").forward(request, response);
                }else{
                    session.setAttribute("authUser", user);
                    response.sendRedirect(request.getContextPath()+"/changeRole");
                    
                }
                break;
            case "/statisticForm":
                SimpleDateFormat sdt = new SimpleDateFormat("y");
                Integer year = Integer.parseInt(sdt.format(new Date()));
                List<Integer> years = new ArrayList<>();
                years.add(year - 1);
                years.add(year);
                request.setAttribute("years", years);
                request.getRequestDispatcher("/WEB-INF/admin/changeDateCtatistic.jsp").forward(request, response);
                break;
            case "/calcStatistic":
                String yearStr = request.getParameter("year");
                String month = request.getParameter("month");
                String day = request.getParameter("day");
                if((month == null || month.isEmpty()) && (day == null || day.isEmpty())){
                    request.setAttribute("period", yearStr+" год");
                }else if(day == null || day.isEmpty() && (month != null || !month.isEmpty())){
                    request.setAttribute("period", month+" месяц");
                }else{
                    request.setAttribute("period",yearStr +" год, "+ month+" месяц, "+day+" день");
                }
                Map<Book,Integer> mapBooksRange = historyFacade.getTakedBooksInPeriod(yearStr,month,day);
                if(mapBooksRange.isEmpty()){
                    request.setAttribute("info", "В этот период книги не выдавались");
                }else{
                    request.setAttribute("mapBooksRange", mapBooksRange);
                }
                request.getRequestDispatcher("/WEB-INF/admin/statisticForm.jsp").forward(request, response);
                
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
