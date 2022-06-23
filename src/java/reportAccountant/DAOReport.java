/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportAccountant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sort.SortReport;
import utils.DBUtils;

/**
 *
 * @author 84348
 */
public class DAOReport {
    private static final String SHOW_REPORT = "SELECT p.productID,p.brand, od.quantity,rd.quantityInShipping FROM Product AS p,OrderDetail AS od, ReceiptDetail AS rd WHERE p.productID=rd.productID AND p.productID=od.productID AND p.productID like ?";
    
    public List<UserReport> getListShowReport(String search) throws SQLException {
        List<UserReport> listReprot = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_REPORT);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String brand = rs.getString("brand");
                    int quantityImport = Integer.parseInt(rs.getString("quantity"));
                    int quantityExport = Integer.parseInt(rs.getString("quantityInShipping"));
                    listReprot.add(new UserReport(productID, brand, quantityImport, quantityExport));

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
        return listReprot;
    }
    
    public List<UserReport> getListSortReport() throws SQLException {
        List<UserReport> listReprot = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_REPORT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String brand = rs.getString("brand");
                    int quantityImport = Integer.parseInt(rs.getString("quantity"));                    
                    int quantityExport = Integer.parseInt(rs.getString("quantityInShipping"));
                    listReprot.add(new UserReport(productID, brand, quantityImport, quantityExport));
                }
                Collections.sort(listReprot, new SortReport());
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
        return listReprot;
    }
}
