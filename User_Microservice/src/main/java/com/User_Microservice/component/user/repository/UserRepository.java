package com.User_Microservice.component.user.repository;

import com.User_Microservice.component.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

    interface MetaData {
        String TABLE_NAME = "user_details";
    }

    String FIND_BY_USER_NAME = "SELECT * FROM " + MetaData.TABLE_NAME + " WHERE user_name = :userName OR email = :userName";

    @Query(value = FIND_BY_USER_NAME, nativeQuery = true)
    Optional<UserEntity> findByUserName(String userName);
}
