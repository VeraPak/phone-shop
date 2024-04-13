package org.example;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PhoneBook {
    private static PhoneBook INSTANCE = null;
    ConcurrentMap<String, String> phoneBook = new ConcurrentHashMap<>();

    private PhoneBook(){}
    public static PhoneBook getInstance() {
        if(INSTANCE == null) {
            synchronized (PhoneBook.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PhoneBook();
                }
            }
        }
        return INSTANCE;
    }

    int add(String name, String phone){
        try {
            phoneBook.putIfAbsent(name, phone);
        } catch (NullPointerException e) {
            System.out.println("Пользователь с таким именем существует");
        }
        return phoneBook.size();
    }
}
