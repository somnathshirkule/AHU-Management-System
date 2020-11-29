/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bci.ahu.businesslayer;

import com.bci.ahu.datalayer.DlUser;
import com.bci.ahu.dataobjets.User;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Somnath Shirkule
 */
public class BlUser extends DlUser{
    public ArrayList<String> getUserTypes()
    {
        return dlGetUserTypes();
    }
    public int blInsertUser(final String userId,final String username,final String password,final String uType,final String status,final String cBy,final String cOn)
    {
        return dlInsertUser(userId,username,password,uType,status,cBy,cOn);
    }
    public HashSet<User> getUserList()
    {
        return dlGetUserList();
    }
    public String getUserRights(final String userType)
    {
        return dlGetUserRights(userType);
    }
    public String updateRights(final String rightsl, final String typel)
    {
        return dlUpdateRights(rightsl,typel);
    }
    public String login(final String username, final String password)
    {
        return dlLogin(username, password);
    }
    public String getLoggedUserRights(final String id)
    {
        String type = dlGetLoggedUserType(id);
        return dlGetLoggedUserRights(type);
    }
}
