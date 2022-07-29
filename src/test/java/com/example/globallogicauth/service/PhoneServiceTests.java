package com.example.globallogicauth.service;

import com.example.globallogicauth.dto.PhoneDto;
import com.example.globallogicauth.dto.UserDto;
import com.example.globallogicauth.entity.Phone;
import com.example.globallogicauth.facade.ModelMapperService;
import com.example.globallogicauth.repository.PhoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class PhoneServiceTests {

    @InjectMocks
    PhoneService phoneService;

    @Mock
    PhoneRepository phoneRepository;

    @Mock
    ModelMapperService modelMapperService;

    private final String uuid = "d010bc3f-f7cb-4c7c-98f8-a57a7855afc4";
    private final long number = 1123456788L;
    private final int cityCode = 111;
    private final String countryCode = "+54";

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        Phone phone = Phone.builder()
                .uuid(uuid)
                .number(number)
                .cityCode(cityCode)
                .countryCode(countryCode)
                .build();

        long number2 = 1198654377L;
        int cityCode2 = 333;
        String countryCode2 = "+111";

        Phone phone2 = Phone.builder()
                .uuid(uuid)
                .number(number2)
                .cityCode(cityCode2)
                .countryCode(countryCode2)
                .build();

        PhoneDto phoneDto = PhoneDto.builder()
                .uuid(uuid)
                .number(number)
                .cityCode(cityCode)
                .countryCode(countryCode)
                .build();

        PhoneDto phoneDto2 = PhoneDto.builder()
                .uuid(uuid)
                .number(number2)
                .cityCode(cityCode2)
                .countryCode(countryCode2)
                .build();

        ArrayList<Phone> phones = new ArrayList<>();
        phones.add(phone);
        phones.add(phone2);

        when(this.phoneRepository.findByUuid(uuid)).thenReturn(phones);
        when(modelMapperService.map(phone, PhoneDto.class)).thenReturn(phoneDto);
        when(modelMapperService.map(phoneDto, Phone.class)).thenReturn(phone);
        when(modelMapperService.map(phone2, PhoneDto.class)).thenReturn(phoneDto2);
        when(modelMapperService.map(phoneDto2, Phone.class)).thenReturn(phone2);
        doThrow(IllegalArgumentException.class).when(this.phoneRepository).save(phone);
    }

    @Test
    public void findByUuidTest(){
        List<PhoneDto> phoneDtos = this.phoneService.findByUuid(uuid);

        assertNotNull(phoneDtos);
        assertEquals(phoneDtos.get(0).getClass(), PhoneDto.class);
        assertEquals(phoneDtos.get(1).getClass(), PhoneDto.class);
        assertEquals(2, phoneDtos.size());
    }

    @Test
    public void saveTest(){
        assertDoesNotThrow( () -> this.phoneService.save(PhoneDto.builder()
                .uuid(uuid)
                .number(number)
                .cityCode(cityCode)
                .countryCode(countryCode)
                .build()));
    }

}
