package com.example.phoneBookDatabase;
import java.util.*;

class Statistic <T extends AnyCall>{
    private ArrayList<T> lst;
    
    public Statistic(){
        lst = new ArrayList<T>();
    }
    
    public int allTime(){
        int summ = 0;
        for (T x: this.lst){
            summ += x.time;
        }
        return summ;
    }
    
    public int getTimeForOneUser(String user){
        int summ = 0;
        for (T x: this.lst){
            if(x.isInvolved(user)) {
                summ += x.time;
            }
        }
        return summ;
    }
    
    public T getMax(){
        T max;
        if (!lst.isEmpty()){
            max = this.lst.get(0);
            for(T x: this.lst){
                if(x.time > max.time){
                    max = x;
                }
            }
            return max;
        }
        else return null;
    }
    
    public int size(){
        return lst.size();
    }
    
    public void add(T newCall){
        this.lst.add(newCall);
    }
    
    public void clear(){
        Call.index = 0;
        this.lst.clear();
    }
    
    public String[] toCSV(){
        String[] data = new String[this.size()];
        int iterator = 0;
        for (T x: this.lst){
            data[iterator] = x.toCSV();
            iterator++;
        }
        return data;
    }
    
    public String[] toSQL(){
        String[] data = new String[this.size()];
        int iterator = 0;
        for (T x: this.lst){
            data[iterator] = x.toSQL();
            iterator++;
        }
        return data;
    }
   
}

