package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PhoneBookTest {
    PhoneBook phoneBook = PhoneBook.getInstance();

    @Order(1)
    @ParameterizedTest
    @CsvSource(value = {
            "Varya,+79682347543, 1",
            "Katrin,8(972)883-74-56, 2",
            "Lola,984-678-93-34, 3",
            "Varya,948-456-22-33, 3",
            ",980-345-44-33,3",
            ",Don,3",
            ",,3"
    })
    void testAddition(String name, String phone, int expectedValue) {
        int result = phoneBook.add(name, phone);
        assertEquals(result, expectedValue);
    }

    @Order(2)
    @ParameterizedTest
    @CsvSource(value = {
            "+79682347543, Varya",
            "8(972)883-74-56, Katrin",
            "984-678-93-34, Lola",
            ", Номер не найден",
            "000-000-00-00, Номер не найден",
    })
    void testFindByNumber(String phone, String expectedValue) {
        String result = phoneBook.findByNumber(phone);
        assertEquals(result, expectedValue);
    }

    @Order(3)
    @ParameterizedTest
    @CsvSource(value = {
            "Varya, +79682347543",
            "Katrin, 8(972)883-74-56",
            "Lola, 984-678-93-34",
            ", Номер не найден",
            "000-000-00-00, Номер не найден",
    })
    void testFindByName(String name, String expectedValue) {
        String result = phoneBook.findByName(name);
        assertEquals(result, expectedValue);
    }

    @Order(4)
    @Test
    void testPrintAllNames() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        phoneBook.printAllNames();
        String printed = out.toString().trim();

        String[] expected = new String[]{"Katrin", "Lola", "Varya"};
        String ex = Arrays.toString(expected);

        assertEquals(printed, ex);
    }
}