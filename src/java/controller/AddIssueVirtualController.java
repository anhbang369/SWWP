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
@WebServlet(name = "AddIssueVirtualController", urlPatterns = {"/AddIssueVirtualController"})
public class AddIssueVirtualController extends HttpServlet {

    private static final String ERROR = "issueVirtual.jsp";
    private static final String SUCCESS = "issueVirtual.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean k = true;
            boolean h = true;
            String issueID = request.getParameter("issueID");
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            HttpSession session = request.getSession();
            ListVirtual ca = (ListVirtual) session.getAttribute("LIST_VIRTUAL");
            if (ca != null) {
                if (ca.getListVirtual().size() > 0) {
                    for (UserVirtualIssue tm : ca.getListVirtual().values()) {
                        if (tm.getOrderID() == orderID) {
                            h=false;
                            if(tm.getIssueID().equalsIgnoreCase(issueID)){
                                k = false;
                            }
                        }
                    }
                }
            }
            if (k == true && h==true) {
                String note = request.getParameter("note");
                String accountantID = request.getParameter("accountantID");
                String sellerID = request.getParameter("sellerID");
                DAOIssue dao = new DAOIssue();
                boolean checkAccountA = dao.checkExistAccount(accountantID);
                boolean checkAccountS = dao.checkExistAccount(sellerID);
                boolean checkOrderID = dao.checkExistOrderID(orderID);
                boolean checkOrderIdI = dao.checkOrderIdIssue(orderID);
                if (checkAccountA == true) {
                    if (checkAccountS == true) {
                        if (checkOrderID) {
                            if (checkOrderIdI == true) {
                                UserVirtualIssue tm = new UserVirtualIssue(issueID, note, accountantID, sellerID, orderID);

                                ListVirtual cart = (ListVirtual) session.getAttribute("LIST_VIRTUAL");
                                if (cart == null) {
                                    cart = new ListVirtual();
                                }
                                cart.add(tm);
                                session.setAttribute("LIST_VIRTUAL", cart);
                                url = SUCCESS;
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
            } else {
                request.setAttribute("ERROR", "OrderId or Issue already exists in the table. Please enter again!!");
            }

        } catch (Exception e) {
            System.out.println("Error at InsertController: " + e.toString());
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
