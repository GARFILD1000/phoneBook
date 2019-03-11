//import User;
package com.example.phoneBookDatabase;
public class LegalPerson extends User{
    private String INN;
    public static int index = 0;
    public LegalPerson(String fio, String phone, String address, String INN){
        super(fio, phone, address);
        this.INN = INN;
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
        if (array.length >= 5)
            this.INN = array[4];
        else{ 
            System.out.println("Error here!");
            return 1;
        }
        return 0;
    }
    
    public String toSQL(){
        return super.toSQL() + ",'" + INN + "'";
    }

}
