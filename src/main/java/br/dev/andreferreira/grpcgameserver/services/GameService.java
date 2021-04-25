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
import java.util.List;

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

    responseObserver.onNext(GameResponse.newBuilder()
        .addGame(
            br.dev.andreferreira.entities.Game.newBuilder()
                .setGameId(2L)
                .setName("The Witcher Wild Hunt")
                .setDescription("Geralt of the Rivia a witcher")
                .setPlatform(PS4)
                .setPrice(199.9)
                .build()
        ).addGame(
            br.dev.andreferreira.entities.Game.newBuilder()
                .setGameId(1L)
                .setName("God of War")
                .setDescription("Kratos the God of War")
                .setPlatform(Platform.PS4)
                .setPrice(199.9)
                .build()
        ).build());
    responseObserver.onCompleted();
  }
}
