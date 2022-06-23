/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import issueAccountant.UserIssue;
import receiptAccountant.UserReceipt;
import receiptAccountant.UserReceiptVirtual;
import reportAccountant.UserReport;
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
public class UserDAO {

    private static final String LOGIN = "SELECT name,role,status,phoneNumber FROM Account WHERE accountID=? AND password=?"; 
    private static final String SHOW_PRODUCT = "SELECT productID,model,brand,status,type,width,depth,height,screenSize,voiceRemote,bluetooth,manufacturingDate,madeIn,quantityOnHand FROM Product WHERE productID like ?";

    public UserDTO checkLogin(String accountID, String password) throws SQLException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(LOGIN);
                ptm.setString(1, accountID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String role = rs.getString("role");
                    String status = rs.getString("status");
                    String phoneNumber = rs.getString("phoneNumber");
                    user = new UserDTO(accountID, password, name, role, status, phoneNumber);

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
        return user;
    }

    

    

    public List<UserProduct> getListShowProduct(String search) throws SQLException {
        List<UserProduct> listProduct = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_PRODUCT);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String model = rs.getString("model");
                    String brand = rs.getString("brand");
                    String status = rs.getString("status");
                    String type = rs.getString("type");
                    float width = Float.parseFloat(rs.getString("width"));
                    float depth = Float.parseFloat(rs.getString("depth"));
                    float height = Float.parseFloat(rs.getString("height"));
                    float screenSize = Float.parseFloat(rs.getString("screenSize"));
                    String voiceRemote = rs.getString("voiceRemote");
                    String bluetooth = rs.getString("bluetooth");
                    int manufacturingDate = Integer.parseInt(rs.getString("manufacturingDate"));
                    String madeIn = rs.getString("madeIn");
                    int quantityOnHand = Integer.parseInt(rs.getString("quantityOnHand"));
                    listProduct.add(new UserProduct(productID, model, brand, status, type, width, depth, height, screenSize, voiceRemote, bluetooth, manufacturingDate, madeIn, quantityOnHand));

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
        return listProduct;
    }
    

}
