package com.bridgelab;

//UC1 - Ability to find frequency of words in a sentence like “To be or not to be”

import java.util.*;

class Hash<K,V> {
     K key;
     V value;

    final int hashCode;
    Hash<K,V> next;
// Parameterized Constructor
    Hash(K key, V value, int hashCode) {
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

    HashTables() {
        bucketList = new ArrayList<Hash>();
        numOfBuckets = 10;
        size = 0;

        for (int i = 0; i < numOfBuckets; i++){
            bucketList.add(null);
        }
    }

    public int hashCode(K key){
        return Objects.hashCode(key);
    }

    public int getBucketIndex(K key){
        int index = hashCode(key) % numOfBuckets;
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
        head = bucketList.get(index); //head = null
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
               System.out.println(" [ Key: " + temp.key + " Value: " + temp.value + " ] ==> ");
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

        for (int i = 0; i < strArray.length; i++){
            map.add(i,strArray[i]);
        }

        System.out.println("Frequency of words is as follows::: ");
        map.print();
    }

}
