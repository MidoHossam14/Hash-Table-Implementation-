import com.hashtable.HashTable;

public class HashTableTest {

    public static void main(String[] args) {
        // Create a HashTable instance
        HashTable hashTable = new HashTable(10);

        System.out.println("=== Testing Insertion ===");
        testInsertion(hashTable);

        System.out.println("\n=== Testing Removal with Rehashing ===");
        testRemoval(hashTable);

        System.out.println("\n=== Testing Search ===");
        testSearch(hashTable);

        System.out.println("\n=== Testing Update ===");
        testUpdate(hashTable);

        System.out.println("\n=== Testing Edge Cases ===");
        testEdgeCases();
    }

    public static void testInsertion(HashTable hashTable) {
        // Insert entries
        assert hashTable.insertEntry("Alice", "1234");
        assert hashTable.insertEntry("Bob", "5678");
        assert hashTable.insertEntry("Charlie", "91011");

        // Print table state
        for (Object[] entry : hashTable.getTableData()) {
            System.out.println("Key: " + entry[0] + ", Index: " + entry[1] + ", Data: " + entry[2]);
        }
    }

    public static void testRemoval(HashTable hashTable) {
        // Remove an entry
        assert hashTable.removeEntry("Alice");

        // Check the removal
        assert hashTable.searchEntry("Alice") == null;

        // Check rehashing (entries should still be accessible)
        assert hashTable.searchEntry("Bob") != null;
        assert hashTable.searchEntry("Charlie") != null;

        // Print table state
        for (Object[] entry : hashTable.getTableData()) {
            System.out.println("Key: " + entry[0] + ", Index: " + entry[1] + ", Data: " + entry[2]);
        }
    }

    public static void testSearch(HashTable hashTable) {
        // Search for existing and non-existing keys
        String phone = hashTable.searchEntry("Bob");
        assert phone != null && phone.equals("5678");

        phone = hashTable.searchEntry("NonExistent");
        assert phone == null;

        System.out.println("Search tests passed.");
    }

    public static void testUpdate(HashTable hashTable) {
        // Update an existing entry
        assert hashTable.updateEntry("Charlie", "111213");

        // Verify update
        String phone = hashTable.searchEntry("Charlie");
        assert phone != null && phone.equals("111213");

        // Try updating a non-existent key
        assert !hashTable.updateEntry("NonExistent", "0000");

        System.out.println("Update tests passed.");
    }

    public static void testEdgeCases() {
        // Test an empty hash table
        HashTable emptyHashTable = new HashTable(5);
        assert emptyHashTable.isTableEmpty();
        assert emptyHashTable.searchEntry("Empty") == null;

        // Test a full hash table
        HashTable fullHashTable = new HashTable(2);
        assert fullHashTable.insertEntry("Key1", "1111");
        assert fullHashTable.insertEntry("Key2", "2222");
        assert !fullHashTable.insertEntry("Key3", "3333"); // Should fail

        System.out.println("Edge case tests passed.");
    }
}
