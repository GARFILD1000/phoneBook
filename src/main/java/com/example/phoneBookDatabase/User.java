package com.example.phoneBookDatabase;

import com.fasterxml.jackson.annotation.JsonProperty;

abstract class User implements CSV{
    protected User(String newFio, String newPhone, String newAddress) {
        this.fio = newFio;
        this.phone = newPhone;
        this.address = newAddress;
    }

    //@JsonSerialize(using = IntToStringSerializer.class, as=String.class)
    @JsonProperty("id")
    protected int ID;
    @JsonProperty("fio")
    protected String fio;
    @JsonProperty("phone")
    protected String phone;
    @JsonProperty("address")
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

    public static String getSqlFields(){
        String fields = "id, fio, phone, address";
        return fields;
    }

    public static String getSqlFieldsFull(){
        String fields = "id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL"
                + ", fio VARCHAR(100) NOT NULL"
                + ", phone VARCHAR(11)"
                + ", address VARCHAR(50)";
        return fields;
    }
    public void copy(User userToCopy){
        this.fio = userToCopy.getFio();
        this.phone = userToCopy.getPhone();
        this.address = userToCopy.getAddress();
    }

}

