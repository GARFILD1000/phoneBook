package com.example.phoneBookDatabase;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.net.URL;
import java.lang.Exception; 

class DatabaseSQL{
    private String databaseName;
    private String hostName;
    private String portNumber;
    private String driverName;
    private String host;
    private final static String psw = "";
    private final static String usr = "root";
    public boolean connected;
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public DatabaseSQL(String newDatabaseName){
        driverName = "jdbc:mysql";
        hostName = "localhost";
        portNumber = "3306";
        databaseName = newDatabaseName;
        con = null;
        st = null;
    }

    public void initTable(String tableName, String fields){
        executeUpdate("CREATE TABLE IF NOT EXISTS " + tableName + "(" + fields + ");");
    }
    
    public ArrayList<String> executeQuery(String query){
        this.connected = false;
        String URL = driverName + "://" + hostName + ":" + portNumber + "/" + databaseName;  
        ArrayList<String> result = new ArrayList<>();
        StringBuilder queryResult;
        try{
            con = DriverManager.getConnection(URL, usr, psw);
            st = con.createStatement();
            if (con != null){
                this.connected = true;
            }
            rs = st.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            while(rs.next()){
                queryResult = new StringBuilder();
                for (int i = 1; i <= metaData.getColumnCount(); i++){
                    queryResult.append(rs.getString(i));
                    if (i < metaData.getColumnCount()){
                        queryResult.append(";");
                    }
                }
                result.add(queryResult.toString());
            }
        }
        catch(SQLException e){
           e.printStackTrace();
        }
        finally {
            closeConnections();
            this.connected = false;
        }
        return result;
    }
    
    public void executeUpdate(String query){ 
        this.connected = false;
        String URL = driverName + "://" + hostName + ":" + portNumber + "/" + databaseName;  
        ArrayList<String> result = new ArrayList<String>();
        StringBuilder queryResult = new StringBuilder();
        try{
            con = DriverManager.getConnection(URL, usr, psw);
            st = con.createStatement();
            if (con != null){
                this.connected = true;
            }
            st.executeUpdate(query);
        }
        catch(SQLException e){
           e.printStackTrace();
        }
        finally {
            closeConnections();
            this.connected = false;
        }
    }

    private void closeConnections(){
        try {
            if (con != null) {
                con.close();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        try {
            if (st != null) {
                st.close();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null){
                rs.close();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }




}


