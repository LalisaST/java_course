package edu.hw3.task5;

import org.jetbrains.annotations.NotNull;

public record Person(String name, String surname) implements Comparable<Person> {

    public int compareTo(@NotNull Person o) {
        String thisPerson = this.surname == null ? this.name : this.surname;
        String oPerson = o.surname == null ? o.name : o.surname;

        return thisPerson.compareTo(oPerson);
    }
}

