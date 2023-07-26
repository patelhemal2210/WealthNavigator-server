package com.hemalpatel.wealthnavigator.service;

import com.hemalpatel.wealthnavigator.domain.User;
import com.hemalpatel.wealthnavigator.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthAttemptService authAttemptService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        if (authAttemptService.isAuthLimitOverrun(user)) {
            if (!user.isLocked()) {
                userService.block(user);
            }
        }

        return new UserPrincipal(user);
    }
}
