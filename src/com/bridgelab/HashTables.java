package com.bridgelab;

//UC2 - Ability to find frequency of words in a large
//paragraph phrase “Paranoids are not
//paranoid because they are paranoid but
//because they keep putting themselves
//deliberately into paranoid avoidable
//situations”

import java.util.*;

class Hash<K,V> {
     K key;
     V value;

    final int hashCode;
    Hash<K,V> next;
// Parameterized Constructor
    public Hash(K key, V value, int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }
}

public class HashTables<K,V> {
//     bucketArray is used to store array of chains
    private ArrayList<Hash> bucketList;
//    Current Capacity of array list
    private int numOfBuckets;
//    Current size of array list
    private int size;

    public HashTables() {
        bucketList = new ArrayList<>();
        numOfBuckets = 10;
        size = 0;

        for (int i = 0; i < numOfBuckets; i++){
            bucketList.add(null);
        }
    }

    private int hashCode(K key){
        return Objects.hashCode(key);
    }

    public int getBucketIndex(K key){
        int hashCode = hashCode(key);
        int index = hashCode % numOfBuckets;
        index = index < 0 ? index * -1 : index;
        return index;
    }

    public void add(K key, V value){
        int hashCode = hashCode(key);
        int index = getBucketIndex(key);

        Hash<K,V> head = bucketList.get(index);

        while (head != null){
            if (head.key.equals(key) && head.hashCode == hashCode) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = bucketList.get(index);
        Hash<K,V> newNode = new Hash<K,V>(key, value, hashCode);
        newNode.next = head;
        bucketList.set(index, newNode);
    }

    public void print(){
        for (Hash<K,V> head : bucketList){
           Hash<K,V> temp = head;
           if (head != null)
               System.out.print(" [ " + bucketList.indexOf(head) + " ] ==> ");
           else
               System.out.println(" [ ] ");
           while (temp != null){
               System.out.print(" [ Key: " + temp.key + " Value: " + temp.value + " ] ==> ");
               temp = temp.next;
           }
           if (head != null)
               System.out.println();
        }
    }

    public static void main(String[] args) {

        System.out.println("Welcome to the Hash Tables Problems");
        HashTables<Integer,String> map = new HashTables<>();
        String str = "To be or not to be";
        String[] strArray = str.split(" ");
        Random random = new Random();
        for (int i = 0; i < strArray.length; i++){
            map.add(random.nextInt(10),strArray[i]);
        }

        System.out.println("Frequency of words is as follows::: ");
        map.print();
    }

}
