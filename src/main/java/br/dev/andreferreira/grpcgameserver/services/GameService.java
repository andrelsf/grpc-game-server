package br.dev.andreferreira.grpcgameserver.services;

import static br.dev.andreferreira.entities.Platform.PS4;

import br.dev.andreferreira.entities.Game;
import br.dev.andreferreira.entities.Platform;
import br.dev.andreferreira.services.GameResponse;
import br.dev.andreferreira.services.GameServiceGrpc.GameServiceImplBase;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

public class GameService extends GameServiceImplBase {

  private GameServiceImpl service;

  @Override
  public void getAllGames(Empty request, StreamObserver<GameResponse> responseObserver) {
    GameResponse games = GameResponse.newBuilder()
        .addGame(
            Game.newBuilder()
                  .setGameId(2L)
                  .setName("The Witcher Wild Hunt")
                  .setDescription("Geralt of the Rivia a witcher")
                  .setPlatform(PS4)
                  .setPrice(199.9)
                .build()
        ).addGame(
            Game.newBuilder()
                  .setGameId(1L)
                  .setName("God of War")
                  .setDescription("Kratos the God of War")
                  .setPlatform(Platform.PS4)
                  .setPrice(199.9)
                .build()
        ).build();

    responseObserver.onNext(games);
    responseObserver.onCompleted();
  }
}
