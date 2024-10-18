public class Testing {
    public static void main(String[] args) {
        MyHashMapWithQuad<Integer, Integer> map = new MyHashMapWithQuad<>();

        map.put(44, 44);
        map.put(4, 4);
        map.put(17, 17);
        map.put(28, 28);
        map.put(21, 21);
        map.put(26, 26);

        System.out.println("Map capacity: " + map.table.length);
        System.out.println("Map size: " + map.size());
        System.out.println(map);
    }
}
