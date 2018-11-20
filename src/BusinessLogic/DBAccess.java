/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataAccess.BoreHole;
import DataAccess.WaterLevel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Dave
 */
public class DBAccess {

    public DBAccess() throws SQLException, ClassNotFoundException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.jdbc.Driver");
    }

    // BOREHOLE FUNCTIONALITY
    //Get all boreholes from database and return a list of objects(boreholes) to form
    public static List<BoreHole> getAllBoreholes() {
        List<BoreHole> borehole_list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/boreholes?user=root&password=")) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `borehole`");
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                res.getInt("BoreholeType");
                int boreholeID = res.getInt("BoreholeID");
                String boreholeName = res.getString("BoreholeName");
                int boreholeTypeID = res.getInt("BoreholeType");
                String boreholeLatitude = res.getString("BoreholeLatitude");
                String boreholeLongitude = res.getString("BoreholeLongitude");
                float boreholeElevation = res.getFloat("BoreholeElevation");

                PreparedStatement stmt2 = conn.prepareStatement("SELECT `TypeName` FROM `boreholetypes` WHERE `TypeID` = ?");
                stmt2.setInt(1, boreholeTypeID);
                ResultSet res2 = stmt2.executeQuery();
                res2.next();
                String boreholeType = res2.getString("TypeName");

                BoreHole bh = new BoreHole(boreholeID, boreholeName, boreholeType, boreholeLatitude, boreholeLongitude, boreholeElevation);
                borehole_list.add(bh);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return borehole_list;
    }

    //Add a new borehole to the database
    public static void addNewBorehole(BoreHole borehole) {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/boreholes?user=root&password=")) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO `borehole`(`BoreholeName`, `BoreholeType`, `BoreholeLatitude`, `BoreholeLongitude`, `BoreholeElevation`) VALUES (?,?,?,?,?)");
            stmt.setString(1, borehole.getBoreholeName());
            int BoreholeTypeID = 0;
            String boreHoleType = borehole.getBoreholeType();
            switch (boreHoleType) {

                case "Auger-Hole":
                    BoreholeTypeID = 1;
                    break;

                case "Shot-Hole":
                    BoreholeTypeID = 2;
                    break;

                case "Well":
                    BoreholeTypeID = 3;
                    break;

                case "Vibracore":
                    BoreholeTypeID = 4;
                    break;

            }
            stmt.setInt(2, BoreholeTypeID);
            stmt.setString(3, borehole.getBoreholeLatitude());
            stmt.setString(4, borehole.getBoreholeLongitude());
            stmt.setFloat(5, (float) borehole.getBoreholeElevation());
            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //Delete a selected borehole by it's ID
    public static void deleteBorehole(int boreholeID) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/boreholes?user=root&password=")) {

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM `borehole` WHERE `BoreholeID` = ?");
            stmt.setInt(1, boreholeID);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Borehole Deleted");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //Update a boreholes details by it' ID
    public static void updateBorehole(BoreHole borehole) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/boreholes?user=root&password=")) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE `borehole` SET `BoreholeName`=?,`BoreholeType`=?,`BoreholeLatitude`=?,`BoreholeLongitude`=?,`BoreholeElevation`=? WHERE `BoreholeID` = ?");
            stmt.setString(1, borehole.getBoreholeName());
            int BoreholeTypeID = 0;
            String boreHoleType = borehole.getBoreholeType();
            switch (boreHoleType) {

                case "Auger-Hole":
                    BoreholeTypeID = 1;
                    break;

                case "Shot-Hole":
                    BoreholeTypeID = 2;
                    break;

                case "Well":
                    BoreholeTypeID = 3;
                    break;

                case "Vibracore":
                    BoreholeTypeID = 4;
                    break;

            }
            stmt.setInt(2, BoreholeTypeID);
            stmt.setString(3, borehole.getBoreholeLatitude());
            stmt.setString(4, borehole.getBoreholeLongitude());
            stmt.setFloat(5, (float) borehole.getBoreholeElevation());
            stmt.setInt(6, borehole.getBoreholeID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //WATER LEVEL FUNCTIONAITY
    //Get a list of all water level readings from the database and return as a list of objects
    public static List<WaterLevel> getAllWaterlevelChecks() {
        List<WaterLevel> waterlevel_list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/boreholes?user=root&password=")) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `water_level`");
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                int checkID = res.getInt("CheckID");
                int boreholeID = res.getInt("BoreholeID");
                float waterLevel = res.getFloat("WaterLevel(mbgl)");
                Date dateOfLastCheck = res.getDate("DateOfLastCheck");
                WaterLevel wl = new WaterLevel(checkID, boreholeID, waterLevel, dateOfLastCheck);
                waterlevel_list.add(wl);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return waterlevel_list;
    }

    //Add a new water level reading to the database
    public static void addNewWaterLevelCheck(WaterLevel waterlevel) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/boreholes?user=root&password=")) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO `water_level`(`BoreholeID`, `WaterLevel(mbgl)`, `DateOfLastCheck`) VALUES (?,?,?)");
            stmt.setInt(1, waterlevel.getBoreholeID());
            stmt.setFloat(2, waterlevel.getWaterLevel());
            stmt.setDate(3, (java.sql.Date) waterlevel.getDateOfLastCheck());
            stmt.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //Update water level check details
    public static void updateWaterLevelCheck(WaterLevel waterlevel) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/boreholes?user=root&password=")) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE `water_level` SET `BoreholeID`=?,`WaterLevel(mbgl)`=?,`DateOfLastCheck`=? WHERE `CheckID` = ?");
            stmt.setInt(1, waterlevel.getBoreholeID());
            stmt.setFloat(2, waterlevel.getWaterLevel());
            stmt.setDate(3, (java.sql.Date) waterlevel.getDateOfLastCheck());
            stmt.setInt(4, waterlevel.getCheckID());
            stmt.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void deletWaterLevel(int checkID) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/boreholes?user=root&password=")) {
            PreparedStatement stmt = conn.prepareStatement("UPDELETE FROM `water_level` WHERE 0`CheckID` = ?");
            stmt.setInt(1, checkID);
            stmt.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static String getDescription(String type) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/boreholes?user=root&password=")) {
            PreparedStatement stmt = conn.prepareStatement("SELECT `TypeDesc` FROM `boreholetypes` WHERE `TypeName` = ?");
            stmt.setString(1, type);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                String desc = res.getString("TypeDesc");
                return desc;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return null;
    }

}
