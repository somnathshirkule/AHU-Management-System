/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bci.ahu.datalayer;

import com.bci.ahu.businesslayer.BlUser;
import com.bci.ahu.dataobjets.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Somnath Shirkule
 */
public class DlUser extends CommanDb {
    Connection con;
    PreparedStatement ps;
    ArrayList<String> typeList = new ArrayList<>();
    HashSet<User> userList = new HashSet<>();
    String userRights = "";
    public int dlInsertUser(String userId,String username,String password,String uType,String status,String cBy,String cOn)
    {
        int result = 0;
        con = getDbConnection();
        try {
            ps = con.prepareStatement("insert into userMaster values(?,?,?,?,?,?,?)");
            ps.setString(1, userId);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, uType);
            ps.setString(5, status);
            ps.setString(6, cBy);
            ps.setString(7, cOn);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlUser.class.getName()).log(Level.SEVERE, null, ex);
            closeDbConnection();
            result = 0;
        }
        return result;
    }
    public ArrayList<String> dlGetUserTypes()
    {
        con = getDbConnection();
        try {
            ps = con.prepareStatement("select type from userTypes");
            ResultSet rs = clGetUserTypes(ps);
            while(rs.next())
            {
                //while(rs.next())
                {
                    typeList.add(rs.getString(1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlUser.class.getName()).log(Level.SEVERE, null, ex);
            closeDbConnection();
        }
        finally
        {
            closeDbConnection();
        }
        return typeList;
    }
    public HashSet<User> dlGetUserList()
    {
        con = getDbConnection();
        try {
            ps = con.prepareStatement("select * from userMaster");
            ResultSet rs = clGetUserTypes(ps);
            while(rs.next())
            {
                //while(rs.next())
                {
                    userList.add(new User(rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(5)));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlUser.class.getName()).log(Level.SEVERE, null, ex);
            closeDbConnection();
        }
        finally
        {
            closeDbConnection();
        }
        return userList;
    }
    public String dlGetUserRights(final String userType)
    {
        con = getDbConnection();
        try {
            ps = con.prepareStatement("select * from userRights where type=?");
            ps.setString(1, userType);
            ResultSet rs = clGetUserTypes(ps);
            while(rs.next())
            {
                //while(rs.next())
                {
                    userRights = rs.getString(2);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlUser.class.getName()).log(Level.SEVERE, null, ex);
            closeDbConnection();
        }
        finally
        {
            closeDbConnection();
        }
        return userRights;
    }
    public String dlUpdateRights(final String rights, final String uType)
    {
        String uResult = "";
        try{
            con = getDbConnection();
            ps = con.prepareStatement("update userRights set rights=? where type=?");
            ps.setString(1, rights);
            ps.setString(2, uType);
            uResult = String.valueOf(ps.execute());
        }
        catch(Exception ex)
        {
            uResult = "Exception";
        }
        finally
        {
            closeDbConnection();
        }
        return uResult;
    }
    public String dlLogin(final String username, final String password)
    {
        String loginResult = "";
        try{
            String checkUserResult = checkUser(username);
            if(checkUserResult.equals("1"))
            {
                String validateResult = validateLogin(username, password);
                if(validateResult.equals("7"))
                {
                    loginResult = "Invalid password.";
                }
                else if(validateResult.contains("Exception"))
                {
                    loginResult = validateResult;
                }
                else
                {
                    return "111";
                }
            }
            else if(checkUserResult.equals("0"))
            {
                loginResult = "Login disabled.\nContact your Administrator.";
            }
            else if(checkUserResult.equalsIgnoreCase("9"))
            {
                loginResult = "User not found.";
            }
            else if(checkUserResult.equalsIgnoreCase("8"))
            {
                loginResult = checkUserResult.toString().trim();
            }
        }
        catch(Exception ex)
        {
            return "Exception" + ex.toString();
        }
        finally{
            closeDbConnection();
        }
        return loginResult;
    }
    public String dlGetLoggedUserRights(final String userType)
    {
        String resss = "";
        try
        {
            con = getDbConnection();
            ps = con.prepareStatement("select rights from userrights where type=?");
            ps.setString(1,userType);
            rs = ps.executeQuery();
            if(rs.next())
            {
                resss = rs.getString(1);
            }
            else
            {
                resss = "7";
            }
        }
        catch(Exception ex)
        {
            resss = ex.toString();
        }
        finally
        {
            closeDbConnection();
        }
        return resss;
    }
    public String dlGetLoggedUserType(final String id)
    {
        String resss = "";
        try
        {
            con = getDbConnection();
            ps = con.prepareStatement("select type from userMaster where userId=?");
            ps.setString(1,id);
            rs = ps.executeQuery();
            if(rs.next())
            {
                resss = rs.getString(1);
            }
            else
            {
                resss = "7";
            }
        }
        catch(Exception ex)
        {
            resss = ex.toString();
        }
        finally
        {
            closeDbConnection();
        }
        return resss;
    }
    private String validateLogin(final String usern, final String passd)
    {
        String resss = "";
        try
        {
            con = getDbConnection();
            ps = con.prepareStatement("select type from userMaster where userId=? and password=?");
            ps.setString(1,usern);
            ps.setString(2,passd);
            rs = ps.executeQuery();
            if(rs.next())
            {
                resss = rs.getString(1);
            }
            else
            {
                resss = "7";
            }
        }
        catch(Exception ex)
        {
            resss = ex.toString();
        }
        finally
        {
            closeDbConnection();
        }
        return resss;
    }
    private String checkUser(final String user)
    {
        String checkResult = "";
        try
        {
            con = getDbConnection();
            ps = con.prepareStatement("select status from userMaster where userId=?");
            ps.setString(1, user);
            rs = ps.executeQuery();
            if(rs.next())
            {
                checkResult = rs.getString(1);
            }
            else
            {
                checkResult = "9";
            }
        }
        catch(Exception ex)
        {
            checkResult = "8~" + ex.toString();
            closeDbConnection();
        }
        finally
        {
            closeDbConnection();
        }
        return checkResult;
    }
}
    
