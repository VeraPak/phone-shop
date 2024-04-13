package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PhoneBookTest {
    PhoneBook phoneBook = PhoneBook.getInstance();
    /*
    Метод должен возвращать количество контактов
    после добавления, при этом гарантируется,
    что не будут добавляться повторяющиеся имена
     */
    @ParameterizedTest
    @CsvSource(value = {
            "Varya, +79682347543, 1",
            "Katrin, 8(972)883-74-56, 2",
            "Lola, 984-678-93-34, 3",
            "Varya, 948-456-22-33, 3",
            ",980-345-44-33, 3",
            ",Don, 3",
            ",,3"
    })
    void testAddition(String name, String phone, int expectedValue) {
        int result = phoneBook.add(name, phone);
        assertEquals(result, expectedValue);
    }

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
}