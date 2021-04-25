package br.dev.andreferreira.grpcgameserver.repositories;

import br.dev.andreferreira.grpcgameserver.config.DBConnection;
import br.dev.andreferreira.grpcgameserver.entities.Game;
import java.util.List;

public class GameRepositoryImpl implements  GameRepository {

  @Override
  public List findAll() {
    return DBConnection.getEntityManager()
        .createNamedQuery("Game.findAll")
        .getResultList();
  }

}
