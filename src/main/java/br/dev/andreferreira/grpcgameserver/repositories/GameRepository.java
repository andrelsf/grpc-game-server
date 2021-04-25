package br.dev.andreferreira.grpcgameserver.repositories;

import br.dev.andreferreira.entities.Game;
import java.util.List;

public interface GameRepository {

  List<Game> findAll();

}
