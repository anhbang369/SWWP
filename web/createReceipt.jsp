<%-- 
    Document   : createReceipt
    Created on : Jun 16, 2022, 4:25:46 PM
    Author     : 84348
--%>

<%@page import="user.UserProduct"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Receipt Page</title>
    </head>
    <body>
        <a href="MainController?action=ShowRequest&showRequest=">Request</a>
        <a href="MainController?action=SeacrhReceipt&search=">Receipt</a>
        <a href="MainController?action=SeacrhIssue&seacrhIssue=">Issue</a>
        <a href="MainController?action=ShowReport&showReport=">Report</a>
        <a href="MainController?action=SearchInventoryF&searchInventoryF=">Inventory Report</a>
     
        <form action="MainController">

            <input type="submit" name="action" value="Export"/>
        </form>

        <form action="MainController">
            InPut Date<input type="date" name="inputDate" value="" required=""/>
            Status<input type="text" name="status" value="" required=""/>
            Total Quantity<input type="number" name="totalQuantity" value="" required=""/>
            Note<input type="text" name="note" value="" placeholder="Note here..." required=""/>
            Stock Keeper<input type="text" name="accountantID" value="" required=""/>
            Accountant<input type="text" name="stockKeeperID" value="" required=""/>
            
            <input type="submit" name="action" value="AddReceiptVirtual"/>
        </form>     
        
        <%
            String error = (String) request.getAttribute("VIRTUAL_RECEI_ERROR");
            if (error == null) {
                error = "";
            }
        %>
        <h2><%=error%></h2>
        
    </body>
</html>
