package com.example.globallogicauth.service;

import com.example.globallogicauth.dto.UserDto;
import com.example.globallogicauth.entity.UserBase;
import com.example.globallogicauth.error.ConflictException;
import com.example.globallogicauth.error.UnauthorizedException;
import com.example.globallogicauth.facade.ModelMapperService;
import com.example.globallogicauth.repository.UserBaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.Servlet;
import java.time.Instant;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserBaseServiceTests {

    @InjectMocks
    UserBaseService userBaseService;

    @Mock
    UserBaseRepository userBaseRepository;

    @Mock
    ModelMapperService modelMapperService;

    private final String uuid = "d010bc3f-f7cb-4c7c-98f8-a57a7855afc4";
    private final String uuid2 = "e123cc3k-g5k7-j7u3-8iu7-b47a9055afc6";
    private final String email = "franco@gmail.com";
    private final String name = "Franco";
    private final String password = "andNew12";
    private final Instant created = Instant.now();
    private final Instant lastLogin = Instant.now().plusSeconds(3600);
    private final boolean isActive = true;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        UserBase userBase = UserBase.builder()
                .uuid(uuid)
                .email(email)
                .password(password)
                .created(created)
                .isActive(isActive)
                .name(name)
                .lastLogin(lastLogin)
                .build();

        boolean isActive2 = false;
        String email2 = "franco2@gmail.com";

        UserBase userBase2 = UserBase.builder()
                .uuid(uuid2)
                .email(email2)
                .password(password)
                .created(created)
                .isActive(isActive2)
                .name(name)
                .lastLogin(lastLogin)
                .build();

        UserDto userDto = UserDto.builder()
                .uuid(uuid)
                .email(email)
                .created(created)
                .isActive(isActive)
                .name(name)
                .lastLogin(lastLogin)
                .build();

        when(userBaseRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userBaseRepository.findByUuid(anyString())).thenReturn(Optional.empty());
        when(userBaseRepository.findByUuid(uuid)).thenReturn(Optional.of(userBase));
        when(userBaseRepository.findByUuid(uuid2)).thenReturn(Optional.of(userBase2));
        when(userBaseRepository.findByEmail(email)).thenReturn(Optional.of(userBase));
        when(modelMapperService.map(userBase, UserDto.class)).thenReturn(userDto);
        doThrow(IllegalArgumentException.class).when(this.userBaseRepository).save(userBase);
    }

    @Test
    public void findByUuidAndSafeNullTest(){
        UserBase userBase = this.userBaseService.findByUuidSafeNull(uuid);

        assertNotNull(userBase);
        assertEquals(email, userBase.getEmail());
    }

    @Test
    public void findDtoByUuidTest(){
        UserDto userDto = this.userBaseService.findDtoByUuid(uuid);

        assertNotNull(userDto);
        assertEquals(userDto.getClass(), UserDto.class);
    }

    @Test
    public void isEmailInUseTest(){
        assertThrows(UnauthorizedException.class, () -> this.userBaseService.isEmailInUse(email));
        assertDoesNotThrow(() -> this.userBaseService.isEmailInUse("franco2@gmail.com"));
    }


    @Test
    public void insertUserTest(){
        assertDoesNotThrow(() -> this.userBaseService.insertUser(UserDto.builder()
                        .uuid(uuid)
                        .email(email)
                        .created(created)
                        .isActive(isActive)
                        .name(name)
                        .lastLogin(lastLogin)
                        .build(), "andNew123"
        ));
    }

    @Test
    public void validationAuthSignInTest(){
        assertThrows(ConflictException.class, () -> this.userBaseService.validationAuthSignIn("anotherEmail@gmail.com"));
        assertDoesNotThrow(() -> this.userBaseService.validationAuthSignIn(email));
    }

    @Test
    public void existingUserWithEmailTest(){
        assertTrue(this.userBaseService.existingUserWithEmail(email));
        assertFalse(this.userBaseService.existingUserWithEmail("anotherEmail@gmail.com"));
    }

    @Test
    public void loadUserByUsernameTest(){
        UserDetails userDetails = this.userBaseService.loadUserByUsername(uuid);

        assertEquals(email, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
    }

    @Test
    public void userEnabledTest(){
        assertTrue(this.userBaseService.userEnabled(uuid));
        assertTrue(this.userBaseService.userEnabled("nonExistingUuid"));
        assertFalse(this.userBaseService.userEnabled(uuid2));
    }

    @Test
    public void isUuidRegisteredTest(){
        assertTrue(this.userBaseService.isUuidRegistered(uuid));
        assertFalse(this.userBaseService.isUuidRegistered("nonExistingUuid"));
    }
}
