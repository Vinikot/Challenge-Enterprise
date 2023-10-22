package br.com.fiap.challenge.domain.repository;

import br.com.fiap.challenge.domain.entity.Itens;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class ItensRepository implements Repository<Itens, Integer> {
    private static final AtomicReference<ItensRepository> instance = new AtomicReference<>();
    private final EntityManager manager;

    private ItensRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static ItensRepository build(EntityManager manager) {
        ItensRepository result = instance.get();
        if (Objects.isNull(result)) {
            ItensRepository repo = new ItensRepository(manager);
            if (instance.compareAndSet(null, repo)) {
                result = repo;
            } else {
                result = instance.get();
            }
        }
        return result;
    }

    @Override
    public List<Itens> findAll() {
        return manager.createQuery("FROM Itens", Itens.class).getResultList();
    }

    @Override
    public Itens findById(Integer id) {
        return manager.find(Itens.class, id);
    }

    @Override
    public Itens persist(Itens itens) {
        manager.getTransaction().begin();
        manager.persist(itens);
        manager.getTransaction().commit();
        return itens;
    }

    @Override
    public Itens update(Itens itens) {
        manager.getTransaction().begin();
        itens = manager.merge(itens);
        manager.getTransaction().commit();
        return itens;
    }

    @Override
    public boolean delete(Itens itens) {
        manager.getTransaction().begin();
        manager.remove(itens);
        manager.getTransaction().commit();
        return true;
    }
}
