package com.mechanitis.mongodb.gettingstarted;

import com.mechanitis.mongodb.gettingstarted.person.Address;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.List;

class Person {
    private final String id;
    private final String name;
    private final Address address;
    private final List<Integer> bookIds;

    Person(final String id, final String name, final Address address, final List<Integer> bookIds) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.bookIds = bookIds;
    }

    public DBObject toDBObject() {
        DBObject addressAsDBObject = new BasicDBObject("street", address.getStreet())
                                     .append("city", address.getTown())
                                     .append("phone", address.getPhone());
        return new BasicDBObject("_id", id)
               .append("name", name)
               .append("address", addressAsDBObject)
               .append("books", bookIds);
    }

    //getters and setters

    String getId() {
        return id;
    }

    String getName() {
        return name;
    }

    Address getAddress() {
        return address;
    }

    List<Integer> getBookIds() {
        return bookIds;
    }


    //useful for testing

    @Override
    public String toString() {
        return "Person{"
               + "id='" + id + '\''
               + ", name='" + name + '\''
               + ", address=" + address
               + ", bookIds=" + bookIds
               + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Person person = (Person) o;

        if (!address.equals(person.address)) {
            return false;
        }
        if (!bookIds.equals(person.bookIds)) {
            return false;
        }
        if (!id.equals(person.id)) {
            return false;
        }
        if (!name.equals(person.name)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + bookIds.hashCode();
        return result;
    }
}
