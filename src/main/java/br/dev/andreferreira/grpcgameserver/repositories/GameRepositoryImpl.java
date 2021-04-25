package br.dev.andreferreira.grpcgameserver.repositories;

import br.dev.andreferreira.entities.Game;
import br.dev.andreferreira.grpcgameserver.config.DBConnection;
import java.util.List;

public class GameRepositoryImpl implements  GameRepository {

  @Override
  public List<Game> findAll() {
    return DBConnection.getEntityManager()
        .createNamedQuery("Game.findAll")
        .getResultList();
  }

}
