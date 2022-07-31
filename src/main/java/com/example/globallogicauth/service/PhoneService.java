package com.example.globallogicauth.service;

import com.example.globallogicauth.dto.PhoneDto;
import com.example.globallogicauth.entity.Phone;
import com.example.globallogicauth.facade.ModelMapperService;
import com.example.globallogicauth.repository.PhoneRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PhoneService {

    private final PhoneRepository phoneRepository;

    private final ModelMapperService modelMapperService;

    @NonNull public List<PhoneDto> findByUuid(@NonNull String uuid){
        List<Phone> phones = this.phoneRepository.findByUuid(uuid);
        // this::toDto method reference incorporated in Java8
        return phones.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void save(@NonNull PhoneDto phoneDto){
        Phone phone = this.toEntity(phoneDto);
        this.phoneRepository.save(phone);
    }

    @NonNull private Phone toEntity(@NonNull PhoneDto phoneDto){
       return this.modelMapperService.map(phoneDto, Phone.class);
    }

    @NonNull private PhoneDto toDto(@NonNull Phone phone){
        return this.modelMapperService.map(phone, PhoneDto.class);
    }
}
