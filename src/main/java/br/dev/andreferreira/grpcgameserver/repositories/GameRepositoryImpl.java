package br.dev.andreferreira.grpcgameserver.repositories;

import br.dev.andreferreira.grpcgameserver.config.DBConnection;
import br.dev.andreferreira.grpcgameserver.entities.Game;
import java.util.List;

public class GameRepositoryImpl implements  GameRepository {

  @Override
  public List findAll() {
    return DBConnection.getEntityManager()
        .createNamedQuery("Game.findAll")
        .setMaxResults(50)
        .getResultList();
  }

  @Override
  public Game save(Game game) {
    try {
      DBConnection.beginTransaction();

      Game gameSaved = DBConnection.getEntityManager().merge(game);
      DBConnection.commit();

      return gameSaved;
    } catch (RuntimeException ex) {
      if (DBConnection.getEntityManager() != null &&
          DBConnection.getEntityManager().isOpen()) {
        DBConnection.rollback();
      }

      throw ex;
    } finally {
      DBConnection.closeEntityManager();
    }
  }

}
