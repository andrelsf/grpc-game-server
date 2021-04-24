package br.dev.andreferreira.grpcgameserver.services;

import br.dev.andreferreira.services.GameResponse;
import br.dev.andreferreira.services.GameServiceGrpc.GameServiceImplBase;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

public class GameServer extends GameServiceImplBase {

  @Override
  public void getAllGames(Empty request, StreamObserver<GameResponse> responseObserver) {
    // TODO: Get All games
  }
}
