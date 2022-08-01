package com.example.myauthenticationserver.repository;

import com.example.myauthenticationserver.entity.Phone;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    @NonNull List<Phone> findByUuid(String uuid);

}
