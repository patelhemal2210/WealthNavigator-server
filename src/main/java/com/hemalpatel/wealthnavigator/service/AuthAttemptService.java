package com.hemalpatel.wealthnavigator.service;

import com.hemalpatel.wealthnavigator.domain.AuthAttempt;
import com.hemalpatel.wealthnavigator.domain.User;
import com.hemalpatel.wealthnavigator.repository.AuthAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class AuthAttemptService {

    @Value("${wealthnavigator.auth.max_attempt}")
    private Integer MAX_ATTEMPT_COUNT;

    @Autowired
    private AuthAttemptRepository authAttemptRepository;

    public void successAuthEvent(User user) {
        this.authAttemptRepository.save(new AuthAttempt().setUser(user).setSuccess(true).setDate(new Date()));
    }

    public void failedAuthEvent(User user) {
        this.authAttemptRepository.save(new AuthAttempt().setUser(user).setSuccess(false).setDate(new Date()));
    }

    public boolean isAuthLimitOverrun(User user) {
        System.out.println("MAX_ATTEMPT_COUNT : " + MAX_ATTEMPT_COUNT);
        return authAttemptRepository.countUnsuccessfulByDateBetweenAndUser(
                Date.from(LocalDateTime.now().minusDays(1).toInstant(ZoneOffset.UTC)),
                user) > MAX_ATTEMPT_COUNT;
    }


}
