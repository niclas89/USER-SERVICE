package sst.userservice.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sst.userservice.model.User;

import sst.userservice.repository.UserRepository;

import java.sql.Array;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private ArrayList<String> emailList = new ArrayList<>();





    private UserRepository userRepository;


    private RestTemplate restTemplate = new RestTemplate();
    String temp = "http://localhost:8080//album-service/api/v1/users/";


    private final String [] URLS = {"http://localhost:8080//played-media-service/api/v1/users/","http://localhost:8080//rating-service/api/v1/users/"} ;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        emailList.add("outlook.com");
        emailList.add("gmail.com");
        emailList.add("hotmail.com");
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new NoSuchElementException("No user found with the id:" + id);
        }
        return user.get();
    }

    @Override
    public User createUser(User user) throws SQLIntegrityConstraintViolationException {
        if(validateEmail(user.getEmail())) {
            User createdUser = userRepository.save(user);
            notifyServices(createdUser,URLS);
            return createdUser;
        }
        throw new SQLIntegrityConstraintViolationException("!email");
    }

    @Override
    public User updateUser(User user,Long id) {
        Optional<User> dbUser = userRepository.findById(id);
        if(dbUser.isPresent()){
            if(validateEmail(user.getEmail())) {
                dbUser.get().update(user);

            }
            return userRepository.save(dbUser.get());
        }else{
            throw new NoSuchElementException("No user with that id");
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

    public boolean validateEmail(String email){
        if(!email.startsWith(".")){
            if(email.contains("@")){
                int start = email.indexOf("@")+1;
                    for(int i = 0; i< emailList.size(); i++) {
                        if (email.substring(start).equals(emailList.get(i))  ){
                            return true;
                        } ;
                    }

            }
        }
        return false;




    }

    public void notifyServices(User user,String [] URLS){
        System.out.println("In notifyServices");
        for(int i = 0; i<URLS.length; i++) {
            try {
                System.out.println(URLS[i]);
                restTemplate.postForLocation(URLS[i], user);

            } catch (Exception exc) {
              exc.printStackTrace();
            }
        }

    }
}
