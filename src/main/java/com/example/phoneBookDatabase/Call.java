package com.example.phoneBookDatabase;

class Call extends AnyCall{
  private String a;
  private String b;
  static int index = 0;
  
  public Call(String newA, String newB, int newTime){
      this.a = newA;
      this.b = newB;
      this.time = newTime;
      index++;
      this.ID = index;
  }
  public Call(){
      this.a = "";
      this.b = "";
      this.time = 0;
      index++;
      this.ID = index;
  }
  public void setA(String newA){
      this.a = newA;
  }
  public String getA(){
      return this.a;
  }
    public void setB(String newB){
      this.b = newB;
  }
  public String getB(){
      return this.b;
  }
  public void setTime(int newTime){
      this.time = newTime;
  }
  public int getTime(){
      return this.time;
  }
  public boolean isInvolved(String user){
      if (user.equals(a) || user.equals(b)){ 
          return true;
      }
      else return false;
  }
  
  public String toCSV(){
        StringBuffer stringUsers = new StringBuffer("");
        stringUsers.append(this.a);
        stringUsers.append("-");
        stringUsers.append(this.b);
        return (super.toCSV() + ";" + stringUsers.toString());
    }
    
    public int fromCSV(String str){
        String[] array = str.split(";");
        if (super.fromCSV(str) != 0) return 1;
        if (array.length >= 3){
            String[] stringUsers = array[2].split("-");
            if (stringUsers.length == 2){
                this.a = stringUsers[0];
                this.b = stringUsers[1];
            }
            else return 1;
        }
        else{
            System.out.println("Error here3!");
            return 1;
        }
        return 0;
    }
    
    public String toSQL(){
        StringBuffer stringUsers = new StringBuffer("");
        stringUsers.append(this.a);
        stringUsers.append("-");
        stringUsers.append(this.b);
        return (super.toSQL() + ",'" + stringUsers.toString() + "'");
    }
}
