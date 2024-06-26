package org.example;

import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PhoneBook {
    private static PhoneBook INSTANCE = null;
    private static ConcurrentMap<String, String> phoneBook = new ConcurrentHashMap<>();

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

    public int add(String name, String phone){
        try {
            phoneBook.putIfAbsent(name, phone);
        } catch (NullPointerException e) {
            System.out.println("Пользователь с таким именем существует");
        }
        return phoneBook.size();
    }

    public String findByNumber(String number) {
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            try {
                if (number.equals(entry.getValue())) {
                    return entry.getKey();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return "Номер не найден";
    }

    public String findByName(String name) {
        try {
            return phoneBook.getOrDefault(name, "Номер не найден");
        } catch (NullPointerException e) {
            e.printStackTrace();
            return "Номер не найден";
        }
    }

    public void printAllNames() {
        System.out.println(new TreeSet<>(phoneBook.keySet()));
    }
}
