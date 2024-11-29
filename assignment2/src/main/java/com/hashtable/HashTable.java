package com.hashtable;

import java.util.Objects;

public class HashTable {
     private final Entry[] table;
    private final int capacity;
    private int size;


    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new Entry[capacity];
        this.size = 0;
    }

    private class Entry {
        String Name;
        String phoneNumber;
        boolean occupiedBefore;

        public Entry(String Name, String phoneNumber) {
            this.Name = Name;
            this.phoneNumber = phoneNumber;
            this.occupiedBefore = true;
        }
    }

    private int hashFunction(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public void insert(String name, String phone_number) {
        if (isFull()) {
            System.out.println("Hash table is full");
        } else {
            int index = hashFunction(name);
            while (table[index] != null) {
                index = (index + 1) % capacity;
            }
            table[index] = new Entry(name, phone_number);
            size++;
        }
    }

    public boolean search(String name) {
        if (isEmpty()) {
            System.out.println("Hash table is empty");
            return false;
        }
        int index = Math.abs(name.hashCode() % capacity);
        while (table[index] != null && table[index].occupiedBefore) {
            if (table[index] != null && Objects.equals(table[index].Name, name)) {
                System.out.println("Contact found!");
                System.out.println("Name: " + table[index].Name + " ,Number: " + table[index].phoneNumber);
                return true;
            }
            index = (index + 1) % capacity;
        }
        System.out.println("Name not found");
        return false;
    }

    public void remove(String name) {
        int index = Math.abs(name.hashCode() % capacity);
        boolean exist = false;
        while (table[index] != null && table[index].occupiedBefore) {
            if (Objects.equals(table[index].Name, name)) {
                System.out.println(table[index].Name + " removed from contacts!");
                table[index] = null;
                size--;
                exist = true;
            }
            index = (index + 1) % capacity;
        }
        if(!exist) System.out.println("Contact doesn't exist!");

    }

    public void print() {
        boolean exist = false;
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                System.out.println("Name: " + table[i].Name + " ,Number :" + table[i].phoneNumber);
                exist = true;
            }
        }
        if(!exist) System.out.println("No contacts in the list!");
    }

    public void update(String name, String new_phone_number) {
        int index = hashFunction(name);
        while (table[index] != null && table[index].occupiedBefore) {
            if (Objects.equals(table[index].Name, name)) {
                table[index].phoneNumber = new_phone_number;
                System.out.println("Contact name: " + table[index].Name + " ,Number updated to: " + table[index].phoneNumber);
                return;
            }
            index = (index + 1) % capacity;
        }
        System.out.println("Name not found!");
    }

}
