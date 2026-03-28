public interface CustomMap<K, V> {
    int hash(K key);
    void put(K key, V value);
    V get(K key);
    void remove(K key);
}