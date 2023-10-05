package sst.userservice.service;

import sst.userservice.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(Long id);

    User createUser(User user);

    User updateUser(User user,Long id);

    boolean deleteUser(Long id);
}
