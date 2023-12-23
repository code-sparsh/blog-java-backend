package com.example.welcome.repo;
import com.example.welcome.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends CrudRepository<User, UUID> {

    public Optional<User> findByEmail(String email);
}
