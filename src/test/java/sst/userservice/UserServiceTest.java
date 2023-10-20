package sst.userservice;



import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import sst.userservice.model.User;
import sst.userservice.repository.UserRepository;
import sst.userservice.service.UserServiceImpl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest()
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;


    public List<User> myUsers;

    LocalDate date = LocalDate.parse("1995-09-14");


    User user1 = new User("test95","Niclas","test95@outlook.com",date );
    User user2 = new User("test96","Niclas","test96@outlook.com",date );
    User user3 = new User("test97","Niclas","test97@outlook.com",date );


    @Test
    @Order(1)
    public void test_getAllUsers(){
        List<User> myUsers = new ArrayList<User>();

        myUsers.add(user1);
        myUsers.add(user2);
        myUsers.add(user3);


        when(userRepository.findAll()).thenReturn(myUsers); // Mocking

        assertEquals(3,userService.getAllUsers().size());
    }

    @Test
    @Order(2)
    public void test_getUser(){


        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
        when(userRepository.findById(3L)).thenReturn(Optional.of(user3));
        when(userRepository.findById(4L)).thenReturn(Optional.empty());

        assertEquals("test95", userService.getUser(1L).getUserName());
        assertEquals("test96", userService.getUser(2L).getUserName());
        assertEquals("test97", userService.getUser(3L).getUserName());
        assertThrowsExactly(NoSuchElementException.class, () -> userService.getUser(4L));
    }

    @Test
    @Order(3)
    public void test_createUser() throws SQLIntegrityConstraintViolationException {
        User dbUser = user1;
        dbUser.setUserId(1);
        User failEmail = new User();
        failEmail.update(user1);
        failEmail.setEmail("testmail@fail.com");

        when(userRepository.save(user1)).thenReturn(dbUser);

        assertEquals(1, userService.createUser(user1).getUserId());
        assertThrowsExactly(SQLIntegrityConstraintViolationException.class, () -> userService.createUser(failEmail));

    }

    @Test
    @Order(4)
    public void test_deleteUser(){

        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.existsById(2L)).thenReturn(false);
        assertTrue(userService.deleteUser(1L));
        assertFalse(userService.deleteUser(2L));

    }

    @Test
    @Order((5))
    public void test_updateUser(){
        User updated = new User("niclas95","niclas","niclas.larsson@outlook.com", date);
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
        when(userRepository.findById(3L)).thenReturn(Optional.empty());
        when(userRepository.save(user2)).thenReturn(user2);
        assertEquals("niclas.larsson@outlook.com",userService.updateUser(updated,2L).getEmail());
        assertThrowsExactly(NoSuchElementException.class, () -> userService.updateUser(updated,3L));
    }

    @Test
    public void test_validateEmail(){

        assertTrue(userService.validateEmail("testmail@outlook.com"));
        assertTrue(userService.validateEmail("testmail@hotmail.com"));
        assertTrue(userService.validateEmail("testmail@gmail.com"));
        assertFalse(userService.validateEmail("notvalid.email.com"));
        assertFalse(userService.validateEmail("notvalid@email"));
        assertFalse(userService.validateEmail("test@outlook.com.se"));
        assertFalse(userService.validateEmail(".test@outlook.com"));
    }
}
