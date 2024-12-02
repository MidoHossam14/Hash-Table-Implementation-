package com.hashtable;

import java.util.Objects;

public class CustomHashMap {

    public static Entry[] table = new Entry[1009];
    private static int tableCapacity = table.length;
    private static int currentElementCount = 0;

    public static class Entry {
        String key;
        String value;
        boolean isOccupied;

        public Entry(String key, String value) {
            this.key = key;
            this.value = value;
            this.isOccupied = true;
        }
    }

    private static long computeHashValue(String key) {
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
        return (hash > 0) ? hash % tableCapacity : -hash % tableCapacity;
    }

    public static boolean isTableEmpty() {
        return currentElementCount == 0;
    }

    public static boolean isTableFull() {
        return currentElementCount == tableCapacity;
    }

    public static boolean insertEntry(String key, String value) {
        if (!isTableFull()) {
            int index = (int) computeHashValue(key);
            while (table[index] != null && table[index].isOccupied) {
                index = (index + 1) % tableCapacity;
            }
            table[index] = new Entry(key, String.valueOf(value));
            currentElementCount++;
            return true;
        }
        return false;
    }

    public static boolean removeEntry(String key) {
        int index = (int) computeHashValue(key);
        while (table[index] != null && table[index].isOccupied) {
            if (Objects.equals(table[index].key, key)) {
                table[index].isOccupied = false;
                currentElementCount--;
                return true;
            }
            index = (index + 1) % tableCapacity;
        }
        return false;
    }

    public static String searchEntry(String key) {
        if (!isTableEmpty()) {
            int index = (int) computeHashValue(key);
            while (table[index] != null && table[index].isOccupied) {
                if (Objects.equals(table[index].key, key)) {
                    return table[index].value;
                }
                index = (index + 1) % tableCapacity;
            }
        }
        return null;
    }

    public static boolean updateEntry(String key, String newValue) {
        int index = (int) computeHashValue(key);
        while (table[index] != null && table[index].isOccupied) {
            if (Objects.equals(table[index].key, key)) {
                table[index].value = newValue;
                return true;
            }
            index = (index + 1) % tableCapacity;
        }
        return false;
    }

    public static String printTable() {
        StringBuilder tableContents = new StringBuilder();
        for (int i = 0; i < tableCapacity; i++) {
            if (table[i] != null && table[i].isOccupied) {
                tableContents.append("Index ").append(i)
                        .append(": Key = ").append(table[i].key)
                        .append(", Value = ").append(table[i].value)
                        .append("\n");
            }
        }
        return tableContents.toString();
    }

    public static Object[][] getTableData() {
        Object[][] data = new Object[currentElementCount][3];
        int index = 0;
        for (int i = 0; i < tableCapacity; i++) {
            if (table[i] != null && table[i].isOccupied) {
                data[index][0] = i;
                data[index][1] = table[i].key;
                data[index][2] = table[i].value;
                index++;
            }
        }
        return data;
    }
}