/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bci.ahu.datalayer;

import com.bci.ahu.dataobjets.Db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Somnath Shirkule
 */
public class CommanDb {
    Connection con;
    ResultSet rs;
    public Connection getDbConnection()
    {
        try
        {
            Class.forName(Db.getJdbcDriver());
            con = DriverManager.getConnection(Db.getJdbcUrl(), Db.getJdbcUsername(),Db.getJdbcPassword());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CommanDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    public void closeDbConnection()
    {
        try{
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CommanDb.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    public ResultSet clGetUserTypes(final PreparedStatement ps)
    {
        try
        {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(CommanDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
