package com.hemalpatel.wealthnavigator.repository;

import com.hemalpatel.wealthnavigator.domain.ERole;
import com.hemalpatel.wealthnavigator.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole role);
}
