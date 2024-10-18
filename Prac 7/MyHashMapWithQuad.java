import java.util.HashSet;
import java.util.Set;

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
    Entry<K, V>[] table;

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
        table = new Entry[capacity];
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
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null){
                if (table[i].getValue().equals(value)){
                    return true;
                }
            }
        }
        return false;
    }

    /** Return a set of entries in the map */
    public Set<Entry<K,V>> entrySet() {// 3 marks
        Set<Entry<K, V>> set = new HashSet<>();
                                          
        // add your code here
        for (int i = 0; i < capacity; i++) {
            //Adds all elements in table into set
            if (table[i] != null){
                set.add(table[i]);
            }
        }
        return set;
    }

    /** Return the first value that matches the specified key */
    public V get(K key) {                                                   // 10 marks
        // Perform quadratic probing
        //add your code here

        //j used to determine number of collisions
        for (int j = 0; j < capacity; j++) {
            //Quadratic probing
            int index = hash(key.hashCode() + (j * j));
            if (table[index] != null){
                //First value that matches key is returned
                if (table[index].getKey().equals(key)){
                    return table[index].getValue();
                }
            } else {
                //Exit loop if index value is null
                break;
            }
        }
        return null;
    }

    /** Return all values for the specified key in this map */
    public Set<V> getAll(K key) {                              // 4 marks
        Set<V> set = new HashSet<V>();

		// add your cide here
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null){
                if (table[i].getKey().equals(key)){
                    //Set of all values for specific key
                    set.add(table[i].getValue());
                }
            }
        }
        return set;
    }

    /** Return true if this map contains no entries */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return a set consisting of the keys in this map */
    public Set<K> keySet() {                                  // 3 marks
        Set<K> set = new HashSet<K>();

		//add your code here
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null){
                //Set of all keys in map
                set.add(table[i].getKey());
            }
        }
        return set;
    }

    /** Add an entry (key, value) into the map */
    public V put(K key, V value) {                                      //10 marks
		//add your code here
        //Initialise index
		int index = 0;

        //The key already in map
        if (get(key) != null){
            for (int j = 0; j < capacity; j++) {
                index = hash(key.hashCode() + (j * j)); //Quad probing
                if (table[index] != null){
                    //Replace old value with new value
                    if (table[index].getKey().equals(key)){
                        V oldValue = table[index].getValue();
                        table[index].value = value;
                        return oldValue;
                    }
                }
            }
        }

        //Check load factor
        if (size >= capacity * loadFactorThreshold) {
            if (capacity == MAXIMUM_CAPACITY) {
                throw new RuntimeException("Maximum capacity exceeded");
            }
            rehash();
        }

        //Perform quadratic probing
        for (int j = 0; j < capacity; j++) {
            index = hash(key.hashCode() + (j * j)); //Quad probing
            if (table[index] == null){
                break;
            }
        }

        //Add new value to map
        table[index] = new Entry<>(key, value);
        size++;
        return value;
    }

    /** Remove the element for the specified key */
    public void remove(K key) {                                             // 10 marks
		//add your code here
        //Perform quadratic probing
        for (int j = 0; j < capacity; j++) {
            int index = hash(key.hashCode() + (j * j)); //Quad probing
            if (table[index] != null){
                //Remove value when key matches
                if (table[index].getKey().equals(key)){
                    table[index] = null;
                    size--; //Decrease size
                    break;
                }
            } else {
                //Exit loop when index value is null
                break;
            }
        }
    }

    /** Return the number of mappings in this map */
    public int size() {
        return size;
    }

    /** Return a set consisting of the values in this map */
    public Set<V> values() {                                          // 3 marks
        Set<V> set = new HashSet<V>();

		//add your code
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null){
                //Set of all values in map
                set.add(table[i].getValue());
            }
        }
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
        //Creates a table with same capacity, lose reference to old table
		table = new Entry[capacity];
    }

    /** Rehash the map */
    private void rehash() {
        Set<Entry<K, V>> set = entrySet(); // Get entries
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
