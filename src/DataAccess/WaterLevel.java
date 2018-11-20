/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import BusinessLogic.DBAccess;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dave
 */
public class WaterLevel {

    private int checkID;
    private int boreholeID;
    private float waterLevel;
    private Date dateOfLastCheck;

    public WaterLevel() {
    }

    public int getCheckID() {
        return checkID;
    }

    public int getBoreholeID() {
        return boreholeID;
    }

    public void setBoreholeID(int boreholeID) {
        this.boreholeID = boreholeID;
    }

    public float getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(float waterLevel) {
        this.waterLevel = waterLevel;
    }

    public Date getDateOfLastCheck() {
        return dateOfLastCheck;
    }

    public void setDateOfLastCheck(Date dateOfLastCheck) {
        this.dateOfLastCheck = dateOfLastCheck;
    }

    public WaterLevel(int checkID, int boreholeID, float waterLevel, Date dateOfLastCheck) {
        this.checkID = checkID;
        this.boreholeID = boreholeID;
        this.waterLevel = waterLevel;
        this.dateOfLastCheck = dateOfLastCheck;
    }

    public WaterLevel(int boreholeID, float waterLevel, Date dateOfLastCheck) {
        this.boreholeID = boreholeID;
        this.waterLevel = waterLevel;
        this.dateOfLastCheck = dateOfLastCheck;
    }

    public static List<WaterLevel> getAllWaterlevelChecks() {
        List<WaterLevel> waterlevel_list = new ArrayList<>();

        waterlevel_list = DBAccess.getAllWaterlevelChecks();

        return waterlevel_list;
    }

    public static void addNewWaterLevelCheck(WaterLevel waterlevel) {

    }

}
