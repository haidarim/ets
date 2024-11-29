package com.ets.ets_backend.service;

import com.ets.ets_backend.model.Client;
import com.ets.ets_backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

     
    @Transactional
    public void createUser(Client user){
        if(repository.findByUsername(user.getUsername()).orElse(null) != null){
            return;
        }
        Client u = new Client();
        //u.setSalt(hashUtil.generateSalt(32));
        u.setPassword(passwordEncoder.encode(user.getPassword()));

        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setExited(false);
        repository.save(u);
    }

    @Transactional
    public Long getClientId(String uname){
        return repository.findByUsername(uname).orElseThrow(
                ()-> new IllegalArgumentException("User Not Found!")
        ).getId();
    }

    @Transactional
    public boolean updateUser(Client user){
        Client existing = repository.findByUsername(user.getUsername()).orElse(null);
        if(existing != null){
            // Update fields of existing with user's fields
            existing.setUsername(user.getUsername());
            existing.setEmail(user.getEmail());
            existing.setPassword(user.getPassword());

            repository.save(existing); // This saves the updated existing entity
            return true;
        }
        return false;
    }

     
    @Transactional
    public Boolean isClientExited(Client client){
        return repository.findByUsername(client.getUsername()).orElseThrow(
                ()-> new IllegalArgumentException("User Not Found!")
        ).getExited();
    }

    @Transactional
    public void deleteUser(Client user){
        repository.findByUsername(user.getUsername()).ifPresent(toDelete -> repository.deleteById(toDelete.getId()));
    }


//    @Transactional(readOnly = true)
//    public boolean isVerified(User user){
//        User toCheck = repository.findByUsername(user.getUsername()).orElse(null);
//        return toCheck != null && toCheck.isVerified();
//    }

}
