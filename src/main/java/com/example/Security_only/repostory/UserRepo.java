package com.example.Security_only.repostory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Security_only.entity.UserData;

@Repository
public interface UserRepo extends JpaRepository<UserData, Integer>{

	Optional<UserData> findByName(String username);

	
}
