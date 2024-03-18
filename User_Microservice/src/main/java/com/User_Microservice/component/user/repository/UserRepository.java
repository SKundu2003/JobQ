package com.User_Microservice.component.user.repository;

import com.User_Microservice.component.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
}
