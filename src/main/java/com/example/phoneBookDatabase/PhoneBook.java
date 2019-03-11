package com.example.phoneBookDatabase;
import java.util.*;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

class PhoneBook{
    static Scanner input;
    static Scanner fileReader;
    static Statistic<Call> statCall;
    static Statistic<Conf> statConf;
    static TreeSet<LegalPerson> legalPersonTree;
    static TreeSet<PhysicalPerson> physicalPersonTree;
    static DatabaseSQL databaseSQL;
    
    static class LegalPersonComparator implements Comparator<LegalPerson>{
        public int compare(LegalPerson paramT1, LegalPerson paramT2) {
            if (paramT1.getFio().compareTo(paramT2.getFio()) > 0){
                return 1;
            }
            else if (paramT1.getFio().compareTo(paramT2.getFio()) < 0){
                return -1;
            }
            else{
                return 0;
            }
        }
    }
    static class PhysicalPersonComparator implements Comparator<PhysicalPerson> {
        public int compare(PhysicalPerson paramT1, PhysicalPerson paramT2) {
            if (paramT1.getFio().compareTo(paramT2.getFio()) > 0){
                return 1;
            }
            else if (paramT1.getFio().compareTo(paramT2.getFio()) < 0){
                return -1;
            }
            else{
                return 0;
            }
        }
    }
    
    public static void main(String[] args){
            databaseSQL = new DatabaseSQL("phoneBook");
        ArrayList<String> resultFields = new ArrayList<String>(Arrays.asList("id","fio","phone","address","inn"));
        databaseSQL.executeUpdate("INSERT INTO legalPersons VALUES(0,'Someone new', '8121323433', 'Random 21', '221534231')");
        ArrayList<String> list = databaseSQL.executeQuery("SELECT * FROM legalPersons", resultFields);
        
        for (String x: list){
            System.out.println(x);
        }
        
        input = new Scanner(System.in);
        statCall = new Statistic<Call>();
        statConf = new Statistic<Conf>();
        legalPersonTree = new TreeSet<LegalPerson>(new LegalPersonComparator()); 
        physicalPersonTree = new TreeSet<PhysicalPerson>(new PhysicalPersonComparator());
        legalPersonTree.add(new LegalPerson("Bulgakov Dmitriy Olegovich","3506800","Pushkina-Kolotushkina","1435236257"));
        physicalPersonTree.add(new PhysicalPerson("Teslenok Roman Konstantinovich","3502100","Petrovskaya-Rasumovskaya","88005553535"));
        statCall.add(new Call("Dmitriy","Roman",44));
        String[] users = {"Andrey", "Roman", "Dmitriy"};
        statConf.add(new Conf(users,100));
        while(true){
            if (mainCycle() <= 0) break;
        }
    }

    public static void printAllPersons(){
        clearConsole();
        System.out.println("<><><> Legal Persons: <><><>");
        if (legalPersonTree.isEmpty()){
            System.out.println("Empty.");
        }
        for(LegalPerson x: legalPersonTree){
            printPerson(x);
            System.out.println("");
        }
        System.out.println("");
        System.out.println("<><><> Physical Persons: <><><>");
        if (physicalPersonTree.isEmpty()){
            System.out.println("Empty.");
        }
        for(PhysicalPerson x: physicalPersonTree){
            printPerson(x);
            System.out.println("");
        }
        input.nextLine();
    }
    
    public static int deleteAnyPerson(){
        clearConsole();
        System.out.println("Is person to delete Legal or Physical? (input L or P):");
        String buffer = input.nextLine();
        if (buffer.equals("L") || buffer.equals("l") ){
            System.out.print("Enter FIO: ");
            String FIO = input.nextLine();
            while(FIO.isEmpty()){
                FIO = input.nextLine();
            }
            if(legalPersonTree.remove(new LegalPerson(FIO,"","",""))){
                System.out.print("Successfully deleted!");
                LegalPerson.remove();
            }
            else{
                System.out.print("There is no person with same FIO in PhoneBook!");
                input.nextLine();
                return 1;
            }
        }
        if (buffer.equals("P") || buffer.equals("p") ){
            System.out.print("Enter FIO: ");
            String FIO = input.nextLine();
            while(FIO.isEmpty()){
                FIO = input.nextLine();
            }
            if(physicalPersonTree.remove(new PhysicalPerson(FIO,"","",""))){
                System.out.print("Successfully deleted!");
                PhysicalPerson.remove();
            }
            else{
                System.out.print("There is no person with same FIO in PhoneBook!");
                input.nextLine();
                return 1;
            }
        }
        input.nextLine();
        return 0;
    }

    public static int addNewPerson(){
        clearConsole();
        System.out.println("Is new person Legal or Physical? (input L or P):");
        String buffer = input.nextLine();
        if (buffer.equals("L") || buffer.equals("l") ){
            System.out.print("Enter FIO: ");
            String FIO = input.nextLine();
            while(FIO.isEmpty()){
                FIO = input.nextLine();
            }
            System.out.print("Enter Phone Number: ");
            String Phone = input.nextLine();
            while(Phone.isEmpty()){
                Phone = input.nextLine();
            }
            System.out.print("Enter Address: ");
            String Address = input.nextLine();
            while(Address.isEmpty()){
                Address = input.nextLine();
            }
            System.out.print("Enter INN: ");
            String INN = input.nextLine();
            while(INN.isEmpty()){
                INN = input.nextLine();
            }
            LegalPerson newPerson = new LegalPerson(FIO, Phone, Address, INN);
            if (legalPersonTree.add(newPerson)){
                System.out.print("New person added to database!");
            }
            else{
                System.out.print("This person already exists!");
                input.nextLine();
                return 1;
            }
        }
        if (buffer.equals("P") || buffer.equals("p") ){
            System.out.print("Enter FIO: ");
            String FIO = input.nextLine();
            while(FIO.isEmpty()){
                FIO = input.nextLine();
            }
            System.out.print("Enter Phone Number: ");
            String Phone = input.nextLine();
            while(Phone.isEmpty()){
                Phone = input.nextLine();
            }
            System.out.print("Enter Address: ");
            String Address = input.nextLine();
            while(Address.isEmpty()){
                Address = input.nextLine();
            }
            System.out.print("Enter Mobile Phone Number: ");
            String mobilePhone = input.nextLine();
            while(mobilePhone.isEmpty()){
                mobilePhone = input.nextLine();
            }
            PhysicalPerson newPerson = new PhysicalPerson(FIO, Phone, Address, mobilePhone);
            if (physicalPersonTree.add(newPerson)){
                System.out.print("New person added to database!");
            }
            else{
                System.out.print("This person already exists!");
                input.nextLine();
                return 1;
            }
        }
        input.nextLine();
        return 0;
    }

public static void writeDatabasesSQL(){
        clearConsole(); 
        databaseSQL = new DatabaseSQL("phoneBook");
        databaseSQL.executeUpdate("DELETE FROM legalPersons");
        for (LegalPerson x: legalPersonTree){
            databaseSQL.executeUpdate("INSERT INTO legalPersons VALUES(" + x.toSQL() + ")");
        }
        databaseSQL.executeUpdate("DELETE FROM physicalPersons");
        for (PhysicalPerson x: physicalPersonTree){
            databaseSQL.executeUpdate("INSERT INTO physicalPersons VALUES(" + x.toSQL() + ")");
        }
        String[] data = statCall.toSQL();
        databaseSQL.executeUpdate("DELETE FROM calls");
        for (String x: data){
            databaseSQL.executeUpdate("INSERT INTO calls VALUES(" + x + ")");
        }
        data = statConf.toSQL();
        databaseSQL.executeUpdate("DELETE FROM conferences");
        for (String x: data){
            databaseSQL.executeUpdate("INSERT INTO conferences VALUES(" + x + ")");
        }
        input.nextLine();
        return;
    }


    public static void readDatabasesSQL(){
        clearConsole();
        
        databaseSQL = new DatabaseSQL("phoneBook");
        ArrayList<String> resultFields = new ArrayList<String>(Arrays.asList("id","fio","phone","address","inn"));
        ArrayList<String> resultOfQuery = databaseSQL.executeQuery("SELECT * FROM legalPersons", resultFields);
        LegalPerson.clear();
        legalPersonTree.clear();
        for (String x: resultOfQuery){
            LegalPerson newLegalPerson = new LegalPerson("","","","");
            if (newLegalPerson.fromCSV(x) == 0){
                legalPersonTree.add(newLegalPerson);
            }
            else {
                newLegalPerson.clear();
            }
        }
        
        resultFields = new ArrayList<String>(Arrays.asList("id","fio","phone","address","mobile_phone"));
        resultOfQuery = databaseSQL.executeQuery("SELECT * FROM physicalPersons", resultFields);
        PhysicalPerson.clear();
        physicalPersonTree.clear();
        for (String x: resultOfQuery){
            PhysicalPerson newPhysicalPerson = new PhysicalPerson("","","","");
            if (newPhysicalPerson.fromCSV(x) == 0){
                physicalPersonTree.add(newPhysicalPerson);
            }
            else {
                newPhysicalPerson.clear();
            }
        }
        
        resultFields = new ArrayList<String>(Arrays.asList("id","time","participants"));
        resultOfQuery = databaseSQL.executeQuery("SELECT * FROM calls", resultFields);
        statCall.clear();
        for(String x: resultOfQuery){
            Call newCall = new Call("","",0);
            if (newCall.fromCSV(x) == 0){
                statCall.add(newCall);
            }
        }
        
        resultFields = new ArrayList<String>(Arrays.asList("id","time","participants"));
        resultOfQuery = databaseSQL.executeQuery("SELECT * FROM conferences", resultFields);
        statConf.clear();
        for(String x: resultOfQuery){
            Conf newConf = new Conf(new String[0],0);
            if (newConf.fromCSV(x) == 0){
                statConf.add(newConf);
            }
        }
        input.nextLine();
        return;
    }

    public static void writeDatabasesCSV(){
        clearConsole(); 
        String[] data = new String[legalPersonTree.size()];
        int iterator = 0;
        for (LegalPerson x: legalPersonTree){
            data[iterator] = x.toCSV();
            iterator++;
        }
        DatabaseCSV.write("LegalPersons.csv", data);
        data = new String[physicalPersonTree.size()];
        iterator = 0;
        for (PhysicalPerson x: physicalPersonTree){
            data[iterator] = x.toCSV();
            iterator++;
        }
        DatabaseCSV.write("PhysicalPersons.csv", data);
        data = statCall.toCSV();
        DatabaseCSV.write("Calls.csv", data);
        data = statConf.toCSV();
        DatabaseCSV.write("Conferences.csv", data);
        input.nextLine();
        return;
    }


    public static void readDatabasesCSV(){
        clearConsole();
        String stringFromDatabase = DatabaseCSV.read("LegalPersons.csv");
        String[] lines = stringFromDatabase.split("\n");
        LegalPerson.clear();
        legalPersonTree.clear();
        for(String x: lines){
            LegalPerson newLegalPerson = new LegalPerson("","","","");
            if (newLegalPerson.fromCSV(x) == 0){
                legalPersonTree.add(newLegalPerson);
            }
            else {
                newLegalPerson.clear();
            }
        }
        stringFromDatabase = DatabaseCSV.read("PhysicalPersons.csv");
        lines = stringFromDatabase.split("\n");
        PhysicalPerson.clear();
        physicalPersonTree.clear();
        for(String x: lines){
            PhysicalPerson newPhysicalPerson = new PhysicalPerson("","","","");
            if (newPhysicalPerson.fromCSV(x) == 0){
                physicalPersonTree.add(newPhysicalPerson);
            }
            else {
                newPhysicalPerson.clear();
            }
        }
        stringFromDatabase = DatabaseCSV.read("Calls.csv");
        lines = stringFromDatabase.split("\n");
        statCall.clear();
        for(String x: lines){
            Call newCall = new Call("","",0);
            if (newCall.fromCSV(x) == 0){
                statCall.add(newCall);
            }
        }
        stringFromDatabase = DatabaseCSV.read("Conferences.csv");
        lines = stringFromDatabase.split("\n");
        statConf.clear();
        for(String x: lines){
            Conf newConf = new Conf(new String[0],0);
            if (newConf.fromCSV(x) == 0){
                statConf.add(newConf);
            }
        }
        input.nextLine();
        return;
    }
    
    private static void addNewCall(){
        clearConsole();
        System.out.println("Is call? (input y or n)");
        if (input.nextLine().equals("y")){
            System.out.print("User A: ");
            String userA = input.nextLine();
            System.out.print("User B: ");
            String userB = input.nextLine();
            System.out.print("Time: ");
            int time = input.nextInt();
            Call newCall = new Call(userA, userB, time); 
            statCall.add(newCall);
        }
        else{
            System.out.println("Is conference? (input y or n)");
            if (input.nextLine().equals("y")){
                ArrayList<String> users = new ArrayList<String>();
                System.out.print("User " + users.size() + " : ");
                String name = input.nextLine();
                while(name.length() > 0){
                    users.add(name);
                    System.out.print("User " + users.size() + " : ");
                    name = input.nextLine();
                }
                if (users.size() > 2){
                    
                    String[] stringUsers = users.toArray(new String[0]);
                    System.out.print("Time: ");
                    int time = input.nextInt();
                    Conf newConf = new Conf(stringUsers, time);
                    statConf.add(newConf);
                }
                else{
                    System.out.print("It's not conference! Try to add into calls.");
                }
            }
        }
        input.nextLine();
    }
    
    private static void printStatistic(){
        clearConsole();
        System.out.println("<><><> Calls: <><><>");
        System.out.println("All time: " + statCall.allTime() + " seconds");
        Call maxCall = statCall.getMax();
        if (maxCall != null){
            System.out.println("The longest call: " + maxCall.getTime() + " seconds" + " ( " + maxCall.getA() + " -> " + maxCall.getB() + " )");
        }
        System.out.println("<><><> Conferences: <><><>");
        System.out.println("All time: " + statConf.allTime() + " seconds");
        Conf maxConf = statConf.getMax();    
        if (maxConf != null){
            System.out.print("The longest conference: " + maxConf.getTime() + " seconds ( Members: ");
            for (String x: maxConf.getUsers()){
                System.out.print(x + ", ");
            }
            System.out.println(" )");
        }
        input.nextLine();
    } 
    
    private static void printPerson(LegalPerson person){
        System.out.println("ID: " + person.getID() + "\nName: " + person.getFio() + "\nPhone: " + person.getPhone() + "\nAddress: " + person.getAddress() + "\nINN: " +
                person.getINN());
    }
    
    private static void printPerson(PhysicalPerson person){
        System.out.println("ID: " + person.getID() + "\nName: " + person.getFio() + "\nPhone: " + person.getPhone() + "\nAddress: " + person.getAddress() + 
            "\nMobileNumber: " + person.getMobilePhone());
    }
    
    public static void clearConsole(){
        try{
            new ProcessBuilder("/bin/bash","-c","clear").inheritIO().start().waitFor();
        }
        catch(InterruptedException | IOException e){
            e.printStackTrace();
        }
    }
    
    public static int mainCycle(){
        clearConsole();
        System.out.println("<><><> Phone Book Menu <><><>");
        System.out.println("1. Print all persons\n2. Add new person\n3. Delete any person\n4. Read CSV Database\n5. Write CSV Database\n6. Read SQL Database\n7. Write SQL Database\n8. Print Statistic\n9. Add new Call\n0. Exit program");
        System.out.print("Enter menu item number: ");
        String buffer = input.nextLine();
        switch(Integer.parseInt(buffer)){
        case 1: 
            printAllPersons();
        break; 
        case 2:
            addNewPerson();
        break;
        case 3:   
            deleteAnyPerson();
        break;
        case 4:
            readDatabasesCSV();
        break;
        case 5:
            writeDatabasesCSV();
        break;
        case 6:
            readDatabasesSQL();
        break;
        case 7:
            writeDatabasesSQL();
        break;
        case 8:
            printStatistic();
        break;
        case 9:
            addNewCall();
         break;
        case 0:
            return 0;
        default:
            return -1;
        }
        return 5; 
    } 
    
}

