package com.turkcell.authserver.services.concretes;




import com.turkcell.authserver.entities.User;
import com.turkcell.authserver.repositories.UserRepository;
import com.turkcell.authserver.services.abstracts.UserService;
import com.turkcell.authserver.services.messages.AuthMessages;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {
    private final UserRepository userRepository;


    @Override
    public void add(User user) {
        userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= userRepository
                .findByEmail(username)
                .orElseThrow(() -> new RuntimeException(AuthMessages.LOGIN_FAILED));
        return user;
    }
}
