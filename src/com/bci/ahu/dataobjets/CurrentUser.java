/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bci.ahu.dataobjets;

/**
 *
 * @author Somnath Shirkule
 */
public class CurrentUser {
    private static String userId;
    private static String usertype;
    private static String rights;

    public static void setUserId(String userId) {
        CurrentUser.userId = userId;
    }

    public static void setUsertype(String usertype) {
        CurrentUser.usertype = usertype;
    }

    public static void setRights(String rights) {
        CurrentUser.rights = rights;
    }

    public static String getUserId() {
        return userId;
    }

    public static String getUsertype() {
        return usertype;
    }

    public static String getRights() {
        return rights;
    }
    
}
