package br.dev.andreferreira.grpcgameserver.repositories;

import br.dev.andreferreira.grpcgameserver.entities.Game;
import java.util.List;
import java.util.Optional;

public interface GameRepository {

  List<Game> findAll();

  Game save(Game game);

  Optional<Game> findById(long gameId);

  void delete(Game game);
}
