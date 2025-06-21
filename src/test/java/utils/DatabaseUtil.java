package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {

    public static String[] getCustomerData(Connection con, String firstnameKey) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM customer WHERE firstname = '" + firstnameKey + "'");

        String[] data = new String[3];
        if (rs.next()) {
            data[0] = rs.getString("firstname");
            data[1] = rs.getString("lastname");
            data[2] = rs.getString("address");
        }

        rs.close();
        stmt.close();

        return data;
    }
}
