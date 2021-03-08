package com.example.comparator.repos;

import com.example.comparator.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    @Query("SELECT user FROM User user WHERE user.login=:login")
    User findUserByLogin(@Param("login") String login);
}
