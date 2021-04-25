package br.dev.andreferreira.grpcgameserver;

import br.dev.andreferreira.grpcgameserver.repositories.GameRepositoryImpl;
import br.dev.andreferreira.grpcgameserver.services.GameService;
import br.dev.andreferreira.grpcgameserver.services.GameServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class GrpcGameServerApplication {

  private static final Integer PORT = 9001;

  public static void main(String args[]) throws IOException, InterruptedException {
    GameRepositoryImpl repository = new GameRepositoryImpl();
    GameServiceImpl service = new GameServiceImpl(repository);

    Server gRPCGameServer = ServerBuilder
        .forPort(PORT)
          .addService(new GameService(service))
          .addService(new GameService(service))
        .build();

    gRPCGameServer.start();
    System.out.println("gRPC Game Server is running on port: " + PORT);
    gRPCGameServer.awaitTermination();
  }
}
