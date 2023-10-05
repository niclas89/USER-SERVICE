package sst.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sst.userservice.model.User;

import sst.userservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;



    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new RuntimeException();
        }
        return user.get();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user,Long id) {
        Optional<User> dbUser = userRepository.findById(id);
        if(dbUser.isPresent()){
            dbUser.get().update(user);  ;
            return userRepository.save(dbUser.get());
        }else{
            throw new RuntimeException("No user with that id");
        }

    }

    @Override
    public boolean deleteUser(Long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return  true;
        }
        return false;
    }
}
