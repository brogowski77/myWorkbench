package dbexplorer.dbutils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by RENT on 2017-01-28.
 */
public class MySQLDAO {
    private Connection  connect;        //obiekt do łączenia sie z baza
    private Statement statement;        //obiekt przechowjacy zapytanie
    private ResultSet resultSet;        //obiekt przechowujacy wynik zapytania

    public static void main(String[] args) {
        MySQLDAO dao = new MySQLDAO();
        dao.connect();
        ResultSet result = dao.executeQuery("SELECT * FROM FILM LIMIT 10");

        try {
            int columnCount = result.getMetaData().getColumnCount();
/*            while(result.next()){
                for (int i = 1; i < columnCount; i++) {
                    System.out.print(result.getString(i) + "|");
                }
                System.out.println();
            }*/
            System.out.println(Arrays.deepToString(resultSetToArray(result)));
            System.out.println(Arrays.deepToString(resultSetToColumnNames(result)));
            dao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void connect(){
        //url to tzw. JDBC ENDPOINT
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost/sakila?user=root");
            System.out.println("Polaczono");
        } catch (SQLException e) {
            System.out.println("Nie udało sie polaczyc z baza");
            e.printStackTrace();
        }
    }

    public void close(){
            try {
                if(connect != null)
                    connect.close();
                if(statement != null)
                    statement.close();
                if(resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public ResultSet executeQuery(String query){
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;

    }

    public static String[] resultSetToColumnNames(ResultSet resultSet){
        int columnCount = 0;
        String[] columns = null;
        try {
            columnCount = resultSet.getMetaData().getColumnCount();
            columns = new String[columnCount];

            for (int i = 1; i < columnCount; i++) {
                columns[i-1] = resultSet.getMetaData().getColumnName(i);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }

    public static String[][] resultSetToArray(ResultSet resultSet){
        String[][] result = null;
        int columnCount = 0;
        ArrayList<String[]> resultAll = new ArrayList<>();

        try {
            columnCount = resultSet.getMetaData().getColumnCount();
            while(resultSet.next()){
                String[] colValues = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    colValues[i-1] = resultSet.getString(i);

                }
                resultAll.add(colValues);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        result = resultAll.toArray(new String[resultAll.size()][columnCount]);


        return result;
    }


}
