//package com.example.welcome.service;
//
//import com.example.welcome.exception.AuthException;
//import com.example.welcome.model.User;
//import com.example.welcome.repo.UserRepo;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTests {
//
//    @Mock
//    UserRepo userRepo;
//
//    @Mock
//    PasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    UserService userService;
//
//    @Test
//    public void Should_Return_List_Of_Users_On_Get_All_Users() {
//
//        User user1 = new User("sparsh@example.com", "1234","Sparsh Sethi");
//        User user2 = new User("john@example.com", "0987","John Doe");
//
//        when(userRepo.findAll()).thenReturn(List.of(user1, user2));
//
//        List<User> foundUsers = userService.getAllUsers();
//
//        assertThat(foundUsers).isNotNull();
//        assertThat(foundUsers).isInstanceOf(List.class);
//        assertThat(foundUsers).contains(user1);
//        assertThat(foundUsers).contains(user2);
//    }
//
//
//    @Test
//    public void Should_Return_One_User_On_Signup() throws AuthException {
//
//        User user = new User("sparsh@example.com", "1234","Sparsh Sethi");
//
//        when(userRepo.findByEmail(user.getEmail())).thenReturn(Optional.empty());
//
//        user.setId(UUID.randomUUID());
//        when(userRepo.save(user)).thenReturn(user);
//
//        User signedUpUser = userService.signup(user);
//
//        assertThat(signedUpUser).isNotNull();
//        assertThat(signedUpUser.getId()).isEqualTo(user.getId());
//        assertThat(signedUpUser.getPassword()).isNotEqualTo(user.getPassword());
//    }
//
//    @Test
//    public void Should_Encode_The_Password_On_Signup() throws AuthException {
//        User user = new User("sparsh@example.com", "1234","Sparsh Sethi");
//        when(userRepo.findByEmail(user.getEmail())).thenReturn(Optional.empty());
//
//        user.setId(UUID.randomUUID());
//        when(userRepo.save(user)).thenReturn(user);
//        when(passwordEncoder.encode(user.getPassword())).thenReturn("encoded_password");
//
//        User signedUpUser = userService.signup(user);
//
//        verify(passwordEncoder).encode(any(String.class));
//    }
//
//    @Test()
//    public void Should_Throw_Exception_When_Existing_Email_On_Signup() {
//
//        User user = new User("sparsh@example.com", "1234","Sparsh Sethi");
//        user.setId(UUID.randomUUID());
//
//        when(userRepo.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
//
//        AuthException authException = assertThrows(AuthException.class, () -> {
//            userService.signup(user);
//        });
//
//        assertThat(authException).isInstanceOf(AuthException.class);
//        assertThat(authException).hasMessage("Email ID already exists");
//    }
//}
//
//
