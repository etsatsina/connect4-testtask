package com.testtask.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Liza on 27-Jul-16.
 */
@Repository
public interface GameRepository extends MongoRepository<Game, String> {

    public Game findByPlayerUsernameOrOpponentUsername(String username);
}
