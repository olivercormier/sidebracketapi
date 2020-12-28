package com.olivercormier.sidebracketapi.tournaments;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TournamentRepository extends MongoRepository<Tournament, String> {
}
