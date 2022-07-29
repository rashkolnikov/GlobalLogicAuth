package com.example.globallogicauth.repository;

import com.example.globallogicauth.entity.UserBase;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBaseRepository extends JpaRepository<UserBase, Long> {

    @NonNull Optional<UserBase> findByUuid(String uuid);

    @NonNull Optional<UserBase> findByEmail(String email);

}
