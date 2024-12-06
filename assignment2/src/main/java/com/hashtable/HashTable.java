package com.hashtable;

public class HashTable {

    public final Entry[] table;
    private final int Capacity;
    private int size;

    public HashTable(int capacity) {
        this.Capacity = capacity;
        this.table = new Entry[capacity];
        this.size = 0;
    }

    public static class Entry {
        String key;
        String phoneNumber;
        boolean isOccupied;

        public Entry(String key, String phoneNumber) {
            this.key = key;
            this.phoneNumber = phoneNumber;
            this.isOccupied = true;
        }
    }

    private long calc_hash(String key) {
        int keyLength = key.length();
        long hash = 0;
        for (int i = 0; i < keyLength; i++) {
            hash += Character.getNumericValue(key.charAt(i));
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);

        return (hash > 0) ? hash % Capacity : -hash % Capacity;
    }

    public boolean isTableEmpty() {
        return size == 0;
    }

    public boolean isTableFull() {
        return size == Capacity;
    }

    public boolean insertEntry(String key, String PhoneNumber) {
        if (!isTableFull()) {
            int index = (int) calc_hash(key);
            while (table[index] != null && table[index].isOccupied) {
                index = (index + 1) % Capacity;
            }
            table[index] = new Entry(key, String.valueOf(PhoneNumber));
            size++;
            return true;
        }
        return false;
    }

    public boolean removeEntry(String key) {
        int index = (int) calc_hash(key);
        int originalIndex = index; // To detect a full cycle

        while (table[index] != null) {
            if (table[index].isOccupied && table[index].key.equalsIgnoreCase(key)) {
                // Mark the entry as not occupied
                table[index].isOccupied = false;
                size--;

                // Rehash elements in the cluster
                int nextIndex = (index + 1) % Capacity;

                if (table[nextIndex] != null) {
                    while (table[nextIndex] != null && table[nextIndex].isOccupied) {
                        Entry reHashEntry = table[nextIndex];
                        table[nextIndex] = null;
                        size--; // Temporarily reduce size for reinsertion

                        insertEntry(reHashEntry.key, reHashEntry.phoneNumber);
                        nextIndex = (nextIndex + 1) % Capacity;
                    }
                }

                return true;
            }

            index = (index + 1) % Capacity;

            if (index == originalIndex) {
                // We've looped back to the start; exit to prevent infinite loops
                break;
            }
        }

        return false;
    }


    public String searchEntry(String key) {
        if (!isTableEmpty()) {
            int index = (int) calc_hash(key);
            // To avoid infinity loop if the table is full.
            int originalIndex = index;
            while (table[index] != null && table[index].isOccupied) {
                if (table[index].key.equalsIgnoreCase(key)) {
                    return table[index].phoneNumber;
                }
                index = (index + 1) % Capacity;
                // Break the loop 
                if ( index == originalIndex)
                    break ;
            }
        }
        return null;
    }

    public boolean updateEntry(String key, String newPhoneNumber) {
        int index = (int) calc_hash(key);
        // To avoid infinity loop if the table is full.
        int originalIndex = index;
        while (table[index] != null && table[index].isOccupied) {
            if (table[index].key.equalsIgnoreCase(key)) {
                table[index].phoneNumber = newPhoneNumber;
                return true;
            }
            index = (index + 1) % Capacity;
            if ( index == originalIndex)
                    break ;
        }
        return false;
    }

    public Object[][] getTableData() {
        Object[][] data = new Object[size][3];
        int index = 0;
        for (int i = 0; i < Capacity; i++) {
            if (table[i] != null && table[i].isOccupied) {
                data[index][0] = table[i].key;
                data[index][1] = i;
                data[index][2] = table[i].key + " - " + table[i].phoneNumber;
                index++;
            }
        }
        return data;
    }

    
}
