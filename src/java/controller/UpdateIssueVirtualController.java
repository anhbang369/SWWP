/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import issueAccountant.DAOIssue;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import issueAccountant.UserVirtualIssue;
import virtual.ListVirtual;

/**
 *
 * @author 84348
 */
@WebServlet(name = "UpdateIssueVirtualController", urlPatterns = {"/UpdateIssueVirtualController"})
public class UpdateIssueVirtualController extends HttpServlet {

    private static final String ERROR = "issueVirtual.jsp";
    private static final String SUCCESS = "issueVirtual.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String issueID = request.getParameter("issueID");
            String note = request.getParameter("note");
            String accountantID = request.getParameter("accountantID");
            String sellerID = request.getParameter("sellerID");
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            DAOIssue dao = new DAOIssue();
            boolean checkAccountA = dao.checkExistAccount(accountantID);
            boolean checkAccountS = dao.checkExistAccount(sellerID);
            boolean checkOrderID = dao.checkExistOrderID(orderID);
            boolean checkOrderIdI = dao.checkOrderIdIssue(orderID);
            if (checkAccountA == true) {
                if (checkAccountS == true) {
                    if (checkOrderID) {
                        if (checkOrderIdI == true) {
                            HttpSession session = request.getSession();
                            if (session != null) {
                                ListVirtual cart = (ListVirtual) session.getAttribute("LIST_VIRTUAL");
                                if (cart != null) {
                                    if (cart.getListVirtual().containsKey(issueID)) {
                                        UserVirtualIssue tea = cart.getListVirtual().get(issueID);
                                        tea.setNote(note);
                                        tea.setAccountantID(accountantID);
                                        tea.setSellerID(sellerID);
                                        tea.setOrderID(orderID);
                                        cart.edit(issueID, tea);
                                        session.setAttribute("LIST_VIRTUAL", cart);
                                        url = SUCCESS;
                                    }
                                }
                            }
                        } else {
                            request.setAttribute("ERROR", "OrderID already exists in the table Order. Please enter again!!");
                        }
                    } else {
                        request.setAttribute("ERROR", "OrderID not exists in the table. Please enter again!!");
                    }
                } else {
                    request.setAttribute("ERROR", "SellerID not exists in the table. Please enter again!!");
                }
            } else {
                request.setAttribute("ERROR", "accountantID not exists in the table. Please enter again!!");
            }

            HttpSession session = request.getSession();
            if (session != null) {
                ListVirtual cart = (ListVirtual) session.getAttribute("LIST_VIRTUAL");
                if (cart != null) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
