package br.dev.andreferreira.grpcgameserver.services;

import static br.dev.andreferreira.entities.Platform.PS4;

import br.dev.andreferreira.entities.Platform;
import br.dev.andreferreira.grpcgameserver.entities.Game;
import br.dev.andreferreira.grpcgameserver.exceptions.GameNotFoundException;
import br.dev.andreferreira.services.GameResponse;
import br.dev.andreferreira.services.GameServiceGrpc.GameServiceImplBase;
import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameService extends GameServiceImplBase {

  private GameServiceImpl service;

  public GameService(GameServiceImpl service) {
    this.service = service;
  }

  @Override
  public void getAllGames(Empty request, StreamObserver<GameResponse> responseObserver) {
    List<Game> games = service.findAll();

    if (games.isEmpty()) {
      String message = "Games not found";
      responseObserver.onError(
          Status.NOT_FOUND
              .withDescription(message)
              .withCause(new GameNotFoundException(message))
          .asRuntimeException());
      return;
    }

    List<br.dev.andreferreira.entities.Game> listGames = new ArrayList<>();
    games.forEach(game -> {
      listGames.add(game.toGameResponse());
    });

    GameResponse gamesResponse = GameResponse.newBuilder().addAllGame(listGames).build();

    responseObserver.onNext(gamesResponse);
    responseObserver.onCompleted();
  }
}
