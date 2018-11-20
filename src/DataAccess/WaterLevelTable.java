/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dave
 */
public class WaterLevelTable  extends AbstractTableModel{
     List<WaterLevel> waterLevel_list = new ArrayList<>();

    public WaterLevelTable(List<WaterLevel> borehole_list) {
        this.waterLevel_list = waterLevel_list;
    }

    @Override
    public int getRowCount() {
        return waterLevel_list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        WaterLevel w = getWaterLevel(rowIndex);

        switch (columnIndex) {
            case 0:
                return w.getBoreholeID();

            case 1:
                return w.getWaterLevel();

            case 2:
                return w.getDateOfLastCheck();
                
            case 3: 
                return w.getCheckID();

            default:
                return null;

        }
    }

    public WaterLevel getWaterLevel(int i) {
        return this.waterLevel_list.get(i);
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "BoreholeID";

            case 1:
                return "Water Level (mbgl)";

            case 2:
                return "Date last checked";
                
            case 3:
                return "Check ID";

            default:
                return null;

        }
    }
}
