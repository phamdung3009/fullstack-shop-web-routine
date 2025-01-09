package com.fullstack.shop.web.routine.repository;

import com.fullstack.shop.web.routine.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	/*@Query(value = "SELECT * FROM USER WHERE email = :email AND password = :password", nativeQuery = true)
	User checkExistEmailPassword(@Param("email") String email, @Param("password") String password);*/
	@Query(value = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
	User checkExistEmailPassword(@Param("email") String email, @Param("password") String password);

	@Query(value = "SELECT u FROM User u WHERE u.email = :email")
	User checkExistEmail(@Param("email") String email);
}
