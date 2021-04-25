package br.dev.andreferreira.grpcgameserver.services;

import br.dev.andreferreira.grpcgameserver.entities.Game;
import br.dev.andreferreira.grpcgameserver.exceptions.GameNotFoundException;
import br.dev.andreferreira.services.GameDeletedResponse;
import br.dev.andreferreira.services.GameRequest;
import br.dev.andreferreira.services.GameRequestById;
import br.dev.andreferreira.services.GameResponse;
import br.dev.andreferreira.services.GameServiceGrpc.GameServiceImplBase;
import br.dev.andreferreira.services.StatusResponse;
import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

  @Override
  public void createNewGame(GameRequest request,
      StreamObserver<br.dev.andreferreira.entities.Game> responseObserver) {
    Game newGame = service.createNewGame(request);
    responseObserver.onNext(newGame.toGameResponse());
    responseObserver.onCompleted();
  }

  @Override
  public void findById(GameRequestById request,
      StreamObserver<br.dev.andreferreira.entities.Game> responseObserver) {
    Optional<Game> game = service.findById(request.getGameId());

    if (game.isEmpty()) {
      String message = String.format("Game not found by id %s", request.getGameId());
      responseObserver.onError(
          Status.NOT_FOUND
              .withDescription(message)
              .withCause(new GameNotFoundException(message))
          .asRuntimeException());
      return;
    }

    responseObserver.onNext(game.get().toGameResponse());
    responseObserver.onCompleted();
  }

  @Override
  public void deleteById(GameRequestById request,
      StreamObserver<GameDeletedResponse> responseObserver) {
    Optional<Game> game = service.deleteById(request.getGameId());

    if (game.isEmpty()) {
      String message = String.format("Game not found by id %s", request.getGameId());
      responseObserver.onError(
          Status.NOT_FOUND
              .withDescription(message)
              .withCause(new GameNotFoundException(message))
              .asRuntimeException());
      return;
    }

    responseObserver.onNext(
        GameDeletedResponse.newBuilder()
        .setGameId(request.getGameId())
        .setStatus(StatusResponse.SUCCESS)
        .build());
    responseObserver.onCompleted();
  }
}
