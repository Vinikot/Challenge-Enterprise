package br.com.fiap.challenge.domain.repository;

import br.com.fiap.challenge.domain.entity.Compra;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class CompraRepository implements Repository<Compra, Long> {
    private static final AtomicReference<CompraRepository> instance = new AtomicReference<>();
    private final EntityManager manager;

    private CompraRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static CompraRepository build(EntityManager manager) {
        CompraRepository result = instance.get();
        if (Objects.isNull(result)) {
            CompraRepository repo = new CompraRepository(manager);
            if (instance.compareAndSet(null, repo)) {
                result = repo;
            } else {
                result = instance.get();
            }
        }
        return result;
    }

    @Override
    public List<Compra> findAll() {
        return manager.createQuery("FROM Compra", Compra.class).getResultList();
    }

    @Override
    public Compra findById(Long id) {
        return manager.find(Compra.class, id);
    }

    @Override
    public Compra persist(Compra compra) {
        manager.getTransaction().begin();
        manager.persist(compra);
        manager.getTransaction().commit();
        return compra;
    }

    @Override
    public Compra update(Compra compra) {
        manager.getTransaction().begin();
        compra = manager.merge(compra);
        manager.getTransaction().commit();
        return compra;
    }

    @Override
    public boolean delete(Compra compra) {
        manager.getTransaction().begin();
        manager.remove(compra);
        manager.getTransaction().commit();
        return true;
    }
}
