package com.example.phoneBookDatabase;
import java.util.*;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

class DatabaseCSV{
    public static void write(String fileName, String[] data){
        FileWriter fw;
        try{
            fw = new FileWriter(fileName);
            for(String person: data){
                fw.write(person);
                fw.write("\n");
                System.out.println("Writed to " + fileName + "\n" + person);
            }
            
            fw.close();
        }
        catch(IOException error){
            String exception = error.getMessage();
            System.out.println("Error writing to " + fileName + "\n" + exception);
        }
    }

    public static String read(String fileName){
        FileReader fr;
        StringBuffer stringBuffer = new StringBuffer("");
        try{
            fr = new FileReader(fileName);
            char[] buffer = new char[3];
            int result = fr.read(buffer, 0, 3);
            while(result > 0){
                stringBuffer = stringBuffer.append(String.valueOf(buffer));
                result = fr.read(buffer, 0, 3);
            }
            System.out.println("Readed from " + fileName + "\n" + stringBuffer);
            fr.close();
        }
        catch(IOException error){
            String exception = error.getMessage();
            System.out.println("Error reading from" + fileName + "\n" + exception);
        }
        return stringBuffer.toString();
    }
    
}
