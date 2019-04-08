//import User;
package com.example.phoneBookDatabase;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LegalPerson extends User{
    @JsonProperty("inn")
    private String INN;
    public static int index = 0;
    public LegalPerson(String fio, String phone, String address, String INN){
        super(fio, phone, address);
        this.INN = INN;
        index++;
        this.ID = index;
        
    }
    public LegalPerson(){
        super("", "", "");
        this.INN = "";
        index++;
        this.ID = index;
    }

    public static void clear(){
        index = 0;
    }
    public static void remove(){
        if (index > 0) index--;
    }
    public String getINN(){
        return this.INN;
    }
    public void setINN(String newINN){
        this.INN = newINN;
    }
    public String toCSV(){
        return super.toCSV() + ";" + INN;
    }
    public int fromCSV(String str){
        String[] array = str.split(";");
        if (super.fromCSV(str) != 0) return 1;
        if (this.ID > index){
            index = this.ID;
        }
        if (array.length >= 5) {
            this.INN = array[4];
        }
        else{ 
            System.out.println("Error here!");
            return 1;
        }
        return 0;
    }



    public String toSQL(){
        return super.toSQL() + ",'" + INN + "'";
    }

    public static String getSqlFields(){
        String fields = User.getSqlFields() + ", inn";
        return fields;
    }

    public static String getSqlFieldsFull(){
        String fields = User.getSqlFieldsFull() + ", inn VARCHAR(11)";
        return fields;
    }

    public void copy(LegalPerson personToCopy) {
        this.fio = personToCopy.getFio();
        this.phone = personToCopy.getPhone();
        this.address = personToCopy.getAddress();
        this.INN = personToCopy.getINN();
    }
}
