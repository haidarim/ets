package com.ets.ets_backend.repository;

import com.ets.ets_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    // sophisticated queries if needed! else enough :)

    // @Query("update clients set clients.password = :pass where clients.Id = :Id and clients.email = :email and clients.username = :username")
    // Optional<Void> changeUserPassword(@Param("Id") Long Id, @Param("password") String password, @Param("email") String email, @Param("username") String username);


  //  @Query("SELECT u FROM User u WHERE u.email = :email")
  // List<User> findByEmail(@Param("email") String email);

}
