package com.app.ecommerce.repositories;

import com.app.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findUserByEmail(String email);
}
