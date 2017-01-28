package dbexplorer.app;

/**
 * Created by RENT on 2017-01-28.
 */
import dbexplorer.dbutils.MySQLDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

import static dbexplorer.dbutils.MySQLDAO.resultSetToArray;

public class DataTable extends JPanel{
    JTable dataTable;
    JScrollPane scrollPane;

    public DataTable() {
        super(new GridLayout(1,1));
        dataTable = new JTable();
        dataTable.setPreferredScrollableViewportSize(new Dimension(500, 300));
        dataTable.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(dataTable);

        add(scrollPane);
    }

    public void fillTableWithDummyData(String table){
        String[] columns = {"Imie", "Nazwisko", "Miasto", "Plec"};
        String[][] tableData = {{"Jan" + table,"Kowalski","Waraszawa","M"}, {"Jan2" + table,"Kowalski2","Opole","M"},
                {"Jan3" + table,"Kowalski3","Wroclaw","M"}, {"Jan4" + table,"Kowalski4","Krakow","M"}};

        dataTable.setModel(new DefaultTableModel(tableData, columns));
    }

    public void fillTable(String table) {
        MySQLDAO dao = new MySQLDAO();
        dao.connect();
        ResultSet result = dao.executeQuery("SELECT * FROM " + table + " LIMIT 15");
        String[] columns = dao.resultSetToColumnNames(result);
        String[][] tableDate = dao.resultSetToArray(result);
        dao.close();

        dataTable.setModel(new DefaultTableModel(tableDate, columns));

    }


}

