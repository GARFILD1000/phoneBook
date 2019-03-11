package com.example.phoneBookDatabase;
import java.util.*;

class Conf extends AnyCall{
  private String[] users;
  static int index = 0;
  
  public Conf(String[] newUsers, int newTime){
      this.users = newUsers;
      this.time = newTime;
      index++;
      this.ID = index;
  }
  public Conf(){
      this.users = new String[0];
      this.time = 0;
      index++;
      this.ID = index;
  }
  public void setUsers(String[] newUsers){
      this.users = newUsers;
  }
  
  public String[] getUsers(){
      return this.users;
  }
  
  public void setTime(int newTime){
      this.time = newTime;
  }
  
  public int getTime(){
      return this.time;
  }
  
  public boolean isInvolved(String user){
      for(String x: users){
          if (user.equals(x)){
              return true;
          }
      }
      return false;
  }
  public String toCSV(){
        StringBuffer stringUsers = new StringBuffer("");
        for (String x: users){
            stringUsers.append(x);
            stringUsers.append("-");
        }
        stringUsers.deleteCharAt(stringUsers.lastIndexOf("-"));
        return super.toCSV() + ";" + stringUsers.toString();
  }
    
    public int fromCSV(String str){
        String[] array = str.split(";");
        if (super.fromCSV(str) != 0) return 1;
        if (array.length >= 3){
            String[] stringUsers = array[2].split("-");
            this.users = stringUsers;
        }
        else{
            System.out.println("Error here2!");
            return 1;
        }
        return 0;
    }
    
    public String toSQL(){
        StringBuffer stringUsers = new StringBuffer("");
        for (String x: users){
            stringUsers.append(x);
            stringUsers.append("-");
        }
        stringUsers.deleteCharAt(stringUsers.lastIndexOf("-"));
        return super.toSQL() + ",'" + stringUsers.toString() + "'";
    }
}
