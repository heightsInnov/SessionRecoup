/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbankng.ussd.dao;

import com.unionbankng.ussd.constants.Constants;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author oawe
 */
public class DatasourceSqlServer {

    private static final BasicDataSource ds;

    static {
        ds = new BasicDataSource();
        ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        ds.setUsername(Constants.DB_USERNAME);
        ds.setPassword(Constants.DB_PASSWORD);
        ds.setUrl(Constants.DB_URL);
        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(180);

    }

    public Connection getConnection()  {

        try {
            return this.ds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DatasourceSqlServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public static void main(String[] args) {
        DatasourceSqlServer ds= new DatasourceSqlServer();
        Connection conn = ds.getConnection();
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Datasource.class.getName()).log(Level.SEVERE, null, ex);
        }
        ds.getConnection();
    }

}
