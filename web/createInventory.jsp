<%-- 
    Document   : createInventory
    Created on : Jun 21, 2022, 10:38:35 PM
    Author     : 84348
--%>

<%@page import="inventoryAccountant.UserInventoryVirtual"%>
<%@page import="virtual.ListInventory"%>
<%@page import="user.UserProduct"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/backNoWork.js" type="text/javascript" ></script>
        <title>Create Inventory Page</title>
    </head>
    <body>

        <form action="MainController">
            <input type="text" name="showProductReprot">
            <input type="submit" name="action" value="ShowProductReprot">
        </form>
        <%
            List<UserProduct> product = (List<UserProduct>) request.getAttribute("LIST_PRODUCT_REPORT");
            if (product != null) {
                if (product.size() > 0) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>ProductID</th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>quality</th>
                    <th>quantityInChecking</th>
                    <th>note</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (UserProduct rc : product) {
                %>
            <form action="MainController">
                <tr>
                    <td>
                        <input type="text" name="productID" value="<%=rc.getProductID()%>"/>
                    </td>
                    <td>
                        <input type="text" name="brand" value="<%=rc.getBrand()%>"/>
                    </td>
                    <td>
                        <input type="text" name="model" value="<%=rc.getModel()%>"/>
                    </td>
                    <td>
                        <input type="number" name="quality" required=""/>
                    </td>
                    <td>
                        <input type="number" name="quantityInChecking" required=""/>
                    </td>
                    <td>
                        <input type="text" name="note" required=""/>
                    </td>
                    <td>
                        <input type="submit" name="action" value="AddVirtualInventory"/>
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
        ListInventory rd = (ListInventory) session.getAttribute("LIST_INVEN_VIRTUAL");
        if (rd != null) {
            if (rd.getListInventory().size() > 0) {
    %>
    <table border="1">
        <thead>
            <tr>
                <th>No</th>
                <th>productID</th>
                <th>quality</th>
                <th>quantityInChecking</th>
                <th>note</th>
            </tr>
        </thead>
        <tbody>
            <%
                int count = 1;
                for (UserInventoryVirtual tm : rd.getListInventory().values()) {
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
                    <input type="text" name="quality" value="<%= tm.getQuality()%>" required=""/>
                </td>
                <td>
                    <input type="text" name="quantityInChecking" value="<%= tm.getQuantityInChecking()%>" required=""/>
                </td>
                <td>
                    <input type="text" name="note" value="<%= tm.getNote()%>" required=""/>
                </td>
                <td>
                    <input type="submit" name="action" value="UpdateInventoryVirtua" readonly=""/>
                </td>
                <td>
                    <input type="submit" name="action" value="RemoveInventoryVirtual" readonly=""/>
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
<form action="MainController">
    <input type="submit" name="action" value="Complete">
</form>
<form action="MainController">
    <input type="submit" name="action" value="BackInventory">
</form>

<%
    String error = (String) request.getAttribute("ERROR");
    if (error == null) {
        error = "";
    }
%>
<h2><%=error%></h2>
</body>
</html>
