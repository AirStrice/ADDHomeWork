import java.util.*;

class PhoneBook {
    private Map<String, List<String>> contacts;

    public PhoneBook() {
        this.contacts = new HashMap<>();
    }

    public void add(String surname, String phoneNumber) {
        if (surname == null || surname.isBlank()) throw new IllegalArgumentException("Фамилия пуста");
        if (phoneNumber == null || phoneNumber.isBlank()) throw new IllegalArgumentException("Номер пуст");

        contacts.putIfAbsent(surname, new ArrayList<>());
        contacts.get(surname).add(phoneNumber);
    }

    public void get(String surname) {
        List<String> phones = contacts.get(surname);
        if (phones == null || phones.isEmpty()) {
            System.out.println(surname + ": не найдено");
            return;
        }
        System.out.println(surname + ": " + phones);
    }

    public void printAll() {
        for (Map.Entry<String, List<String>> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}