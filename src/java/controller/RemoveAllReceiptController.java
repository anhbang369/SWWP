/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import virtual.ListReceipt;
import virtual.ListReceiptM;

/**
 *
 * @author 84348
 */
@WebServlet(name = "RemoveAllReceiptController", urlPatterns = {"/RemoveAllReceiptController"})
public class RemoveAllReceiptController extends HttpServlet {

    private static final String ERROR = "MainController?action=SeacrhReceipt&search=";
    private static final String SUCCESS = "MainController?action=SeacrhReceipt&search=";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            ListReceipt cart = (ListReceipt) session.getAttribute("VIRTUAL_RECEI_BASIC");
            ListReceiptM ca = (ListReceiptM) session.getAttribute("VIRTUAL_RECEI");
            if (session != null) {
                if (cart != null || ca!=null) {
                    session.removeAttribute("VIRTUAL_RECEI_BASIC");
                    session.removeAttribute("VIRTUAL_RECEI");
                    url = SUCCESS;

                }
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
