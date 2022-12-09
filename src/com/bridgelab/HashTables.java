package com.bridgelab;

//UC3 - Remove avoidable word from the
//phrase “Paranoids are not paranoid
//because they are paranoid but
//because they keep putting themselves
//deliberately into paranoid avoidable
//situations”

import java.security.Key;
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


    public HashTables() {
        bucketList = new ArrayList<>();
        numOfBuckets = 10;

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
        head = bucketList.get(index);
        Hash<K,V> newNode = new Hash<K,V>(key, value, hashCode);
        newNode.next = head;
        bucketList.set(index, newNode);
    }
    
//    Remove method
    public void remove(K key){
        int index = getBucketIndex(key);
        int hashCode = hashCode(key);
//        head of the list finding
        Hash<K,V> head = bucketList.get(index);
        if (head == null) {
            System.out.println("Hash table is empty......");
            return;
        } else if (head.next == null && head.key.equals(key) && hashCode == head.hashCode) {
            head = null;
            bucketList.set(index,head);
            return;
        }

        Hash<K,V> temp = head;
        Hash<K,V> left = temp;
        Hash<K,V> right = left.next;
//        delete first element
        if (temp.key.equals(key) && hashCode == temp.hashCode){
            temp = temp.next;
        }
        while (right != null){
            if (right.key.equals(key) && hashCode == temp.hashCode){
                left.next = right.next;
                break;
            }
            left = left.next;
            right = right.next;
        }
        bucketList.set(index,temp);
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
        map.add(10,strArray[0]);
        map.add(40,strArray[1]);
        map.add(25,strArray[2]);
        map.add(13,strArray[3]);
        map.add(45,strArray[4]);
        map.add(2,strArray[5]);

        
//        Random random = new Random();
//        for (int i = 0; i < strArray.length; i++){
//            map.add(random.nextInt(10),strArray[i]);
//        }

        System.out.println("Frequency of words is as follows::: ");
        map.print();

        map.remove(45);
        map.remove(2);
        System.out.println("After remove Operation::: ");
        map.print();

    }

}
