package com.example.phoneBookDatabase;

abstract class User implements CSV{
    protected User(String newFio, String newPhone, String newAddress){
        this.fio = newFio;
        this.phone = newPhone;
        this.address = newAddress;
    }
    protected int ID; 
    protected String fio;
    protected String phone;
    protected String address;

    public int getID(){
        return this.ID;
    }
    
    public String getFio(){
        return this.fio;
    }
    public void setFio(String newFio){
        this.fio = newFio;
    }

    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String newPhone){
        this.phone = newPhone;
    }

    public String getAddress(){
        return this.address;
    }
    public void setAddress(String newAddress){
        this.address = newAddress;
    }
    
    public String toCSV(){
        return (ID + ";" + fio + ";" + phone + ";" + address);
    }
    
    public int fromCSV(String str){
        String[] array = str.split(";");
        if (array.length >= 4){
            
            this.ID = Integer.valueOf(array[0]);
            this.fio = array[1];
            this.phone = array[2];
            this.address = array[3];
        }
        else {
            System.out.println("Error here!");
            return 1;
        }
        return 0;
    }
    
    public String toSQL(){
        return (ID + ",'" + fio + "','" + phone + "','" + address + "'");
    }
}

