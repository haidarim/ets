package com.orelease.etc.impl.repository;

import com.orelease.etc.impl.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);

//    @Query("SELECT c FROM Client c WHERE c.id = :id")
//    Optional<Client> findByClientId(@Param("id") Long id);
    // sophisticated queries if needed! else enough :)

    // @Query("update clients set clients.password = :pass where clients.Id = :Id and clients.email = :email and clients.username = :username")
    // Optional<Void> changeUserPassword(@Param("Id") Long Id, @Param("password") String password, @Param("email") String email, @Param("username") String username);


  //  @Query("SELECT u FROM User u WHERE u.email = :email")
  // List<User> findByEmail(@Param("email") String email);

}
