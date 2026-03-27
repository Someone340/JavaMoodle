import java.util.HashMap;

public class CustomHashMap<K, V> implements CustomMap<K, V>{
    private static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public final String toString() {
            return key + " = " + value;
        }

        public final boolean equals(Object o) {
            return o == this;
        }
    }

    private Node<K, V>[] buckets;
    private final int CAPACITY = 16;

    public CustomHashMap() {
        buckets = new Node[CAPACITY];
    }

    @Override
    public int hash(Object key) {
        return (key == null) ? 0 : Math.abs(key.hashCode() % CAPACITY);
    }

    @Override
    public void put(K key, V value) {
        int index = hash(key);
        Node<K, V> node = buckets[index];

        while (node != null) {
            if (node.key != null && node.key.equals(key)) {
                node.value = value;
                return;
            }
            if (node.next == null) break;
            node = node.next;
        }

        Node<K, V> newNode = new Node<>(hash(key), key, value);
        if (buckets[index] == null) {
            buckets[index] = newNode;
        } else {
            node.next = newNode;
        }
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        Node<K, V> node = buckets[index];
        while (node != null) {
            if (node.key != null && node.key.equals(key)) return node.value;
            node = node.next;
        }
        return null;
    }

    @Override
    public void remove(K key) {
        int index = hash(key);
        Node<K, V> node = buckets[index];
        Node<K, V> prev = null;

        while (node != null) {
            if (node.key != null && node.key.equals(key)) {
                if (prev == null) buckets[index] = node.next;
                else prev.next = node.next;
                return;
            }
            prev = node;
            node = node.next;
        }
    }

    public static void main(String[] args) {
        CustomHashMap<String, Integer> test = new CustomHashMap<>();
        test.put("Test1", 1);
        test.put("Test2", 2);
        System.out.println(test.get("Test1"));
        test.remove("Test1");
        System.out.println(test.get("Test1"));
        test.remove("Test2");
        System.out.println(test.get("Test2"));
    }
}
