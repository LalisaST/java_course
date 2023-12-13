package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynchronizedPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> idToPersonMap = new HashMap<>();
    private final Map<String, List<Person>> nameIndex = new HashMap<>();
    private final Map<String, List<Person>> addressIndex = new HashMap<>();
    private final Map<String, List<Person>> phoneIndex = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        checkingValidityPerson(person);

        idToPersonMap.put(person.id(), person);

        addToIndex(nameIndex, person.name(), person);
        addToIndex(addressIndex, person.address(), person);
        addToIndex(phoneIndex, person.phoneNumber(), person);
    }

    @Override
    public synchronized void delete(int id) {
        Person personToRemove = idToPersonMap.remove(id);
        if (personToRemove != null) {
            removeFromIndex(nameIndex, personToRemove.name(), personToRemove);
            removeFromIndex(addressIndex, personToRemove.address(), personToRemove);
            removeFromIndex(phoneIndex, personToRemove.phoneNumber(), personToRemove);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }

        return nameIndex.getOrDefault(name, new ArrayList<>());
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException();
        }

        return addressIndex.getOrDefault(address, new ArrayList<>());
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException();
        }

        return phoneIndex.getOrDefault(phone, new ArrayList<>());
    }

    private <T> void addToIndex(Map<T, List<Person>> index, T key, Person person) {
        index.computeIfAbsent(key, k -> new ArrayList<>()).add(person);
    }

    private <T> void removeFromIndex(Map<T, List<Person>> index, T key, Person person) {
        List<Person> persons = index.get(key);
        if (persons != null) {
            persons.remove(person);
            if (persons.isEmpty()) {
                index.remove(key);
            }
        }
    }

    private void checkingValidityPerson(Person person) {
        if (person == null || person.id() < 0 || person.address() == null || person.name() == null
            || person.phoneNumber() == null || person.phoneNumber().isBlank() || person.address().isBlank()
            || person.name().isBlank()) {
            throw new IllegalArgumentException();
        }
    }
}
