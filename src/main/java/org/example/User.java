package org.example;

import java.util.Objects;

/**
 * When working with database entities, the same entity instance may be manipulated in multiple places.
 * Without overriding `equals`, comparisons might return `false` even when only the `id` field should determine equality.
 * Overriding `equals` ensures that two instances representing the same entity (based on `id`) are considered equal.
 */
public class User {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private String telephone;
    private UserRole role = UserRole.REGULAR;

    public User() {

    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(final String name, final String email, final String address, final String telephone) {
        this.id = null;
        this.name = name;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserRole getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof User) && Objects.equals(this.id, ((User)obj).id);
    }

    // The hash code is used storing the object in data structures like HashMap, HashSet, ...
    // If we don't override storing the same entity (based on the `id`) will lead to duplications
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
