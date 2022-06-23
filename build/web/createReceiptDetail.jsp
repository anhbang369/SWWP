<%-- 
    Document   : createReceiptDetail
    Created on : Jun 18, 2022, 5:12:32 PM
    Author     : 84348
--%>

<%@page import="receiptAccountant.UserReceiptDetail"%>
<%@page import="virtual.ListReceiptM"%>
<%@page import="virtual.ListReceipt"%>
<%@page import="user.UserProduct"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Receipt Detail Page</title>
        <script src="js/backNoWork.js" type="text/javascript" ></script>
    </head>
    <body>
        <form action="MainController">
            <input type="text" name="showProductCreate">
            <input type="submit" name="action" value="ShowProductCreate">
        </form>
      <%
            List<UserProduct> product = (List<UserProduct>) request.getAttribute("LIST_PRODUCT");
            if (product != null) {
                if (product.size() > 0) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>ProductID</th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>quantityInBill</th>
                    <th>quantityInShipping</th>
                    <th>Solution</th>
                </tr>
            </thead>
            <tbody>
<%
                                for (UserProduct rc : product) {
                            %>
            <form action="MainController">
                <tr>
                    <td>
                        <input type="text" name="productID" value="<%=rc.getProductID() %>"/>
                    </td>
                    <td>
                        <input type="text" name="brand" value="<%=rc.getBrand()%>"/>
                    </td>
                    <td>
                        <input type="text" name="model" value="<%=rc.getModel()%>"/>
                    </td>
                    <td>
                        <input type="number" name="quantityInBill" required=""/>
                    </td>
                    <td>
                        <input type="number" name="quantityInShipping" required=""/>
                    </td>
                    <td>
                        <input type="text" name="solution" required=""/>
                    </td>
                    <td>
                        <input type="submit" name="action" value="AddVirtualReceiDetail"/>
                    </td>
                </tr>
            </form>
<%
                                }
                            %>
        </tbody>
    </table>
    <%
            }
        }
    %> 

    <h3>Product Will Add Detail</h3>

    <%
        ListReceiptM rd = (ListReceiptM) session.getAttribute("VIRTUAL_RECEI");
        if (rd != null) {
            if (rd.getListReceipt().size() > 0) {
    %>
    <table border="1">
        <thead>
            <tr>
                <th>No</th>
                <th>productID</th>
                <th>quantityInBill</th>
                <th>quantityInShipping</th>
                <th>solution</th>
            </tr>
        </thead>
        <tbody>
            <%
                int count = 1;
                for (UserReceiptDetail tm : rd.getListReceipt().values()) {
            %>
        <form action="MainController">
            <tr>
                <td>
                    <input type="text" name="" value="<%= count++%>" readonly=""/>
                </td>
                <td>
                    <input type="text" name="productID" value="<%= tm.getProductID()%>" readonly=""/>
                </td>
                <td>
                    <input type="text" name="quantityInBill" value="<%= tm.getQuantityInBill()%>" required=""/>
                </td>
                <td>
                    <input type="text" name="quantityInShipping" value="<%= tm.getQuantityInShipping()%>" required=""/>
                </td>
                <td>
                    <input type="text" name="solution" value="<%= tm.getSolution()%>" required=""/>
                </td>
                <td>
                    <input type="submit" name="action" value="UpdateReceiptDetailVirtual" readonly=""/>
                </td>
                <td>
                    <input type="submit" name="action" value="RemoveReceiptDetailVirtual" readonly=""/>
                </td>
            </tr>
        </form>
        <%
            }
        %>
    </tbody>
</table>
<%
        }
    }
%>

<%
            String error = (String) request.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
        %>
        <h2><%=error%></h2>


<form action="MainController">
    <input type="submit" name="action" value="Finish">
</form>
        
        <form action="MainController">
    <input type="submit" name="action" value="BackReceipt">
</form>

</body>
</html>
