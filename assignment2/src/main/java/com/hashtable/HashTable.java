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

    private long calc_hash(String key) {

        int i, l = key.length();
        long hash = 0;
        for (i = 0; i < l; i++) {
            hash += Character.getNumericValue(key.charAt(i));
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);

        if (hash > 0)
            return hash % this.capacity;
        else
            return -hash % this.capacity;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == this.capacity;
    }

    public int size() {
        return size;
    }

    public void insert(String name, String phone_number) {
        if (isFull()) {
            System.out.println("Hash table is full");
        } else {
            int index = (int) calc_hash(name);
            while (table[index] != null) {
                index = (index + 1) % this.capacity;
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
        int index = (int) calc_hash(name);
        while (table[index] != null && table[index].occupiedBefore) {
            if (table[index] != null && Objects.equals(table[index].Name, name)) {
                System.out.println("Contact found!");
                System.out.println("Name: " + table[index].Name + " ,Number: " + table[index].phoneNumber);
                return true;
            }
            index = (index + 1) % this.capacity;
        }
        System.out.println("Name not found");
        return false;
    }

    public void remove(String name) {
        int index = (int) calc_hash(name);
        boolean exist = false;
        while (table[index] != null && table[index].occupiedBefore) {
            if (Objects.equals(table[index].Name, name)) {
                table[index] = null;
                size--;
                System.out.println(table[index].Name + " removed from contacts!");
                exist = true;
            }
            index = (index + 1) % this.capacity;
        }
        if (!exist)
            System.out.println("Contact doesn't exist!");

    }

    public void print() {
        boolean exist = false;
        for (int i = 0; i < this.capacity; i++) {
            if (table[i] != null) {
                System.out.println("Name: " + table[i].Name + ", Number :" + table[i].phoneNumber);
                exist = true;
            }
        }
        if (!exist)
            System.out.println("No contacts in the list!");
    }

    public void update(String name, String new_phone_number) {
        int index = (int) calc_hash(name);
        while (table[index] != null && table[index].occupiedBefore) {
            if (Objects.equals(table[index].Name, name)) {
                table[index].phoneNumber = new_phone_number;
                System.out.println(
                        "Contact name: " + table[index].Name + ", Number updated to: " + table[index].phoneNumber);
                return;
            }
            index = (index + 1) % this.capacity;
        }
        System.out.println("Name not found!");
    }

}
