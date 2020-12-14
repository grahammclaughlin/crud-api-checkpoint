package com.CRUDAPI.checkpoint;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
    public User findByEmail(String email);
}
