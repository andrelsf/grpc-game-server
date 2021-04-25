package br.dev.andreferreira.grpcgameserver.services;

import static br.dev.andreferreira.entities.Platform.PS4;

import br.dev.andreferreira.entities.Platform;
import br.dev.andreferreira.grpcgameserver.entities.Game;
import br.dev.andreferreira.grpcgameserver.repositories.GameRepositoryImpl;
import br.dev.andreferreira.services.GameRequest;
import br.dev.andreferreira.services.GameResponse;
import java.util.List;

public class GameServiceImpl {

  private GameRepositoryImpl repository;

  public GameServiceImpl(GameRepositoryImpl repository) {
    this.repository = repository;
  }

  public List<Game> findAll() {
    List<Game> games = repository.findAll();
    return games;
  }

  public Game createNewGame(GameRequest request) {
    Game gameForSave = new Game(request);
    Game gameSaved = repository.save(gameForSave);
    return gameSaved;
  }
}
