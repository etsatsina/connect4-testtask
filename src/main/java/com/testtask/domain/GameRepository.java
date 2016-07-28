package com.testtask.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by etsatsina on 27-Jul-16.
 */
@Repository
public interface GameRepository extends MongoRepository<Game, String> {

    public Game findByOpponentUsernameAndState(String opponent, Game.State state);

    public Game findByPlayerUsernameAndState(String player, Game.State state);

    public Game findById(String uuid);

}
