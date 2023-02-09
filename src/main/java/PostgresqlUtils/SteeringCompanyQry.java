/*
 * Copyright (c) 2023.
 */

package PostgresqlUtils;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SteeringCompanyQry {

    public static void main( String args[] ) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://172.24.78.90:5432/demo1",
                            "naqaba", "naqaba");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select  * from service_receiver_msg lrm " +
                    " Order by service_receiver_msg_id desc  " +
                    "limit 1;" );
            while ( rs.next() ) {
                int service_receiver_msg_id = rs.getInt("service_receiver_msg_id");
                String  service_receiving_status = rs.getString("service_receiving_status");
                String service_error_code  = rs.getString("service_error_code");
                String  service_error_description = rs.getString("service_error_description");
                Timestamp  service_processing_timestamp = rs.getTimestamp("service_processing_timestamp");

                //  float salary = rs.getFloat("salary");
                System.out.println( "service_receiver_msg_id = " + service_receiver_msg_id );
                System.out.println( "service_receiving_status = " + service_receiving_status );
                System.out.println( "service_error_code = " + service_error_code );
                System.out.println( "service_error_description = " + service_error_description );
                System.out.println( "service_processing_timestamp = " + service_processing_timestamp );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}
