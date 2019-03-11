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
    
    public ArrayList<String> executeQuery(String query, ArrayList<String> fields){ 
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
            rs = st.executeQuery(query);
            int counter = 0;
            while(rs.next()){
                queryResult = new StringBuilder();
                for (int i = 0; i < fields.size(); i++){
                    queryResult.append(rs.getString(fields.get(i)));
                    if (i < fields.size()-1){
                        queryResult.append(";");
                    }
                }
                result.add(queryResult.toString());
                counter++;
            }
        }
        catch(SQLException e){
           e.printStackTrace();
        }
        finally {
            try { 
                con.close(); 
            } 
            catch(SQLException e) {
                e.printStackTrace();
            }
            try {
                st.close(); 
            } 
            catch(SQLException e) { 
                e.printStackTrace();
            }
            try { 
                rs.close(); 
            } 
            catch(SQLException e) { 
                e.printStackTrace();
            }
            
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
            try { 
                con.close(); 
            } 
            catch(SQLException e) {
                e.printStackTrace();
            }
            try {
                st.close(); 
            } 
            catch(SQLException e) { 
                e.printStackTrace();
            }
        }
    }
    




}


