package com.hashtable;

public class Main {

    
    public static void main(String[] args) {
        
        HashTable table = new HashTable(1009);

        table.insert("Yasser", "015");
        table.insert("Hossam", "012");
        table.insert("Amr", "011")   ;
        table.insert("Moaz", "010")  ;

        table.print();

        System.out.println(table.size());

        table.insert("Hamada k", "01222");

        table.print();
    }
}