/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryAccountant;

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
public class DAOInventory {
    private static final String SEARCH_INVENTORY = "SELECT p.productID,rd.quality,rd.quantityInChecking,r.checkingDate,rd.note FROM Product as p,ReportDetail as rd,Report as r WHERE p.productID=rd.productID AND rd.reportID=r.reportID AND r.checkingDate BETWEEN ? AND ? ORDER BY YEAR(checkingDate) DESC,Month(checkingDate) DESC,day(checkingDate) DESC";
    private static final String SEARCH_INVENTORY_F = "SELECT p.productID,rd.quality,rd.quantityInChecking,r.checkingDate,rd.note FROM Product as p,ReportDetail as rd,Report as r WHERE p.productID=rd.productID AND rd.reportID=r.reportID ORDER BY YEAR(checkingDate) DESC,Month(checkingDate) DESC,day(checkingDate) DESC";
    private static final String INSERT_REPORT = "INSERT INTO Report(checkingDate) VALUES(?)";
    private static final String INSERT_REPORT_DETAIL = "INSERT INTO ReportDetail(reportID,productID,quality,quantityInChecking,quantity,note) VALUES(?,?,?,?,?,?)";
    private static final String GET_REPORT_ID = "SELECT TOP 1 * from Report ORDER BY reportID DESC";
    private static final String GET_QUANTITY_BIN = "SELECT SUM(b.quantity) as quantity FROM Bin as b, Inventory as i WHERE i.productID like ?";
    
    public List<UserInventory> getListSearchInventoryF() throws SQLException {
        List<UserInventory> listInventory = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_INVENTORY_F);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String checkingDate = rs.getString("checkingDate");
                    int quantityInChecking = Integer.parseInt(rs.getString("quantityInChecking"));
                    int quality = Integer.parseInt(rs.getString("quality"));
                    String note = rs.getString("note");
                    listInventory.add(new UserInventory(productID, checkingDate, quantityInChecking, quality, note));
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
        return listInventory;
    }
    
    public List<UserInventory> getListSearchInventory(String searchInventory, String searchInventoryM) throws SQLException {
        List<UserInventory> listInventory = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_INVENTORY);
                ptm.setString(1, searchInventory);
                ptm.setString(2, searchInventoryM);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String checkingDate = rs.getString("checkingDate");
                    int quantityInChecking = Integer.parseInt(rs.getString("quantityInChecking"));
                    int quality = Integer.parseInt(rs.getString("quality"));
                    String note = rs.getString("note");
                    listInventory.add(new UserInventory(productID, checkingDate, quantityInChecking, quality, note));
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
        return listInventory;
    }
    
    public int getReportID() throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_REPORT_ID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int reportID = Integer.parseInt(rs.getString("reportID"));
                    check = reportID;
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
    
    public int getQuantityInBin(String productID) throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_QUANTITY_BIN);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    check = quantity;
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
    
    public boolean createReport(UserReportInventory userReport) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_REPORT);
                ptm.setString(1, userReport.getCheckingDate());
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
    
    public boolean createReportDetail(UserInventoryVirtual reportDetail) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_REPORT_DETAIL);
                ptm.setInt(1, reportDetail.getReportID());
                ptm.setString(2, reportDetail.getProductID());
                ptm.setInt(3, reportDetail.getQuality());
                ptm.setInt(4, reportDetail.getQuantityInChecking());
                ptm.setInt(5, reportDetail.getQuantity());
                ptm.setString(6, reportDetail.getNote());
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
}
