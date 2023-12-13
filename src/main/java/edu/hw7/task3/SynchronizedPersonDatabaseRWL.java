package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SynchronizedPersonDatabaseRWL implements PersonDatabase {
    private final Map<Integer, Person> idToPersonMap = new HashMap<>();
    private final Map<String, List<Person>> nameIndex = new HashMap<>();
    private final Map<String, List<Person>> addressIndex = new HashMap<>();
    private final Map<String, List<Person>> phoneIndex = new HashMap<>();

    ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();

    @Override
    public void add(Person person) {
        try {
            writeLock.lock();
            checkingValidityPerson(person);

            idToPersonMap.put(person.id(), person);

            addToIndex(nameIndex, person.name(), person);
            addToIndex(addressIndex, person.address(), person);
            addToIndex(phoneIndex, person.phoneNumber(), person);
        } finally {
            writeLock.unlock();
        }

    }

    @Override
    public void delete(int id) {
        try {
            writeLock.lock();

            Person personToRemove = idToPersonMap.remove(id);
            if (personToRemove != null) {
                removeFromIndex(nameIndex, personToRemove.name(), personToRemove);
                removeFromIndex(addressIndex, personToRemove.address(), personToRemove);
                removeFromIndex(phoneIndex, personToRemove.phoneNumber(), personToRemove);
            } else {
                throw new IllegalArgumentException();
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        List<Person> result;

        try {
            readLock.lock();
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException();
            }

            result = nameIndex.getOrDefault(name, new ArrayList<>());
        } finally {
            readLock.unlock();
        }

        return result;
    }

    @Override
    public List<Person> findByAddress(String address) {
        List<Person> result;

        try {
            readLock.lock();
            if (address == null || address.isBlank()) {
                throw new IllegalArgumentException();
            }

            result = addressIndex.getOrDefault(address, new ArrayList<>());
        } finally {
            readLock.unlock();
        }

        return result;
    }

    @Override
    public List<Person> findByPhone(String phone) {
        List<Person> result;

        try {
            readLock.lock();
            if (phone == null || phone.isBlank()) {
                throw new IllegalArgumentException();
            }

            result = phoneIndex.getOrDefault(phone, new ArrayList<>());
        } finally {
            readLock.unlock();
        }

        return result;
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
