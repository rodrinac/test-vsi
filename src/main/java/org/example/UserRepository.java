package org.example;

public class UserRepository {

    public User findByEmail(String mail) {
        return new User();
    }

    public User findById(Integer id) {
        return new User();
    }

    public boolean delete(Integer id) {
        return true;
    }
}
