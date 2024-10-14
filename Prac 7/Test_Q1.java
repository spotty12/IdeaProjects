public class Test_Q1 {
    public static void main(String[] args) {
        MyHashMapWithQuad<String, Integer> map = new MyHashMapWithQuad<String, Integer>();
        System.out.println("The hashing table initial capacity is: "+map.table.length);
        map.put("Edgar", 29);
        map.put("Lennon", 32);
        map.put("Bill", 42);
        map.put("Gloria", 53);
        map.put("Mark", 61);
        map.put("Thabo", 78);
        //map.put("Russ", 18);
        System.out.println(map);
        System.out.println("The hashing table capacity is now: "+map.table.length);
        System.out.println("Is key Lennon in the map? " + map.containsKey("Lennon"));
        map.remove("Lennon");
        System.out.println("Is key John in the map? " + map.containsKey("John"));
        map.remove("Bill");
        System.out.print(map);
    }
}
