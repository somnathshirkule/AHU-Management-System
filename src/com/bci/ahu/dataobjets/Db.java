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
public class Db {
    private static String jdbcUrl;
    private static String jdbcDriver;
    private static String jdbcUsername;
    private static String jdbcPassword;

    public static void setJdbcUrl(String jdbcUrl) {
        Db.jdbcUrl = jdbcUrl;
    }

    public static void setJdbcDriver(String jdbcDriver) {
        Db.jdbcDriver = jdbcDriver;
    }

    public static void setJdbcUsername(String jdbcUsername) {
        Db.jdbcUsername = jdbcUsername;
    }

    public static void setJdbcPassword(String jdbcPassword) {
        Db.jdbcPassword = jdbcPassword;
    }

    public static String getJdbcUrl() {
        return jdbcUrl;
    }

    public static String getJdbcDriver() {
        return jdbcDriver;
    }

    public static String getJdbcUsername() {
        return jdbcUsername;
    }

    public static String getJdbcPassword() {
        return jdbcPassword;
    }
}
