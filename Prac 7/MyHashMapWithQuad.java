//Total 50 marks
public class MyHashMapWithQuad<K, V> implements MyMap<K, V>{
    // Define the default hash table size.
    private static final int DEFAULT_INITIAL_CAPACITY = 4;

    // Define the maximum hash table size. 1 << 30 is same as 2^30
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    // Current hash table capacity.
    private int capacity;

    // Define default load factor
    private static final float DEFAULT_MAX_LOAD_FACTOR = 0.5f;

    // Specify a load factor used in the hash table
    private float loadFactorThreshold;

    // The number of entries in the map
    private int size = 0;

    // add your variable for your hashing table

    /** Construct a map with the default capacity and load factor */
    public MyHashMapWithQuad() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    /** Construct a map with the specified initial capacity and
     * default load factor */
    public MyHashMapWithQuad(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    /** Construct a map with the specified initial capacity
     * and load factor */
    public MyHashMapWithQuad(int initialCapacity, float loadFactorThreshold) {
        if(initialCapacity>MAXIMUM_CAPACITY)
            this.capacity = initialCapacity;
        else
            this.capacity = trimToPowerOf2(initialCapacity);

        this.loadFactorThreshold = loadFactorThreshold;
        //add a line of code for your hashing table                                 // 1 marks
    }

    /** Remove all the entries from this map */
    public void clear() {
        size = 0;
        removeEntries();
    }

    /** Return true if the specified key is in the map */
    public boolean containsKey(K key) {
        if (get(key) != null)
            return true;
        else
            return false;
    }

    /** Return true if this map contains the specified value */
    public boolean containsValue(V value) {                            // 4 marks
    
		//add your code here

        return false;
    }

    /** Return a set of entries in the map */
    public java.util.Set<MyMap.Entry<K,V>> entrySet() {// 3 marks
        java.util.Set<MyMap.Entry<K, V>> set = new java.util.HashSet<MyMap.Entry<K, V>>();
                                          
            // add your code here

        return set;
    }

    /** Return the first value that matches the specified key */
    public V get(K key) {                                                   // 10 marks
        // Perform quadratic probing
		
		//add your code here

        return null;
    }

    /** Return all values for the specified key in this map */
    public java.util.Set<V> getAll(K key) {                              // 4 marks
        java.util.Set<V> set = new java.util.HashSet<V>();

		// add your cide here

        return set;
    }

    /** Return true if this map contains no entries */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return a set consisting of the keys in this map */
    public java.util.Set<K> keySet() {                                  // 3 marks
        java.util.Set<K> set = new java.util.HashSet<K>();

		//add your code here

        return set;
    }

    /** Add an entry (key, value) into the map */
    public V put(K key, V value) {                                      //10 marks
		
		//add your code here
		
        return value;
    }

    /** Remove the element for the specified key */
    public void remove(K key) {                                             // 10 marks
		
		//add your code here
		
    }

    /** Return the number of mappings in this map */
    public int size() {
        return size;
    }

    /** Return a set consisting of the values in this map */
    public java.util.Set<V> values() {                                          // 3 marks
        java.util.Set<V> set = new java.util.HashSet<V>();

		//add your code

        return set;
    }

    /** Hash function */
    private int hash(int hashCode) {
       // return hashCode % capacity;
      return supplementalHash(hashCode) & (capacity - 1);
    }

    /** Ensure the hashing is evenly distributed */
    private static int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /** Return a power of 2 for initialCapacity */
    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }
        return capacity;
    }

    /** Remove all entries from each bucket */
    private void removeEntries() {                                              // 2 marks
		
		//add your code here. this should run in O(1)
		
    }

    /** Rehash the map */
    private void rehash() {
        java.util.Set<Entry<K, V>> set = entrySet(); // Get entries
        capacity <<= 1; // Double capacity
        table = new Entry[capacity]; // Create a new hash table
        size = 0; // Clear size
        for (Entry<K, V> entry: set) {
            put(entry.getKey(), entry.getValue()); // Store to new table
        }
    }

    @Override /** Return a string representation for this map */
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && table[i].key != null)
                builder.append(table[i].toString());
        }
        return builder.append("]").toString();
    }
}
