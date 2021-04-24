package br.dev.andreferreira.grpcgameserver;

import br.dev.andreferreira.grpcgameserver.services.GameServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class GrpcGameServerApplication {

  private static final Integer PORT = 9001;

  public static void main(String args[]) throws IOException, InterruptedException {
    System.out.println("gRPC Game Server is running on port: " + PORT);

    Server gRPCGameServer = ServerBuilder
        .forPort(PORT)
          .addService(new GameServer())
        .build();

    gRPCGameServer.start();
    gRPCGameServer.awaitTermination();
  }
}
