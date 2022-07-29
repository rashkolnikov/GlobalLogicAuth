package com.example.globallogicauth.repository;

import com.example.globallogicauth.entity.Phone;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    @NonNull List<Phone> findByUuid(String uuid);

}
