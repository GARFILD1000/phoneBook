//import User;
package com.example.phoneBookDatabase;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;

public class PhysicalPerson extends User{
    @JsonProperty("mobilePhone")
    private String mobilePhone;
    public static int index = 0;

    public PhysicalPerson(String fio, String phone, String address, String mobilePhone){
        super(fio, phone, address);
        this.mobilePhone = mobilePhone;
        index++;
        this.ID = index;
    }
    public PhysicalPerson(){
        super("", "", "");
        this.mobilePhone = "";
        index++;
        this.ID = index;
    }
    public static void clear(){
        index = 0;
    }
    public static void remove(){
        if (index > 0) index--;
    }
    public String getMobilePhone(){
        return this.mobilePhone;
    }
    public void setMobilePhone(String newMobilePhone){
        this.mobilePhone = newMobilePhone;
    }
    public String toCSV(){
        return super.toCSV() + ";" + mobilePhone;
    }
    public int fromCSV(String str){
        String[] array = str.split(";");
        if (super.fromCSV(str) != 0) return 1;
        if (this.ID > index){
            index = this.ID;
        }
        if (array.length >= 5)
            this.mobilePhone = array[4];
        else return 1;
        return 0;
    }
    
    public String toSQL(){
        return super.toSQL() + ",'" + mobilePhone + "'";
    }

    public static String getSqlFields(){
        String fields = User.getSqlFields() + ", mobilePhone";
        return fields;
    }

    public static String getSqlFieldsFull(){
        String fields = User.getSqlFieldsFull() + ", mobilePhone VARCHAR(11)";
        return fields;
    }

    public void copy(PhysicalPerson personToCopy) {
        this.fio = personToCopy.getFio();
        this.phone = personToCopy.getPhone();
        this.address = personToCopy.getAddress();
        this.mobilePhone = personToCopy.getMobilePhone();
    }
}

