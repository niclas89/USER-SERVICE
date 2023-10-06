package sst.userservice.service;

import sst.userservice.model.User;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(Long id);

    User createUser(User user) throws SQLIntegrityConstraintViolationException;

    User updateUser(User user,Long id);

    boolean deleteUser(Long id);
}
