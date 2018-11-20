/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import BusinessLogic.DBAccess;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dave
 */
public class BoreHole {
    private int boreholeID;
    private String boreholeName;
    private String boreholeType;
    private String boreholeLatitude; 
    private String boreholeLongitude;
    private float boreholeElevation;

    public BoreHole() {
    }

    public int getBoreholeID() {
        return boreholeID;
    }

    public void setBoreholeID(int boreholeID) {
        this.boreholeID = boreholeID;
    }

    public String getBoreholeName() {
        return boreholeName;
    }

    public void setBoreholeName(String boreholeName) {
        this.boreholeName = boreholeName;
    }

    public String getBoreholeType() {
        return boreholeType;
    }

    public void setBoreholeType(String boreholeType) {
        this.boreholeType = boreholeType;
    }

    public String getBoreholeLatitude() {
        return boreholeLatitude;
    }

    public void setBoreholeLatitude(String boreholeLatitude) {
        this.boreholeLatitude = boreholeLatitude;
    }

    public String getBoreholeLongitude() {
        return boreholeLongitude;
    }

    public void setBoreholeLongitude(String boreholeLongitude) {
        this.boreholeLongitude = boreholeLongitude;
    }

    public double getBoreholeElevation() {
        return boreholeElevation;
    }

    public void setBoreholeElevation(float boreholeElevation) {
        this.boreholeElevation = boreholeElevation;
    }

    public BoreHole(int boreholeID, String boreholeName, String boreholeType, String boreholeLatitude, String boreholeLongitude, float boreholeElevation) {
        this.boreholeID = boreholeID;
        this.boreholeName = boreholeName;
        this.boreholeType = boreholeType;
        this.boreholeLatitude = boreholeLatitude;
        this.boreholeLongitude = boreholeLongitude;
        this.boreholeElevation = boreholeElevation;
    }
    
     public BoreHole(String boreholeName, String boreholeType, String boreholeLatitude, String boreholeLongitude, float boreholeElevation) {
        this.boreholeName = boreholeName;
        this.boreholeType = boreholeType;
        this.boreholeLatitude = boreholeLatitude;
        this.boreholeLongitude = boreholeLongitude;
        this.boreholeElevation = boreholeElevation;
    }
     
    public static List<BoreHole> getAllBoreholes(){
        List<BoreHole> borehole_list = new ArrayList<>();
        
        borehole_list = DBAccess.getAllBoreholes();
        
        return borehole_list;
    }
    
    public static void addNewBorehole(BoreHole borehole){
        DBAccess.addNewBorehole(borehole);
    }
    
    public static void deleteBorehole(int boreholeID){
        DBAccess.deleteBorehole(boreholeID);
    }
    
    public static void updateBorehole(BoreHole borehole){
        DBAccess.updateBorehole(borehole);
    }
    
}
