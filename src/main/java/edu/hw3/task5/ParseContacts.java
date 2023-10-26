package edu.hw3.task5;

import java.util.Arrays;

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
                String person = contacts[i].trim();
                String[] parsedFullName = person.split(" ");
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
            Arrays.sort(parseContactsArray, (a, b) -> {
                if (a.surname() != null && b.surname() != null) {
                    return a.surname().compareTo(b.surname());
                } else if (a.surname() == null && b.surname() != null) {
                    return a.name().compareTo(b.surname());
                } else if (a.surname() != null) {
                    return a.surname().compareTo(b.name());
                } else {
                    return a.name().compareTo(b.name());
                }
            });
        } else if (sorting.equals("DESC")) {
            Arrays.sort(parseContactsArray, (a, b) -> {
                if (b.surname() != null && a.surname() != null) {
                    return b.surname().compareTo(a.surname());
                } else if (b.surname() == null && a.surname() != null) {
                    return b.name().compareTo(a.surname());
                } else if (b.surname() != null) {
                    return b.surname().compareTo(a.name());
                } else {
                    return b.name().compareTo(a.name());
                }
            });
        } else {
            throw new IllegalArgumentException();
        }

    }
}
