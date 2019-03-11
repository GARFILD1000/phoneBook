package com.example.phoneBookDatabase;

abstract class AnyCall implements CSV{
    protected int time;
    public abstract boolean isInvolved(String user);
    protected int ID;
    public String toCSV(){
        return (ID + ";" + time);
    }
    
    public int fromCSV(String str){
        String[] array = str.split(";");
        if (array.length >= 2){
            this.ID = Integer.valueOf(array[0]);
            this.time = Integer.valueOf(array[1]);
        }
        else {
            System.out.println("Error here1!");
            return 1;
        }
        return 0;
    }
    public String toSQL(){
        return ("'" + ID + "','" + time + "'");
    }
    
    
};
