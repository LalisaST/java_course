package edu.hw3.task5;

import java.util.Arrays;
import java.util.Comparator;

public class ParseContacts {

    public Person[] parseContacts(String[] contacts, String sorting) {
        if (contacts == null || contacts.length == 0) {
            return new Person[] {};
        }
        if (sorting == null) {
            throw new IllegalArgumentException();
        }

        Person[] parseContactsArray;
        parseContactsArray = parser(contacts);
        sorter(parseContactsArray, sorting);

        return parseContactsArray;
    }

    private Person[] parser(String[] contacts) {
        Person fullName;

        Person[] parseContactsArray = new Person[contacts.length];
        for (int i = 0; i < contacts.length; i++) {
            if (contacts[i] != null) {
                String person = contacts[i];
                String[] parsedFullName = person.split("\\s+");
                if (parsedFullName[0].isEmpty()) {
                    throw new IllegalArgumentException();
                }
                if (parsedFullName.length == 1) {
                    fullName = new Person(parsedFullName[0], null);
                    parseContactsArray[i] = fullName;
                } else if (parsedFullName.length == 2) {
                    fullName = new Person(parsedFullName[0], parsedFullName[1]);
                    parseContactsArray[i] = fullName;
                } else {
                    throw new IllegalArgumentException();
                }
                parseContactsArray[i] = fullName;
            } else {
                throw new IllegalArgumentException();
            }
        }
        return parseContactsArray;
    }

    private void sorter(Person[] parseContactsArray, String sorting) {

        if (sorting.equals("ASC")) {
            Arrays.sort(parseContactsArray);
        } else if (sorting.equals("DESC")) {
            Arrays.sort(parseContactsArray, Comparator.reverseOrder());
        } else {
            throw new IllegalArgumentException();
        }

    }
}
