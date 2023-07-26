package com.hemalpatel.wealthnavigator.repository;

import com.hemalpatel.wealthnavigator.domain.AuthAttempt;
import com.hemalpatel.wealthnavigator.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AuthAttemptRepository extends JpaRepository<AuthAttempt, Long> {

    @Query("SELECT COUNT(a.id) FROM AuthAttempt a WHERE a.success is false AND a.date > :date AND a.user = :user")
    Integer countUnsuccessfulByDateBetweenAndUser(Date date, User user);
}
