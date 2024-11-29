package com.ets.ets_backend.security;


import com.ets.ets_backend.model.Client;
import com.ets.ets_backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This class is a service class and responsible to take care of authentication process.
 * This class loads the user.
 *
 * Implements:
 *  - org.springframework.security.core.userdetails.UserDetailsService; which queries the database and retrieves user information.
 * Used Model:
 *  - User
 * Used Repository:
 *  - UserRepository
 *
 * @author Mehdi Haidari
 * */


@Service
public class ETSUserDetailsService implements UserDetailsService {
    @Autowired
    private ClientRepository userRepository;
//    @Autowired
//    private HashUtil hashUtil;

    /**
     * @param  username, String
     * @return ETSUserDetails
     * @throws UsernameNotFoundException if user not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Client user = userRepository.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("User Not Founded!")
        );

        return new ETSUserDetails(user);
    }
}
