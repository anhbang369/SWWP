/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 84348
 */
public class MainController extends HttpServlet {

    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String ERROR = "error.jsp";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String SEARCH_RECEIPT = "SeacrhReceipt";
    private static final String SEARCH_RECEIPT_CONTROLLER = "SearchReceptController";
    private static final String SHOW_PRODUCT_CREATE = "ShowProductCreate";
    private static final String SHOW_PRODUCT_CREATE_CONTROLLER = "ShowProductCreateController";
    private static final String ADD_RECEIPT = "Finish";
    private static final String ADD_RECEIPT_CONTROLLER = "AddReceiptController";
    private static final String ADD_RECEIPT_DETAIL = "AddReceiptDetail";
    private static final String ADD_RECEIPT_DETAIL_CONTROLLER = "AddReceiptDetailController";
    private static final String SEARCH_RECEIPT_DETAIL = "SeacrhReceiptDetail";
    private static final String SEARCH_RECEIPT_DETAIL_CONTROLLER = "SeacrhReceiptDetailController";
    private static final String EXPROT = "Export";
    private static final String EXPROT_CONTROLLER = "ExportController";
    private static final String SHOW_REQUEST = "ShowRequest";
    private static final String SHOW_REQUEST_CONTROLLER = "ShowRequestController";
    private static final String UPDATE_REQUEST = "UpdateRequest";
    private static final String UPDATE_REQUEST_CONTROLLER = "UpdateRequestController";
    private static final String SHOW_REPORT = "ShowReport";
    private static final String SHOW_REPORT_CONTROLLER = "ShowReportController";
    private static final String SORT_REPORT = "SortReport";
    private static final String SORT_REPORT_CONTROLLER = "SortReportController";
    private static final String SEARCH_INVENTORY = "SearchInventory";
    private static final String SEARCH_INVENTORY_CONTROLLER = "SearchInventoryController";
    private static final String SEARCH_INVENTORY_F = "SearchInventoryF";
    private static final String SEARCH_INVENTORY_F_CONTROLLER = "SearchInventoryFController";
    private static final String SEARCH_ISSUE = "SeacrhIssue";
    private static final String SEARCH_ISSUE_CONTROLLER = "SeacrhIssueController";
    private static final String UPDATE_ISSUE = "UpdateIssue";
    private static final String UPDATE_ISSUE_CONTROLLER = "UpdateIssueController";
    private static final String ADD_VIRTUAL = "AddIssueVirtual";
    private static final String ADD_VIRTUAL_CONTROLLER = "AddIssueVirtualController";
    private static final String REMOVE_VIRTUAL = "RemoveIssueVirtual";
    private static final String REMOVE_VIRTUAL_CONTROLLER = "RemoveIssueVirtualController";
    private static final String INSERT_ISSUE = "InsertIssue";
    private static final String INSERT_ISSUE_CONTROLLER = "InsertIssueController";
    private static final String ADD_RECEI_DETAIL_VIRTUAL = "AddVirtualReceiDetail";
    private static final String ADD_RECEI_DETAIL_VIRTUAL_CONTROLLER = "AddVirtualReceiDetailController";
    private static final String ADD_RECEI_VIRTUAL = "AddReceiptVirtual";
    private static final String ADD_RECEI_VIRTUAL_CONTROLLER = "AddReceiptVirtualController";
    private static final String ADD_INVEN_VIRTUAL = "AddVirtualInventory";
    private static final String ADD_INVEN_VIRTUAL_CONTROLLER = "AddVirtualInventoryController";
    private static final String SHOW_PRODUCT_REPORT = "ShowProductReprot";
    private static final String SHOW_PRODUCT_REPORT_CONTROLLER = "ShowProductReprotController";
    private static final String INSERT_REPORT = "Complete";
    private static final String INSERT_REPORT_CONTROLLER = "InsertReportController";
    private static final String INSERT_REPORT_DETAIL = "InsertReportDetail";
    private static final String INSERT_REPORT_DETAIL_CONTROLLER = "InsertReportDetailController";
    private static final String REMOVE_INVENTORY_VIRTUAL = "RemoveInventoryVirtual";
    private static final String REMOVE_INVENTORY_VIRTUAL_CONTROLLER = "RemoveInventoryVirtualController";
    private static final String UPDATE_INVENTORY_VIRTUAL = "UpdateInventoryVirtua";
    private static final String UPDATE_INVENTORY_VIRTUAL_CONTROLLER = "UpdateInventoryVirtuaController";
    private static final String REMOVE_RECEIPT_DETAIL_VIRTUAL = "RemoveReceiptDetailVirtual";
    private static final String REMOVE_RECEIPT_DETAIL_VIRTUAL_CONTROLLER = "RemoveReceiptDetailVirtualController";
    private static final String UPDATE_RECEIPT_DETAIL_VIRTUAL = "UpdateReceiptDetailVirtual";
    private static final String UPDATE_RECEIPT_DETAIL_VIRTUAL_CONTROLLER = "UpdateReceiptDetailVirtualController";
    private static final String UPDATE_ISSUE_VIRTUAL = "UpdateIssueVirtual";
    private static final String UPDATE_ISSUE_VIRTUAL_CONTROLLER = "UpdateIssueVirtualController";
    private static final String REMOVE_ALL_RECEIPT = "BackReceipt";
    private static final String REMOVE_ALL_RECEIPT_CONTROLLER = "RemoveAllReceiptController";
    private static final String REMOVE_ALL_INVENTORY = "BackInventory";
    private static final String REMOVE_ALL_INVENTORY_CONTROLLER = "RemoveAllInventoryController";
    private static final String REMOVE_ALL_ISSUE = "BackIssue";
    private static final String REMOVE_ALL_ISSUE_CONTROLLER = "RemoveAllIssueController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try{
            String action = request.getParameter("action");
            if(LOGIN.equals(action)){
                url = LOGIN_CONTROLLER;
            }else if(LOGOUT.equals(action)){
                url = LOGOUT_CONTROLLER;
            }else if(SEARCH_RECEIPT.equals(action)){
                url = SEARCH_RECEIPT_CONTROLLER;
            }else if(SHOW_PRODUCT_CREATE.equals(action)){
                url = SHOW_PRODUCT_CREATE_CONTROLLER;
            }else if(ADD_RECEIPT.equals(action)){
                url = ADD_RECEIPT_CONTROLLER;
            }else if(ADD_RECEIPT_DETAIL.equals(action)){
                url = ADD_RECEIPT_DETAIL_CONTROLLER;
            }else if(SEARCH_RECEIPT_DETAIL.equals(action)){
                url = SEARCH_RECEIPT_DETAIL_CONTROLLER;
            }else if(EXPROT.equals(action)){
                url = EXPROT_CONTROLLER;
            }else if(SHOW_REQUEST.equals(action)){
                url = SHOW_REQUEST_CONTROLLER;
            }else if(UPDATE_REQUEST.equals(action)){
                url = UPDATE_REQUEST_CONTROLLER;
            }else if(SHOW_REPORT.equals(action)){
                url = SHOW_REPORT_CONTROLLER;
            }else if(SORT_REPORT.equals(action)){
                url = SORT_REPORT_CONTROLLER;
            }else if(SEARCH_INVENTORY.equals(action)){
                url = SEARCH_INVENTORY_CONTROLLER;
            }else if(SEARCH_INVENTORY_F.equals(action)){
                url = SEARCH_INVENTORY_F_CONTROLLER;
            }else if(SEARCH_ISSUE.equals(action)){
                url = SEARCH_ISSUE_CONTROLLER;
            }else if(UPDATE_ISSUE.equals(action)){
                url = UPDATE_ISSUE_CONTROLLER;
            }else if(ADD_VIRTUAL.equals(action)){
                url = ADD_VIRTUAL_CONTROLLER;
            }else if(REMOVE_VIRTUAL.equals(action)){
                url = REMOVE_VIRTUAL_CONTROLLER;
            }else if(INSERT_ISSUE.equals(action)){
                url = INSERT_ISSUE_CONTROLLER;
            }else if(ADD_RECEI_DETAIL_VIRTUAL.equals(action)){
                url = ADD_RECEI_DETAIL_VIRTUAL_CONTROLLER;
            }else if(ADD_RECEI_VIRTUAL.equals(action)){
                url = ADD_RECEI_VIRTUAL_CONTROLLER;
            }else if(ADD_INVEN_VIRTUAL.equals(action)){
                url = ADD_INVEN_VIRTUAL_CONTROLLER;
            }else if(SHOW_PRODUCT_REPORT.equals(action)){
                url = SHOW_PRODUCT_REPORT_CONTROLLER;
            }else if(INSERT_REPORT.equals(action)){
                url = INSERT_REPORT_CONTROLLER;
            }else if(INSERT_REPORT_DETAIL.equals(action)){
                url = INSERT_REPORT_DETAIL_CONTROLLER;
            }else if(REMOVE_INVENTORY_VIRTUAL.equals(action)){
                url = REMOVE_INVENTORY_VIRTUAL_CONTROLLER;
            }else if(UPDATE_INVENTORY_VIRTUAL.equals(action)){
                url = UPDATE_INVENTORY_VIRTUAL_CONTROLLER;
            }else if(REMOVE_RECEIPT_DETAIL_VIRTUAL.equals(action)){
                url = REMOVE_RECEIPT_DETAIL_VIRTUAL_CONTROLLER;
            }else if(UPDATE_RECEIPT_DETAIL_VIRTUAL.equals(action)){
                url = UPDATE_RECEIPT_DETAIL_VIRTUAL_CONTROLLER;
            }else if(UPDATE_ISSUE_VIRTUAL.equals(action)){
                url = UPDATE_ISSUE_VIRTUAL_CONTROLLER;
            }else if(REMOVE_ALL_RECEIPT.equals(action)){
                url = REMOVE_ALL_RECEIPT_CONTROLLER;
            }else if(REMOVE_ALL_INVENTORY.equals(action)){
                url = REMOVE_ALL_INVENTORY_CONTROLLER;
            }else if(REMOVE_ALL_ISSUE.equals(action)){
                url = REMOVE_ALL_ISSUE_CONTROLLER;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
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
