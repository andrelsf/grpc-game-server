package br.dev.andreferreira.grpcgameserver.entities;

import br.dev.andreferreira.entities.Platform;
import br.dev.andreferreira.services.GameRequest;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tb_games")
@NamedQueries({
    @NamedQuery(
        name = "Game.findAll",
        query = "SELECT g FROM Game g ORDER BY g.gameId ASC"
    ),
    @NamedQuery(
        name = "Game.findById",
        query = "SELECT g FROM Game g WHERE g.gameId = :gameId"
    ),
    @NamedQuery(
        name = "Game.deleteById",
        query = "DELETE FROM Game g WHERE g.gameId = :gameId"
    )
})
public class Game implements Serializable {

  @Id
  @Column(name = "game_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long gameId;

  @Column(nullable = false, length = 120)
  private String name;

  @Column(length = 180)
  private String description;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Platform platform;

  @Column(nullable = false, precision = 9, scale = 2)
  private Double price;

  public Game () {
  }

  public Game (GameRequest request) {
    this.name = request.getName();
    this.description = request.getDescription();
    this.platform = request.getPlatform();
    this.price = request.getPrice();
  }

  @Override
  public String toString() {
    return "Game{" +
        "gameId=" + gameId +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", platform=" + platform +
        ", price=" + price +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Game game = (Game) o;

    if (!gameId.equals(game.gameId)) {
      return false;
    }
    return name.equals(game.name);
  }

  @Override
  public int hashCode() {
    int result = gameId.hashCode();
    result = 31 * result + name.hashCode();
    return result;
  }

  public Long getGameId() {
    return gameId;
  }

  public void setGameId(Long gameId) {
    this.gameId = gameId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Platform getPlatform() {
    return platform;
  }

  public void setPlatform(Platform platform) {
    this.platform = platform;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public br.dev.andreferreira.entities.Game toGameResponse() {
    return br.dev.andreferreira.entities.Game.newBuilder()
        .setGameId(this.gameId)
        .setName(this.name)
        .setDescription(this.description)
        .setPlatform(this.platform)
        .setPrice(this.price)
        .build();
  }
}
