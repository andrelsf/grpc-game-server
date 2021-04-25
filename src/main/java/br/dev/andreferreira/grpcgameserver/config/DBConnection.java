package br.dev.andreferreira.grpcgameserver.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBConnection {

  private static final EntityManagerFactory EMF;
  private static final ThreadLocal<EntityManager> THREAD_LOCAL;

  static {
    EMF = Persistence.createEntityManagerFactory("postgres_pu");
    THREAD_LOCAL = new ThreadLocal<>();
  }

  public static EntityManager getEntityManager() {
    EntityManager entityManager = THREAD_LOCAL.get();

    if (entityManager == null) {
      entityManager = EMF.createEntityManager();
      THREAD_LOCAL.set(entityManager);
    }

    return entityManager;
  }

  public static void closeEntityManager() {
    EntityManager entityManager = THREAD_LOCAL.get();
    if (entityManager != null) {
      entityManager.close();
      THREAD_LOCAL.set(null);
    }
  }

  public static void closeEntityManagerFactory() {
    EMF.close();
  }

  public static void beginTransaction() {
    getEntityManager().getTransaction().begin();
  }

  public static void rollback() {
    getEntityManager().getTransaction().rollback();
  }

  public static void commit() {
    getEntityManager().getTransaction().commit();
  }
}
