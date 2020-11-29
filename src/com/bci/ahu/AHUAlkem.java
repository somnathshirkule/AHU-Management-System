/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bci.ahu;

import com.bci.ahu.dataobjets.Db;
import com.bci.ahu.modules.DatabaseSetting;
import com.bci.ahu.modules.LoginForm;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Somnath Shirkule
 */
public class AHUAlkem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AHUAlkem obj = new AHUAlkem();
        if(obj.isConnectedToDatabse())
        {
            LoginForm frm = new LoginForm();
            frm.show();
        }
        else
        {
            //JOptionPane.showMessageDialog(this, "Database Connection not found!", "Database error", JOptionPane.ERROR_MESSAGE);
            DatabaseSetting frm = new DatabaseSetting();
            frm.show();
        }
        // TODO code application logic here
    }
    private boolean isConnectedToDatabse()
    {
        Properties ps = new Properties();
        try
        {
            //String currentDirectory = System.getProperty("user.dir");
            //System.out.println(currentDirectory);
            ps.load(new FileReader(new File(System.getProperty("user.dir") + "\\dbahu.properties")));
            Db.setJdbcUrl(ps.getProperty("url"));
            Db.setJdbcDriver(ps.getProperty("driver"));
            Db.setJdbcUsername(ps.getProperty("username"));
            Db.setJdbcPassword(ps.getProperty("password"));
            return hitToDatabase();
        }
        catch(IOException ex)
        {
            return false;
        }
    }
    public boolean hitToDatabase()
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            Class.forName(Db.getJdbcDriver());
            //System.out.print(Db.getJdbcUrl() + Db.getJdbcDriver() + Db.getJdbcUsername() + Db.getJdbcPassword());
            con = DriverManager.getConnection(Db.getJdbcUrl(),Db.getJdbcUsername(),Db.getJdbcPassword());
            ps = con.prepareStatement("show tables");
            rs = ps.executeQuery();
            if(rs.next())
            {
                return true;
            }
            return false;
        }
        catch(ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(AHUAlkem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally
        {
            //con.close();
        }
    }
    
}
