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
public class BoreholesTable extends AbstractTableModel {

    List<BoreHole> borehole_list = new ArrayList<>();

    public BoreholesTable(List<BoreHole> borehole_list) {
        this.borehole_list = borehole_list;
    }

    @Override
    public int getRowCount() {
        return borehole_list.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BoreHole b = getBorehole(rowIndex);

        switch (columnIndex) {
            case 0:
                return b.getBoreholeName();

            case 1:
                return b.getBoreholeType();

            case 2:
                return b.getBoreholeLatitude();

            case 3:
                return b.getBoreholeLongitude();

            case 4:
                return b.getBoreholeElevation();

            case 5:
                return b.getBoreholeID();
                
            default:
                return null;

        }
    }

    public BoreHole getBorehole(int i) {
        return this.borehole_list.get(i);
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Name";

            case 1:
                return "Type";

            case 2:
                return "Latitude";

            case 3:
                return "Longitude";

            case 4:
                return "Elevation (metres)";

            case 5:
                return "Borehole ID";

            default:
                return null;

        }
    }
}
