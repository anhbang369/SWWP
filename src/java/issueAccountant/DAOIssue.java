/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package issueAccountant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author 84348
 */
public class DAOIssue {
    private static final String INSERT_ISSUE = "INSERT INTO Issue(note, accountantID, sellerID, orderID) VALUES(?,?,?,?)";
    private static final String CHECK_ORDER_ISSUE = "SELECT issueID,note,accountantID,sellerID FROM Issue WHERE orderID like ?";
    private static final String UPDATE_ISSUE = "UPDATE Issue SET note=? WHERE issueID=?";
    private static final String CHECK_EXIST_ACCOUNT = "SELECT name,role,status,phoneNumber FROM Account WHERE accountID like ?";
    private static final String CHECK_EXIST_ORDERID = "SELECT customerName,address,phoneNumber,status,note,deliveryDate,sellerID FROM Orders WHERE orderID like ?";
    private static final String CHECK_DUPLICATE = "SELECT name,role,status,phoneNumber,password FROM Account WHERE accountID=?";
    private static final String SEARCH_ORDER = "SELECT od.orderDetailID,o.customerName,o.address,o.phoneNumber,i.accountantID,i.issueID,i.orderID,i.note FROM Issue as i,Orders as o,OrderDetail as od WHERE i.orderID=o.orderID AND o.orderID=od.orderID AND o.customerName like ?";
    
    public boolean updateIssue(UserUpdateIssue user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_ISSUE);
                ptm.setString(1, user.getNote());
                ptm.setInt(2, user.getIssueID());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ptm != null) {
                ptm.close();
            }

        }
        return check;
    }
    
    public boolean createDetail(UserVirtualIssueS virtualIssue) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_ISSUE);
                ptm.setString(1, virtualIssue.getNote());
                ptm.setString(2, virtualIssue.getAccountantID());
                ptm.setString(3, virtualIssue.getSellerID());
                ptm.setInt(4, virtualIssue.getOrderID());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
            
        }
        return check;
    }
    
    public boolean checkOrderIdIssue(int orderID) throws SQLException {
        boolean check = true;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_ORDER_ISSUE);
                ptm.setInt(1, orderID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public boolean checkExistAccount(String Account) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_EXIST_ACCOUNT);
                ptm.setString(1, Account);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public boolean checkExistOrderID(int orderID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_EXIST_ORDERID);
                ptm.setInt(1, orderID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public boolean checkDupAccountantID(String accountantID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, accountantID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDupStockKeeperID(String stockKeeperID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, stockKeeperID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public List<UserIssue> getListIssue(String search) throws SQLException {
        List<UserIssue> listIssue = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_ORDER);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderDetailID = Integer.parseInt(rs.getString("orderDetailID"));
                    String customerName = rs.getString("customerName");
                    String address = rs.getString("address");
                    String phoneNumber = rs.getString("phoneNumber");
                    String accountantID = rs.getString("accountantID");
                    int issueID = Integer.parseInt(rs.getString("issueID"));
                    int orderID = Integer.parseInt(rs.getString("orderID"));
                    String note = rs.getString("note");
                    listIssue.add(new UserIssue(orderDetailID, customerName, address, phoneNumber, accountantID, issueID, orderID, note));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listIssue;
    }
}
