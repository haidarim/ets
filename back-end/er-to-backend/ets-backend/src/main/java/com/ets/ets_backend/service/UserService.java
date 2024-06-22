package com.ets.ets_backend.service;

import com.ets.ets_backend.model.User;
import com.ets.ets_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

//    @Transactional(readOnly = true)
//    public User getUser(String username){
//        return repository.findByUserName(username).orElse(null);
//    }


    // TODO: Encrypt and hash the pass + salt + pepper before saving
    @Transactional
    public void createUser(User user){
        repository.save(user);
    }

    @Transactional
    public boolean updateUser(User user){
        User existing = repository.findByUsername(user.getUsername()).orElse(null);
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
    public void deleteUser(User user){
        repository.findByUsername(user.getUsername()).ifPresent(toDelete -> repository.deleteById(toDelete.getId()));
    }

    @Transactional(readOnly = true)
    public boolean isVerified(User user){
        User toCheck = repository.findByUsername(user.getUsername()).orElse(null);
        return toCheck != null && toCheck.isVerified();
    }

}
