package br.dev.andreferreira.grpcgameserver.repositories;

import br.dev.andreferreira.grpcgameserver.entities.Game;
import java.util.List;

public interface GameRepository {

  List<Game> findAll();

  Game save(Game game);
}
